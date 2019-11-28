package marcwe.component.controler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static marcwe.component.Utility.*;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.data.web.PagedResourcesAssembler;

import javassist.NotFoundException;
import marcwe.component.AffordanceTools.AffordanceTemplate;
import marcwe.component.AffordanceTools.AffordanceTemplateBuilder;
import marcwe.component.AffordanceTools.AffordedPageRessources;
import marcwe.component.DTO.ItemValueModel;
import marcwe.component.DTO.itemDTODetails.ItemDTODetail;
import marcwe.component.DTO.itemDTODetails.ItemDetailsAssembler;
import marcwe.component.DTO.itemDTOsummary.ItemDTOSummary;
import marcwe.component.DTO.itemDTOsummary.ItemSummaryAssembler;
import marcwe.component.entity.Item;
import marcwe.component.entity.Transaction;
import marcwe.component.service.ItemService;
import marcwe.component.service.ItemServiceType;

@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping(value = "/items")
public class ItemController {

	
	//--FIELDS
	
	public ItemServiceType itemService;
	public PagedResourcesAssembler<Item> pagedResourcesAssembler;
	public ItemDetailsAssembler itemDetailsAssembler;
	public ItemSummaryAssembler itemSummaryAssembler;
	
	//--CONSTRUCTOR
	
	@Autowired
	public ItemController(
			ItemService itemService,
			PagedResourcesAssembler<Item> pagedResourcesAssembler,
			ItemDetailsAssembler itemDetailsAssembler,
			ItemSummaryAssembler itemSummaryAssembler) {
		this.itemService = itemService;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
		this.itemDetailsAssembler = itemDetailsAssembler;
		this.itemSummaryAssembler = itemSummaryAssembler;
	}

	//--GET ALL ITEM
	
	@ApiOperation(value = "endpoint for all the items currently in the inventory, pageable result")
	@GetMapping
	public ResponseEntity<AffordedPageRessources<ItemDTOSummary>> findAll(Pageable pageable) {
		
		Page<Item> items = itemService.findAll(pageable);	
		
		PagedResources<ItemDTOSummary> pagedRessource = pagedResourcesAssembler.toResource(items, itemSummaryAssembler);

		AffordedPageRessources<ItemDTOSummary> affordedPageRessources = new AffordedPageRessources<>(pagedRessource) ;
		AffordanceTemplate createMethodAffordance = AffordanceTemplateBuilder.getCreateMethodAffordance(ItemValueModel.class);
		createMethodAffordance.setTitle("Create Item");
		affordedPageRessources.afford(createMethodAffordance);
		
		return ResponseEntity.ok(affordedPageRessources);
	}
	
	//-- GET ITEM BY ID
	@ApiOperation(value = "endpoint for a specific item",response = Item.class)
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTODetail> findOne(@PathVariable long id) {

		Optional<Item> optionalItem = itemService.findById(id);
		
		ResponseEntity<ItemDTODetail> response = optionalItem
				.map(item -> itemDetailsAssembler.toResource(item))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

		return response;
	}

	//-- NEW ITEM
	
	@ApiOperation(value = "Adds an item to the inventory", code = 201)
	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody ItemValueModel newItem) {

		Item savedItem = itemService.save(newItem.toEntity(0));
		
		return ResponseEntity.created(linkTo(methodOn(ItemController.class).findOne(savedItem.getNumber())).toUri()).build();
	}

	//-- UPDATE ITEM
	
	@ApiOperation(value = "update specific item data", code = 204)
	@PutMapping("/{id}")
	public ResponseEntity<?> updateItem(@RequestBody ItemValueModel itemValue, @PathVariable long id) throws NotFoundException {
		Item itemToUpdate = itemValue.toEntity(id);
		itemService.update(itemToUpdate);
		URI uri = URI.create(linksForId(id).getHref());
		return ResponseEntity.noContent().location(uri).build();
	}

	//-- MAKE TRANSACTION
	
	@ApiOperation(value = "make specific quantity of Withdraw/Deposit  of a specific item in inventory",code = 204)
	@PutMapping("/{id}/transaction")
	public ResponseEntity<?> makeTransaction(@PathVariable long id, @RequestBody Transaction transaction) {
		
		Optional<Item> optional = itemService.findById(id);
		URI uri = URI.create(linksForId(id).getHref());
		return optional
				.map(itemToUpdate-> {
					itemToUpdate.amount += transaction.getDeposit();
					itemToUpdate.amount -= transaction.getWithdrawal();
					itemToUpdate.transaction.setDeposit(transaction.getDeposit()+itemToUpdate.transaction.getDeposit());
					itemToUpdate.transaction.setWithdrawal(transaction.getWithdrawal()+itemToUpdate.transaction.getWithdrawal());
					itemService.save(itemToUpdate);
					return ResponseEntity.noContent().location(uri).build();})
				.orElse(ResponseEntity.badRequest().body("Unable to deposit quantity for item " + id));
	}


	//-- DELETE ITEM BY ID
	@ApiOperation( value = "endpoint to deletes an item from the inventory", code = 204)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) throws NotFoundException {
		itemService.deleteById(id);
		URI uri = URI.create(linkToCollection().getHref());
		return ResponseEntity.noContent().location(uri).build();
	}

	//-- GET TRANSACTION CLASS
	
	@ApiOperation(value = "endpoint to get quantity of Withdraw and Deposit  of a specific item in inventory")
	@GetMapping("/{itemNumber}/transaction")
	public ResponseEntity<Resource<Transaction>> getTransaction(@PathVariable long itemNumber) throws NotFoundException{
		Transaction transaction = itemService.getTransaction(itemNumber);
		Resource<Transaction> transactionRessource = new Resource<>(
				transaction,
				linksForDeposit(itemNumber).withSelfRel(),
				linksForId(itemNumber).withRel("item"),
				linksForWithdrawal(itemNumber),
				linkToCollection()
				);
		
		return ResponseEntity.ok(transactionRessource);
	}

	
	//-- GET DEPOSIT QUANTITY
	
	@ApiOperation(value = "endpoint to get quantity of deposit  of a specific item in inventory")
	@GetMapping("/{itemNumber}/deposit")
	public ResponseEntity<Resource<Long>> getDeposit(@PathVariable long itemNumber) throws NotFoundException{
		Long deposit = itemService.getDeposit(itemNumber);
		Resource<Long> depositRessource = new Resource<>(
				deposit,
				linksForDeposit(itemNumber).withSelfRel(),
				linksForId(itemNumber).withRel("item"),
				linksForWithdrawal(itemNumber),
				linkToCollection()
				);
		return ResponseEntity.ok(depositRessource);
	}
	
	
	//-- GET WITHDRAWAL QUANTITY
	
	@ApiOperation(value = "endpoint to get quantity of withdrawal  of a specific item in inventory")
	@GetMapping("/{itemNumber}/withdrawal")
	public ResponseEntity<Resource<Long>> getWithdrawal(@PathVariable long itemNumber) throws NotFoundException{
		Long withdrawal = itemService.getWithdrawal(itemNumber);
		Resource<Long> withdrawalRessource = new Resource<>(
				withdrawal,
				linksForWithdrawal(itemNumber).withSelfRel() ,
				linksForId(itemNumber).withRel("item"),
				linksForDeposit(itemNumber),
				linkToCollection()
				);
		return ResponseEntity.ok(withdrawalRessource);
	}
	
}

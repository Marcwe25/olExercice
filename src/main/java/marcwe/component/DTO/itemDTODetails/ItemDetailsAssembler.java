package marcwe.component.DTO.itemDTODetails;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import javassist.NotFoundException;
import marcwe.component.AffordanceTools.AffordanceTemplate;
import marcwe.component.AffordanceTools.AffordanceTemplateBuilder;
import marcwe.component.DTO.ItemValueModel;
import marcwe.component.controler.ItemController;
import marcwe.component.entity.Item;

public class ItemDetailsAssembler  extends ResourceAssemblerSupport<Item, ItemDTODetail> {

	public ItemDetailsAssembler() {
		super(ItemController.class, ItemDTODetail.class);
	}

	@Override
	public ItemDTODetail toResource(Item entity) {
		
		ItemDTODetail itemDTODetail = super.createResourceWithId(entity.getNumber(), entity);
		
		BeanUtils.copyProperties(entity, itemDTODetail);

		AffordanceTemplate updateTemplate = AffordanceTemplateBuilder.getUpdateAffordance(ItemValueModel.class);
		AffordanceTemplate deleteTemplate = AffordanceTemplateBuilder.getDeleteAffordance();
		
		itemDTODetail.afford(updateTemplate);
		itemDTODetail.afford(deleteTemplate);

		try {
			itemDTODetail.add(linkTo(methodOn(ItemController.class).getDeposit(entity.getNumber())).withRel("deposit"));
			itemDTODetail.add(linkTo(methodOn(ItemController.class).getWithdrawal(entity.getNumber())).withRel("withdrawal"));
			itemDTODetail.add(linkTo(methodOn(ItemController.class).findAll(null)).withRel("collection"));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return itemDTODetail;
	}

}

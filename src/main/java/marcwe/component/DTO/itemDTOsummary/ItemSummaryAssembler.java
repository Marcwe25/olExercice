package marcwe.component.DTO.itemDTOsummary;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import marcwe.component.controler.ItemController;
import marcwe.component.entity.Item;

public class ItemSummaryAssembler  extends ResourceAssemblerSupport<Item, ItemDTOSummary> {

	public ItemSummaryAssembler() {
		super(ItemController.class, ItemDTOSummary.class);
	}

	@Override
	public ItemDTOSummary toResource(Item entity) {
		ItemDTOSummary itemDTOSummary = super.createResourceWithId(entity.getNumber(), entity);
		BeanUtils.copyProperties(entity, itemDTOSummary);
		return itemDTOSummary;
	}

}

package marcwe.component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

import javassist.NotFoundException;
import marcwe.component.controler.ItemController;

public  class Utility {

	public static Link linksForId(Long id) {
		return linkTo(methodOn(ItemController.class).findOne(id)).withSelfRel();
}

public static Link linksForDeposit(Long id) throws NotFoundException {
	return linkTo(methodOn(ItemController.class).getDeposit(id)).withRel("deposit");
}

public static Link linksForWithdrawal(Long id) throws NotFoundException {
	return linkTo(methodOn(ItemController.class).getWithdrawal(id)).withRel("withdrawal");
}

public static Link linkToCollection() {
	return linkTo(methodOn(ItemController.class).findAll(null)).withRel("collection");
}
}

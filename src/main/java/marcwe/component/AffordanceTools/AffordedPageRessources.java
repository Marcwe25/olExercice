package marcwe.component.AffordanceTools;

import java.util.ArrayList;

import org.springframework.hateoas.PagedResources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AffordedPageRessources<T> extends PagedResources<T>{

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonSerialize(using = AffordanceRessourceSerializer.class)
	ArrayList<AffordanceTemplate> _templates = null;

	public AffordedPageRessources(ArrayList<AffordanceTemplate> _templates) {
		super();
		this._templates = _templates;
	}

	public AffordedPageRessources(PagedResources<T> PagedResources) {
		super(PagedResources.getContent(), PagedResources.getMetadata(), PagedResources.getLinks());
	}
	
	

	public AffordedPageRessources() {
	}
	
	
	public ArrayList<AffordanceTemplate> get_templates() {
		return _templates;
	}

	public void set_templates(ArrayList<AffordanceTemplate> _templates) {
		this._templates = _templates;
	}

	public void afford(AffordanceTemplate affordanceTemplate) {
		if(_templates==null) {
			_templates= new ArrayList<>();
		}
		_templates.add(affordanceTemplate);
	}
}

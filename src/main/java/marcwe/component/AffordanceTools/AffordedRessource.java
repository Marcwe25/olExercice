package marcwe.component.AffordanceTools;

import java.util.ArrayList;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AffordedRessource extends ResourceSupport{

	@JsonSerialize(using = AffordanceRessourceSerializer.class)
	ArrayList<AffordanceTemplate> _templates = null;

	public ArrayList<AffordanceTemplate> get_templates() {
		return _templates;
	}

	public void set_templates(ArrayList<AffordanceTemplate> _templates) {
		this._templates = _templates;
	}

	public void afford(AffordanceTemplate affordanceTemplate) {
		if(_templates==null) _templates = new ArrayList<>();
		_templates.add(affordanceTemplate);
	}
	
}

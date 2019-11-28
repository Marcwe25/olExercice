package marcwe.component.AffordanceTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import javassist.NotFoundException;
import marcwe.component.controler.ItemController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AffordanceRessourceSerializer extends StdSerializer<ArrayList<AffordanceTemplate>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1094719342518588553L;

	public AffordanceRessourceSerializer() {
		this(null);
	}
	protected AffordanceRessourceSerializer(Class<ArrayList<AffordanceTemplate>> type) {
		super(type);
	}

	@Override
	public void serialize(ArrayList<AffordanceTemplate> values, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		for(AffordanceTemplate template : values) {
			gen.writeObjectField(template.getTitle(), template);
		}
		gen.writeEndObject();
	}
	
}

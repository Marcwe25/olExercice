package marcwe.component.AffordanceTools;

import java.lang.reflect.Field;

import org.springframework.http.HttpMethod;

public class AffordanceTemplateBuilder {

	public static AffordanceTemplate getUpdateAffordance(Class<?> clazz) {
		AffordanceTemplate template = new AffordanceTemplate();
		template.setMethod(HttpMethod.PUT);
		template.setTitle("Update");
		setPropertiesFromClass(clazz,template);
		return template;
	}

	public static AffordanceTemplate getCreateMethodAffordance(Class<?> clazz) {
		AffordanceTemplate template = new AffordanceTemplate();
		template.setMethod(HttpMethod.POST);
		template.setTitle("Create");
		setPropertiesFromClass(clazz,template);
		return template;
	}

	public static AffordanceTemplate getDeleteAffordance() {
		AffordanceTemplate template = new AffordanceTemplate();
		template.setMethod(HttpMethod.DELETE);
		template.setTitle("Delete");
		return template;

	}
	
	public static void setPropertiesFromClass(Class<?> classObject, AffordanceTemplate affordanceTemplate) {
		Field[] fields = classObject.getDeclaredFields();
		for(Field field : fields) {
			RessourceProperty ressourceProperty = new RessourceProperty(field);
			affordanceTemplate.getProperties().add(ressourceProperty);
		}
	}
}

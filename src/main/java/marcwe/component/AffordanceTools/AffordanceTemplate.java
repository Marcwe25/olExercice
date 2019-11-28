package marcwe.component.AffordanceTools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AffordanceTemplate {

	public String title = "default title";
	public HttpMethod method;
	@JsonSerialize(using = ToStringSerializer.class)
	public MediaType contentType;
	public List<RessourceProperty> properties = new ArrayList<RessourceProperty>();
	

	public AffordanceTemplate(String title, HttpMethod method, MediaType contentType,
			List<RessourceProperty> ressourceProperties) {
		super();
		this.title = title;
		this.method = method;
		this.contentType = contentType;
		this.properties = ressourceProperties;
	}

	public AffordanceTemplate() {
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public HttpMethod getMethod() {
		return method;
	}


	public void setMethod(HttpMethod method) {
		this.method = method;
	}


	public MediaType getContentType() {
		return contentType;
	}


	public void setContentType(MediaType contentType) {
		this.contentType = contentType;
	}

	public List<RessourceProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<RessourceProperty> properties) {
		this.properties = properties;
	}


}

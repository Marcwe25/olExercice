package marcwe.component.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import marcwe.component.AffordanceTools.AffordanceTemplate;
import marcwe.component.AffordanceTools.AffordedPageRessources;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableSwagger2
public class swaggerConfiguration {
	
	private static Set<String> producesAndConsumes = new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.produces(producesAndConsumes)
				.consumes(producesAndConsumes).forCodeGeneration(true)
				.ignoredParameterTypes(AffordanceTemplate.class,AffordedPageRessources.class,Link.class,ModelAndView.class,Resource.class,View.class)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();	
	}
	
	private ApiInfo apiDetails() {
		
		return new ApiInfo(
				"item REST API",
				"provide CRUD endpoint to manage item in database",
				"1.0",
				"free",
				new springfox.documentation.service.Contact("Marc Weiss", "https://github.com/Marcwe25", "marcwe@gmail.com"),
				"license free",
				"license url",
				Collections.emptyList()
				);
	}
}

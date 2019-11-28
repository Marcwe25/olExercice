package marcwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import marcwe.component.DTO.itemDTODetails.ItemDetailsAssembler;
import marcwe.component.DTO.itemDTOsummary.ItemSummaryAssembler;

@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class OLRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OLRestApiApplication.class, args);
	}
	
	@Bean
	public ItemDetailsAssembler itemDetailsAssembler() {
		return new ItemDetailsAssembler();
	}
	@Bean
	public ItemSummaryAssembler itemSummaryAssembler() {
		return new ItemSummaryAssembler();
	}

}

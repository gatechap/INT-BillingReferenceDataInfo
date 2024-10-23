package th.truecorp.it.dsm.intatom.billingreferencedatainfo.conf;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	
	// With or without (Class optional)
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(new Components()).info(new Info()
				.title("BillingReferenceDataInfo Swagger APIs")
				.description("Initial BillingReferenceDataInfo 0.0.1")
				.version("0.0.1")
		);
	}

	@Bean
	public GroupedOpenApi api()
	{
		return GroupedOpenApi.builder()
				.group("BillingReferenceDataInfo") //group not null
				.packagesToScan("th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers")
				.pathsToMatch(
						"/BillingReferenceDataInfo/**") // ** = all
				.build(); 
	}

}

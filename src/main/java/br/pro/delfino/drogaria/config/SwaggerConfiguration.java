package br.pro.delfino.drogaria.config;

import java.util.Arrays;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfiguration {
	

	@Value("${urlExterna}")
	String urlExterna;


	@Bean
	public GroupedOpenApi actuatorGroupedOpenApi() {
		return GroupedOpenApi.builder().group("Actuator").pathsToMatch("/actuator/**")
				.addOpenApiCustomiser(actuatorOpenApiCustomiser()).build();
	}

	@Bean
	public GroupedOpenApi drogariaGroupedOpenApi() {
		return GroupedOpenApi.builder().group("drogaria").pathsToMatch("/**")
				.addOpenApiCustomiser(salaobelezaOpenApiCustomiser())
				.packagesToScan("br.pro.delfino.drogaria.controller").build();
	}

	public OpenApiCustomiser actuatorOpenApiCustomiser() {
		return openApi -> openApi.info(actuatorInfo())
				.servers(Arrays.asList(new Server().url(urlExterna)));
	}

	public OpenApiCustomiser salaobelezaOpenApiCustomiser() {
		return openApi -> openApi.info(commonsInfo())
				.servers(Arrays.asList(new Server().url(urlExterna)));
	}

	private Info commonsInfo() {
		return new Info().title("API drogaria").description("Documentação Api drogaria.")
				.license(new License().name("Apache License Version 2.0")
						.url("https://www.apache.org/licenses/LICENSE-2.0\""))
				.contact(new Contact().name("DBS").url("https://www.dbs.com.br/").email("diegobizerra@gmail.com"));
	}

	private Info actuatorInfo() {
		return new Info().title("Actuator API").description("Actuator API Documentation.")
				.license(new License().name("Apache License Version 2.0")
						.url("https://www.apache.org/licenses/LICENSE-2.0\""))
				.contact(new Contact().name("DBS").url("https://www.dbs.com.br/").email("diegobizerra@gmail.com"));
	}

	

}

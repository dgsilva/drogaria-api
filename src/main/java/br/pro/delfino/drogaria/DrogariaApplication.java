package br.pro.delfino.drogaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrogariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrogariaApplication.class, args);
	}
	
	
//	@Bean
//	MeterRegistryCustomizer<MeterRegistry> configurer() {
//	    return (registry) -> registry.config().commonTags("application", "drogaria-api");
//	}

}

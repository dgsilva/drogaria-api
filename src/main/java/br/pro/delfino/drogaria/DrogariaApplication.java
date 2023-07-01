package br.pro.delfino.drogaria;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DrogariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrogariaApplication.class, args);
	}
	
	
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(
	    @Value("${spring.application.name}") String applicationName) {
	    return (registry) -> registry.config().commonTags("application", applicationName);
	}


}

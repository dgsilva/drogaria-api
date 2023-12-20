package br.pro.delfino.drogaria;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;

@EnableRabbit
@SpringBootApplication
public class DrogariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrogariaApplication.class, args);
	}
	
	
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer() {
	    return (registry) -> registry.config().commonTags("application", "drogaria-api");
	}

}

package br.com.joaofontella.autoescolaonlinespringangular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "br.com.joaofontella.autoescolaonlinespringangular")
@EntityScan(basePackages = "br.com.joaofontella.autoescolaonlinespringangular.model")
public class AutoescolaOnlineSpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoescolaOnlineSpringAngularApplication.class, args);
	}

}




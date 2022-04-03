package io.github.marcoantoniossilva.assets_manager;

import io.github.marcoantoniossilva.assets_manager.common.TokenConfigurationsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class AssetsManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetsManagerApiApplication.class, args);
	}

}

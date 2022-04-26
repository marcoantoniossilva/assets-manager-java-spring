package io.github.marcoantoniossilva.assets_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class AssetsManagerApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		SpringApplication.run(AssetsManagerApiApplication.class, args);
	}

}

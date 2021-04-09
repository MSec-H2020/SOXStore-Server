package com.drgnman.management_server_gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ManagementServerGradleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagementServerGradleApplication.class, args);
	}

}

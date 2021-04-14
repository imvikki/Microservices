package it.viki.technologyinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TechnologyInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnologyInfoServiceApplication.class, args);
	}

}

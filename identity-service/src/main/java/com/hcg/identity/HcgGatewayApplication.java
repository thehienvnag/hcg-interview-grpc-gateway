package com.hcg.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class HcgGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(HcgGatewayApplication.class, args);
	}

}

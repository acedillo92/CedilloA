package com.mx.avon.pruebaAvon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mx.avon.pruebaAvon.model"})
public class PruebaAvonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaAvonApplication.class, args);
	}

}

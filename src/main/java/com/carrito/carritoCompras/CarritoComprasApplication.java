package com.carrito.carritoCompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarritoComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoComprasApplication.class, args);
		
	}

}

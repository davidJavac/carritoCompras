package com.carrito.carritoCompras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.carrito.carritoCompras.model.Producto;
import com.carrito.carritoCompras.repository.ProductRepository;

@Component
public class DBInitializer implements CommandLineRunner{

	private ProductRepository productRepository;
	
	public DBInitializer(ProductRepository productRepository) {
		
		this.productRepository = productRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Producto prod1 = new Producto("Lavarropa", 10000.0, 10L);
		Producto prod2 = new Producto("Cocina", 9000, 20L);
		Producto prod3 = new Producto("SmartTV", 12000, 100L);
		Producto prod4 = new Producto("Notebook", 15000, 10L);
		
		List<Producto> productos = new ArrayList<>();
		Collections.addAll(productos, prod1, prod2, prod3, prod4);
		productRepository.saveAll(productos);
		
		
		
	}

}

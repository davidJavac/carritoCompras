package com.carrito.carritoCompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.carrito.carritoCompras.repository.CartRepository;

@Component
public class HandleEvent {

	@Autowired
	private CartRepository cartRepository;	
	@Autowired
	private CartService cartService;
	
	
	@Scheduled(fixedRate = 10000)
	public void verifyCarts() {
		
		cartRepository.findAll().parallelStream().forEach(e -> {cartService	.update(e.getCartId());});
		
	}
}

package com.carrito.carritoCompras.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.SolicitudCarrito;
import com.carrito.carritoCompras.service.CartService;

@Controller
@RequestMapping("api/v1")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/carts")
	public ResponseEntity<Cart> crearCarrito(@RequestBody SolicitudCarrito solicitudCarrito){
		
		Cart carrito = new Cart();
		carrito.setFullName(solicitudCarrito.getFullName());
		carrito.setEmail(solicitudCarrito.getEmail());
		
		Optional<Cart> optionalCart = cartService.crearCart(carrito);
		
		if(optionalCart.isPresent())
			return new ResponseEntity<>(optionalCart.get(), HttpStatus.CREATED);
		else
			return Resopnse
		
	}
	
}

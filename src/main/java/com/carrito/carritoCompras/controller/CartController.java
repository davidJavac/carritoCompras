package com.carrito.carritoCompras.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.carritoCompras.model.BusinessException;
import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.ResponseTransfer;
import com.carrito.carritoCompras.model.SolicitudCarrito;
import com.carrito.carritoCompras.model.SolicitudProducto;
import com.carrito.carritoCompras.service.CartService;

@RestController
@RequestMapping("api/v1")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/carts")
	public ResponseEntity<ResponseTransfer<Cart>> crearCarrito(@RequestBody SolicitudCarrito solicitudCarrito) throws BusinessException{
		
		Cart carrito = new Cart();
		carrito.setFullName(solicitudCarrito.getFullName());
		carrito.setEmail(solicitudCarrito.getEmail());
		
		Optional<ResponseTransfer<Cart>> optionalResponse = cartService.crearCart(carrito);
		
		if(optionalResponse.isPresent())
			return new ResponseEntity<>(optionalResponse.get(), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(optionalResponse.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@PostMapping("/carts/{id}/products")
	public ResponseEntity<ResponseTransfer<Cart>> agregarProducto(@PathVariable("id") String id, 
			@RequestBody SolicitudProducto solicitudProducto) throws BusinessException{
		
		Optional<ResponseTransfer<Cart>> optionalResponse = cartService.agregarProducto(solicitudProducto, id);
		
		if(optionalResponse.isPresent())
			return new ResponseEntity<>(optionalResponse.get(), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(optionalResponse.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}

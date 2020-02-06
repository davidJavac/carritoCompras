package com.carrito.carritoCompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.carritoCompras.repository.CartProductRepository;
import com.carrito.carritoCompras.repository.CartRepository;
import com.carrito.carritoCompras.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartProductRepository cartProductRepository;
	@Autowired
	private ProductRepository productRepository;

}

package com.carrito.carritoCompras.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.carritoCompras.model.BusinessException;
import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.CartProduct;
import com.carrito.carritoCompras.model.Producto;
import com.carrito.carritoCompras.model.ResponseTransfer;
import com.carrito.carritoCompras.model.SolicitudProducto;
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

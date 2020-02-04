package com.carrito.carritoCompras.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CartProductId implements Serializable{

	private Long cart;
	private Long producto;
	
	public CartProductId() {}
	
	public CartProductId(Long cart, Long producto) {
		
		this.cart = cart;
		this.producto = producto;
	}
}

package com.carrito.carritoCompras.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/*@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cartId")
	private Cart cart;*/
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "productId")
	private Producto producto;
	
	private double unit_price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Integer quantity;
	
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	/*public Cart getCart() {
		return cart;
	}*/
	public Producto getProducto() {
		return producto;
	}
	public Integer getQuantity() {
		return quantity;
	}
	/*public void setCart(Cart cart) {
		this.cart = cart;
	}*/
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

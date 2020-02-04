package com.carrito.carritoCompras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	private Long productId;
	private String description;
	private double unit_price;
	private Long stock;
	
	public Producto() {}
	public Producto(String description, double unit_price, Long stock) {
		
		this.description = description;
		this.unit_price = unit_price;
		this.stock = stock;
	}
	
	public String getDescription() {
		return description;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public Long getStock() {
		return stock;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
}

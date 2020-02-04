package com.carrito.carritoCompras.dto;

import org.springframework.stereotype.Component;

public class ProductDTO {

	private Long id;
	private double unit_price;
	private Integer quantity;
	
	public ProductDTO(Long id, double unit_price, Integer quantity) {
		
		this.id = id;
		this.quantity = quantity;
		this.unit_price = unit_price;
	}
	
	public Long getId() {
		return id;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

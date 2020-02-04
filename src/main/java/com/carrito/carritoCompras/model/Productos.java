package com.carrito.carritoCompras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Productos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	private Long id;
	private String description;
	private double unit_price;
	private Long stock;
	public Long getId() {
		return id;
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
	public void setId(Long id) {
		this.id = id;
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

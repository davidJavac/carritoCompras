package com.carrito.carritoCompras.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	private Long id;
	private String fullName;
	private String email;
	private java.util.Date creationDate;
	private ArrayList<Productos> products;
	private double total;
	private String status;
	
	public Long getId() {
		return id;
	}
	public String getFullName() {
		return fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getCreationDate() {
		return creationDate;
	}
	public ArrayList<Productos> getProducts() {
		return products;
	}
	public double getTotal() {
		return total;
	}
	public String getStatus() {
		return status;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setProducts(ArrayList<Productos> products) {
		this.products = products;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

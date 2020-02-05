package com.carrito.carritoCompras.model;

import java.io.Serializable;
import java.util.Set;

public class CartDTO<T> implements Serializable{

	private Long cartId;
	private String fullName;
	private String email;
	private java.util.Date creationDate;
	private Set<T> products;
	private double total;
	private String status;
	
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
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
	public Set<T> getProducts() {
		return products;
	}
	public double getTotal() {
		return total;
	}
	public String getStatus() {
		return status;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setProducts(Set<T> products) {
		this.products = products;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

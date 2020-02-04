package com.carrito.carritoCompras.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.carrito.carritoCompras.dto.ProductDTO;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	private Long cartId;
	private String fullName;
	private String email;
	private java.util.Date creationDate;
	@OneToMany(cascade = CascadeType.MERGE)
	private Set<CartProduct> products;
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
	public Set<CartProduct> getProducts() {
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
	public void setProducts(Set<CartProduct> products) {
		this.products = products;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

package com.carrito.carritoCompras.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseTransfer<T> {

	private String message;
	@JsonInclude(Include.NON_NULL)
	private T entity;
	
	public ResponseTransfer(String message, T entity) {
		
		this.message = message;
		this.entity = entity;
	}
	
	public String getMessage() {
		return message;
	}

	public T getEntity() {
		return entity;
	}

}

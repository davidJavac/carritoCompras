package com.carrito.carritoCompras.model;

public class BusinessException extends Exception{

	private ResponseTransfer responseTransfer;
	
	public BusinessException(String message, Object entity) {
		
		this.responseTransfer = new ResponseTransfer(message, entity);
	}

	public ResponseTransfer getResponseTransfer() {
		return responseTransfer;
	}

	public void setResponseTransfer(ResponseTransfer responseTransfer) {
		this.responseTransfer = responseTransfer;
	}

}

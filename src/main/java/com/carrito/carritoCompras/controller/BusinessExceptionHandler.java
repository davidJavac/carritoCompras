package com.carrito.carritoCompras.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carrito.carritoCompras.model.BusinessException;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex){
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getResponseTransfer());
	}

}

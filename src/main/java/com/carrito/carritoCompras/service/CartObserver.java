package com.carrito.carritoCompras.service;

import com.carrito.carritoCompras.model.BusinessException;

public interface CartObserver {

	public void update(Long id) throws BusinessException;
}

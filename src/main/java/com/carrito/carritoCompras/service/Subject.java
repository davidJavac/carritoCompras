package com.carrito.carritoCompras.service;

public interface Subject {

	public void attach(CartObserver cartObserver);
	public void dettach(CartObserver cartObserver);
	public void notifyObservers();
}

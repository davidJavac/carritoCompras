package com.carrito.carritoCompras.service;

import java.util.List;

public class SubjectCart implements Subject{

	private List<CartObserver> listaCartObservers;
	
	public List<CartObserver> getListaCartObservers() {
		return listaCartObservers;
	}

	public void setListaCartObservers(List<CartObserver> listaCartObservers) {
		this.listaCartObservers = listaCartObservers;
	}

	@Override
	public void attach(CartObserver cartObserver) {
		// TODO Auto-generated method stub
		listaCartObservers.add(cartObserver);
	}

	@Override
	public void dettach(CartObserver cartObserver) {
		// TODO Auto-generated method stub
		listaCartObservers.remove(cartObserver);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

}

package com.carrito.carritoCompras.service;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.CartProduct;
import com.carrito.carritoCompras.model.Producto;

public class EventService implements Visitor{

	private CartService cartService;
	private Logger logger = Logger.getLogger("Log checkout");
	private String status;
	private Cart cart;



	@Override
	public void visit(CartService cartService) {
		// TODO Auto-generated method stub
		this.cartService = cartService;
	}

	
	public void update(Cart cart){
		
		Optional<Cart> optionalCart = cartService.getCartRepository().findById(cart.getCartId());
		
		if(optionalCart.isPresent()) {
			
			cart = optionalCart.get();
			Set<CartProduct> cartProduct = cartService.getCartProductRepository().
					allCartProduct(cart); 
			
			if(cartProduct.stream().allMatch(e -> checkStock(e))) {
				
				cart.setStatus("PROCESSED");
				
			}
			else {
				
				cart.setStatus("FAILED");
				logger.log(Level.SEVERE, "No hay stock disponible para el carro de compra " + cart.getCartId());
				//logger.info("No hay stock disponible para el carro de compra " + id);
			}
			
			cartService.getCartRepository().save(cart);
			System.out.println("id " + cart.getCartId());
			System.out.println("status " + cart.getStatus());
		}
		
	}

	private boolean checkStock(CartProduct cartProduct){
		
		Long id = cartProduct.getCart().getCartId();
		if(cartService.getSetProducto(id).isPresent()) {
			
			Set<Producto> setProducto = cartService.getSetProducto(id).get().getEntity();
			
			return setProducto.stream().filter(e -> e.getProductId() == cartProduct.getProducto().getProductId())
					.allMatch(e -> e.getStock() >= cartProduct.getQuantity());
		}
		return false;
	}
	public String getStatus() {
		return status;
	}
	
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}
}

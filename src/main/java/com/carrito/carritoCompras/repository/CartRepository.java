package com.carrito.carritoCompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrito.carritoCompras.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}

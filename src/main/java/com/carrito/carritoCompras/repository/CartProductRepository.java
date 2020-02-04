package com.carrito.carritoCompras.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.CartProduct;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long>{

	@Query(value = "select cp from CartProduct cp inner join Cart c on (cp.id in (select products from c where c = :cart))")
	Set<CartProduct> allCartProduct(@Param("cart") Cart cart);
}

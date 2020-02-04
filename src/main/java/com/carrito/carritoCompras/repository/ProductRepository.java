package com.carrito.carritoCompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrito.carritoCompras.model.Producto;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long>{

}

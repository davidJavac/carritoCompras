package com.carrito.carritoCompras.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.carritoCompras.dto.ProductDTO;
import com.carrito.carritoCompras.model.BusinessException;
import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.CartProduct;
import com.carrito.carritoCompras.model.Producto;
import com.carrito.carritoCompras.model.ResponseTransfer;
import com.carrito.carritoCompras.model.SolicitudProducto;
import com.carrito.carritoCompras.repository.CartProductRepository;
import com.carrito.carritoCompras.repository.CartRepository;
import com.carrito.carritoCompras.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartProductRepository cartProductRepository;
	@Autowired
	private ProductRepository productRepository;
	
	
	public Optional<ResponseTransfer<Cart>> crearCart(Cart carrito) throws BusinessException{
		
		Cart carrito_response = carrito;
		carrito_response.setCreationDate(new java.util.Date());
		carrito_response.setTotal(0);
		carrito_response.setStatus("NEW");
		carrito_response.setProducts(new HashSet<CartProduct>());
		
		Optional<Cart> optionalCart = Optional.of(cartRepository.save(carrito_response));
		if(optionalCart.isPresent()) {
			
			ResponseTransfer rt = new ResponseTransfer("El carro de compras se ha generado exitosamente", optionalCart.get());
			return Optional.of(rt);
		}
		else
			throw new BusinessException("No se ha podido generar el carro de compras", null);
	}

	public Optional<ResponseTransfer<Cart>> agregarProducto(SolicitudProducto solicitudProducto, String id) throws BusinessException{
		
		Optional<Cart> optionalCart = cartRepository.findById(Long.parseLong(id)); 
		
		if(optionalCart.isPresent()) {
			Optional<Producto> optionalProducto = productRepository.findById(solicitudProducto.getId()); 
			Cart cartUpdate = optionalCart.get();
			
			if(optionalProducto.isPresent()) {
				Producto producto = optionalProducto.get();
				CartProduct cartProduct = new CartProduct();
				//cartProduct.setCart(optionalCart.get());
				cartProduct.setProducto(optionalProducto.get());
				cartProduct.setQuantity(solicitudProducto.getQuantity());
				cartProduct.setUnit_price(producto.getUnit_price());
				cartProductRepository.save(cartProduct);
				Set<ProductDTO> listaProductos = cartProductRepository.
						allCartProduct(cartUpdate).stream().map(e -> castDTO(e)).collect(Collectors.toSet()); 
				cartUpdate.setProducts(cartProductRepository.allCartProduct(cartUpdate));
				
			}
			else
				throw new BusinessException("No existe el producto solicitado", null);
			
			ResponseTransfer rt = new ResponseTransfer("Se han agregado los productos al carro de compra", cartUpdate);
			return Optional.of(rt);
		}
		else
			throw new BusinessException("El carro de compra solicitado no existe", null);
	}
	
	public ProductDTO castDTO(CartProduct cartProduct) {
		
		return new ProductDTO(cartProduct.getProducto().getProductId(), 
				cartProduct.getUnit_price(), cartProduct.getQuantity());
	}

}

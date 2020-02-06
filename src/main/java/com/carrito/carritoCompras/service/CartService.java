package com.carrito.carritoCompras.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.carritoCompras.dto.ProductDTO;
import com.carrito.carritoCompras.model.BusinessException;
import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.CartDTO;
import com.carrito.carritoCompras.model.CartProduct;
import com.carrito.carritoCompras.model.Producto;
import com.carrito.carritoCompras.model.ResponseTransfer;
import com.carrito.carritoCompras.model.SolicitudProducto;
import com.carrito.carritoCompras.repository.CartProductRepository;
import com.carrito.carritoCompras.repository.CartRepository;
import com.carrito.carritoCompras.repository.ProductRepository;

@Service
public class CartService{

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartProductRepository cartProductRepository;
	@Autowired
	private ProductRepository productRepository;
	
	private Logger logger = Logger.getLogger("Log checkout");
	private FileHandler fh;
	
	public CartService() {
		
		/*try {
			fh = new FileHandler("\\log\\logCheckout.log", true);
			fh = new FileHandler("%h\\carritoCompras\\log\\logCheckout.log", true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public Optional<ResponseTransfer<Cart>> crearCart(Cart carrito) throws BusinessException{
		
		Cart carrito_response = carrito;
		carrito_response.setCreationDate(new java.util.Date());
		carrito_response.setTotal(0);
		carrito_response.setStatus("NEW");
		carrito_response.setProducts(new HashSet<ProductDTO>());
		
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
				cartProduct.setCart(optionalCart.get());
				cartProduct.setProducto(optionalProducto.get());
				cartProduct.setQuantity(solicitudProducto.getQuantity());
				cartProduct.setUnit_price(producto.getUnit_price());
				cartProductRepository.save(cartProduct);
				Set<ProductDTO> listaProductos = cartProductRepository.
						allCartProduct(cartUpdate).stream().map(e -> castDTO(e)).collect(Collectors.toSet()); 
				cartUpdate.setProducts(listaProductos);
				
			}
			else
				throw new BusinessException("No existe el producto solicitado", null);
			
			ResponseTransfer<Cart> rt = new ResponseTransfer<Cart>("Se han agregado los productos al carro de compra", cartUpdate);
			return Optional.of(rt);
		}
		else
			throw new BusinessException("El carro de compra solicitado no existe", null);
	}
	
	public Optional<ResponseTransfer<Object>> borrarProducto(String cartId, String productoId) throws BusinessException{
		
		Cart cart = null;
		Producto producto = null;
		Optional<Cart> optionalCart =cartRepository.findById(Long.parseLong(cartId)); 
		Optional<Producto> optionalProducto =productRepository.findById(Long.parseLong(productoId)); 
		if(optionalCart.isPresent())
			cart = optionalCart.get();
		else
			throw new BusinessException("No existe el carro de compra solicitado", null);

		if(optionalProducto.isPresent())
			producto = optionalProducto.get();
		else
			throw new BusinessException("No existe el producto solicitado", null);
		
		if(cartProductRepository.deleteByCartAndProducto(cart, producto) != null) {
			
			return Optional.of(new ResponseTransfer<Object>("Se ha eliminado el producto del carro de compra", null));
		}
		throw new BusinessException("No se ha podido eliminar el producto", null);
	}
	
	public Optional<ResponseTransfer<Set<Producto>>> getProductos(Long id) throws BusinessException{
		
		if(this.getSetProducto(id).isPresent())
			this.getSetProducto(id).get();
		throw new BusinessException("No existe el carro de compra solicitado", null);
	}

	public Optional<ResponseTransfer<CartDTO<Long>>> getCarritoArmado(String id) throws BusinessException{
		
		Optional<Cart> optionalCart =cartRepository.findById(Long.parseLong(id));
		
		if(optionalCart.isPresent()) {
			
			Cart cart = optionalCart.get();
			Set<CartProduct> cartProduct = cartProductRepository.
					allCartProduct(optionalCart.get()); 
			Set<Long> setIdsProducts = cartProduct.stream().map(e -> e.getProducto().getProductId())
					.collect(Collectors.toSet());
			CartDTO<Long> cartDTO = new CartDTO<Long>();
			cartDTO.setCartId(cart.getCartId());
			cartDTO.setCreationDate(cart.getCreationDate());
			cartDTO.setEmail(cart.getEmail());
			cartDTO.setFullName(cart.getFullName());
			cartDTO.setProducts(setIdsProducts);
			cartDTO.setStatus(cart.getStatus());
			double total = cartProduct.stream().mapToDouble(e -> e.getUnit_price() * e.getQuantity()).sum(); 
			cartDTO.setTotal(total);
			
			return Optional.of(new ResponseTransfer<CartDTO<Long>>("Carro de compra armado", cartDTO));
		}
		throw new BusinessException("No existe el carro de compra solicitado", null);
	}
	
	public Optional<ResponseTransfer<Object>> checkOut(String id) throws BusinessException{
		
		Optional<Cart> optionalCart =cartRepository.findById(Long.parseLong(id));
		
		if(optionalCart.isPresent()) {
			
			Cart cartFetch = optionalCart.get();
			cartFetch.setStatus("READY");
			
			cartRepository.save(cartFetch);
			
			return Optional.of(new ResponseTransfer<Object>("Carro de compra modificado a status READY", null));
			
		}
		throw new BusinessException("No existe el carro de compra solicitado", null);
		
	}
	
	private ProductDTO castDTO(CartProduct cartProduct) {
		
		return new ProductDTO(cartProduct.getProducto().getProductId(), 
				cartProduct.getUnit_price(), cartProduct.getQuantity());
	}
	
	private Optional<Producto> castProducto(CartProduct cartProduct) {
		
		Optional<Producto> optionalProducto = productRepository.findById(cartProduct.getProducto().getProductId());
		if(optionalProducto.isPresent())
			return optionalProducto;
		return optionalProducto.empty();

	}

	
	public void update(Long id){
	
		Optional<Cart> optionalCart =cartRepository.findById(id);
		
		if(optionalCart.isPresent()) {
			
			Cart cartFetch = optionalCart.get();
			Set<CartProduct> cartProduct = cartProductRepository.
					allCartProduct(cartFetch); 
			
			if(cartProduct.stream().allMatch(e -> checkStock(e))) {
				
				cartFetch.setStatus("PROCESSED");				
			}
			else {
				
				cartFetch.setStatus("FAILED");
				logger.log(Level.SEVERE, "No hay stock disponible para el carro de compra " + id);
				//logger.info("No hay stock disponible para el carro de compra " + id);
			}
			
			cartRepository.save(cartFetch);
			System.out.println("id " + cartFetch.getCartId());
			System.out.println("status " + cartFetch.getStatus());
		}
		
	}

	private boolean checkStock(CartProduct cartProduct){
		
		Long id = cartProduct.getCart().getCartId();
		if(this.getSetProducto(id).isPresent()) {
			
			Set<Producto> setProducto = this.getSetProducto(id).get().getEntity();
			
			return setProducto.stream().filter(e -> e.getProductId() == cartProduct.getProducto().getProductId())
					.allMatch(e -> e.getStock() >= cartProduct.getQuantity());
		}
		return false;
	}
	
	private Optional<ResponseTransfer<Set<Producto>>> getSetProducto(Long id){
		
		Optional<Cart> optionalCart =cartRepository.findById(id);
		
		if(optionalCart.isPresent()) {
			
			Set<Producto> setProducto = cartProductRepository.
				allCartProduct(optionalCart.get()).stream().map(e -> castProducto(e)).filter(e -> e.isPresent()).map(e -> e.get())
					.collect(Collectors.toSet());
			
			return Optional.of(new ResponseTransfer<Set<Producto>>("Lista de productos del carro de compra ", setProducto));
		}
		return Optional.empty();
	}
}

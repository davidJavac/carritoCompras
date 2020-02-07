package com.carrito.carritoCompras;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.carrito.carritoCompras.model.BusinessException;
import com.carrito.carritoCompras.model.Cart;
import com.carrito.carritoCompras.model.Producto;
import com.carrito.carritoCompras.model.SolicitudProducto;
import com.carrito.carritoCompras.repository.ProductRepository;
import com.carrito.carritoCompras.service.CartService;
import com.carrito.carritoCompras.service.EventService;

public class CarritoCompraTest {

	//@InjectMocks
	@Mock
	EventService eventService;
	//@Mock
	Cart cart;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	CartService cartService;
	@Mock
	ProductRepository productRepository;
	
	/*@Before
	void setUp() throws Exception{
		
	}*/
	
	/*@Before
	void init() throws Exception{
		cart  = new Cart();
		cartService = new CartService();
		eventService = new EventService();
		
		
	}*/
	
	@Test
	public void test() {
		MockitoAnnotations.initMocks(this);
		
		Producto prod1 = new Producto("Lavarropa", 10000.0, 10L);
		when(productRepository.save(Mockito.any(Producto.class))).thenReturn(prod1);
		productRepository.save(prod1);
		
		cart = new Cart();
		cart.setCartId(1L);
		cart.setCreationDate(new java.util.Date());
		cart.setEmail("david.abramovich84@gmail.com");
		cart.setFullName("David Abramovich");
		cart.setStatus("NEW");
		cart.setTotal(0);
		cart.setProducts(new HashSet<>());
		
		SolicitudProducto sp = new SolicitudProducto();
		sp.setId(2L);
		sp.setQuantity(1000);
		
		try {
			cartService.agregarProducto(sp, String.valueOf(1L));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertTrue("FAILED".equals(eventService.getStatus()));
		/*doAnswer((c) ->{
			System.out.println("argument " + c.getArgument(0));
			assertTrue("FAILED".equals(c.getArgument(0)));
			return null;
		}).when(cart).setStatus(Mockito.anyString());
		//}).when(eventService).update(1L);*/
		/*doAnswer((c) ->{
			System.out.println("argument " + c.getArgument(0));
			System.out.println("status " + ((Cart)c.getArgument(0)).getStatus());
			assertTrue("FAILED".equals(((Cart)c.getArgument(0)).getStatus()));
			return null;
		//}).when(cart).setStatus(Mockito.anyString());
		}).when(eventService).update(Mockito.any(Cart.class));*/
		eventService.setCart(cart);
		eventService.update(cart);
	
	}

}

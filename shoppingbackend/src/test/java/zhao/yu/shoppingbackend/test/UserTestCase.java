package zhao.yu.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import zhao.yu.shoppingbackend.dao.UserDAO;
import zhao.yu.shoppingbackend.dto.Address;
import zhao.yu.shoppingbackend.dto.Cart;
import zhao.yu.shoppingbackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null ;
	private Cart cart = null ;
	private Address address = null ;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("zhao.yu.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
//	@Test
//	public void testAdd() {
//		
//		user = new User() ;
//		user.setFirstName("yu");
//		user.setLastName("zhao");
//		user.setEmail("zy@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("12345");
//		
//		assertEquals("Failed to add user!", true, userDAO.addUser(user));
//		
//		address = new Address();
//		address.setAddressLineOne("6720 windwillow dr");
//		address.setAddressLineTwo("#14204");
//		address.setCity("Fort Worth");
//		address.setState("TX");
//		address.setCountry("USA");
//		address.setPostalCode("76137");
//		address.setBilling(true);
//		
//		address.setUserId(user.getId());
//		assertEquals("Failed to add billing address!", true, userDAO.addAddress(address));
//		
//		if(user.getRole().equals("USER")) {
//			cart = new Cart();
//			cart.setUser(user);
//			
//			assertEquals("Failed to add cart!", true, userDAO.addCart(cart));
//			
//			address = new Address();
//			address.setAddressLineOne("6720 windwillow dr");
//			address.setAddressLineTwo("#14204");
//			address.setCity("Fort Worth");
//			address.setState("TX");
//			address.setCountry("USA");
//			address.setPostalCode("76137");
//			address.setShipping(true);
//			
//			address.setUserId(user.getId());
//			assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
//		}
//		
//	}
	
	@Test
	public void testUpdateCart() {
		user = userDAO.getByEmail("zy@gmail.com");
		cart = user.getCart();
		
		cart.setCartLines(2);
		cart.setGrandTotal(5555);
		
		assertEquals("Failed to add shipping address!", true, userDAO.updateCart(cart));
	}
	
}





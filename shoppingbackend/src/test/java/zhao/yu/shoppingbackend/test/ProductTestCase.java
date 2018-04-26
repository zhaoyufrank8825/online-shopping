package zhao.yu.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import zhao.yu.shoppingbackend.dao.ProductDAO;
import zhao.yu.shoppingbackend.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("zhao.yu.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}
	
//	@Test
//	public void testCRUDProduct() {
//		product = new Product();
//		product.setName("Oppo Selfie S53");
//		product.setBrand("Oppo");
//		product.setDescription("This is some description for Oppo phones!");
//		product.setUnitPrice(25000);
//		product.setActive(true);
//		product.setCategoryId(3);
//		product.setSupplierId(3);
//		assertEquals("Error!", true, productDAO.add(product));
//		
//		
//		product = productDAO.get(2);
//		product.setName("Samsung Galaxy S7");
//		assertEquals("Error!", true, productDAO.update(product));
//		
//		assertEquals("Error!", true, productDAO.delete(product));
//		
//		assertEquals("Error!", 6, productDAO.list().size());
//	}
	
	
	@Test
	public void test() {
		assertEquals("Error!", 3, productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Error!", 3, productDAO.getLatestActiveProducts(3).size());
	}
	
	
}

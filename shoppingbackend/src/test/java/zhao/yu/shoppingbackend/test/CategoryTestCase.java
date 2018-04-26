package zhao.yu.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import zhao.yu.shoppingbackend.dao.CategoryDAO;
import zhao.yu.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("zhao.yu.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
//	@Test
//	public void testAddCategory() {
//		category = new Category();
//		
//		category.setName("Television");
//		category.setDescription("This is some description for television!");
//		category.setImageURL("CAT_1.png");
//		
//		assertEquals("Successfully added!", true, categoryDAO.add(category));
//	}
	
//	@Test
//	public void testGetCategory() {
//		category = categoryDAO.get(3);
//		assertEquals("Successfully added!", "Mobile", category.getName());
//	}
	
//	@Test
//	public void testUpdateCategory() {
//		category = categoryDAO.get(3);
//		category.setName("TV");
//		assertEquals("Successfully added!", true, categoryDAO.update(category));
//	}
//	
//	@Test
//	public void testDeleteCategory() {
//		category = categoryDAO.get(3);
//		assertEquals("Successfully added!", true, categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testListCategory() {
//		assertEquals("Successfully!", 4, categoryDAO.list().size());
//	}

	@Test
	public void testCRUDCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added!", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("This is some description for television!");
		category.setImageURL("CAT_2.png");
		assertEquals("Successfully added!", true, categoryDAO.add(category));
		
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully!", true, categoryDAO.update(category));
		
		assertEquals("Successfully!", 2, categoryDAO.list().size());
	}
}

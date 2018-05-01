package zhao.yu.shoppingbackend.dao;

import zhao.yu.shoppingbackend.dto.Address;
import zhao.yu.shoppingbackend.dto.Cart;
import zhao.yu.shoppingbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);
	
	boolean addAddress(Address address);
	
	boolean updateCart(Cart cart);
}

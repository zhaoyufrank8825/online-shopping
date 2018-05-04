package zhao.yu.shoppingbackend.dao;

import java.util.List;

import zhao.yu.shoppingbackend.dto.Address;
import zhao.yu.shoppingbackend.dto.Cart;
import zhao.yu.shoppingbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);
	
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddress(User user);
	
}

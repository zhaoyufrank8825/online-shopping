package zhao.yu.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zhao.yu.shoppingbackend.dao.UserDAO;
import zhao.yu.shoppingbackend.dto.Address;
import zhao.yu.shoppingbackend.dto.Cart;
import zhao.yu.shoppingbackend.dto.User;


@Repository("userDAO")
@Transactional
public class UserDAOImple implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {			
			sessionFactory.getCurrentSession().persist(user);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().persist(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email =: email";
		
		try {			
			return (User) sessionFactory.getCurrentSession().createQuery(selectQuery)
					.setParameter("email", email).getSingleResult();
		}
		catch(Exception ex) {
//			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND billing = :billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("user", user).setParameter("billing", true).getSingleResult();	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND shipping = :shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("user", user).setParameter("shipping", true).getResultList();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}

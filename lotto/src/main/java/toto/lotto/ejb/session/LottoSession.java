package toto.lotto.ejb.session;

import java.io.Serializable;
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.*;

import toto.lotto.ejb.entity.*;

@Stateless(name = "lottosession")
public class LottoSession implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sql;
	
	@PersistenceContext
    private EntityManager em;
	
	public List<Role> getAllRole() {
		sql = "select * from role";
		return em.createNativeQuery(sql, Role.class).getResultList();
	}
	
	public Role getRoleById(Long r) {
		return em.find(Role.class,  r);
	}
	
	public void deleteRole(Role r) {
		r = getRoleById(r.getId());
		em.remove(r);
	}
	
	public void updateRole(Role r) {
		em.merge(r);
	}
	
	public List<Customer> getAllCustomer() {
		sql = "select * from customer";
		return em.createNativeQuery(sql, Customer.class).getResultList();
	}
	
	public Customer getCustomerByName(String username) {
		Customer c = null;
		sql = "select * from customer where username = '" + username + "'";
		List<Customer> cs = em.createNativeQuery(sql, Customer.class).getResultList();
		if(cs != null) {
			c = cs.get(0);
		}
		return c ;
	}
	
	public List<Customer> getCustomerByRole(Role r) {
		sql = "select * from customer where role_id = " + r.getId();
		return em.createNativeQuery(sql, Customer.class).getResultList();
	}
	
	public Customer getCustomerById(Customer c) {
		return em.find(Customer.class, c.getId());
	}
	
	public void updateCustomer(Customer c) {
		em.merge(c);
	}
	
	public void deleteCustomer(Customer c) {
		c = em.find(Customer.class, c.getId());
		em.remove(c);
	}
}

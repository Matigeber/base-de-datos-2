package ar.edu.unlp.info.bd2.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.edu.unlp.info.bd2.model.Category;
import ar.edu.unlp.info.bd2.model.User;

public class MLRepository {
	
	private SessionFactory sessionFactory;
	
	public void createCategory(Category category) { 
		this.sessionFactory.getCurrentSession().save(category);
	}
	
	public Category getCategoryByName(String name) {
		String query = "FROM Category WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		Category cat = (Category) session.createQuery(query).setParameter("name", name).uniqueResult(); /* devuelve en formato query hay que castearlo a Category */
		return cat;
		
	}
	
	public void createUser(User user) { 
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	public User getUserByEmail(String email) {
		String query = "FROM User WHERE email = :email";
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.createQuery(query).setParameter("email", email).uniqueResult();
		return user;
	}


}

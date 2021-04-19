package ar.edu.unlp.info.bd2.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.model.Category;
import ar.edu.unlp.info.bd2.model.CreditCardPayment;
import ar.edu.unlp.info.bd2.model.DeliveryMethod;
import ar.edu.unlp.info.bd2.model.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.Provider;
import ar.edu.unlp.info.bd2.model.User;

public class MLRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Category createCategory(Category category) { 
		this.sessionFactory.getCurrentSession().save(category);
		return this.getCategoryByName(category.getName());
	}
	
	public Category getCategoryByName(String name) {
		String query = "FROM Category WHERE name = :name"; /* esto es HQL */
		Session session = this.sessionFactory.getCurrentSession();
		Category cat = (Category) session.createQuery(query).setParameter("name", name).uniqueResult(); /* devuelve en formato query hay que castearlo a Category */
		return cat;
		
	}
	
	public void updateCategory(Category category) {
		this.sessionFactory.getCurrentSession().update(category);
		
		}
	
	public User createUser(User user) { 
		this.sessionFactory.getCurrentSession().save(user);
		return this.getUserByEmail(user.getEmail());
	}
	
	public User getUserByEmail(String email) {
		String query = "FROM User WHERE email = :email";
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.createQuery(query).setParameter("email", email).uniqueResult();
		return user;
	}

	public Provider createProvider(Provider provider) {
		this.sessionFactory.getCurrentSession().save(provider);
		return this.getProviderByCuit(provider.getCuit());
	}
	
	public Provider getProviderByCuit(Long cuit) {
		String query = "FROM Provider WHERE cuit = :cuit";
		Session session = this.sessionFactory.getCurrentSession();
		Provider provider = (Provider) session.createQuery(query).setParameter("cuit", cuit).uniqueResult();
		return provider;
	}
	
	public DeliveryMethod createDeliveryMethod(DeliveryMethod dm) {
		this.sessionFactory.getCurrentSession().save(dm);
		return this.getDeliveryMethodByName(dm.getName());
	}
	
	public DeliveryMethod getDeliveryMethodByName(String name) {
		String query = "FROM DeliveryMethod WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		DeliveryMethod dm = (DeliveryMethod) session.createQuery(query).setParameter("name", name).uniqueResult();
		return dm;
	}
	
	public Product createProduct(Product product) {
		this.sessionFactory.getCurrentSession().save(product);
		return this.getProductByName(product.getName());
	}
	
	public Product getProductByName (String name) {
		String query = "FROM Product WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.createQuery(query).setParameter("name", name).uniqueResult();
		return p;
	}
	
	public CreditCardPayment createCreditCardPayment (CreditCardPayment cp) {
		this.sessionFactory.getCurrentSession().save(cp);
		return this.getCreditCardPayment(cp.getName());
	}

	public CreditCardPayment getCreditCardPayment (String name) {
		String query = "FROM CreditCardPayment WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		CreditCardPayment cp  = (CreditCardPayment) session.createQuery(query).setParameter("name", name).uniqueResult();
		return cp;
	}
	
	public OnDeliveryPayment createOnDeliveryPayment(OnDeliveryPayment dp) {
		this.sessionFactory.getCurrentSession().save(dp);
		return this.getOnDeliveryPayment(dp.getName());
	}
	
	public OnDeliveryPayment getOnDeliveryPayment (String name) {
		String query = "FROM OnDeliveryPayment WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		OnDeliveryPayment dp  = (OnDeliveryPayment) session.createQuery(query).setParameter("name", name).uniqueResult();
		return dp;
	}
}

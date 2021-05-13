package ar.edu.unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.model.Category;
import ar.edu.unlp.info.bd2.model.CreditCardPayment;
import ar.edu.unlp.info.bd2.model.DeliveryMethod;
import ar.edu.unlp.info.bd2.model.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.model.PaymentMethod;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.ProductOnSale;
import ar.edu.unlp.info.bd2.model.Provider;
import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.User;

@Transactional
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
	


	public User getUserByUsername(String username) {
		String query = "FROM User WHERE fullname = :username";
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.createQuery(query).setParameter("username", username).uniqueResult();
		return user;
	}

	public List<User> getUsersSpendingMoreThanInPurchase(Float amount){
		/**select users
		where users natJoin purchases
		group_by user_id 
		having sum (purchase.amount) > amount
		**/
		String query = "SELECT User "
				+ "FROM User inner join Purchase "
				+ "GROUP_BY user.id "
				+ "HAVING sum(Purchase.amount) > :amount";
		Session session = this.sessionFactory.getCurrentSession();
		List<User> users  = session.createQuery(query).setParameter("amount", amount).list();
		
		return users;
	}
	public void updateUser (User user) {
		this.sessionFactory.getCurrentSession().update(user);
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
	
	public void updateProvider (Provider provider) {
		this.sessionFactory.getCurrentSession().update(provider);
	}
	
	public DeliveryMethod createDeliveryMethod(DeliveryMethod dm) {
		this.sessionFactory.getCurrentSession().save(dm);
		return dm;
		/*return this.getDeliveryMethodById(dm.getId())*/ /* Devolver el que me viene por parametro luego del save o volverlo a buscar en la base*/
	}
	
	public DeliveryMethod getDeliveryMethodById(long id) {
		String query = "FROM DeliveryMethod WHERE id = :id";
		Session session = this.sessionFactory.getCurrentSession();
		DeliveryMethod dm  = (DeliveryMethod) session.createQuery(query).setParameter("id", id).uniqueResult();
		return dm;
	}
	
	public DeliveryMethod getDeliveryMethodByName(String name) {
		String query = "FROM DeliveryMethod WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		DeliveryMethod dm = (DeliveryMethod) session.createQuery(query).setParameter("name", name).uniqueResult();
		return dm;
	}
	
	public void updateDeliveryMethod (DeliveryMethod dm) {
		this.sessionFactory.getCurrentSession().update(dm);
	}
	
	public Product createProduct(Product product) {
		/*try*/
		this.sessionFactory.getCurrentSession().save(product);
		return this.getProductByName(product.getName());
		/*catch constraint violation hibernate
		throw new MLException("Constraint Violation");*/
		
	}
	
	public Product getProductByName (String name) {
		String query = "FROM Product WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.createQuery(query).setParameter("name", name).uniqueResult();
		return p;
	}
	
	public void updateProduct (Product product) {
		this.sessionFactory.getCurrentSession().update(product);
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
	
	
	public ProductOnSale createProductOnSale (ProductOnSale ps) {
		this.sessionFactory.getCurrentSession().save(ps);
		return ps;
	}
	
	public ProductOnSale getProductOnSale (Product prod, Provider prov) {
		String query = "FROM ProductOnSale WHERE product_id = :prod_id and provider_id = :prov_id and finalDate = null";
		Session session = this.sessionFactory.getCurrentSession();
		ProductOnSale ps  = (ProductOnSale) session.createQuery(query).setParameter("prod_id", prod.getId()).setParameter("prov_id", prov.getId()).uniqueResult();
		/*List<ProductOnSale> lista = session.createQuery(query).setParameter("prod_id", prod.getId()).setParameter("prov_id", prov.getId()).getResultList();
		for (int i=1 ; i<= lista.size() ; i++ ) {
			System.out.println(lista.get(i).getFinalDate());
		}
		System.out.println(lista);
		System.out.println(lista.get(0).getFinalDate());
		System.out.println(lista.get(1).getFinalDate());
		*/return ps;
	}
	
	public void updateProductOnSale (ProductOnSale ps) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(ps);
	}
	
	public ProductOnSale getProductOnSaleById (long id) {
		String query = "FROM ProductOnSale WHERE id = :id";
		Session session = this.sessionFactory.getCurrentSession();
		ProductOnSale ps  = (ProductOnSale) session.createQuery(query).setParameter("id", id).uniqueResult();
		return ps;
	}
	
	public Purchase createPurchase (Purchase purchase) {
		this.sessionFactory.getCurrentSession().save(purchase);
		return getPurchaseById(purchase.getId());
	}
	
	public Purchase getPurchaseById(long id) {
		String query = "FROM Purchase WHERE id = :id";
		Session session = this.sessionFactory.getCurrentSession();
		Purchase purchase  = (Purchase) session.createQuery(query).setParameter("id", id).uniqueResult();
		return purchase;
	}
	public void updatePaymentMethod (PaymentMethod pm) {
		this.sessionFactory.getCurrentSession().update(pm);
	}
	
	public List<Purchase> getAllPurchasesByUser(long user_id){
		System.out.println("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		String query = "FROM Purchase WHERE user_id = :user_id";
		Session session = this.sessionFactory.getCurrentSession();
		List<Purchase> purchases  = session.createQuery(query).setParameter("user_id", user_id).list();
		return purchases;
	}
}

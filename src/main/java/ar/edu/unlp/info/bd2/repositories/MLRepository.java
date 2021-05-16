package ar.edu.unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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


public class MLRepository {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Category createCategory(Category category) { 
		this.sessionFactory.getCurrentSession().save(category);
		return this.getCategoryByName(category.getName());
	}
	
	@Transactional
	public Category getCategoryByName(String name) {
		String query = "FROM Category WHERE name = :name"; /* esto es HQL */
		Session session = this.sessionFactory.getCurrentSession();
		Category cat = (Category) session.createQuery(query).setParameter("name", name).uniqueResult(); /* devuelve en formato query hay que castearlo a Category */
		return cat;
		
	}
	
	@Transactional
	public void updateCategory(Category category) {
		this.sessionFactory.getCurrentSession().update(category);
		
		}
	
	@Transactional
	public User createUser(User user) { 
		this.sessionFactory.getCurrentSession().save(user);
		return this.getUserByEmail(user.getEmail());
	}
	
	@Transactional
	public User getUserByEmail(String email) {
		String query = "FROM User WHERE email = :email";
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.createQuery(query).setParameter("email", email).uniqueResult();
		return user;
	}
	

	@Transactional
	public User getUserByUsername(String username) {
		String query = "FROM User WHERE fullname = :username";
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.createQuery(query).setParameter("username", username).uniqueResult();
		return user;
	}
	
	@Transactional
	public void updateUser (User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}
	@Transactional
	public Provider createProvider(Provider provider) {
		this.sessionFactory.getCurrentSession().save(provider);
		return this.getProviderByCuit(provider.getCuit());
	}
	@Transactional
	public Provider getProviderByCuit(Long cuit) {
		String query = "FROM Provider WHERE cuit = :cuit";
		Session session = this.sessionFactory.getCurrentSession();
		Provider provider = (Provider) session.createQuery(query).setParameter("cuit", cuit).uniqueResult();
		return provider;
	}
	@Transactional
	public void updateProvider (Provider provider) {
		this.sessionFactory.getCurrentSession().update(provider);
	}
	
	@Transactional
	public DeliveryMethod createDeliveryMethod(DeliveryMethod dm) {
		this.sessionFactory.getCurrentSession().save(dm);
		return dm;
		/*return this.getDeliveryMethodById(dm.getId())*/ /* Devolver el que me viene por parametro luego del save o volverlo a buscar en la base*/
	}
	@Transactional
	public DeliveryMethod getDeliveryMethodById(long id) {
		String query = "FROM DeliveryMethod WHERE id = :id";
		Session session = this.sessionFactory.getCurrentSession();
		DeliveryMethod dm  = (DeliveryMethod) session.createQuery(query).setParameter("id", id).uniqueResult();
		return dm;
	}
	@Transactional
	public DeliveryMethod getDeliveryMethodByName(String name) {
		String query = "FROM DeliveryMethod WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		DeliveryMethod dm = (DeliveryMethod) session.createQuery(query).setParameter("name", name).uniqueResult();
		return dm;
	}
	@Transactional
	public void updateDeliveryMethod (DeliveryMethod dm) {
		this.sessionFactory.getCurrentSession().update(dm);
	}
	@Transactional
	public Product createProduct(Product product) {
		/*try*/
		this.sessionFactory.getCurrentSession().save(product);
		return this.getProductByName(product.getName());
		/*catch constraint violation hibernate
		throw new MLException("Constraint Violation");*/
		
	}
	@Transactional
	public Product getProductByName (String name) {
		String query = "FROM Product WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.createQuery(query).setParameter("name", name).uniqueResult();
		return p;
	}

	
	@Transactional
	public void updateProduct (Product product) {
		this.sessionFactory.getCurrentSession().update(product);
	}
	@Transactional
	public CreditCardPayment createCreditCardPayment (CreditCardPayment cp) {
		this.sessionFactory.getCurrentSession().save(cp);
		return this.getCreditCardPayment(cp.getName());
	}
	@Transactional
	public CreditCardPayment getCreditCardPayment (String name) {
		String query = "FROM CreditCardPayment WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		CreditCardPayment cp  = (CreditCardPayment) session.createQuery(query).setParameter("name", name).uniqueResult();
		return cp;
	}
	
	@Transactional
	public OnDeliveryPayment createOnDeliveryPayment(OnDeliveryPayment dp) {
		this.sessionFactory.getCurrentSession().save(dp);
		return this.getOnDeliveryPayment(dp.getName());
	}
	@Transactional
	public OnDeliveryPayment getOnDeliveryPayment (String name) {
		String query = "FROM OnDeliveryPayment WHERE name = :name";
		Session session = this.sessionFactory.getCurrentSession();
		OnDeliveryPayment dp  = (OnDeliveryPayment) session.createQuery(query).setParameter("name", name).uniqueResult();
		return dp;
	}
	
	@Transactional
	public ProductOnSale createProductOnSale (ProductOnSale ps) {
		this.sessionFactory.getCurrentSession().save(ps);
		return this.getProductOnSale(ps.getProduct(), ps.getProvider());
	}
	@Transactional
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
	
	@Transactional
	public void updateProductOnSale (ProductOnSale ps) {
		this.sessionFactory.getCurrentSession().update(ps);
	}
	
	@Transactional
	public ProductOnSale getProductOnSaleById (long id) {
		String query = "FROM ProductOnSale WHERE id = :id";
		Session session = this.sessionFactory.getCurrentSession();
		ProductOnSale ps  = (ProductOnSale) session.createQuery(query).setParameter("id", id).uniqueResult();
		return ps;
	}
	@Transactional
	public Purchase createPurchase (Purchase purchase) {
		this.sessionFactory.getCurrentSession().save(purchase);
		return getPurchaseById(purchase.getId());
	}
	@Transactional
	public Purchase getPurchaseById(long id) {
		String query = "FROM Purchase WHERE id = :id";
		Session session = this.sessionFactory.getCurrentSession();
		Purchase purchase  = (Purchase) session.createQuery(query).setParameter("id", id).uniqueResult();
		return purchase;
	}
	@Transactional
	public void updatePaymentMethod (PaymentMethod pm) {
		this.sessionFactory.getCurrentSession().update(pm);
	}
	
	//---------------- CONSULTAS PARTE 2 ---------------------------------------
	@Transactional
	public List<Purchase> getAllPurchasesByUser(long user_id){
		String query = "FROM Purchase WHERE user_id = :user_id";
		Session session = this.sessionFactory.getCurrentSession();
		List<Purchase> purchases  = session.createQuery(query).setParameter("user_id", user_id).list();
		return purchases;
	}
	
	@Transactional
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount){ 
		String query = "SELECT distinct cli "
				+ "FROM Purchase p join p.client as cli "
				+ "WHERE p.amount > :amount";
		Session session = this.sessionFactory.getCurrentSession();
		List<User> users  = session.createQuery(query).setParameter("amount", amount).list();
		return users;
	}
	
	@Transactional
	public List<User> getUsersSpendingMoreThan(Float amount){
		String query = "SELECT cli "
				+ "FROM Purchase p join p.client as cli "
				+ "GROUP BY cli "
				+ "HAVING sum(p.amount) > :amount";
		Session session = this.sessionFactory.getCurrentSession();
		List<User> users  = session.createQuery(query).setParameter("amount", amount.doubleValue()).list();
		return users;
	}
	
	@Transactional
	public List<Provider> getTopNProvidersInPurchases(int n){ 
		/**" Esto se refiere a ProductOnSale -> Purchase -> Quantity (Sumatoria de cantidades) (HICIMOS ESTA)
		 * 	o se refiere a ProductOnSale -> Product (Sumatoria de productos individual)
		 */ 
		String query = "SELECT p " +
				"FROM Purchase ps join ps.productOnSale as pos join pos.provider as p "+
		        "GROUP BY p "+ 
		        "order by sum(ps.quantity) desc ";
		Session session = this.sessionFactory.getCurrentSession();
		List<Provider> topN  = session.createQuery(query).setMaxResults(n).list();
		return topN;
	}
	
	@Transactional
	public List<Product> getTop3MoreExpensiveProducts(){
		String query = "SELECT p " +
		        "FROM ProductOnSale pos JOIN pos.product as p " +
				"WHERE pos.finalDate is null " +
		        "GROUP BY p "+ 
		        "order by max(pos.price) desc ";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setMaxResults(3).list();
	}
	
	@Transactional
	public List<User> getTopNUsersMorePurchase(int n){
	String query = "SELECT u " +
	        "FROM Purchase p JOIN p.client as u " +
	        "GROUP BY u "+ 
	        "order by count(*) desc ";
	Session session = this.sessionFactory.getCurrentSession();
	return session.createQuery(query).setMaxResults(n).list();
	}
	
	@Transactional
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		String query = "SELECT pur " +
						"FROM Purchase pur "+
						"WHERE pur.DateOfPurchase between :startDate and :endDate";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
	}
	
	@Transactional
	public List<Product> getProductForCategory(Category category) {
		String query = "SELECT p "+
						"FROM Product p "+
						"WHERE p.category = :category";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("category", category).getResultList();
	}
	
	@Transactional
	public List<Purchase> getPurchasesForProvider(Long cuit) {
		String query = "SELECT pur "
				+ "FROM Purchase pur JOIN pur.productOnSale as pos JOIN pos.provider as prov "
				+ "WHERE prov.cuit = :cuit";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("cuit", cuit).getResultList();
	}
	
	public Product getBestSellingProduct() {
		String query = "SELECT prod "
				+ "FROM Purchase p JOIN p.productOnSale as pos JOIN pos.product as prod "
				+ "GROUP BY prod "
				+ "ORDER BY count(p) desc";
		Session session = this.sessionFactory.getCurrentSession();
		Product p  = (Product) session.createQuery(query).list().get(0);
		return p;
	}
	
	public List<Product> getProductsOnePrice(){
		String query = "SELECT prod " 
				+ "FROM ProductOnSale pos JOIN pos.product as prod "
				+ "GROUP BY prod "
				+ "HAVING count(pos) = 1";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).getResultList();
	}
	
	/* 12
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice(){
		//1.filtrar el ultimo POS de un Prov para todos Prov
		 * "(FROM ProductOnSale.id "
		+ "WHERE product_id = :prod_id "
		+ "and provider_id = :prov_id "
		+ "and finalDate = null)";
		//2.calcular diferencia max() min() entre Prov
		//3. comparar dif > 20%
		String query = "SELECT prod"
						+ "FROM ProductOnSale pos JOIN p.provider as prov JOIN p.product as prod "
						+ "WHERE pos.finalDate = null
							GROUP BY prod
							HAVING max(
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).getResultList();
	}
	*/
	
	public Provider getProviderLessExpensiveProduct() {
		String query = "SELECT prov"
					+ "FROM ProductOnSale pos JOIN p.provider as prov "
					+ "WHERE pos.finalDate = null";
		Session session = this.sessionFactory.getCurrentSession();
		Purchase purchase  = (Purchase) session.createQuery(query).setParameter("id", id).uniqueResult();
		return purchase;
	}
}

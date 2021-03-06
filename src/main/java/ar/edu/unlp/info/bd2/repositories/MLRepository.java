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
		String query = "FROM Category WHERE name = :name"; 
		Session session = this.sessionFactory.getCurrentSession();
		Category cat = (Category) session.createQuery(query).setParameter("name", name).uniqueResult();
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
		/*return this.getDeliveryMethodById(dm.getId())*/
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
		return ps;
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
		return session.createQuery(query).setParameter("user_id", user_id).list();
	}
	
	@Transactional
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount){ 
		String query = "SELECT distinct cli "
				+ "FROM Purchase p join p.client as cli "
				+ "WHERE p.amount > :amount";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("amount", amount).list();
	}
	
	@Transactional
	public List<User> getUsersSpendingMoreThan(Float amount){
		String query = "SELECT cli "
				+ "FROM Purchase p join p.client as cli "
				+ "GROUP BY cli "
				+ "HAVING sum(p.amount) > :amount";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("amount", amount.doubleValue()).list();
	}
	
	@Transactional
	public List<Provider> getTopNProvidersInPurchases(int n){ 
		String query = "SELECT p " +
				"FROM Purchase ps join ps.productOnSale as pos join pos.provider as p "+
		        "GROUP BY p "+ 
		        "order by sum(ps.quantity) desc ";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setMaxResults(n).list();
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
						"WHERE pur.dateOfPurchase between :startDate and :endDate";
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
	
	@Transactional
	public Product getBestSellingProduct() {
		String query = "SELECT prod "
				+ "FROM Purchase p JOIN p.productOnSale as pos JOIN pos.product as prod "
				+ "GROUP BY prod "
				+ "ORDER BY count(p) desc";
		Session session = this.sessionFactory.getCurrentSession();
		Product p  = (Product) session.createQuery(query).list().get(0);
		return p;
	}
	
	@Transactional
	public List<Product> getProductsOnePrice(){
		String query = "SELECT prod " 
				+ "FROM ProductOnSale pos JOIN pos.product as prod "
				+ "GROUP BY prod "
				+ "HAVING count(pos) = 1";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).getResultList();
	}
	
	@Transactional
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice(){
		String query = "SELECT prod "
						+"FROM ProductOnSale pos JOIN pos.provider as prov JOIN pos.product as prod "
						+"WHERE pos.finalDate = null "
						+"GROUP BY prod "
						+"HAVING ((max(pos.price) - min(pos.price)) / max(pos.price)) > 0.20";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).getResultList();
	}
	
	@Transactional
	public Provider getProviderLessExpensiveProduct() {
		String query = "SELECT prov "
					+ "FROM ProductOnSale pos JOIN pos.provider as prov "
					+ "WHERE pos.finalDate = null "
					+ "GROUP BY prov "
					+ "ORDER BY min(pos.price)";
		Session session = this.sessionFactory.getCurrentSession();
		return (Provider) session.createQuery(query).list().get(0);
	}
	
	@Transactional
	public List<Provider> getProvidersDoNotSellOn(Date day) {
		String query = "select prov"
				+ " from Provider prov"
				+ " where prov not in ("
					+ " select p"
					+ " from Purchase pur JOIN pur.productOnSale as pos JOIN pos.provider as p"
					+ " where pur.dateOfPurchase = :day)";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("day",day).getResultList();
	}
	
	@Transactional
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		String query = "select distinct pos "
				+ "from Purchase p JOIN p.productOnSale as pos "
				+ "where p.dateOfPurchase = :day";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).setParameter("day",day).getResultList();
	}
	
	@Transactional
	public List<Product> getProductsNotSold() {
		String query = "Select prod"
				+ " From Product prod"
				+ " where prod not in ("
					+ " select prod"
					+ " from Purchase p join p.productOnSale as pos join pos.product as prod )";
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery(query).getResultList();
	}
	
	@Transactional
	public DeliveryMethod getMostUsedDeliveryMethod() {
		String query = "select dm"
				+ " from Purchase p JOIN p.deliveryMethod as dm"
				+ " group by dm"
				+ " order by count(p) DESC";
		Session session = this.sessionFactory.getCurrentSession();
		return (DeliveryMethod) session.createQuery(query).list().get(0);
	}
	
	@Transactional
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod() {
		String query = "select pm"
				+ " from Purchase p JOIN p.paymentMethod as pm"
				+ " where pm.class = OnDeliveryPayment"
				+ " order by (pm.promisedAmount - p.amount) DESC";
		Session session = this.sessionFactory.getCurrentSession();
		return (OnDeliveryPayment)session.createQuery(query).list().get(0);
	}
	
	@Transactional
	public Product getHeaviestProduct() {
		String query = "select p"
				+ " from Product p"
				+ " order by p.weight DESC";
		Session session = this.sessionFactory.getCurrentSession();
		return (Product) session.createQuery(query).list().get(0);
	}
	
	@Transactional
	public Category getCategoryWithLessProducts() {
		String query = "select c"
				+ " from Product p JOIN p.category as c"
				+ " group by c"
				+ " order by count(p) ASC";
		Session session = this.sessionFactory.getCurrentSession();
		return (Category) session.createQuery(query).list().get(0);
	}
}

package ar.edu.unlp.info.bd2.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import ar.edu.unlp.info.bd2.repositories.MLException;
import ar.edu.unlp.info.bd2.repositories.MLRepository;


@Transactional
public class MLServiceImpl implements MLService{

	private MLRepository repository;
	
	public MLServiceImpl(MLRepository repository) {
		this.repository = repository;
	}


	@Override
	public Category createCategory(String name) throws MLException {
		Category cat = repository.getCategoryByName(name);
		if (cat == null){
			Category category = new Category(name);
			Category category2 = repository.createCategory(category);
			System.out.println(category2);
			return category2;
		} else {
			throw new MLException("ya existe la categoria con el nombre: %".format(name));
					}
	}

	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException {
		Product prod = repository.getProductByName(name);
		if (prod == null) {
			Product p = new Product(weight, name, category);
			Product product = repository.createProduct(p);
			return product;
		}else {
			throw new MLException("Constraint Violation");
		}
	}

	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		User user = repository.getUserByEmail(email);
		if (user == null) {
			User usr = new User(email,password,fullname,dayOfBirth);
			return repository.createUser(usr);
		}else {
			throw new MLException("Constraint Violation");
		}
	}

	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		Provider provider = repository.getProviderByCuit(cuit);
		if (provider == null) {
			Provider prov = new Provider(name,cuit);
			return repository.createProvider(prov);
		}else {
			throw new MLException("Constraint Violation");
		}
	}

	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		DeliveryMethod method = new DeliveryMethod(name, cost, startWeight, endWeight);
		return repository.createDeliveryMethod(method);
	}

	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		CreditCardPayment cp = repository.getCreditCardPayment(name);
		if (cp == null) {
			CreditCardPayment creditCard = new CreditCardPayment(name, brand, number, expiry, cvv, owner);
			return repository.createCreditCardPayment(creditCard);
		}else {
			throw new MLException("Constraint Violation");
		}
	}

	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		OnDeliveryPayment dp = repository.getOnDeliveryPayment(name);
		if (dp == null) {
			OnDeliveryPayment deliveryPayment = new OnDeliveryPayment(name, promisedAmount);
			return repository.createOnDeliveryPayment(deliveryPayment);
		}else {
			throw new MLException("Constraint Violation");
		}
		
	}

	
	/*@Transactional
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate) throws MLException {
		ProductOnSale ps = repository.getProductOnSale(product, provider);
		if (ps == null) {
			ProductOnSale prodSale = new ProductOnSale(product, provider, price, initialDate);
			ProductOnSale prodSalecreated = repository.createProductOnSale(prodSale);
			product.addProductOnsale(prodSale);
			repository.updateProduct(product);
			provider.addProductOnSale(prodSale);
			repository.updateProvider(provider);
			return prodSalecreated;
		}else {
			if (ps.getInitialDate().before(initialDate)) {
				ps.setFinalDate(this.addOrSubtractDays(initialDate, -1));
				repository.updateProductOnSale(ps);
				ProductOnSale prodSale = new ProductOnSale(product, provider, price, initialDate);
				ProductOnSale prodSaleCreated = repository.createProductOnSale(prodSale);
				System.out.println(prodSaleCreated.getId());
				product.addProductOnsale(prodSaleCreated);
				this.repository.updateProduct(product);
				provider.addProductOnSale(prodSaleCreated);
				repository.updateProvider(provider);
				return prodSale;
			}else {
				throw new MLException("Ya existe un precio para el producto con fecha de inicio de vigencia posterior a la fecha de inicio dada");
			}
			
		}
		
	}*/
	
	@Override
	@Transactional
	public ProductOnSale createProductOnSale (Product product, Provider provider, Float price, Date initialDate) throws MLException {
		List<ProductOnSale> productsOfProvider = product.getProductsOnSaleByProvider(provider);
		if (productsOfProvider.size() > 0) {
			ProductOnSale ps = productsOfProvider.get(productsOfProvider.size() - 1);
			if (ps.getInitialDate().after(initialDate)) {
				throw new MLException("Ya existe un precio para el producto con fecha de inicio de vigencia posterior a la fecha de inicio dada");
			}
			ps.setFinalDate(this.addOrSubtractDays(initialDate, -1));
		}
		ProductOnSale productOnSale = new ProductOnSale(product,provider,price,initialDate);
		product.addProductOnsale(productOnSale);
		this.repository.updateProduct(product);
		return product.getProductsOnSale().get(product.getProductsOnSale().size() - 1);
	}


	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		if (deliveryMethod.checkShipping(productOnSale.getProduct().getWeight() * quantity)) {
			Purchase p = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
			Purchase purchase = repository.createPurchase(p);
			return purchase;
		}else {
			throw new MLException("m??todo de delivery no v??lido");
		}
		
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		Optional<User> user = Optional.ofNullable(this.repository.getUserByEmail(email));
		return user;
	}

	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		Optional<Provider> provider = Optional.ofNullable(this.repository.getProviderByCuit(cuit));
		return provider;
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		Optional<Category> cat = Optional.ofNullable(this.repository.getCategoryByName(name));
		return cat;
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		Optional<Product> prod = Optional.ofNullable(this.repository.getProductByName(name));
		return prod;
	}

	@Override
	public ProductOnSale getProductOnSaleById(Long id) {
		
		return this.repository.getProductOnSaleById(id);
	}

	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		
		Optional<DeliveryMethod> dm = Optional.ofNullable(this.repository.getDeliveryMethodByName(name));
		
		return dm;
	}

	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		Optional<CreditCardPayment> cp = Optional.ofNullable(this.repository.getCreditCardPayment(name));
		return cp;
	}

	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		Optional<OnDeliveryPayment> dp = Optional.ofNullable(this.repository.getOnDeliveryPayment(name));
		return dp;
	}

	@Override
	public Optional<Purchase> getPurchaseById(Long id) {
		Optional<Purchase> dp = Optional.ofNullable(this.repository.getPurchaseById(id));
		return dp;
	}
	
	private Date addOrSubtractDays (Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	@Override
	@Transactional
	public List<Purchase> getAllPurchasesMadeByUser(String username){
		User user = this.repository.getUserByEmail(username);
		System.out.println(user);
		if (user == null){
			System.out.println("No existe el user");
			return null;
		}else {
			return this.repository.getAllPurchasesByUser(user.getId());
		}
	}


	@Override
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
		return this.repository.getUsersSpendingMoreThanInPurchase(amount);
	}


	@Override
	public List<User> getUsersSpendingMoreThan(Float amount) {
		return this.repository.getUsersSpendingMoreThan(amount);
	}


	@Override
	public List<Provider> getTopNProvidersInPurchases(int n) {
		return this.repository.getTopNProvidersInPurchases(n);
	}


	@Override
	public List<Product> getTop3MoreExpensiveProducts() {
		return this.repository.getTop3MoreExpensiveProducts();
	}


	@Override
	public List<User> getTopNUsersMorePurchase(int n) {
		return this.repository.getTopNUsersMorePurchase(n);
	}


	@Override
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		return repository.getPurchasesInPeriod(startDate, endDate);
	}


	@Override
	public List<Product> getProductForCategory(Category category) {
		return repository.getProductForCategory(category);
	}


	@Override
	public List<Purchase> getPurchasesForProvider(Long cuit) {
		return repository.getPurchasesForProvider(cuit);
	}


	@Override
	public Product getBestSellingProduct() {
		return repository.getBestSellingProduct();
	}


	@Override
	public List<Product> getProductsOnePrice() {
		return repository.getProductsOnePrice();
	}


	@Override
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice() {
		
		return repository.getProductWithMoreThan20percentDiferenceInPrice();
	}


	@Override
	public Provider getProviderLessExpensiveProduct() {

		return repository.getProviderLessExpensiveProduct();
	}


	@Override
	public List<Provider> getProvidersDoNotSellOn(Date day) {
		return repository.getProvidersDoNotSellOn(day);
	}


	@Override
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		
		return repository.getSoldProductsOn(day);
	}


	@Override
	public List<Product> getProductsNotSold() {
		
		return repository.getProductsNotSold();
	}


	@Override
	public DeliveryMethod getMostUsedDeliveryMethod() {

		return repository.getMostUsedDeliveryMethod();
	}


	@Override
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod() {
		
		return repository.getMoreChangeOnDeliveryMethod();
	}


	@Override
	public Product getHeaviestProduct() {
		
		return repository.getHeaviestProduct();
	}


	@Override
	public Category getCategoryWithLessProducts() {
		
		return repository.getCategoryWithLessProducts();
	}
}

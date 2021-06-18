package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;


public class SpringDataMLService implements MLService{
	@Autowired
	CategoryRepository categoryR;
	@Autowired
	CreditCardPaymentRepository creditCardPaymentR;
	@Autowired
	DeliveryMethodRepository deliveryMethodR;
	@Autowired
	OnDeliveryPaymentRepository onDeliveryPaymentR;
	@Autowired
	PaymentMethodRepository paymentMethodR;
	@Autowired
	ProductRepository productR;
	@Autowired
	ProductOnSaleRepository productOnSaleR;
	@Autowired
	ProviderRepository providerR;
	@Autowired
	PurchaseRepository purchaseR;
	@Autowired
	UserRepository userR;
	
	private Date addOrSubtractDays (Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	@Override
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		User u = userR.findByEmail(username);
		if (u != null) {
			return userR.getAllPurchasesByUser(u.getId());
		}else {
			System.out.println("User does not exist.");
			return null;
		}
	}

	@Override
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
		return purchaseR.getUsersSpendingMoreThanInPurchase(amount);
		
	}

	@Override
	public List<User> getUsersSpendingMoreThan(Float amount) {
		return purchaseR.getUsersSpendingMoreThan(amount.doubleValue());
	}

	@Override
	public List<Provider> getTopNProvidersInPurchases(int n) {
		Pageable p = PageRequest.of(0,n);
		return purchaseR.getTopNProvidersInPurchases(p);
	}

	@Override
	public List<Product> getTop3MoreExpensiveProducts() {
		Pageable p = PageRequest.of(0,3);
		return productOnSaleR.getTop3MoreExpensiveProducts(p);
	}

	@Override
	public List<User> getTopNUsersMorePurchase(int n) {
		Pageable p = PageRequest.of(0,n);
		return userR.getTopNUsersMorePurchase(p);
	}

	@Override
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		return purchaseR.getPurchasesInPeriod(startDate, endDate);
		
	}

	@Override
	public List<Product> getProductForCategory(Category category) {
		return productR.getProductForCategory(category);
	}

	@Override
	public List<Purchase> getPurchasesForProvider(Long cuit) {
		return purchaseR.getPurchasesForProvider(cuit);
	}

	@Override
	public Product getBestSellingProduct() {
		Pageable p = PageRequest.of(0, 1);
		//return productR.getBestSellingProduct(p);
		return null;
	}

	@Override
	public List<Product> getProductsOnePrice() {
		return productR.getProductsOnePrice();
		
	}

	@Override
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice() {
		return productR.getProductWithMoreThan20percentDiferenceInPrice();
	}

	@Override
	public Provider getProviderLessExpensiveProduct() {
		Pageable p = PageRequest.of(0, 1);
		//return providerR.getProviderLessExpensiveProduct(p);
		return null;
	}

	@Override
	public List<Provider> getProvidersDoNotSellOn(Date day) {
		return providerR.getProvidersDoNotSellOn(day);
	}

	@Override
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		return productOnSaleR.getSoldProductsOn(day);
	}

	@Override
	public List<Product> getProductsNotSold() {
		return productR.getProductsNotSold();
	}

	@Override
	public DeliveryMethod getMostUsedDeliveryMethod() {
		Pageable p = PageRequest.of(0,1);
		//return deliveryMethodR.getMostUsedDeliveryMethod(p);
		return null;
	}

	@Override
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod() {
		Pageable p = PageRequest.of(0, 1);
		//return onDeliveryPaymentR.getMoreChangeOnDeliveryMethod(p);
		return null;
	}

	@Override
	public Product getHeaviestProduct() {
		Pageable p = new PageRequest(0,1);
		//return productR.getHeaviestProduct(p);
		return null;
	}

	@Override
	public Category getCategoryWithLessProducts() {
		Pageable p = PageRequest.of(0, 1);
		//return categoryR.getCategoryWithLessProducts(p);
		return null;
	}

	@Override
	public Category createCategory(String name) throws MLException {
		Category cat = new Category(name);
		return categoryR.save(cat);
	}

	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException {
		if(productR.existsByName(name) == false) {
		Product prod = new Product(weight, name, category);
		return productR.save(prod);
		}else {
			throw new MLException("Constraint Violation");
		}
	}

	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		if(userR.existsByEmail(email) == false) {
			User u = new User(email, password, fullname, dayOfBirth);
			return userR.save(u);
			}else {
				throw new MLException("Constraint Violation");
			}
	}

	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		if(providerR.existsByCuit(cuit) == false) {
			Provider prov = new Provider(name, cuit);
			return providerR.save(prov);
			}else {
				throw new MLException("Constraint Violation");
			}
	}

	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		DeliveryMethod delM = new DeliveryMethod(name, cost, startWeight, endWeight);
		return deliveryMethodR.save(delM);
	}

	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		System.out.println("------------------------------------------------------------------");
		System.out.println("***");
		boolean cp = creditCardPaymentR.existsByName(name);
		System.out.println("------------------------------------------------------------------");
		System.out.println(cp);
		if (cp == false) {
			CreditCardPayment credCP = new CreditCardPayment(name, brand, number, expiry, cvv, owner);
			CreditCardPayment returned = creditCardPaymentR.save(credCP);
			System.out.println("------------------------------------------------------------------");
			System.out.println(returned);
			
			return returned;}
		else {
			throw new MLException("Constraint Violation");
		}
	}

	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		OnDeliveryPayment ODP = new OnDeliveryPayment(name, promisedAmount);
		return onDeliveryPaymentR.save(ODP);
	}

	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate)
			throws MLException {
		List<ProductOnSale> productsOfProvider = product.getProductsOnSaleByProvider(provider);
		if (productsOfProvider.size() > 0) {
			ProductOnSale ps = productsOfProvider.get(productsOfProvider.size() - 1);
			if (ps.getInitialDate().after(initialDate)) {
				throw new MLException("Ya existe un precio para el producto con fecha de inicio de vigencia posterior a la fecha de inicio dada");
			}
			ps.setFinalDate(this.addOrSubtractDays(initialDate, -1));
			productOnSaleR.save(ps);
		}
		ProductOnSale productOnSale = new ProductOnSale(product,provider,price,initialDate);
		product.addProductOnsale(productOnSale);
		/*productR.save(product);*/
		return productOnSaleR.save(productOnSale);
		//return product.getProductsOnSale().get(product.getProductsOnSale().size() - 1);
	}

	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		if (deliveryMethod.checkShipping(productOnSale.getProduct().getWeight() * quantity)) {
			Purchase p = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
			Purchase purchase = purchaseR.save(p);
			return purchase;
		}else {
			throw new MLException("método de delivery no válido");
		}
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		Optional<User> user = Optional.ofNullable(userR.findByEmail(email));
		return user;
	}

	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		Optional<Provider> provider = Optional.ofNullable(providerR.findByCuit(cuit));
		return provider;
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		Optional<Category> cat = Optional.ofNullable(categoryR.findByName(name));
		return cat;
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		Optional<Product> prod = Optional.ofNullable(productR.findByName(name));
		return prod;
	}

	@Override
	public ProductOnSale getProductOnSaleById(Long id) {
		return productOnSaleR.findById(id).orElseGet(null);
	}

	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		return Optional.ofNullable(deliveryMethodR.findFirstByName(name));
	}

	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		return Optional.ofNullable(creditCardPaymentR.findByName(name));
	}

	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		return Optional.ofNullable(onDeliveryPaymentR.findByName(name));
	}

	@Override
	public Optional<Purchase> getPurchaseById(Long id) {
		return purchaseR.findById(id);
	}

}

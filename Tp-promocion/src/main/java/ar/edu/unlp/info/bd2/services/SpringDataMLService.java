package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.*;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import javax.inject.Inject;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SpringDataMLService implements MLService{
	
	@Inject
	private CategoryRepository categoryR;
	
	@Inject
	private UserRepository userR;
	
	@Inject
	private ProviderRepository providerR;
	
	@Inject
	private ProductRepository productR;
	
	@Inject
	private DeliveryMethodRepository deliveryMethodR;
	
	@Inject
	private CreditCardPaymentRepository creditCardPaymentR;

	@Inject
	private OnDeliveryPaymentRepository onDeliveryPaymentR;
	
	@Inject
	private ProductOnSaleRepository productOnSaleR;
		
	@Inject
	private PurchaseRepository purchaseR;
	

	
	private Date addOrSubtractDays (Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	public CategoryRepository getCategoryRepository() {
		return this.categoryR;
	}
	
	@Override
	public Category createCategory(String name) throws MLException {
		Category cat = new Category(name);
		return this.getCategoryRepository().save(cat);
	}
	
	
	@Override
	public Optional<Category> getCategoryByName(String name) {
		Optional<Category> cat = Optional.ofNullable(categoryR.findByName(name));
		return cat;
	}
	
	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		if(userR.findByEmail(email) == null) {
			User u = new User(email, password, fullname, dayOfBirth);
			return userR.save(u);
			}else {
				throw new MLException("Constraint Violation");
			}
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		Optional<User> user = Optional.ofNullable(userR.findByEmail(email));
		return user;
	}
	
	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		if(providerR.findByCuit(cuit) == null) {
			Provider prov = new Provider(name, cuit);
			return providerR.save(prov);
			}else {
				throw new MLException("Constraint Violation");
			}
	}
	
	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		Optional<Provider> provider = Optional.ofNullable(providerR.findByCuit(cuit));
		return provider;
	}
	
	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException {
		if(productR.findByName(name) == null) {
			Product prod = new Product(weight, name, category);
			return productR.save(prod);
		}else {
			throw new MLException("Constraint Violation");
		}
	}
	
	@Override
	public Optional<Product> getProductByName(String name) {
		Optional<Product> prod = Optional.ofNullable(productR.findByName(name));
		return prod;
	}
	
	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		DeliveryMethod delM = new DeliveryMethod(name, cost, startWeight, endWeight);
		return deliveryMethodR.save(delM);
	}
	
	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		return Optional.ofNullable(deliveryMethodR.findFirstByName(name));
	}
	
	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		if (creditCardPaymentR.findByName(name) == null) {
			CreditCardPayment credCP = new CreditCardPayment(name, brand, number, expiry, cvv, owner);
			CreditCardPayment returned = creditCardPaymentR.save(credCP);
			System.out.println(returned);
			
			return returned;}
		else {
			throw new MLException("Constraint Violation");
		}
	}
	
	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		return Optional.ofNullable(creditCardPaymentR.findByName(name));
	}
	
	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		OnDeliveryPayment ODP = new OnDeliveryPayment(name, promisedAmount);
		return onDeliveryPaymentR.save(ODP);
	}
	
	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		return Optional.ofNullable(onDeliveryPaymentR.findByName(name));
	}
	
	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate)
			throws MLException {
		Product producto = productR.findById(product.getId()).get();
		List<ProductOnSale> listPs = producto.getLastProductsOnSaleByProvider(provider);
		if (!listPs.isEmpty()) {
			ProductOnSale ps = listPs.get(0);
			if (ps != null) {
				if (ps.getInitialDate().after(initialDate)) {
					throw new MLException("Ya existe un precio para el producto con fecha de inicio de vigencia posterior a la fecha de inicio dada");
				}
				ps.setFinalDate(this.addOrSubtractDays(initialDate, -1));
				producto.updateProductOnsale(ps);
				productOnSaleR.save(ps);
			}
		}
		ProductOnSale productOnSale = new ProductOnSale(provider,price,initialDate);
		productOnSaleR.save(productOnSale);
		producto.addProductOnsale(productOnSale);
		productR.save(producto);
		product.setProductsOnSale(producto.getProductsOnSale());
		return productOnSale;
	}
	
		
	@Override
	public ProductOnSale getProductOnSaleById(String id) {
			return productOnSaleR.findById(id).orElseGet(null);
		}
	
	@Override
	public Purchase createPurchase(ProductOnSale productOnSale,Product product, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		
		if (deliveryMethod.checkShipping(product.getWeight() * quantity)) {
			Purchase p = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
			Purchase purchase = purchaseR.save(p);
			return purchase;
		}else {
			throw new MLException("método de delivery no válido");
		}
	}
	
	@Override
	public Optional<Purchase> getPurchaseById(String id) {
		return purchaseR.findById(id);
	}
		

	
	
	@Override
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		User u = userR.findByEmail(username);
		if (u != null) {
			return purchaseR.getAllPurchasesMadeByUser(username);
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
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		return purchaseR.getPurchasesInPeriod(startDate, endDate);
		
	}
	

	
}
package ar.edu.unlp.info.bd2.services;

import java.util.Date;
import java.util.Optional;

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

public class MLServiceImpl implements MLService{

	private MLRepository repository;
	
	public MLServiceImpl(MLRepository repository) {
		this.repository = repository;
	}


	@Override
	public Category createCategory(String name) throws MLException {
		/*throw new MLException("ya existe la categoria con el nombre pepito"); */
		System.out.println("PUTOOOOOOOOOOOOOOOOOOOOOOOO");
		System.out.println(this.repository);
		Category cat = this.repository.getCategoryByName(name);
		System.out.println("PUTOOOOOOOOOOOOOOOOOOOOOOOO");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		User user = repository.getUserByEmail(email);
		if (user.equals(null)) {
			throw new MLException("ya existe un usuario con el email: %".format(email));
		}else {
			User usr = new User(email,password,fullname,dayOfBirth);
			repository.createUser(usr);
			/* al crear al usuario pero no retornarlo desde el repositorio, ya tiene id? cuando se le establece el id? */
			return usr;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate)
			throws MLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		Optional<Category> cat = Optional.ofNullable(this.repository.getCategoryByName(name));
		return cat;
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOnSale getProductOnSaleById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Purchase> getPurchaseById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

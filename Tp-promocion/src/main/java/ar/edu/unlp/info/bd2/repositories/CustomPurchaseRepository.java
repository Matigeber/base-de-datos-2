package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.User;

@Repository
public interface CustomPurchaseRepository {

	public List<User> getUsersSpendingMoreThanInPurchase(Float amount);
	
	public List<Purchase> getAllPurchasesMadeByUser(String username);
}

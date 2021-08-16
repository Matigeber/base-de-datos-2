package ar.edu.unlp.info.bd2.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import ar.edu.unlp.info.bd2.model.Category;

import ar.edu.unlp.info.bd2.model.DeliveryMethod;
import ar.edu.unlp.info.bd2.model.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.ProductOnSale;
import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.Provider;
import ar.edu.unlp.info.bd2.model.User;


public interface MLStatisticsService {
	
	/**
	 * @param username
	 * @return Una lista con todas las compras realizadas por el usuario con username <code>username</code>
	 */
	List<Purchase> getAllPurchasesMadeByUser(String username);
//
	/**
	 * @param amount
	 * @return una lista de los usuarios que han gastando más de <code>amount</code> en alguna compra en la plataforma
	 */
	List<User> getUsersSpendingMoreThanInPurchase(Float amount);
	
	/**
	 * @param startDate
	 * @param endDate
	 * @return una lista con las compras realizadas entre dos fechas
	 */
	List <Purchase> getPurchasesInPeriod(Date startDate, Date endDate);
	
	
	
}

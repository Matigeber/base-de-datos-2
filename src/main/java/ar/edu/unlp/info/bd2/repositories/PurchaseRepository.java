package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Provider;
import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.User;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseRepository extends CrudRepository<Purchase, Long>{
	
	@Query("SELECT distinct cli "
			+ "FROM Purchase p join p.client as cli "
			+ "WHERE p.amount > :amount")
	List<User> getUsersSpendingMoreThanInPurchase(@Param("amount") Float amount);
	
	@Query("SELECT cli "
			+ "FROM Purchase p join p.client as cli "
			+ "GROUP BY cli "
			+ "HAVING sum(p.amount) > :amount")
	List<User> getUsersSpendingMoreThan(@Param("amount") Double amount);
	
	@Query("SELECT p " +
			"FROM Purchase ps join ps.productOnSale as pos join pos.provider as p "+
	        "GROUP BY p "+ 
	        "order by sum(ps.quantity) desc ")
	List<Provider> getTopNProvidersInPurchases(Pageable p);
	
	@Query("SELECT pur " +
			"FROM Purchase pur "+
			"WHERE pur.dateOfPurchase between :startDate and :endDate")
	public List<Purchase> getPurchasesInPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT pur "
			+ "FROM Purchase pur JOIN pur.productOnSale as pos JOIN pos.provider as prov "
			+ "WHERE prov.cuit = :cuit")
	public List<Purchase> getPurchasesForProvider(@Param("cuit") Long cuit);
	
}

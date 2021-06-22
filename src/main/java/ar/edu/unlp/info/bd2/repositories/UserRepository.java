package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.User;

import ar.edu.unlp.info.bd2.model.Purchase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	boolean existsByEmail(String email);
	
	@Query("FROM Purchase WHERE user_id = :user_id")
	List<Purchase> getAllPurchasesByUser(@Param("user_id") long user_id);
	
	@Query("SELECT u " +
	        "FROM Purchase p JOIN p.client as u " +
	        "GROUP BY u "+ 
	        "order by count(*) desc ")
	public List<User> getTopNUsersMorePurchase(Pageable n);
}

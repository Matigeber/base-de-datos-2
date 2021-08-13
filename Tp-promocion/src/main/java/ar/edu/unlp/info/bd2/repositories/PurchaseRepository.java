package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.User;

@Repository
public interface PurchaseRepository extends ElasticsearchRepository<Purchase, String>{ 
	
	List<Purchase> findByClient(User user);

	@Query(" {"
			+ "  \"match\": {"
			+ "    \"client.email\": \"silviasez428@gmail.com\""
			+ "  }"
			+ "}"
			)
	List<Purchase> findByUser(User user);
	
}
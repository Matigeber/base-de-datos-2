package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Provider;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long>{
	
	Provider findByCuit(long cuit);
	boolean existsByCuit(long cuit);
	
	
	@Query("SELECT prov "
			+ "FROM ProductOnSale pos JOIN pos.provider as prov "
			+ "WHERE pos.finalDate = null "
			+ "GROUP BY prov "
			+ "ORDER BY min(pos.price)")
	public List<Provider> getProviderLessExpensiveProduct(Pageable p);
	
	@Query("select prov"
			+ " from Provider prov"
			+ " where prov not in ("
				+ " select p"
				+ " from Purchase pur JOIN pur.productOnSale as pos JOIN pos.provider as p"
				+ " where pur.dateOfPurchase = :day)")
	public List<Provider> getProvidersDoNotSellOn(@Param("day")Date day);
}

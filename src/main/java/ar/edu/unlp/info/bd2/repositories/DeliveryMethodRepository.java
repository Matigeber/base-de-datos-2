package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.DeliveryMethod;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DeliveryMethodRepository extends CrudRepository<DeliveryMethod, Long>{
	
	DeliveryMethod findByName(String name);
	DeliveryMethod findFirstByName(String name);
	/*
	@Query("select dm"
			+ " from Purchase p JOIN p.deliveryMethod as dm"
			+ " group by dm"
			+ " order by count(p) DESC")
public DeliveryMethod getMostUsedDeliveryMethod(Pageable p);*/
}

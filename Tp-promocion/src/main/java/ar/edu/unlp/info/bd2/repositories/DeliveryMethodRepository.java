package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;
import ar.edu.unlp.info.bd2.model.DeliveryMethod;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface DeliveryMethodRepository extends ElasticsearchRepository<DeliveryMethod, String>{

	DeliveryMethod findByName(String name);
	DeliveryMethod findFirstByName(String name);
}

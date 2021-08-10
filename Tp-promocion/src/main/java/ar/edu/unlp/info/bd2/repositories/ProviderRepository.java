package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;
import ar.edu.unlp.info.bd2.model.Provider;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface ProviderRepository extends ElasticsearchRepository<Provider,String>{
	
	Provider findByCuit(long cuit);
	
}

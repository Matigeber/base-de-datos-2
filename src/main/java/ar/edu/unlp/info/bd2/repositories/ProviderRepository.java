package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long>{
	
	Provider findByCuit(long cuit);
	boolean existsByCuit(long cuit);
}

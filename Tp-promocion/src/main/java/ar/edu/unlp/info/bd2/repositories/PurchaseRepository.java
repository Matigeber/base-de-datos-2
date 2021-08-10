package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.Purchase;

@Repository
public interface PurchaseRepository extends ElasticsearchRepository<Purchase, String>{ 

}

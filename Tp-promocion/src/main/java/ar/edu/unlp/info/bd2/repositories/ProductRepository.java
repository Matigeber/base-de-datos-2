package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Category;
import ar.edu.unlp.info.bd2.model.Product;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long>{
	
	Product findByName(String name);
	boolean existsByName(String name);
	
	List<Product> findByCategory(Category category);

}

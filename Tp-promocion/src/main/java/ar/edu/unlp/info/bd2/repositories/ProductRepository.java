package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Category;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.ProductOnSale;
import ar.edu.unlp.info.bd2.model.Provider;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String>{
	
	Product findByName(String name);
	boolean existsByName(String name);
	
	List<Product> findByCategory(Category category);
	
	
	@Query("{"
				  +"\"bool\": {"
				    +"\"must\": ["
				      +"{\"match\": {"
				        +"\"id\": \"?1\""
				      +"}},"
				      +"{\"nested\": {"
						+"\"path\": \"productsOnSale\","
						+"\"query\" : {"
						  +"\"bool\" : {"
						 		+"\"must\": ["
						 				+"{ \"match\" : {\"productsOnSale.provider.id\": \"?0\"}}"
						 		+"],"
						 		+"\"must_not\": ["
						 		  +"{ \"exists\": {\"field\": \"productsOnSale.finalDate\"}}"
						 		+"]"		
						 +"}"
						+"}"
					+"}}"
				   +"]"
				 +"}"
			+"}")
		List<Product> getLastProductOnSaleByProvider (String prov, String id);
	

}

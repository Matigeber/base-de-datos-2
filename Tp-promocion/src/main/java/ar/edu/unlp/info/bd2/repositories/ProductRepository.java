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
public interface ProductRepository extends ElasticsearchRepository<Product, Long>{
	
	Product findByName(String name);
	boolean existsByName(String name);
	
	List<Product> findByCategory(Category category);
	
	/*
	@Query("{"
			+ "\"query\": {"
			+ 	"\"nested\": {"
			+ 		"\"path\": \"productsOnSale\" ,"
			+ 		"\"query\" : {"
			+ 				"\"bool\" : {"
			+ 						"\"must\": ["
			+ 							"{ \"match\" : {\"productsOnSale.provider\": \"?0\"}, {\"productsOnSale.finalDate\": null} }"
			+ 						"]"
			+ 					"}"
			+ 			"}"
			+ "		} "
			+ 	"}"
			+ "}")
	List<Product> getLastProductOnSaleByProvider (Provider prov, Long id);
	*/
	/*
	@Query("{"
		+ "\"query\": {"
			  +"\"bool\": {"
			    +"\"must\": ["
			      +"{\"match\": {"
			        +"\"id\": 0"
			      +"}},"
			      +"{\"nested\": {"
					+"\"path\": \"productsOnSale\","
					+"\"query\" : {"
					  +"\"bool\" : {"
					 		+"\"must\": ["
					 				+"{ \"match\" : {\"productsOnSale.provider.id\": 0}}"
					 		+"],"
					 		+"\"must_not\": ["
					 		  +"{ \"exists\": {\"field\": \"finalDate\"}}"
					 		+"]"		
					 +"}"
					+"}"
				+"}}"
			   +"]"
			 +"}"
		+"}"
		+"}")
	List<Product> getLastProductOnSaleByProvider (Long prov, Long id);
	*/
	
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
						 		  +"{ \"exists\": {\"field\": \"finalDate\"}}"
						 		+"]"		
						 +"}"
						+"}"
					+"}}"
				   +"]"
				 +"}"
			+"}")
		List<Product> getLastProductOnSaleByProvider (Long prov, Long id);
	

}

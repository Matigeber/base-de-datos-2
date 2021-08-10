package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.ProductOnSale;
import ar.edu.unlp.info.bd2.model.Provider;

import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductOnSaleRepository extends ElasticsearchRepository<ProductOnSale, String>{
	
	@Query("SELECT pos FROM ProductOnSale pos WHERE product= :prod and provider = :prov and finalDate = null")
	public ProductOnSale getLast(@Param("prov") Provider prov, @Param("prod") Product prod);
	
	public List<ProductOnSale> findByProviderAndFinalDate (Provider prov, String nulo);
	
	//public void updateFinalDate(ProductOnSale pos, Date date);

}

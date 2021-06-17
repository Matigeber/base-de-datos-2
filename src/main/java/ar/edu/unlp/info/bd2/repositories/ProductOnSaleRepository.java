package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.ProductOnSale;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductOnSaleRepository extends CrudRepository<ProductOnSale, Long>{

	@Query("SELECT p " +
		        "FROM ProductOnSale pos JOIN pos.product as p " +
				"WHERE pos.finalDate is null " +
		        "GROUP BY p "+ 
		        "order by max(pos.price) desc ")
	List<Product> getTop3MoreExpensiveProducts(Pageable p);
	
	@Query("select distinct pos "
			+ "from Purchase p JOIN p.productOnSale as pos "
			+ "where p.dateOfPurchase = :day")
	public List<ProductOnSale> getSoldProductsOn(@Param("day") Date day);
}

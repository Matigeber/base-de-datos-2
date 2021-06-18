package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	Product findByName(String name);
	boolean existsByName(String name);
	

	@Query("SELECT p "+
						"FROM Product p "+
						"WHERE p.category = :category")
	public List<Product> getProductForCategory(@Param("category") Category category);
	/*
	@Query("SELECT prod "
			+ "FROM Purchase p JOIN p.productOnSale as pos JOIN pos.product as prod "
			+ "GROUP BY prod "
			+ "ORDER BY count(p) desc")
	public Product getBestSellingProduct(Pageable p);
	*/
	@Query("SELECT prod " 
			+ "FROM ProductOnSale pos JOIN pos.product as prod "
			+ "GROUP BY prod "
			+ "HAVING count(pos) = 1")
	public List<Product> getProductsOnePrice();
	
	@Query("SELECT prod "
			+"FROM ProductOnSale pos JOIN pos.provider as prov JOIN pos.product as prod "
			+"WHERE pos.finalDate = null "
			+"GROUP BY prod "
			+"HAVING ((max(pos.price) - min(pos.price)) / max(pos.price)) > 0.20")
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice();
	
	@Query("Select prod"
			+ " From Product prod"
			+ " where prod not in ("
				+ " select prod"
				+ " from Purchase p join p.productOnSale as pos join pos.product as prod )")
	public List<Product> getProductsNotSold();
	/*
	@Query("select p"
			+ " from Product p"
			+ " order by p.weight DESC")
	public Product getHeaviestProduct(Pageable p);*/
}

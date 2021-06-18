package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;



public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	
	Category findByName(String name);
	/*
	@Query("select c"
			+ " from Product p JOIN p.category as c"
			+ " group by c"
			+ " order by count(p) ASC")
public Category getCategoryWithLessProducts(Pageable p);*/
	
	
}

package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	
	Category findByName(String name);
	
}
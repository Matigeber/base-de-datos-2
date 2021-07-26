package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


@Repository
public interface CategoryRepository extends ElasticsearchRepository<Category, Long>{
	
	
	Category findByName(String name);
/*	
	@Query("select c"
			+ " from Product p JOIN p.category as c"
			+ " group by c"
			+ " order by count(p) ASC")
public List<Category> getCategoryWithLessProducts(Pageable p);
*/	
	
}
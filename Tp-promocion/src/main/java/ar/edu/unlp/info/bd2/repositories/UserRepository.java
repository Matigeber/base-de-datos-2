package ar.edu.unlp.info.bd2.repositories;
import org.springframework.stereotype.Repository;
import ar.edu.unlp.info.bd2.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {
	
	User findByEmail(String email);
	boolean existsByEmail(String email);
	

}

package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;
import ar.edu.unlp.info.bd2.model.CreditCardPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface CreditCardPaymentRepository extends ElasticsearchRepository<CreditCardPayment, Long>{

	CreditCardPayment findByName(String name);
}

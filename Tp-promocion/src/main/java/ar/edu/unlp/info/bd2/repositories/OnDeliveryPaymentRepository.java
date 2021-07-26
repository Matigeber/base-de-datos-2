package ar.edu.unlp.info.bd2.repositories;

import org.springframework.stereotype.Repository;
import ar.edu.unlp.info.bd2.model.OnDeliveryPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OnDeliveryPaymentRepository extends ElasticsearchRepository<OnDeliveryPayment, Long>{

	OnDeliveryPayment findByName(String name);
}

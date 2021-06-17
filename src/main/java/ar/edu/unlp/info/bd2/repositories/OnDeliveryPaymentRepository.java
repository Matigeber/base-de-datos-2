package ar.edu.unlp.info.bd2.repositories;
import ar.edu.unlp.info.bd2.model.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.model.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OnDeliveryPaymentRepository extends CrudRepository<OnDeliveryPayment, Long>{

	OnDeliveryPayment findByName(String name);
	
	@Query("select pm"
			+ " from Purchase p JOIN p.paymentMethod as pm"
			+ " where pm.class = OnDeliveryPayment"
			+ " order by (pm.promisedAmount - p.amount) DESC")
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod(Pageable p);
}

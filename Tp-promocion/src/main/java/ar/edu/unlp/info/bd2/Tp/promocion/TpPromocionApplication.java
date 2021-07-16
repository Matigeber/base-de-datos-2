package ar.edu.unlp.info.bd2.Tp.promocion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.MLException;

@SpringBootApplication
public class TpPromocionApplication {

	@Autowired
	static
	SpringDataMLService service;
	
	protected static SpringDataMLService getService() {
        return service;
    }
	
	public static void main(String[] args) throws MLException{
		SpringApplication.run(TpPromocionApplication.class, args);
		SpringDataMLService service = new SpringDataMLService();
		Category cat = service.createCategory("Categoria 1");
		System.out.println(cat);
		
	}

}

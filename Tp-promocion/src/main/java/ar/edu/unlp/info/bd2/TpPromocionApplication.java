package ar.edu.unlp.info.bd2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.MLException;

@SpringBootApplication
public class TpPromocionApplication {


	public static void main(String[] args) throws MLException{
		SpringApplication.run(TpPromocionApplication.class, args);
		/*
		SpringDataMLService service = new SpringDataMLService();
		Category cat = service.createCategory("Categoria 1");
		System.out.println(cat);
		*/

	}

}

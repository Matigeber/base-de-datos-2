package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.MLException;

import java.util.Date;
import java.util.Optional;


public interface MLService extends MLStatisticsService{

	/**
	 * Crea y devuelve una nueva Catogoria
	 * @param name nombre del producto a ser creado
	 * @return la categoria creada
	 * @throws MLException
	 */
	Category createCategory(String name) throws MLException;




}

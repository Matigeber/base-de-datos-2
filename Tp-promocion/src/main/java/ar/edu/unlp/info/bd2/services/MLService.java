package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.MLException;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;


public interface MLService extends MLStatisticsService{


	public Category createCategory(String name) throws MLException;

	
	Optional<Category> getCategoryByName(String name);
	
	/**
	 *
	 * @param email email del usuario con el cual ingresa al sitio
	 * @param fullname nombre y apellido del usuario
	 * @param password clave con la que el usuario ingresa al sitio
	 * @param dayOfBirth fecha de nacimiento del usuario
	 * @return el usuario creado
	 * @throws MLException
	 */
	User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException;
	
	/**
	 *
	 * @param email email del usuario
	 * @return
	 */
	Optional<User> getUserByEmail(String email);
	
	/**
	 *
	 * @param name nombre del proveedor
	 * @param cuit cuil del proveedor
	 * @return el proveedor creado
	 * @throws MLException
	 */
	Provider createProvider(String name, Long cuit) throws MLException;
	
	/**
	 *
	 * @param cuit cuit del proveedor
	 * @return
	 */
	Optional<Provider> getProviderByCuit(long cuit);
	


}

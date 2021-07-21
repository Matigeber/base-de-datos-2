package ar.edu.unlp.info.bd2.services;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.MLException;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;


public interface MLService extends MLStatisticsService{


	public Category createCategory(String name) throws MLException;

	/*
	Optional<Category> getCategoryByName(String name);
	*/


}

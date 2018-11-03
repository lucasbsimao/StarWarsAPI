package br.com.b2w.services.interfaces;

import java.util.List;

import br.com.b2w.entities.Planet;

public interface IPlanetService extends IGenericService<Planet>{
	
	/**
	 * Find the entity that matches the name parameter
	 * @param name
	 * @return The entity with its values
	 */
	public List<Planet> findByName(String name);

}

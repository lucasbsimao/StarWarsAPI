package br.com.b2w.services.interfaces;

import java.util.List;

import javax.validation.Valid;

public interface IGenericService<T> {
	
	/**
	 * Create the entity
	 * @param entity
	 * @return The entity with an Id attached
	 */
	public T create(@Valid T entity);
	
	/**
	 * Delete the entity that matches the id parameter
	 * @param id
	 */
	public void delete(String id);

	/**
	 * Get all data that corresponds to its type
	 * @return A list of entities
	 */
	public List<T> getAll();
	
	/**
	 * Find the entity that matches the id parameter
	 * @param id
	 * @return The entity with its values
	 */
	public T findById(String id); 
}

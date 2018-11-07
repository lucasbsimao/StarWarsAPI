package br.com.b2w.entities;

import java.io.Serializable;

/**
 * Base class used for all entities
 * 
 * @author Lucas Borsatto
 */
public interface IGenericEntity extends Serializable{
	
	/**
	 * Get the entity Id
	 * @return String id
	 */
	public String getId();
	
	/**
	 * Set the entity Id
	 * @param id must not be null
	 */
	public void setId(String id);
}

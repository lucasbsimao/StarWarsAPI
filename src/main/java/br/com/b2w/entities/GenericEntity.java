package br.com.b2w.entities;

import java.io.Serializable;


import org.springframework.data.annotation.Id;

/**
 * Base class used for all entities
 * 
 * @author Lucas Borsatto
 */
public class GenericEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	/**
	 * Get the entity Id
	 * @return String id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set the entity Id
	 * @param id must not be null
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GenericEntity)) {
			return false;
		}
		GenericEntity other = (GenericEntity) obj;
		return getId().equals(other.getId());
	}

}

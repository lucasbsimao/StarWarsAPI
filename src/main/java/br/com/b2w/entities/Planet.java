package br.com.b2w.entities;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

import br.com.b2w.entities.IGenericEntity;


@Document(collection = "planets")
public class Planet implements IGenericEntity{

	private static final long serialVersionUID = 1L;
	
	@Id
	@ApiModelProperty(hidden = true)
	private String id;

	@NotBlank(message = "Planet's name must be not null or empty")
	@JsonProperty(required = true)
	@ApiModelProperty(example="Yavin IV")
	private String name;
	
	@NotBlank(message = "Planet's climate must not be null or empty")
	@JsonProperty(required = true)
	@ApiModelProperty(example="temperate, tropical")
	private String climate;
	
	@NotBlank(message = "Planet's terrain must not be null or empty")
	@JsonProperty(required = true)
	@ApiModelProperty(example="jungle, rainforests")
	private String terrain;
	
	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ApiModelProperty(accessMode=AccessMode.READ_ONLY)
	private Integer appearances;
	
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ApiModelProperty(hidden = true)
	private List<String> films;
	
	public Planet() {
		super();
	}
	
	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get the Planet name
	 * @return name that must not be blank
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the Planet name
	 * @param name that must not be blank
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the Planet climate
	 * @return climate that must not be blank
	 */
	public String getClimate() {
		return climate;
	}
	
	/**
	 * Set the Planet climate
	 * @param climate that must not be blank
	 */
	public void setClimate(String climate) {
		this.climate = climate;
	}
	
	/**
	 * Get the Planet terrain
	 * @return terrain that must not be blank
	 */
	public String getTerrain() {
		return terrain;
	}
	
	/**
	 * Set the Planet terrain
	 * @param terrain that must not be blank
	 */
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	/**
	 * Get the Planet appearances in films, deserializable only 
	 * @return number of appearances
	 */
	public Integer getAppearances() {
		if(appearances == null)
			return 0;
		return appearances;
	}

	/**
	 * Set the Planet appearances in films, deserializable only 
	 * @param number of appearances
	 */
	public void setAppearances(Integer appearances) {
		this.appearances = appearances;
	}
	
	/**
	 * Get a list of movies in which it appeared, serializable only 
	 * @return String list of movies
	 */
	public List<String> getFilms() {
		return films;
	}

	/**
	 * Set a list of movies in which it appeared, serializable only 
	 * @param String list of movies
	 */
	public void setFilms(List<String> films) {
		this.films = films;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }

        if (!Planet.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        
        Planet planet = (Planet)obj;
        if(!planet.getName().equals(this.getName()))
        	return false;
        
        if(!planet.getClimate().equals(this.getClimate()))
        	return false;
        
        if(!planet.getTerrain().equals(this.getTerrain()))
        	return false;
        
		return true;
		
	}
	
	@Override
	public String toString() {
		return String.format(
                "Planet[id=%s, name='%s', climate='%s', terrain='%s']",
                this.getId(), name, climate, terrain);
	}
}

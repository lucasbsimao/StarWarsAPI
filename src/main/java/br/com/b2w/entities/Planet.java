package br.com.b2w.entities;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "planets")
public class Planet implements IGenericEntity{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@NotBlank(message = "Planet's name must be not null or empty")
	@JsonProperty(required = true)
	private String name;
	
	@NotBlank(message = "Planet's climate must not be null or empty")
	@JsonProperty(required = true)
	private String climate;
	
	@NotBlank(message = "Planet's terrain must not be null or empty")
	@JsonProperty(required = true)
	private String terrain;
	
	public Planet() {
		super();
	}
	
	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClimate() {
		return climate;
	}
	
	public void setClimate(String climate) {
		this.climate = climate;
	}
	
	public String getTerrain() {
		return terrain;
	}
	
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	@Override
	public String toString() {
		return String.format(
                "Planet[id=%s, name='%s', climate='%s', terrain='%s']",
                this.getId(), name, climate, terrain);
	}
}

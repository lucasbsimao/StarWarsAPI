package br.com.b2w.repositories;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2w.entities.Planet;

public interface StarWarsRepository extends MongoRepository<Planet, String> {
	public Planet findByName(String name);
}

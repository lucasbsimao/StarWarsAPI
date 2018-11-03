package br.com.b2w.repositories;

import java.util.List;

import br.com.b2w.entities.Planet;

public interface PlanetRepository extends GenericRepository<Planet> {
	public List<Planet> findByName(String name);
}

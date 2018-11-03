package br.com.b2w.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.StarWarsRepository;
import br.com.b2w.services.interfaces.IService;

@Service
public class PlanetService implements IService<Planet>{

	@Autowired
	StarWarsRepository starWarsRepository;
	
	@Override
	public Planet create(Planet entity) {
		return starWarsRepository.save(entity);
	}

	@Override
	public void delete(String id) {
		starWarsRepository.deleteById(id);
	}

	@Override
	public Planet findByName(String name) {
		return starWarsRepository.findByName(name);
	}

	@Override
	public List<Planet> getAll() {
		return starWarsRepository.findAll();
	}

	@Override
	public Planet findById(String id) {
		Planet planet = starWarsRepository.findById(id).orElse(null);
		return planet;
	}

}

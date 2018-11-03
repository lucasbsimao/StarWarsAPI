package br.com.b2w.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;

@Service("planetService")
public class PlanetServiceImpl extends GenericService<Planet,PlanetRepository> implements IPlanetService{
	
	@Override
	public List<Planet> findByName(String name) {
		return starWarsRepository.findByName(name);
	}

}

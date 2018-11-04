package br.com.b2w.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.b2w.entities.Planet;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;

@Service("planetService")
public class PlanetServiceImpl extends GenericService<Planet,PlanetRepository> implements IPlanetService{
	
	@Override
	public List<Planet> findByName(String name) {
		List<Planet> listPlanets = starWarsRepository.findByName(name);
		
		if(listPlanets == null || listPlanets.isEmpty())
			throw new NotFoundEntityException("Entity of name " + name + " not found.");
		
		return listPlanets;
	}

}

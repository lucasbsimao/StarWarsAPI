package br.com.b2w.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.entities.Planet;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;

@Service("planetService")
public class PlanetServiceImpl extends GenericServiceImpl<Planet,PlanetRepository> implements IPlanetService{
	
	@Autowired
	private ClientSwApiService clientSwApi;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Planet create(@Valid Planet entity) {
		Planet planet = super.create(entity);
		
		List<Planet> resultListPlanets = clientSwApi.getAllPlanets();
		this.insertPlanetAppearances(planet,resultListPlanets);
		
		return planet;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Planet> getAll() {
		List<Planet> listPlanets = super.genericRepository.findAll();
		
		this.findPlanetsApperances(listPlanets);
		
		return listPlanets;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Planet findById(@NotBlank String id) {
		Planet planet = genericRepository.findById(id).orElse(null);
		
		List<Planet> resultListPlanets = clientSwApi.getAllPlanets();
		this.insertPlanetAppearances(planet,resultListPlanets);
		
		return planet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Planet> findByName(String name) {
		List<Planet> listPlanets = genericRepository.findByName(name);
		
		this.findPlanetsApperances(listPlanets);
		
		if(listPlanets == null || listPlanets.isEmpty())
			throw new NotFoundEntityException("Entity of name " + name + " not found.");
		
		return listPlanets;
	}
	
	private void insertPlanetAppearances(Planet planet,List<Planet> resultListPlanets) {
		resultListPlanets.forEach(p -> {
			if(planet.equals(p))
				planet.setAppearances(p.getAppearances());
		});
	}
	
	private void findPlanetsApperances(List<Planet> listPlanets) {
		List<Planet> resultListPlanets = clientSwApi.getAllPlanets();
		listPlanets.forEach(p -> {
			this.insertPlanetAppearances(p,resultListPlanets);
		});
	}
}

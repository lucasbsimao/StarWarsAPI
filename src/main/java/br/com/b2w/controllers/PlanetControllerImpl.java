package br.com.b2w.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.controllers.interfaces.IPlanetController;
import br.com.b2w.entities.Planet;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.services.interfaces.IPlanetService;

@RestController
public class PlanetControllerImpl extends GenericController<Planet, IPlanetService> implements IPlanetController{
	
	@Override
	//@Async
	public ResponseEntity<List<Planet>> findByName(@RequestParam(value = "name") String name) {
		List<Planet> listPlanets = genericService.findByName(name);
		
		if(listPlanets == null)
			throw new NotFoundEntityException("Entity of name " + name + " not found.");
		
		return new ResponseEntity<List<Planet>>(listPlanets, HttpStatus.OK);
	}
}

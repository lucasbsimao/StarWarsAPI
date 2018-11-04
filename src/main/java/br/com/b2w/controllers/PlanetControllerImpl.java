package br.com.b2w.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.entities.Planet;
import br.com.b2w.services.interfaces.IPlanetService;

@RestController
@RequestMapping(value="/planets")
public class PlanetControllerImpl extends GenericController<Planet, IPlanetService>{
	
	@GetMapping(value = "/getByName")
	public ResponseEntity<List<Planet>> getByName(@RequestParam(value = "name") String name) {
		List<Planet> listPlanets = genericService.findByName(name);
		
		if(listPlanets == null)
			return new ResponseEntity<List<Planet>>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Planet>>(listPlanets, HttpStatus.OK);
	}
}

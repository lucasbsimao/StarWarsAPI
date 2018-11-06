package br.com.b2w.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.entities.Planet;

@RequestMapping(value="/planets")
public interface IPlanetController extends IGenericController<Planet>{
	
	@GetMapping(value = "/findByName")
	public ResponseEntity<List<Planet>> findByName(@RequestParam(value = "name") String name);
}

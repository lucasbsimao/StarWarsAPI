package br.com.b2w.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.b2w.entities.Planet;

@RequestMapping(value="/planets")
public interface IPlanetController extends IGenericController<Planet>{
	
	/**
	 * Find a list of entities T which matches name text
	 * @param name
	 * @return The T entity with an ok response (200) accordingly to <a href="https://tools.ietf.org/html/rfc7231#section-4.3">HTTP</a>
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<List<Planet>> findByName(@PathVariable(value = "name") String name);
}

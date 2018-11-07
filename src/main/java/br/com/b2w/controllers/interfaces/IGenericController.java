package br.com.b2w.controllers.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IGenericController<T> {
	
	/**
	 * Get the entity object by Id
	 * @param id
	 * @return T entity with an ok response (200) accordingly to <a href="https://tools.ietf.org/html/rfc7231#section-4.3">HTTP</a>
	 */
	@GetMapping(value = "/{id}")
	//@Async
	public ResponseEntity<T> getById(@PathVariable(value = "id") String id);
	
	/**
	 * Get all related entities that are present in repository 
	 * @return List of T entities itself with an ok response (200) accordingly to <a href="https://tools.ietf.org/html/rfc7231#section-4.3">HTTP</a>
	 */
	@GetMapping
	//@Async
	public ResponseEntity<List<T>> getAll();
	
	/**
	 * Create the related entity on repository 
	 * @param The entity T with an ok response (200) accordingly to <a href="https://tools.ietf.org/html/rfc7231#section-4.3">HTTP</a>. 
	 * 		Pay attention to validation
	 */
	@PostMapping
	//@Async
	public ResponseEntity<T> create(@Valid @RequestBody T entity);
	
	/**
	 * Deletes the T entity from the repository
	 * @param id
	 * @return Empty response (204), accordingly to <a href="https://tools.ietf.org/html/rfc7231#section-4.3">HTTP</a>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id);
}

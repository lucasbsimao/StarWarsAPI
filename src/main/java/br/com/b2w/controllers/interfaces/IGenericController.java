package br.com.b2w.controllers.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public interface IGenericController<T> {
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<T> getById(@PathVariable(value = "id") String id);
	
	@GetMapping
	public ResponseEntity<List<T>> getAll();
	
	@PostMapping
	public ResponseEntity<T> create(@Valid @RequestBody T entity);
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id);
}

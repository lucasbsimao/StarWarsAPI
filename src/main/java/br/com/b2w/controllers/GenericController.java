package br.com.b2w.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.b2w.controllers.interfaces.IGenericController;
import br.com.b2w.entities.GenericEntity;
import br.com.b2w.exceptions.NotCreatedEntityException;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.services.interfaces.IGenericService;

public abstract class GenericController<T extends GenericEntity, S extends IGenericService<T>> implements IGenericController{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(GenericController.class);
	
	@Autowired
	protected S genericService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<T> getById(@PathVariable(value = "id") String id) {
		T entity = genericService.findById(id);
		
		if(entity == null)
			throw new NotFoundEntityException("Entity of id " + id + " not found.");
		
		return new ResponseEntity<T>(entity,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<T>> getAll() {
		
		List<T> listEntity = genericService.getAll();
		
		if(listEntity.isEmpty()) 
			throw new NotFoundEntityException("No entity in database yet.");
		
		return new ResponseEntity<List<T>>(listEntity,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<T> create(@Valid @RequestBody T entity) {
		T bdEntity = genericService.create(entity);
		
		if(bdEntity.getId() == null || bdEntity.getId().equals("")) {
			throw new NotCreatedEntityException("It was not possible to create entity. Please contact us!");
		}
		
		return new ResponseEntity<T>(bdEntity,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
		T entity = genericService.findById(id);
		
		if(entity == null)
			throw new NotFoundEntityException("Entity of id " + id + " was not in database.");
		else
			return new ResponseEntity<Void>(HttpStatus.GONE);
	}
	
	
}

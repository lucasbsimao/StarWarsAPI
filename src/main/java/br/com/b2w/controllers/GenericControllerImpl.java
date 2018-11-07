package br.com.b2w.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.b2w.controllers.interfaces.IGenericController;
import br.com.b2w.entities.IGenericEntity;
import br.com.b2w.exceptions.NotCreatedEntityException;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.services.interfaces.IGenericService;

public class GenericControllerImpl<T extends IGenericEntity, S extends IGenericService<T>> implements IGenericController<T>{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(GenericControllerImpl.class);
	
	@Autowired
	protected S genericService;
	
	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<T> getById(@PathVariable(value = "id") String id) {
		T entity = genericService.findById(id);
		
		if(entity == null)
			throw new NotFoundEntityException("Entity of id " + id + " not found.");
		
		return new ResponseEntity<T>(entity,HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<List<T>> getAll() {
		
		List<T> listEntity = genericService.getAll();
		
		if(listEntity.isEmpty()) 
			throw new NotFoundEntityException("No entity in database yet.");
		
		return new ResponseEntity<List<T>>(listEntity,HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<T> create(@Valid @RequestBody T entity) {
		T bdEntity = genericService.create(entity);
		
		if(bdEntity == null || bdEntity.getId() == null || bdEntity.getId().equals("")) {
			throw new NotCreatedEntityException("It was not possible to create entity. Please contact us!");
		}
		
		return new ResponseEntity<T>(bdEntity,HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
		T entity = genericService.findById(id);
		
		if(entity == null) {
			throw new NotFoundEntityException("Entity of id " + id + " was not in database.");
		}else {
			genericService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}

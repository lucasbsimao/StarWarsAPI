package br.com.b2w.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.b2w.entities.IGenericEntity;
import br.com.b2w.repositories.GenericRepository;
import br.com.b2w.services.interfaces.IGenericService;

@Validated
public class GenericService<T extends IGenericEntity, R extends GenericRepository<T>> implements IGenericService<T>{
	
	@Autowired
	R starWarsRepository;
	
	@Override
	public T create(@Valid T entity) {
		return starWarsRepository.save(entity);
	}

	@Override
	public void delete(@NotBlank String id) {
		starWarsRepository.deleteById(id);
	}
	
	@Override
	public List<T> getAll() {
		return starWarsRepository.findAll();
	}

	@Override
	public T findById(@NotBlank String id) {
		T entity = starWarsRepository.findById(id).orElse(null);
		return entity;
	}

}

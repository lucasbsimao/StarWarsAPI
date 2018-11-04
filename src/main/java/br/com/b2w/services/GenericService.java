package br.com.b2w.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import br.com.b2w.entities.GenericEntity;
import br.com.b2w.repositories.GenericRepository;
import br.com.b2w.services.interfaces.IGenericService;

@Validated
public class GenericService<T extends GenericEntity, R extends GenericRepository<T>> implements IGenericService<T>{
	
	@Autowired
	R starWarsRepository;
	
	@Override
	public T create(@Valid T entity) {
		return starWarsRepository.insert(entity);
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

package br.com.b2w.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.b2w.entities.GenericEntity;
import br.com.b2w.repositories.GenericRepository;
import br.com.b2w.services.interfaces.IGenericService;

public class GenericService<T extends GenericEntity, R extends GenericRepository<T>> implements IGenericService<T>{
	
	@Autowired
	R starWarsRepository;
	
	@Override
	public T create(T entity) {
		return starWarsRepository.save(entity);
	}

	@Override
	public void delete(String id) {
		starWarsRepository.deleteById(id);
	}
	
	@Override
	public List<T> getAll() {
		return starWarsRepository.findAll();
	}

	@Override
	public T findById(String id) {
		T entity = starWarsRepository.findById(id).orElse(null);
		return entity;
	}

}

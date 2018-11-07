package br.com.b2w.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import br.com.b2w.entities.IGenericEntity;
import br.com.b2w.repositories.GenericRepository;
import br.com.b2w.services.interfaces.IGenericService;

@Validated
public class GenericServiceImpl<T extends IGenericEntity, R extends GenericRepository<T>> implements IGenericService<T>{
	
	@Autowired
	R genericRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T create(@Valid T entity) {
		return genericRepository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(@NotBlank String id) {
		genericRepository.deleteById(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> getAll() {
		return genericRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findById(@NotBlank String id) {
		T entity = genericRepository.findById(id).orElse(null);
		return entity;
	}

}

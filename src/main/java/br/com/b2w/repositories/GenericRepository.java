package br.com.b2w.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2w.entities.IGenericEntity;

public interface GenericRepository<T extends IGenericEntity> extends MongoRepository<T,String> {
}

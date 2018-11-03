package br.com.b2w.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2w.entities.GenericEntity;

public interface GenericRepository<T extends GenericEntity> extends MongoRepository<T,String> {
}

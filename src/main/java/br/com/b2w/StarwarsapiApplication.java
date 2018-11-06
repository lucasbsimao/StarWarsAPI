package br.com.b2w;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.GenericRepository;

@SpringBootApplication
public class StarwarsapiApplication{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(StarwarsapiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StarwarsapiApplication.class, args);
	}
}

package br.com.b2w;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import br.com.b2w.entities.Planet;
import br.com.b2w.repository.StarWarsRepository;

@SpringBootApplication
public class StarwarsapiApplication implements CommandLineRunner{

	@Autowired
	private StarWarsRepository starWarsRepository;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(StarwarsapiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StarwarsapiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		LOGGER.info("Initializing MongoDB with Star Wars planets...");
		starWarsRepository.deleteAll();

		// save a couple of planets
		starWarsRepository.save(new Planet("Alderaan", "temperate", "grasslands, mountains"));
		starWarsRepository.save(new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests"));
		starWarsRepository.save(new Planet("Hoth", "frozen, tropical", "tundra, ice caves, mountain ranges"));
		starWarsRepository.save(new Planet("Dagobah", "murky", "swamp, jungles"));
		starWarsRepository.save(new Planet("Bespin", "temperate", "gas giant"));

		// fetch all planets
		LOGGER.info("Planets saved:");
		LOGGER.info("-------------------------------");
		for (Planet planet : starWarsRepository.findAll()) {
			LOGGER.info(planet.toString());
		}
		
		LOGGER.info("Planets saved with success!");

	}
}

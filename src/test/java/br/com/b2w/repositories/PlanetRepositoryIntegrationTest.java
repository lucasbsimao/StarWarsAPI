package br.com.b2w.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetRepositoryIntegrationTest {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Test
    public void whenFindByName_thenReturnPlanet() {
		Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		planetRepository.save(planet1);

        List<Planet> results = planetRepository.findByName(planet1.getName());
        assertEquals(results.get(0).getName(),planet1.getName());
    }

    @Test
    public void whenNotFoundName_thenReturnNull() {
    	Planet planet1 = new Planet("asdf", "Test", "Test");
    	
    	List<Planet> planet = planetRepository.findByName(planet1.getName());
    	
        assertThat(planet).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnPlanet() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
    	planet1 = planetRepository.save(planet1);

        Planet planet2 = planetRepository.findById(planet1.getId()).orElse(null);
        assertEquals(planet2.getName(),planet1.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Planet planet = planetRepository.findById("-100").orElse(null);
        assertNull(planet);
    }

    @Test
    public void givenListOfPlanets_whenFindAll_thenReturnListOfPlanets() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");

        planetRepository.save(planet1);
        planetRepository.save(planet2);

        List<Planet> allEmployees = planetRepository.findAll();

        assertThat(allEmployees).isNotNull();
    }
}

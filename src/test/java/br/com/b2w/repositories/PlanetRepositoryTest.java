package br.com.b2w.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.utils.TestPlanetUtils;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlanetRepositoryTest {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@After
	public void tearDown() {
		planetRepository.deleteAll();
	}
	
	@Test
    public void whenFindByName_thenReturnPlanetTest() {
		Planet planet1 = TestPlanetUtils.createPlanet(false);
		planetRepository.save(planet1);

        List<Planet> results = planetRepository.findByName(planet1.getName());
        assertEquals(results.get(0).getName(),planet1.getName());
    }

    @Test
    public void whenNotFoundName_thenReturnNullTest() {
    	Planet planet1 = new Planet("asdf", "Test", "Test");
    	
    	List<Planet> planet = planetRepository.findByName(planet1.getName());
    	
        assertThat(planet).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnPlanetWithSamePropertiesTest() {
    	Planet planet1 = TestPlanetUtils.createPlanet(true);
    	planet1 = planetRepository.save(planet1);

        Planet planet2 = planetRepository.findById(planet1.getId()).orElse(null);
        assertThat(planet2.equals(planet1));
    }
    
    @Test
    public void whenSavePlanet_thenReturnPlanetWithIdTest() {
    	Planet planet1 = TestPlanetUtils.createPlanet(true);
    	planet1 = planetRepository.save(planet1);

        assertNotNull(planet1.getId());
    }

    @Test
    public void whenInvalidId_thenReturnNullTest() {
        Planet planet = planetRepository.findById("-100").orElse(null);
        assertNull(planet);
    }

    @Test
    public void givenListOfPlanets_whenFindAll_thenReturnListOfPlanetsTest() {
    	planetRepository.deleteAll();
    	
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");

        planet1 = planetRepository.save(planet1);
        planet2 = planetRepository.save(planet2);

        List<Planet> allPlanets = planetRepository.findAll();

        assertThat(allPlanets).hasSize(2);
        assertThat(allPlanets.get(0).getId()).isNotNull();
        assertThat(allPlanets.get(0).equals(planet1));
        assertThat(allPlanets.get(1).getId()).isNotNull();
        assertThat(allPlanets.get(1).equals(planet2));
    }
    
    
}

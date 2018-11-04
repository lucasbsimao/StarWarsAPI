package br.com.b2w.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlanetRepositoryTest {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@AfterEach
	public void tearDownEach() {
		planetRepository.deleteAll();
	}
	
	@Test
    public void whenFindByName_thenReturnPlanetTest() {
		Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		planetRepository.save(planet1);

        List<Planet> results = planetRepository.findByName(planet1.getName());
        assertEquals(results.get(0).getName(),planet1.getName());
    }
	
	@Test
	public void whenNameIsNull_ThrowValidationExceptionTest() {
		Planet planet = new Planet(null, "Test", "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetRepository.save(planet);
	}
	
	@Test
	public void whenClimateIsNull_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", null, "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetRepository.save(planet);
	}
	
	@Test
	public void whenTerrainIsNull_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", "Test", null);
		
		exception.expect(ConstraintViolationException.class);
		planetRepository.save(planet);
	}
	
	@Test
	public void whenNameIsBlankSpace_ThrowValidationExceptionTest() {
		Planet planet = new Planet(" ", "Test", "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetRepository.save(planet);
	}
	
	@Test
	public void whenClimateIsBlankSpace_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", " ", "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetRepository.save(planet);
	}
	
	@Test
	public void whenTerrainIsBlankSpace_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", "Test", " ");
		
		exception.expect(ConstraintViolationException.class);
		planetRepository.save(planet);
	}

    @Test
    public void whenNotFoundName_thenReturnNullTest() {
    	Planet planet1 = new Planet("asdf", "Test", "Test");
    	
    	List<Planet> planet = planetRepository.findByName(planet1.getName());
    	
        assertThat(planet).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnPlanetWithSameObjectNameTest() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
    	planet1 = planetRepository.save(planet1);

        Planet planet2 = planetRepository.findById(planet1.getId()).orElse(null);
        assertEquals(planet2.getName(),planet1.getName());
    }
    
    @Test
    public void whenSavePlanet_thenReturnPlanetWithIdTest() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
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
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");

        planetRepository.save(planet1);
        planetRepository.save(planet2);

        List<Planet> allEmployees = planetRepository.findAll();

        assertThat(allEmployees).hasSize(2);
    }
    
    
}

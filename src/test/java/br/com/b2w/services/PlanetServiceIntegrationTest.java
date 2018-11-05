package br.com.b2w.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceIntegrationTest {
	
	@Autowired
	@Qualifier("planetService")
	private IPlanetService planetService;
	
	@Mock
	private PlanetRepository planetRepository;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() {
		Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
	    planet.setId("asdf1234");
	    
	    Planet planet1 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
	    
	    List<Planet> listPlanets = new ArrayList<Planet>();
	    listPlanets.add(planet);
	 
	    when(planetRepository.findByName(planet.getName())).thenReturn(listPlanets);
	    
	    listPlanets.clear();
	    listPlanets.add(planet1);
	    
	    when(planetRepository.findByName(planet1.getName())).thenReturn(listPlanets);
	    
	    listPlanets.add(planet);
	    
	    when(planetRepository.findByName("return_null")).thenReturn(null);
        when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planet));
        when(planetRepository.findAll()).thenReturn(listPlanets);
        when(planetRepository.findById("-100")).thenReturn(Optional.empty());
	}
	
	@After
	public void tearDown() {
		planetRepository.deleteAll();
	}
	
	@Test
    public void givenPlanet_whenSave_thenReturnIdNotNullTest() {
        Planet planet = new Planet("Test", "Test", "Test");
        planet = planetService.create(planet);
        assertNotNull(planet.getId());
        
        planetService.delete(planet.getId());
    }
	
	@Test
	public void whenNameIsNull_ThrowValidationExceptionTest() {
		Planet planet = new Planet(null, "Test", "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetService.create(planet);
	}
	
	@Test
	public void whenClimateIsNull_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", null, "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetService.create(planet);
	}
	
	@Test
	public void whenTerrainIsNull_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", "Test", null);
		
		exception.expect(ConstraintViolationException.class);
		planetService.create(planet);
	}
	
	@Test
	public void whenNameIsBlankSpace_ThrowValidationExceptionTest() {
		Planet planet = new Planet(" ", "Test", "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetService.create(planet);
	}
	
	@Test
	public void whenClimateIsBlankSpace_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", " ", "Test");
		
		exception.expect(ConstraintViolationException.class);
		planetService.create(planet);
	}
	
	@Test
	public void whenTerrainIsBlankSpace_ThrowValidationExceptionTest() {
		Planet planet = new Planet("Test", "Test", " ");
		
		exception.expect(ConstraintViolationException.class);
		planetService.create(planet);
	}

    @Test
    public void whenIdInvalid_thenReturnPlanetNullTest() {
        Planet planet = planetService.findById("asdf1234");
        assertNull(planet);
    }
    
    @Test
    public void givenPlanet_whenFindById_thenReturnPlanetNotNullTest() {
    	Planet planet = new Planet("Test", "Test", "Test");
    	planet = planetService.create(planet);
    	
        planet = planetService.findById(planet.getId());
        assertNotNull(planet);
        
        planetService.delete(planet.getId());
    }
    
    @Test
    public void givenPlanet_whenFindByName_thenReturnPlanetNotNullAndNameEqualsObjectTest() {
    	Planet planet1 = new Planet("Test", "Test", "Test");
    	Planet planet = planetService.create(planet1);
    	
        List<Planet> results = planetService.findByName(planet.getName());
        
        assertNotNull(planet);
        assertEquals(results.get(0).getName(), planet1.getName());
        
        planetService.delete(planet.getId());
    }

    @Test
    public void givenListOfPlanets_whenGetAll_thenReturnNotEmptyListTest() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
    	Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
    	planet1 = planetService.create(planet1);
    	planet2 = planetService.create(planet2);
		
		List<Planet> listPlanets = planetService.getAll();
		assertEquals(listPlanets.isEmpty(), false);
    }
}

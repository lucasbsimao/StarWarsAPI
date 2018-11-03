package br.com.b2w.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
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
public class PlanetServiceIntegrationTest {
	
	@Autowired
	@Qualifier("planetService")
	private IPlanetService planetService;
	
	@MockBean
	private PlanetRepository planetRepository;
	
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
    public void givenPlanet_whenSave_thenReturnIdNotNull() {
        Planet planet = new Planet("Test", "Test", "Test");
        planet = planetService.create(planet);
        assertNotNull(planet.getId());
        
        planetService.delete(planet.getId());
    }

    @Test
    public void whenIdInvalid_thenReturnPlanetNull() {
        Planet planet = planetService.findById("");
        assertNull(planet);
    }
    
    @Test
    public void givenPlanet_whenFindById_thenReturnPlanetNotNull() {
    	Planet planet = new Planet("Test", "Test", "Test");
    	planet = planetService.create(planet);
        planet = planetService.findById(planet.getId());
        assertNotNull(planet);
        
        planetService.delete(planet.getId());
    }
    
    @Test
    public void givenPlanet_whenFindByName_thenReturnPlanetNotNullAndNameEqualsObject() {
    	Planet planet1 = new Planet("Test", "Test", "Test");
    	Planet planet = planetService.create(planet1);
    	
        List<Planet> results = planetService.findByName(planet.getName());
        
        assertNotNull(planet);
        assertEquals(results.get(0).getName(), planet1.getName());
        
        planetService.delete(planet.getId());
    }

    @Test
    public void givenListOfPlanets_when() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
    	Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
    	planet1 = planetService.create(planet1);
    	planet2 = planetService.create(planet2);
		
		List<Planet> listPlanets = planetService.getAll();
		assertEquals(listPlanets.isEmpty(), false);
    }
}

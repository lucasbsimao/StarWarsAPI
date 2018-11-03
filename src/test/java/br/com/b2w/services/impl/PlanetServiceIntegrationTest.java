package br.com.b2w.services.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceIntegrationTest {
	
	@Autowired
	private PlanetService planetService;
	
	@Test
    public void testCreate() {
        Planet planet = new Planet("Test", "Test", "Test");
        planet = planetService.create(planet);
        assertNotNull(planet.getId());
        
        planetService.delete(planet.getId());
    }
    
//    @Test(expected = Exception.class)
//    public void testCreateWithNullProperties() {
//    	Planet planet = new Planet("", "", "");
//    	planetService.create(planet);
//    }


    @Test
    public void testFindByIdWithBlankId() {
        Planet planet = planetService.findById("");
        assertNull(planet);
    }
    
    @Test
    public void testFindById() {
    	Planet planet = new Planet("Test", "Test", "Test");
    	planet = planetService.create(planet);
        planet = planetService.findById(planet.getId());
        assertNotNull(planet);
        
        planetService.delete(planet.getId());
    }
    
    @Test
    public void testFindByName() {
    	Planet planet1 = new Planet("Test", "Test", "Test");
    	Planet planet = planetService.create(planet1);
        planet = planetService.findByName(planet.getName());
        assertNotNull(planet);
        assertEquals(planet.getName(), planet1.getName());
        
        planetService.delete(planet.getId());
    }

    @Test
    public void testGetAll() {
    	Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
    	Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
    	planet1 = planetService.create(planet1);
    	planet2 = planetService.create(planet2);
		
		List<Planet> listPlanets = planetService.getAll();
		assertEquals(listPlanets.isEmpty(), false);
		
		planetService.delete(planet1.getId());
		planetService.delete(planet2.getId());
    }
}

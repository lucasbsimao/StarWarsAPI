package br.com.b2w.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;
import br.com.b2w.utils.TestPlanetUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceTest {
	
	@Autowired
	@Qualifier("planetService")
	private IPlanetService planetService;
	
	@MockBean
	private PlanetRepository planetRepository;
	
	@MockBean
	private ClientSwApiService clientSwApi;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
    public void givenPlanet_whenSave_thenReturnIdNotNullTest() {
        Planet planet = TestPlanetUtils.createTestPlanet(true);

        when(planetRepository.save(any(Planet.class))).thenReturn(planet);
        
        Planet planet3 = planetService.create(planet);
        
        assertThat(planet3.equals(planet));
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
    	Planet planet = TestPlanetUtils.createTestPlanet(true);
    	List<Planet> listPlanets = new ArrayList<>();
    	
    	when(clientSwApi.getAllPlanets()).thenReturn(listPlanets);
    	when(planetRepository.findById(any(String.class))).thenReturn(Optional.of(planet));
    	
        Planet result = planetService.findById(planet.getId());
        assertNotNull(result);
    }
    
    @Test
    public void givenPlanet_whenFindByName_thenReturnPlanetNotNullAndNameEqualsObjectTest() {
    	Planet planet1 = TestPlanetUtils.createTestPlanet(true);
    	
    	List<Planet> listaPlanetas = new ArrayList<>();
    	listaPlanetas.add(planet1);
    	
    	when(planetRepository.findByName(planet1.getName())).thenReturn(listaPlanetas);
    	
        List<Planet> results = planetService.findByName(planet1.getName());
        
        assertNotNull(results);
        assertThat(results.get(0).equals(planet1));
    }

    @Test
    public void givenListOfPlanets_whenGetAll_thenReturnNotEmptyListTest() {
    	List<Planet> initPlanets = TestPlanetUtils.createPlanets(2,true);
    	
    	List<Planet> listPlanets = new ArrayList<>();
    	listPlanets.add(initPlanets.get(0));
    	listPlanets.add(initPlanets.get(1));
    	
    	when(planetRepository.findAll()).thenReturn(listPlanets);
        
		List<Planet> allPlanets = planetService.getAll();
		assertThat(allPlanets).hasSize(2);
        assertThat(allPlanets.get(0).equals(initPlanets.get(0)));
        assertThat(allPlanets.get(1).equals(initPlanets.get(1)));
    }
}

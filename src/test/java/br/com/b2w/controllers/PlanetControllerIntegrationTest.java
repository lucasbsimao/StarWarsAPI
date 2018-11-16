package br.com.b2w.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b2w.StarwarsapiApplication;
import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.utils.TestJsonUtils;
import br.com.b2w.utils.TestPlanetUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarwarsapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetControllerIntegrationTest {
	
	@Autowired
	PlanetRepository planetRepository;
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	private final String END_POINT = "/planets/";

	@After
	public void tearDown() {
		planetRepository.deleteAll();
	}
	
	@Test
	public void whenRequestGetId_thenReturnPlanetEqualsToObjectTest() throws Exception {
		Planet planet = TestPlanetUtils.createPlanet(false);
		
		planet = planetRepository.insert(planet);
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String getIdEndpoint = createURLWithPort(END_POINT) + planet.getId(); 
		
		ResponseEntity<String> result = restTemplate.exchange(
				getIdEndpoint,
				HttpMethod.GET, entity, String.class);

		int status = result.getStatusCode().value();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
		
//		System.out.println("TEste: "+ result.getBody().toString());
		
		Planet resultPlanet = TestJsonUtils.jsonToObject(result.getBody().toString(), Planet.class);
		assertThat(resultPlanet.equals(planet));
		assertThat(resultPlanet.getId().equals(planet.getId()));
	}
	
	@Test
    public void whenRequestCreate_thenReturnPlanetEqualsToObjectTest() throws Exception {
		Planet planet = TestPlanetUtils.createPlanet(false);
		
		HttpEntity<Planet> entity = new HttpEntity<Planet>(planet, headers);

		ResponseEntity<String> result = restTemplate.exchange(
				createURLWithPort(END_POINT),
				HttpMethod.POST, entity, String.class);

		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), result.getStatusCode().value());
        
        Planet resultPlanet = TestJsonUtils.jsonToObject(result.getBody().toString(), Planet.class);
        assertThat(resultPlanet.equals(planet));
        assertNotNull(resultPlanet.getId());
        assertThat(resultPlanet.getFilms() == null);
    }
	
	@Test
    public void givenSetOfPlanets_whenRequestGetAll_thenReturnListEqualToObjectTest() throws Exception {
		planetRepository.deleteAll();
		List<Planet> initPlanets = TestPlanetUtils.createPlanets(2, false);
		
		initPlanets = planetRepository.insert(initPlanets);
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> result = restTemplate.exchange(
				createURLWithPort(END_POINT),
				HttpMethod.GET, entity, String.class);
        
		
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), result.getStatusCode().value());
        
        List<Planet> resultPlanets = TestJsonUtils.jsonToList(new TypeReference<List<Planet>>() {},result.getBody().toString());
        
        assertThat(resultPlanets.get(0).equals(initPlanets.get(0)));
        assertThat(resultPlanets.get(1).equals(initPlanets.get(1)));
    }
	
	@Test
	public void whenRequestDelete_thenReturnNoContentTest() throws Exception {
		Planet planet = TestPlanetUtils.createPlanet(false);
		
		planet = planetRepository.insert(planet);
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String getIdEndpoint = createURLWithPort(END_POINT) + planet.getId(); 
		
		ResponseEntity<String> result = restTemplate.exchange(
				getIdEndpoint,
				HttpMethod.DELETE, entity, String.class);

		int status = result.getStatusCode().value();
		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT.value(), status);
	}
	
	@Test
	public void giverSetOfPlanets_whenRequestGetByName_thenReturnListEqualToObjectTest() throws Exception {
		planetRepository.deleteAll();
		
		List<Planet> initPlanets = TestPlanetUtils.createRepeatedPlanets(2, false);
		
		initPlanets = planetRepository.insert(initPlanets);
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> result = restTemplate.exchange(
				createURLWithPort(END_POINT),
				HttpMethod.GET, entity, String.class);

		int status = result.getStatusCode().value();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		List<Planet> resultPlanets = TestJsonUtils.jsonToList(new TypeReference<List<Planet>>() {},result.getBody().toString());
        
        assertThat(resultPlanets.get(0).equals(initPlanets.get(0)));
        assertThat(resultPlanets.get(1).equals(initPlanets.get(1)));
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}

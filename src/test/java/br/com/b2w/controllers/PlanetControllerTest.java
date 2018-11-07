package br.com.b2w.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b2w.controllers.PlanetControllerImpl;
import br.com.b2w.controllers.interfaces.IPlanetController;
import br.com.b2w.entities.Planet;
import br.com.b2w.exceptions.NotFoundEntityException;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.PlanetServiceImpl;
import br.com.b2w.utils.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetControllerImpl.class)
public class PlanetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlanetServiceImpl planetService;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	private final String END_POINT = "/planets/";
	
	@Test
	public void whenRequestGetId_thenReturnPlanetEqualsToObjectTest() throws Exception {
		Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
		planet.setId("1");
		
		when(planetService.findById(any(String.class))).thenReturn(planet);
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(END_POINT + "{id}", planet.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		verify(planetService).findById(any(String.class));

		Planet resultPlanet = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Planet.class);
		assertThat(resultPlanet.equals(planet));
	}
	
	@Test
    public void whenRequestCreate_thenReturnPlanetEqualsToObjectTest() throws Exception {
		Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
		planet.setId("1");
		when(planetService.create(any(Planet.class))).thenReturn(planet);
		
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(planet)))
                .andExpect(status().isOk());

        ArgumentCaptor<Planet> requestDtoArgumentCaptor = ArgumentCaptor.forClass(Planet.class);
        verify(planetService).create(requestDtoArgumentCaptor.capture());
        Planet result = requestDtoArgumentCaptor.getValue();
        assertThat(result.equals(planet));
    }
	
	@Test
    public void givenSetOfPlanets_whenRequestGetAll_thenReturnListEqualToObjectTest() throws Exception {
		Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		Planet planet2 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");

        List<Planet> listplanets = new ArrayList<>();
        listplanets.addAll(Arrays.asList(planet1, planet2));

        when(planetService.getAll()).thenReturn(listplanets);
        
        MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(END_POINT)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
        
        int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
        
        List<Planet> resultPlanets = TestUtils.jsonToList(new TypeReference<List<Planet>>() {},result.getResponse().getContentAsString());
        
        assertThat(resultPlanets.get(0).equals(planet1));
        assertThat(resultPlanets.get(1).equals(planet2));
        
        verify(planetService).getAll();
    }
	
	@Test
	public void whenRequestDelete_thenReturnPlanetEqualsToObjectTest() throws Exception {
		Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
		planet.setId("1");
		
		when(planetService.findById(any(String.class))).thenReturn(planet);
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete(END_POINT + "{id}", planet.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT.value(), status);

		verify(planetService).delete(any(String.class));
	}
	
	@Test
	public void giverSetOfPlanets_whenRequestGetByName_thenReturnListEqualToObjectTest() throws Exception {
		Planet planet1 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		Planet planet2 = new Planet("Alderaan", "temperate, tropical", "jungle, rainforests");

        List<Planet> listPlanets = new ArrayList<>();
        listPlanets.addAll(Arrays.asList(planet1, planet2));
		
		when(planetService.findByName(any(String.class))).thenReturn(listPlanets);
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(END_POINT + "name/{name}", planet1.getName())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
		

		verify(planetService).findByName(any(String.class));

		List<Planet> resultPlanets = TestUtils.jsonToList(new TypeReference<List<Planet>>() {},result.getResponse().getContentAsString());
        
        assertThat(resultPlanets.get(0).equals(planet1));
        assertThat(resultPlanets.get(1).equals(planet2));
	}
	
}

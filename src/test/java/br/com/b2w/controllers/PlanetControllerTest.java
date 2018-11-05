package br.com.b2w.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.b2w.controllers.PlanetControllerImpl;
import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.PlanetServiceImpl;
//import br.com.b2w.utils.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetControllerImpl.class)
public class PlanetControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	PlanetServiceImpl planetService;
	
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	private final String URL = "/planets/";
//	
//	@Test
//	public void whenConsumeGetId_thenReturnStatusOkTest() throws Exception {
//		
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.get(URL + "{id}", new String("asdf")).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//		
//	}
//	
//	@Test
//	public void whenRequestGetId_thenReturnNotNullPlanetAndIdEqualsObjectTest() throws Exception {
//		Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
//		when(planetService.findById(any(String.class))).thenReturn(planet);
//
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.get(URL + "{id}", new String("asdf")).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//
//		verify(planetService).findById(any(String.class));
//
//		Planet resultPlanet = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Planet.class);
//		assertNotNull(resultPlanet);
//		assertEquals("asdf", resultPlanet.getId());
//	}
	
	@Test
	public void testInit() {
		
	}
}

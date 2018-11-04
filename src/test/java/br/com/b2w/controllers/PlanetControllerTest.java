package br.com.b2w.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.b2w.controllers.impl.PlanetController;
import br.com.b2w.entities.Planet;
import br.com.b2w.repositories.PlanetRepository;
import br.com.b2w.services.interfaces.IPlanetService;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {
	
	@MockBean
	@Qualifier("planetService")
	IPlanetService planetService;
	
	@Autowired
	private MockMvc mockMvc;
	
//	public void testGetEmployee() throws Exception {
//		Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains");
//		when(planetService.findById(any(String.class))).thenReturn(planet);
//
//		// execute
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//		// verify that service method was called once
//		verify(empService).getById(any(Long.class));
//
//		Employee resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Employee.class);
//		assertNotNull(resultEmployee);
//		assertEquals(1l, resultEmployee.getId().longValue());
//	}
}

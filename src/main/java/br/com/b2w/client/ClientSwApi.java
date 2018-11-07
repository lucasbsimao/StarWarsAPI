package br.com.b2w.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import br.com.b2w.client.response.ClientResponse;
import br.com.b2w.entities.Planet;
import br.com.b2w.utils.CustomPageImpl;

@Service
public class ClientSwApi{

	RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	final static String URI = "https://swapi.co/api/planets?page=";

	public List<Planet> getAllPlanets() {
		List<Planet> listPlanets = new ArrayList<>();
		ClientResponse clientResponse = null;
		int page = 1;
		do {
			ResponseEntity<ClientResponse> result = restTemplate.exchange(URI+page, HttpMethod.GET,createHeader(),ClientResponse.class);
			
			clientResponse = result.getBody();
			
			listPlanets.addAll(clientResponse.getResults());
			
			page++;
		}while(clientResponse.getNext() != null);
		
		return listPlanets;
	}
	
	private HttpEntity<String> createHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}

}

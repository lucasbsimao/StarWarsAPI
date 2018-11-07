package br.com.b2w.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.entities.Planet;
import br.com.b2w.responses.ClientResponse;

@Service
public class ClientSwApiService{

	RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	final static String URI = "https://swapi.co/api/planets?page=";

	/**
	 * Search for the list of planets at <a href="https://swapi.co/">SWApi</a>
	 * @return A set of all planets that are present in Star Wars universe 
	 */
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

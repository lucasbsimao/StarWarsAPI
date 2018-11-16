package br.com.b2w.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.b2w.entities.Planet;

public class TestPlanetUtils {
	
	/**
	 * Creates a planet, which may contain an Id or not
	 * @param containsId
	 * @return a object Planet which is NOT present on Star Wars films
	 */
	public static Planet createTestPlanet(boolean containsId) {
		Planet planet = new Planet("Test","Test","Test");
		if(containsId) planet.setId("1");
		
		return planet;
	}
	
	/**
	 * Creates a planet, which may contain an Id or not
	 * @param containsId
	 * @return a object Planet which IS present on Star Wars films
	 */
	public static Planet createPlanet(boolean containsId) {
		return createPlanets(1,containsId).get(0);
	}
	
	/**
	 * Creates a list of planets with a size, which may contain an Id or not
	 * @param size, max value is 4
	 * @param containsId
	 * @return a list of planets which IS present on Star Wars films
	 */
	public static List<Planet> createPlanets(int size,boolean containsId){
		if(size > 4)
			size = 4;
		
		Planet [] planets = new Planet[]{
				 new Planet("Alderaan", "temperate", "grasslands"),
		    	 new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests"),
		    	 new Planet("Bespin", "temperate", "gas giant"),
		    	 new Planet("Endor", "temperate, tropical", "forests, mountains, lakes")
		};
    	
    	List<Planet> listPlanets = Arrays.asList(planets);
    	
    	if(containsId)
	    	for(Integer i = 0; i < listPlanets.size();i++) {
	    		listPlanets.get(i).setId(i.toString());
	    	}
		
		return listPlanets.subList(0, size);
	}
	
	/**
	 * Creates a list of repeated planets with a size, which may contain an Id or not
	 * @param size
	 * @param containsId
	 * @return a list of planets which IS present on Star Wars films
	 */
	public static List<Planet> createRepeatedPlanets(int size,boolean containsId){
    	
    	List<Planet> listPlanets = new ArrayList<>();
    	
    	for(Integer i = 0; i < size;i++) {
    		Planet planet = new Planet("Alderaan", "temperate", "grasslands");
    		if(containsId) planet.setId(i.toString());
    		
    		listPlanets.add(planet);
    	}
		
		return listPlanets;
	}
}

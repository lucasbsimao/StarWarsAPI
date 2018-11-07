package br.com.b2w.client;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.entities.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientSwTest {
	@Autowired
    ClientSwApi rest;
    
	@Test
    public void testa_Retorna_Aparicoes() {
		List<Planet> result = rest.getAllPlanets();
    	assertNotNull(result);
     }
}

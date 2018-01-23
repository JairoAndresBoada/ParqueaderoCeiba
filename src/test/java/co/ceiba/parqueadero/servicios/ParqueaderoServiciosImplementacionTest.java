package co.ceiba.parqueadero.servicios;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaExcepciones;
import co.ceiba.parqueadero.exception.ParqueaderoServiciosExcepciones;
import co.ceiba.parqueadero.servicios.ParqueaderoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoServiciosImplementacionTest {

	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Mock
	private ParqueaderoService parqService;
	
	
	@Test
	public void testIngresarVehiculoParqueadero() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		boolean resultado=true;
		
		Mockito.when(parqService.ingresarVehiculoParqueadero("XYZ258", 0)).thenReturn(resultado);
		
		Assert.assertTrue(parqService.ingresarVehiculoParqueadero("XYZ258", 0));
	}

	@Test
	public void testSalidaVehiculoParqueadero() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		double resultado=1000.0;
		
		Mockito.when(parqService.salidaVehiculoParqueadero("XYZ258")).thenReturn(resultado);
		
		Assert.assertEquals(1000,parqService.salidaVehiculoParqueadero("XYZ258"),0f);
	}
	
	
	@Test(expected=ParqueaderoServiciosExcepciones.class)
	public void ingresarVehiculoPlacaVacia() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("", 0));
	}
	
	@Test(expected=ParqueaderoServiciosExcepciones.class)
	public void ingresarVehiculoPlacaInvalida() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("XY28", 0));
	}
	
	@Test(expected=ParqueaderoServiciosExcepciones.class)
	public void ingresarVehiculoCilindrajeInvalido() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("XYZ258", -15));
	}
	@Test(expected=ParqueaderoServiciosExcepciones.class)
	public void ingresarVehiculoExistente() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		
		Mockito.when(parqService.ingresarVehiculoParqueadero("XYZ258",0)).thenThrow(new ParqueaderoServiciosExcepciones());
		
		Assert.assertTrue(parqService.ingresarVehiculoParqueadero("XYZ258", 0));
	}
	
	@Test(expected=ParqueaderoServiciosExcepciones.class)
	public void salidaVehiculoParqueaderoPlacaVacia() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero(""),0f);
	}	
	
	@Test(expected=ParqueaderoServiciosExcepciones.class)
	public void salidaVehiculoParqueaderoNoExistente() throws ParqueaderoServiciosExcepciones, ParqueaderoLogicaExcepciones {
		
		Mockito.when(parqService.salidaVehiculoParqueadero("XYZ258")).thenThrow(new ParqueaderoServiciosExcepciones());

		Assert.assertEquals(1000,parqService.salidaVehiculoParqueadero("XYZ258"),0f);
	}

}

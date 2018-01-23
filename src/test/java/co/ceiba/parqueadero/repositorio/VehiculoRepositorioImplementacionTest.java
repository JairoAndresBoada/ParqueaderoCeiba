package co.ceiba.parqueadero.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.VehiculoExcepciones;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repositorio.VehiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoRepositorioImplementacionTest {
	
	@Mock
	private VehiculoRepository vehiculoRepo;
	
	@Test
	public void testObtenerTodos() throws Exception {
		//Arrange
		List<Vehiculo> resultado= new ArrayList<Vehiculo>();
		Vehiculo veh1, veh2;
		veh1=new Vehiculo("ABC123");
		veh2=new Vehiculo("XYZ987");
		resultado.add(veh1); resultado.add(veh2);
		
		//Act
		Mockito.when(vehiculoRepo.obtenerTodos()).thenReturn(resultado);

		//Assert
		Assert.assertEquals(resultado, vehiculoRepo.obtenerTodos());
	}
	
	@Test
	public void testObtenerPorPlaca() throws VehiculoExcepciones {
		Vehiculo test=new Vehiculo("SUV123");
		
		Mockito.when(vehiculoRepo.obtenerPorPlaca("SUV123")).thenReturn(test);
		
		Assert.assertEquals(test, vehiculoRepo.obtenerPorPlaca("SUV123"));
	}
	
	@Test
	public void testInsertarCarro() throws Exception{
		Vehiculo resultado=new Vehiculo("SUV123");
		
		Mockito.when(vehiculoRepo.insertar("SUV123",0)).thenReturn(resultado);
		
		Assert.assertEquals(resultado,vehiculoRepo.insertar("SUV123",0));
	}
	
	
	@Test
	public void testInsertarMoto() throws Exception{
		Vehiculo resultado=new Vehiculo("SUV123");
		
		Mockito.when(vehiculoRepo.insertar("SUV123",500)).thenReturn(resultado);
		
		Assert.assertEquals(resultado,vehiculoRepo.insertar("SUV123",500));
	}
	

}

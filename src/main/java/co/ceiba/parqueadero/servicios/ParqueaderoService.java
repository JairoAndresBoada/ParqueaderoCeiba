package co.ceiba.parqueadero.servicios;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoExcepciones;
import co.ceiba.parqueadero.exception.ParqueaderoServiciosExcepciones;
import co.ceiba.parqueadero.modelo.Parqueadero;

public interface ParqueaderoService {
	boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiciosExcepciones;
	double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiciosExcepciones;
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoExcepciones;
}

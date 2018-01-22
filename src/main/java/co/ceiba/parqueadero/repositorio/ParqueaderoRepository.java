package co.ceiba.parqueadero.repositorio;

import java.util.Calendar;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoExcepciones;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface ParqueaderoRepository {
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoExcepciones;
	Parqueadero actualizar(String placa, Calendar fechaSalida) throws ParqueaderoExcepciones;
	Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoExcepciones;
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoExcepciones;
	List<Parqueadero> obtenerCarros() throws ParqueaderoExcepciones;
	List<Parqueadero> obtenerMotos() throws ParqueaderoExcepciones;
}

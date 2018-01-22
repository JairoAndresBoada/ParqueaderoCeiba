package co.ceiba.parqueadero.repositorio;

import java.util.List;

import co.ceiba.parqueadero.exception.VehiculoExcepciones;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface VehiculoRepository {
	List<Vehiculo> obtenerTodos() throws VehiculoExcepciones;
	Vehiculo insertar(String placa, int cilindraje) throws VehiculoExcepciones;
	Vehiculo obtenerPorPlaca(String placa) throws VehiculoExcepciones;
	Moto obtenerMotoPorPlaca(String placa) throws VehiculoExcepciones;

}

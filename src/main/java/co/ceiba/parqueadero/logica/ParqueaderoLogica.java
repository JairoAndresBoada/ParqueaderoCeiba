package co.ceiba.parqueadero.logica;

import java.util.Calendar;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaExcepciones;

import co.ceiba.parqueadero.exception.VehiculoExcepciones;
import co.ceiba.parqueadero.modelo.Parqueadero;

public interface ParqueaderoLogica {
	double calcularMonto(Parqueadero parqueadero) throws VehiculoExcepciones;
	double salidaParqueadero(String placa) throws ParqueaderoLogicaExcepciones;
	boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoLogicaExcepciones;
	long cantidadHoras(Calendar ingreso, Calendar salida);
	long cantidadMinutos(Calendar ingreso, Calendar salida);
}

package co.ceiba.parqueadero.logica;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoExcepciones;
import co.ceiba.parqueadero.exception.ParqueaderoLogicaExcepciones;
import co.ceiba.parqueadero.exception.VehiculoExcepciones;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repositorio.ParqueaderoRepository;
import co.ceiba.parqueadero.repositorio.VehiculoRepository;
import co.ceiba.parqueadero.utils.Constantes;
import co.ceiba.parqueadero.utils.Mensajes;


@Transactional
@Service
public class ParqueaderoLogicaImpl implements ParqueaderoLogica {
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	
	public double calcularMonto(Parqueadero parqueadero) throws VehiculoExcepciones {
		long horas=cantidadHoras(parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
		long minutos=cantidadMinutos(parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
		long minutosHora=minutos%60;
		long dias= horas/24;
		long horasDia= horas%24;
		double monto=0;
		if(minutosHora>0) {horasDia++;}
		monto=calcularMontoVehiculo(dias, horasDia, parqueadero.getVehiculo());
		return monto;
	}
	
	
	private double calcularMontoVehiculo(long dias, long horasDia, Vehiculo veh) throws VehiculoExcepciones {
		double monto=0;
		if(horasDia>=Constantes.MINIMO_HORAS_DIA) {
			if(veh.getTipo().equals("2")) {monto+=(dias+1)*Constantes.DIA_CARRO;}
			else {monto+=(dias+1)*Constantes.DIA_MOTO;}
		}else {
			if(veh.getTipo().equals("2")) {monto+=dias*Constantes.DIA_CARRO + horasDia*Constantes.HORA_CARRO;}
			else {monto+=dias*Constantes.DIA_MOTO + horasDia*Constantes.HORA_MOTO;}
		}
		if(veh.getTipo().equals("1")) {
			Moto moto= vehiculoRepository.obtenerMotoPorPlaca(veh.getPlaca());
			if(moto.getCilindraje()>=Constantes.CILINDRAJE) {
				monto+=2000;
			}
		}
		if(monto==0) {
			if(veh.getTipo().equals("2")) {monto+=Constantes.HORA_CARRO;}
			else {monto+=Constantes.HORA_MOTO;}
		}
		return monto;
	}

	
	@Override
	public double salidaParqueadero(String placa) throws ParqueaderoLogicaExcepciones {
		try{
			Calendar salida=Calendar.getInstance();
			Parqueadero parq=parqueaderoRepository.actualizar(placa, salida);
			return calcularMonto(parq);
		}catch(Exception e) {
			throw new ParqueaderoLogicaExcepciones(Mensajes.ERROR_SALIDA_VEHICULO,e);
		}
	}

	
	@Override
	public boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoLogicaExcepciones {
		try {
			Calendar fecha=Calendar.getInstance();
			validaciones(placa, cilindraje, fecha);
			Vehiculo vehiculo=vehiculoRepository.obtenerPorPlaca(placa);
			if(vehiculo==null) {
				vehiculo=vehiculoRepository.insertar(placa, cilindraje);
			}
			parqueaderoRepository.insertar(vehiculo, fecha);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoLogicaExcepciones(Mensajes.ERROR_INSERTAR,e);
		}
	}
	
	public void validaciones(String placa, int cilindraje, Calendar fecha) throws Exception {
		validarExistencia(placa);
		validarCantidad(cilindraje);
		validarRestricciones(placa, fecha);
	}
	
	public void validarExistencia(String placa) throws ParqueaderoExcepciones {
		Parqueadero parq=parqueaderoRepository.obtenerPorVehiculoSinSalir(placa);
		if(parq!=null) {
			throw new ParqueaderoExcepciones(Mensajes.ERROR_DUPLICADO);
		}
	}
	
	
	public void validarRestricciones(String placa, Calendar fecha) throws ParqueaderoExcepciones {
		if(placa.substring(0, 1).equalsIgnoreCase(Constantes.LETRA_PLACA) && 
				(Constantes.getDiasRestringidos().contains(fecha.get(Calendar.DAY_OF_WEEK))))  {
			throw new ParqueaderoExcepciones(Mensajes.RESTRICCION_PLACA);
		}
	}
	
	
	public void validarCantidad(int cilindraje) throws ParqueaderoExcepciones {
		int cantidadCarros=parqueaderoRepository.obtenerCarros().size();
		int cantidadMotos=parqueaderoRepository.obtenerMotos().size();
		if(cantidadCarros>=Constantes.CANTIDAD_MAXIMA_CARROS && cilindraje==0) {
			throw new ParqueaderoExcepciones(Mensajes.CAPACIDAD_CARROS);
		}
		if(cantidadMotos>=Constantes.CANTIDAD_MAXIMA_MOTOS && cilindraje >0) {
			throw new ParqueaderoExcepciones(Mensajes.CAPACIDAD_MOTOS);
		}
	}

	@Override
	public long cantidadHoras(Calendar ingreso, Calendar salida) {
		long end= salida.getTimeInMillis();
		long start= ingreso.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toHours(Math.abs(end - start));
	}
	
	@Override
	public long cantidadMinutos(Calendar ingreso, Calendar salida) {
		long end= salida.getTimeInMillis();
		long start= ingreso.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toMinutes(Math.abs(end - start));
	}

}

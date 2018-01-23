package co.ceiba.parqueadero.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoExcepciones;
import co.ceiba.parqueadero.exception.ParqueaderoLogicaExcepciones;

import co.ceiba.parqueadero.exception.ParqueaderoServiciosExcepciones;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.repositorio.ParqueaderoRepository;
import co.ceiba.parqueadero.valoresfijos.Constantes;
import co.ceiba.parqueadero.valoresfijos.ValoresFijos;

@Transactional
@Service
public class ParqueaderoServiceImplementacion implements ParqueaderoService {
	
	@Autowired
	ParqueaderoLogica parqueaderoLogica;
	
	@Autowired
	ParqueaderoRepository parqueaderorepository;
	
	

	@Override
	public double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiciosExcepciones {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiciosExcepciones(ValoresFijos.PLACA_VACIA);
		}
		if(!placa.matches(Constantes.PATRON_PLACA)) {
			throw new ParqueaderoServiciosExcepciones(ValoresFijos.PLACA_INVALIDA);
		}
		try {
			return parqueaderoLogica.salidaParqueadero(placa);
		} catch (ParqueaderoLogicaExcepciones e) {
			throw new ParqueaderoServiciosExcepciones(ValoresFijos.ERROR_SALIDA_VEHICULO,e);
		}
}

	@Override
	public List<Parqueadero> obtenerVehiculos() throws ParqueaderoExcepciones {
		return parqueaderorepository.obtenerVehiculos();
	}
	
	@Override
	public boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiciosExcepciones {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiciosExcepciones(ValoresFijos.PLACA_VACIA);
		}
		if(!placa.matches(Constantes.PATRON_PLACA)) {
			throw new ParqueaderoServiciosExcepciones(ValoresFijos.PLACA_INVALIDA);
		}
		if(cilindraje <0) {
			throw new ParqueaderoServiciosExcepciones(ValoresFijos.CILINDRAJE_INVALIDO);
		}
		try {
			return parqueaderoLogica.ingresarVehiculo(placa, cilindraje);
		} catch (ParqueaderoLogicaExcepciones e) {
			throw new ParqueaderoServiciosExcepciones(e.getCause().toString().split(":")[1]);
		}
	}

}


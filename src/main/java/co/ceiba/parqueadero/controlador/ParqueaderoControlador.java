package co.ceiba.parqueadero.controlador;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.ceiba.parqueadero.exception.ParqueaderoExcepciones;
import co.ceiba.parqueadero.exception.ParqueaderoServiciosExcepciones;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.servicios.ParqueaderoService;
import co.ceiba.parqueadero.valoresfijos.ValoresFijos;


@RestController
public class ParqueaderoControlador {
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	
	 @CrossOrigin
	 @RequestMapping(path="/salir/placa={id_placa}", method = RequestMethod.GET)
	 public double salir(@PathVariable String id_placa) throws ParqueaderoServiciosExcepciones {
				return parqueaderoService.salidaVehiculoParqueadero(id_placa); 
	 }
	
	
	@CrossOrigin
	 @RequestMapping(path="/insert/placa={id_placa}&cilindraje={id_cilindraje}", 
			 method = RequestMethod.GET)
	 public boolean ingresar(@PathVariable String id_placa,
			 @PathVariable String id_cilindraje) throws  ParqueaderoServiciosExcepciones {
				try {
					return parqueaderoService.ingresarVehiculoParqueadero(id_placa, 
							Integer.parseInt(id_cilindraje));
				} catch (NumberFormatException e) {
					throw new ParqueaderoServiciosExcepciones("No se pudo convertir el numero");
				}
	 }
	
	 @CrossOrigin
	 @RequestMapping(path="/VehiculosSinSalir", method = RequestMethod.GET)
	 public List<Parqueadero> obtenerVehiculos() throws ParqueaderoServiciosExcepciones {
			try {
				return parqueaderoService.obtenerVehiculos();
			} catch (ParqueaderoExcepciones e) {
				throw new ParqueaderoServiciosExcepciones(ValoresFijos.ERROR_OBTENER_TODOS_SIN_SALIR);
			} 
	 }
}

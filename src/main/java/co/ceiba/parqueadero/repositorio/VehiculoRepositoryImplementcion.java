package co.ceiba.parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.VehiculoExcepciones;
import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.valoresfijos.ValoresFijos;

@Transactional
@Repository
public class VehiculoRepositoryImplementcion implements VehiculoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerTodos() throws VehiculoExcepciones {
		try {
			String hql = "FROM Vehiculo";
			return (List<Vehiculo>) entityManager.createQuery(hql).getResultList();
		}catch(Exception e) {
			throw new VehiculoExcepciones(ValoresFijos.ERROR_TODOS_LOS_VEHICULOS, e);
		}
	}

	
	@Override
	public Vehiculo insertar(String placa, int cilindraje) throws VehiculoExcepciones {
		Vehiculo vehiculo;
		try {
			if(cilindraje==0) {
				vehiculo=new Carro(placa.toUpperCase()); 
			}else {
				vehiculo=new Moto(placa.toUpperCase(),cilindraje); 
			}
			entityManager.persist(vehiculo);
			return vehiculo;
		}catch(Exception e) {
			throw new VehiculoExcepciones(ValoresFijos.ERROR_INSERTAR,e); 
		}
	}


	
	@Override
	public Vehiculo obtenerPorPlaca(String placa) throws VehiculoExcepciones {
		try{
			List<Vehiculo> vehiculos=obtenerTodos();
			for(Vehiculo veh: vehiculos) {
				if(veh.getPlaca().equals(placa)) {
					return veh;
				}
			}
			return null;
		}catch(Exception e) {
			throw new VehiculoExcepciones(ValoresFijos.ERROR_OBTENER_VEHICULO,e);
		}
	}
	
	
	@Override
	public Moto obtenerMotoPorPlaca(String placa) throws VehiculoExcepciones {
		try {
			String hql = "FROM Moto moto WHERE moto.placa = ?";
			return (Moto) entityManager.createQuery(hql).setParameter(1, placa).getResultList().get(0);
		}catch(Exception e) {
			throw new VehiculoExcepciones(ValoresFijos.ERROR_OBTENER_MOTO, e);
		}
	}
}
		


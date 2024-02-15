package ec.edu.ups.transaccion.sistema.business;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Carrito;
import ec.edu.ups.transaccion.sistema.dao.CarritoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCarrito {

	@Inject
	private CarritoDAO dao;
	
	
	public void guardar(Carrito carrito) {
		dao.insert(carrito);
	}

	public void actualizar(Carrito carrito) {
		Carrito categorias = dao.read(carrito.getId());
		if(categorias != null) {
			dao.update(carrito);
		}
	}
	
	public List<Carrito> getCarrito(int id){
		return dao.getCarrito(id);
	}
	
	public void borrar(int id) {
		dao.remove(id);
	}
}

package ec.edu.ups.transaccion.sistema.business;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Categoria;
import ec.edu.ups.transaccion.sistema.dao.CategoriaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCategoria {

	@Inject
	private CategoriaDAO daoCategoria;
	
	public void guardar(Categoria categoria) {
		daoCategoria.insert(categoria);
	}

	public void actualizar(Categoria categoria) {
		Categoria categorias = daoCategoria.read(categoria.getId());
		if(categorias != null) {
			daoCategoria.update(categoria);
		}
	}
	
	public Categoria getCategoria(String descripcion)throws Exception {
		if(descripcion.length() > 0) {
			return daoCategoria.getCategoria(descripcion);
		}
		throw new Exception("Codigo incorrecto");
	}
	
	public List<Categoria> getProductos(){
		return daoCategoria.getAll();
	}
	
	public void borrar(int id) {
		daoCategoria.remove(id);
	}
}

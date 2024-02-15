package ec.edu.ups.transaccion.sistema.business;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Usuarios;
import ec.edu.ups.transaccion.sistema.dao.UsuariosDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuarios {

	@Inject
	private UsuariosDAO daoUsuarios;
	
	public void guardarProducto(Usuarios usuario) {
		System.out.println(usuario);	
		daoUsuarios.insert(usuario);
		System.out.println("Guardado");	
	}

	public void actualizarProducto(Usuarios usuario) {
		  Usuarios cli = daoUsuarios.read(usuario.getId());
		  if (cli != null) {
		    daoUsuarios.update(usuario);
		    System.out.println("Actualizado");
		  }
		}

	public Usuarios getProductoCodigo(String producto_id) throws Exception{
		if(producto_id.length() > 0) {
			return daoUsuarios.getUsuario(producto_id);
		}
		throw new Exception("Codigo incorrecto");
	}
	
	public Usuarios getUsuarioId(int producto_id) throws Exception{
		
			return daoUsuarios.getUsuarioId(producto_id);
		
	}
	
	public List<Usuarios> getProductos(){
		return daoUsuarios.getAll();
	}
	
	public void borrar(int id) {
		daoUsuarios.remove(id);
	}
	
	public Usuarios login(String email, String password) {
		return daoUsuarios.login(email, password);
	}
	
	public void transferirSaldo(Usuarios origen, Usuarios Destino, double monto) {
		daoUsuarios.transferirSaldo(origen, Destino, monto);
	}
	
}

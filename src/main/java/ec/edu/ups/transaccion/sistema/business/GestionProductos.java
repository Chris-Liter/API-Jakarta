package ec.edu.ups.transaccion.sistema.business;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Productos;
import ec.edu.ups.transaccion.sistema.Modelo.ProductosDetallados;
import ec.edu.ups.transaccion.sistema.dao.ProductoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProductos {

	@Inject
	private ProductoDAO daoPro;
	
	public void guardarProducto(Productos productos) {
		
			daoPro.insert(productos);
			System.out.println("Guardado");
		
	}
	
	public  void actualizarProducto(Productos productos) {
		Productos cli = daoPro.read(productos.getId());
		if(cli != null) {
			daoPro.update(productos);
			System.out.println("Actualizado");
		}
	}
	
	public Productos getProductoCodigo(String producto_id) throws Exception{
		if(producto_id.length() > 0) {
			return daoPro.getProducto(producto_id);
		}
		throw new Exception("Codigo incorrecto");
	}
	
	public ProductosDetallados getProductoDetallados(String producto_id) throws Exception{
		if(producto_id.length() > 0) {
			return daoPro.getProductosDetallados(producto_id);
		}
		throw new Exception("Codigo incorrecto");
	}	
	
	
	public List<Productos> getProductos(){
		return daoPro.getAll();
	}
	
	public List<Productos> getAllWoman(){
		return daoPro.getAllWoman();
	}
	
	public List<Productos> getAllMan(){
		return daoPro.getAllMan();
	}
	
	public void borrar(int id) {
		daoPro.remove(id);
	}
	
	public List<Productos> getProdUsuario(int id){
		return daoPro.getProductosUsuario(id);
	}
}

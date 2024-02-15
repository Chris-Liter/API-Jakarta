package ec.edu.ups.transaccion.sistema.business;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Detalle_Factura;
import ec.edu.ups.transaccion.sistema.Modelo.ListaDetallada;
import ec.edu.ups.transaccion.sistema.dao.DetalleFacturaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionDetalleFactura {

	@Inject
	private DetalleFacturaDAO daoDetalle;
	
	public void guardar(Detalle_Factura detalle) {
		daoDetalle.insert(detalle);
	}
	
	public void actualizar(Detalle_Factura detalle) {
		Detalle_Factura fac = daoDetalle.read(detalle.getDet_id());
		if(fac != null) {
			daoDetalle.update(detalle);
		}
	}
	
	public Detalle_Factura getDetalle(int numero){
		try {
			return daoDetalle.getDetalleFactura(numero);
		}catch (Exception e) {
			throw e;
		}
	}
	
	public List<Detalle_Factura> getAll(){
		return daoDetalle.getAll();
	}
	
	public List<Detalle_Factura> getDetalles(int id){
		return daoDetalle.getDetalles(id);
	}
	
	public List<ListaDetallada> getLista(int id){
		return daoDetalle.getListaDetalles(id);
	}
	
	public void eliminar(int id) {
		daoDetalle.remove(id);
	}
}

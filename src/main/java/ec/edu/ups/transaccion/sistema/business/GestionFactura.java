package ec.edu.ups.transaccion.sistema.business;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Factura;
import ec.edu.ups.transaccion.sistema.dao.FacturaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionFactura {

	@Inject
	private FacturaDAO daoFactura;

	public void crear(Factura factura) {
		daoFactura.insert(factura);
	}
	
	public void actualizar(Factura factura) {
		Factura fac = daoFactura.read(factura.getFac_id());
		if(fac != null) {
			daoFactura.update(factura);
		}
	}
	
	public Factura getFactura(int numero){
		try {
			return daoFactura.getFactura(numero);
		}catch (Exception e) {
			throw e;
		}
	}
	public List<Factura> getAll(){
		return daoFactura.getAll();
	}
	
	
	public List<Factura> getFacturas(int numero){
		try {
			return daoFactura.getFacturas(numero);
		}catch (Exception e) {
			throw e;
		}
	}
	
}

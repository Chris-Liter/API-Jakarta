package ec.edu.ups.transaccion.sistema.dao;

import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.ppw63.demo63.Modelo.CantidadFacturasCliente;
import ec.edu.ups.transaccion.sistema.Modelo.Detalle_Factura;
import ec.edu.ups.transaccion.sistema.Modelo.ListaDetallada;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class DetalleFacturaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Detalle_Factura detalle) {
		em.persist(detalle);
		em.flush();
	}
	public void update(Detalle_Factura detalle) {
		em.merge(detalle);
	}
	public void remove(int id) {
		Detalle_Factura detalle = em.find(Detalle_Factura.class, id);
		em.remove(detalle);
	}
	public Detalle_Factura read(int id) {
		Detalle_Factura detalle = em.find(Detalle_Factura.class, id);
	    return detalle;
	}
	public List<Detalle_Factura> getAll(){
		String jpql = "SELECT c FROM Detalle_Factura c";
		Query q = em.createQuery(jpql, Detalle_Factura.class);
		return q.getResultList();
	}
	public Detalle_Factura getDetalleFactura(int detalle_id) {
	    String jpql = "SELECT c FROM Detalle_Factura c WHERE c.det_id = :detalle_id";
	    TypedQuery<Detalle_Factura> query = em.createQuery(jpql, Detalle_Factura.class);
	    query.setParameter("detalle_id", detalle_id);
	    Detalle_Factura producto = query.getSingleResult();
	    return producto;
	}
	
	public List<Detalle_Factura> getDetalles(int id){
		String jpql = "SELECT c FROM Detalle_Factura c WHERE c.factura_id = :id";
		Query q = em.createQuery(jpql, Detalle_Factura.class);
		q.setParameter("id", id);
		return q.getResultList();
	}
	
	/*
	 * Este metodo me retornara una lista con el uso de un Join, pero no utiliza una entidad para representar cada objeto, sino utiliza una clase
	 * con los atributos de las diferentes tablas las cuales quiero que se listen
	 * */
	public List<ListaDetallada> getListaDetalles(int id){
		String jpql = "SELECT d.det_id, d.det_cantidad, d.det_precio_unitario, d.det_subtotal, d.det_iva, d.det_total, d.producto_id, d.factura_id, p.id, p.nombre FROM Detalle_Factura d JOIN Productos p ON d.producto_id = p.id WHERE d.factura_id = :id";
	    Query q = em.createNativeQuery(jpql);
	    q.setParameter("id", id);
	    List<Object[]> list = q.getResultList();
	    List<ListaDetallada> datos = new ArrayList<>();

	    for(Object[] result: list) {
	        datos.add(new ListaDetallada(
	                ((Number)result[0]).intValue(),
	                ((Number)result[1]).intValue(),
	                ((Number)result[2]).doubleValue(),
	                ((Number)result[3]).doubleValue(),
	                ((Number)result[4]).doubleValue(),
	                ((Number)result[5]).doubleValue(),
	                ((Number)result[6]).intValue(),
	                ((Number)result[7]).intValue(),
	                ((Number)result[8]).intValue(),
	                result[9].toString()
	            ));
	    }

	    return datos;
	}


}

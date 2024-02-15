package ec.edu.ups.transaccion.sistema.dao;

import java.util.List;
import java.util.Base64;


import ec.edu.ups.transaccion.sistema.Modelo.Factura;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class FacturaDAO {
	@PersistenceContext
	private EntityManager em;

    
	public void insert(Factura factura) {
		em.persist(factura);
		//em.flush();
		System.out.println(factura);
	}
	
	public void update(Factura factura) {
		em.merge(factura);
	}
	
	
	
	public Factura read(int id) {
		Factura factura = em.find(Factura.class, id);
		return factura;
	}
	
	public List<Factura> getAll(){
	    String jpql = "SELECT c FROM Factura c ORDER BY c.fac_fecha";
	    Query q = em.createQuery(jpql, Factura.class);
	    return q.getResultList();
	}

	
	public Factura getFactura(int numero) {
	    String jpql = "SELECT c FROM Factura c WHERE c.id_cliente = :numero";
	    TypedQuery<Factura> query = em.createQuery(jpql, Factura.class);
	    query.setParameter("numero", numero); 
	    Factura factura = query.getSingleResult();
	    return factura;
	}
	
	/*
	 * Este metodo nos listara las facturas o tambien llamadas compras, dependiendo del Id del usuario al que le corresponde dicha factura
	 * */
	public List<Factura> getFacturas(int numero) {
		String jpql = "SELECT c FROM Factura c WHERE c.id_cliente = :numero ORDER BY c.fac_id DESC";
	    Query q = em.createQuery(jpql, Factura.class);
	    q.setParameter("numero", numero);
	    return q.getResultList();
	}
	
	

}

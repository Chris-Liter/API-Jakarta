package ec.edu.ups.transaccion.sistema.dao;

import java.util.Base64;
import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Carrito;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class CarritoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carrito carrito) {
		em.persist(carrito);
	}
	public void update(Carrito carrito) {
		em.merge(carrito);
	}
	public void remove(int id) {
		Carrito carrito = em.find(Carrito.class, id);
		em.remove(carrito);
	}
	public Carrito read(int id) {
		Carrito carrito= em.find(Carrito.class, id);
	    return carrito;
	}
	
	/*
	 * Este metodo listara a los productos del carrito del usuario, dependiendo del Id para poder identificar los productos que el usuario tenga 
	 * en su carrito
	 * */
	 public List<Carrito> getCarrito(int id) {
	        String jpql = "SELECT c FROM Carrito c WHERE c.id_usuario = :id";
	        TypedQuery<Carrito> query = em.createQuery(jpql, Carrito.class);
	        query.setParameter("id", id);

	        List<Carrito> carritos = query.getResultList();

	        for (Carrito carrito : carritos) {
	            String fotoBase64 = Base64.getEncoder().encodeToString(carrito.getFoto());
	            carrito.setFotoBase64(fotoBase64); 
	        }

	        return carritos;
	    }
	
}

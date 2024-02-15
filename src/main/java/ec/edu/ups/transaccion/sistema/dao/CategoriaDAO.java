package ec.edu.ups.transaccion.sistema.dao;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class CategoriaDAO {
	@PersistenceContext
	private EntityManager em;
	
	
	public void insert(Categoria categoria) {
		em.persist(categoria);
	}
	public void update(Categoria categoria) {
		em.merge(categoria);
	}
	public void remove(int id) {
		Categoria categoria = em.find(Categoria.class, id);
		em.refresh(categoria);
	}
	public Categoria read(int id) {
	    Categoria categoria = em.find(Categoria.class, id);
	    return categoria;
	}
	public List<Categoria> getAll(){
		String jpql = "SELECT c FROM Categoria c";
		Query q = em.createQuery(jpql, Categoria.class);
		return q.getResultList();
	}
	
	public Categoria getCategoria(String descripcion) {
	    String jpql = "SELECT c FROM Categoria c WHERE c.descripcion = :descripcion";
	    TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
	    query.setParameter("descripcion", descripcion);
	    Categoria producto = query.getSingleResult();
	    return producto;
	}
}

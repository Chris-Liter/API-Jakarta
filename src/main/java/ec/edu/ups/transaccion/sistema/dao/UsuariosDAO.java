package ec.edu.ups.transaccion.sistema.dao;

import java.util.List;

import ec.edu.ups.transaccion.sistema.Modelo.Usuarios;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class UsuariosDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Usuarios usuarios) {
		em.persist(usuarios);
	}
	
	public void update(Usuarios usuarios) {
		  em.merge(usuarios);
	}
	
	public void remove(int id) {
		Usuarios usuario = em.find(Usuarios.class, id);
		em.remove(usuario);
	}
	
	public Usuarios read(int id) {
		Usuarios usuario = em.find(Usuarios.class, id);
		return usuario;
	}
	
	public List<Usuarios> getAll(){
		String jpql = "SELECT c FROM Usuarios c";
		Query q = em.createQuery(jpql, Usuarios.class);
		return q.getResultList();
	}
	/*
	 * este metodo nos servira para loguearnos reconociendo el email y si dicho email coincide con la contraseña de ese mismo usuario
	 * */
	public Usuarios login(String email, String password) {
	    try {
	        String jpql = "SELECT u FROM Usuarios u WHERE u.email = :email";
	        TypedQuery<Usuarios> query = em.createQuery(jpql, Usuarios.class);
	        query.setParameter("email", email);
	        Usuarios usuario = query.getSingleResult();
	        
	        // Verificar la contraseña
	        if (usuario.getPasswords().equals(password)) {
	            return usuario;
	        } else {
	            return null; // Contraseña incorrecta
	        }
	    } catch (Exception e) {
	        return null; // Usuario no encontrado
	    }
	}

	public Usuarios getUsuario(String nombre) {
	    String jpql = "SELECT c FROM Usuarios c WHERE c.email = :nombre";
	    TypedQuery<Usuarios> query = em.createQuery(jpql, Usuarios.class);
	    query.setParameter("nombre", nombre);
	    Usuarios producto = query.getSingleResult();
	    return producto;
	}
	

	public Usuarios getUsuarioId(int nombre) {
	    String jpql = "SELECT c FROM Usuarios c WHERE c.id = :nombre";
	    TypedQuery<Usuarios> query = em.createQuery(jpql, Usuarios.class);
	    query.setParameter("nombre", nombre);
	    Usuarios producto = query.getSingleResult();
	    return producto;
	}
	
	/*
	 * Este metodo usara los parametros, cliente origen y cliente destino, para actualizar los datos de sus saldos correspondientes
	 * */
	
	public void transferirSaldo(Usuarios clienteOrigen, Usuarios clienteDestino, double monto) {
		   if (clienteOrigen.getSaldo() >= monto) {
		        em.createQuery("UPDATE Usuarios c SET c.saldo = c.saldo - :monto WHERE c.id = :clienteOrigenId")
		                .setParameter("monto", monto)
		                .setParameter("clienteOrigenId", clienteOrigen.getId())
		                .executeUpdate();

		        em.createQuery("UPDATE Usuarios c SET c.saldo = c.saldo + :monto WHERE c.id = :clienteDestinoId")
		                .setParameter("monto", monto)
		                .setParameter("clienteDestinoId", clienteDestino.getId())
		                .executeUpdate();
	     } else {
		     throw new RuntimeException("Error: Saldo insuficiente para realizar la transferencia.");
	            }
		 }
		
	
	
}

package ec.edu.ups.transaccion.sistema.dao;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;

import ec.edu.ups.transaccion.sistema.Modelo.ProductosDetallados;
import ec.edu.ups.transaccion.sistema.Modelo.Productos;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class ProductoDAO {
	@PersistenceContext
	private EntityManager em;
	
	
	public void insert(Productos producto) {
		em.persist(producto);
	}
	public void update(Productos producto) {
		em.merge(producto);
	}
	public void remove(int id) {
		Productos productos = em.find(Productos.class, id);
		em.remove(productos);
	}
	public Productos read(int id) {
		Productos productos= em.find(Productos.class, id);
		return productos;
	}
	
	/*
	 * Este metodo nos listara llos productos y a su vez dentro del mismo hara una conversion de un Array de bytes al formato Hexadecimal
	 * en Base64
	 * */
	public List<Productos> getAll() {
	    String jpql = "SELECT c FROM Productos c ORDER BY c.nombre ASC ";
	    TypedQuery<Productos> query = em.createQuery(jpql, Productos.class);
	    List<Productos> productos = query.getResultList();

	    productos.forEach(producto -> {
	        // Verifica que los datos binarios no sean nulos
	        if (producto.getFoto() != null) {
	            // Convertir los datos binarios a base64
	            String fotoBase64 = Base64.getEncoder().encodeToString(producto.getFoto());
	            producto.setFotoBase64(fotoBase64);
	        }
	    });

	    return productos;
	}
	
	/*
	 * Realizara la busqueda del producto y tambien hara una convesion de la imagen de Bytes a hexadecimal
	 * */
	public Productos getProducto(String nombres) {
	    String jpql = "SELECT c FROM Productos c WHERE c.nombre = :nombres";
	    TypedQuery<Productos> query = em.createQuery(jpql, Productos.class);
	    query.setParameter("nombres", nombres);
	    Productos producto = query.getSingleResult();
        String fotoBase64 = Base64.getEncoder().encodeToString(producto.getFoto());
        producto.setFotoBase64(fotoBase64);
	    return producto;
	}
	
	/*
	 * Este metodo listara a los productos por el id del usuario al que le pertenece
	 * */
	public List<Productos> getProductosUsuario(int id_usuario) {
		String jpql = "SELECT c FROM Productos c WHERE c.id_usuario = :id_usuario";
	    TypedQuery<Productos> query = em.createQuery(jpql, Productos.class);
	    query.setParameter("id_usuario", id_usuario);
	    List<Productos> productos = query.getResultList();
	    productos.forEach(producto -> {
	        // Verifica que los datos binarios no sean nulos
	        if (producto.getFoto() != null) {
	            // Convertir los datos binarios a base64
	            String fotoBase64 = Base64.getEncoder().encodeToString(producto.getFoto());
	            producto.setFotoBase64(fotoBase64);
	        }
	    });

	    return productos;
	}
	/*
	 * Este metodo nos listara llos productos y a su vez dentro del mismo hara una conversion de un Array de bytes al formato Hexadecimal
	 * en Base64 pero solo para aquellos que tengan categoria de mujer
	 * */
	
	public List<Productos> getAllWoman() {
	    String jpql = "SELECT c FROM Productos c WHERE c.id_categoria IN (1,5,6) ";
	    TypedQuery<Productos> query = em.createQuery(jpql, Productos.class);
	    List<Productos> productos = query.getResultList();

	    productos.forEach(producto -> {
	        // Verifica que los datos binarios no sean nulos
	        if (producto.getFoto() != null) {
	            // Convertir los datos binarios a base64
	            String fotoBase64 = Base64.getEncoder().encodeToString(producto.getFoto());
	            producto.setFotoBase64(fotoBase64);
	        }
	    });

	    return productos;
	}
	
	/*
	 * Este metodo nos listara llos productos y a su vez dentro del mismo hara una conversion de un Array de bytes al formato Hexadecimal
	 * en Base64 pero solo para aquellos que tengan categoria de Hombre
	 * */
	
	
	public List<Productos> getAllMan() {
	    String jpql = "SELECT c FROM Productos c WHERE c.id_categoria IN (2,3,5) ";
	    TypedQuery<Productos> query = em.createQuery(jpql, Productos.class);
	    List<Productos> productos = query.getResultList();

	    productos.forEach(producto -> {
	        // Verifica que los datos binarios no sean nulos
	        if (producto.getFoto() != null) {
	            // Convertir los datos binarios a base64
	            String fotoBase64 = Base64.getEncoder().encodeToString(producto.getFoto());
	            producto.setFotoBase64(fotoBase64);
	        }
	    });

	    return productos;
	}
	
	
	
	public ProductosDetallados getProductosDetallados(String id){
	    String jpql = "SELECT p.id, p.codigo_producto, p.nombre AS producto_nombre, p.precio, p.stock, p.iva, p.id_categoria, p.foto, p.id_usuario, u.nombre AS usuario_nombre " +
	                  "FROM Productos p " +
	                  "JOIN Usuarios u ON p.id_usuario = u.id " +
	                  "WHERE p.nombre = :id";
	    Query q = em.createNativeQuery(jpql);
	    q.setParameter("id", id);
	    Object[] result = (Object[]) q.getSingleResult();

	    ProductosDetallados detalle = new ProductosDetallados(
	        ((Number)result[0]).intValue(),
	        (String)result[1],
	        (String)result[2],
	        ((Number)result[3]).doubleValue(),
	        ((Number)result[4]).intValue(),
	        ((Number)result[5]).doubleValue(),
	        ((Number)result[6]).intValue(),
	        (byte[])result[7],
	        ((Number)result[8]).intValue(),
	        (String)result[9] 
	    );

	    return detalle;
	}



	
	
}

package ec.edu.ups.transaccion.sistema.services;

import ec.edu.ups.transaccion.sistema.Modelo.Categoria;
import ec.edu.ups.transaccion.sistema.business.GestionCategoria;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("categoria")
public class CategoriaServices {

	@Inject
	private GestionCategoria gCategoria;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response crear(Categoria categoria) {
		try {
			gCategoria.guardar(categoria);
			return Response.ok("Categoria insertada correctamente: \n"+categoria).build();
		}catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizar(Categoria categoria) {
		try {
			gCategoria.actualizar(categoria);
			return Response.ok("Categoria actualizada correctamente: \n"+categoria).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99, "Categoria no encontrada");
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try {
			gCategoria.borrar(codigo);
			return "Ok";
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response leer(@QueryParam("descripcion") String descripcion) {
	    if (descripcion != null && !descripcion.isEmpty()) {
	        try {
	        	Categoria categoria = gCategoria.getCategoria(descripcion);
	            System.out.println("nombre a buscar: " + descripcion);
				return Response.ok(categoria).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra esa Categoria");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    } else {
	        try {
	            System.out.println("Listado");
	            gCategoria.getProductos();
				return Response.ok(gCategoria.getProductos()).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran Categorias");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    }
	}
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)	
	 @Path("{nombre}")
	 public Response leer2(@PathParam("nombre") String nombre) {
	    
	        try {
	        	Categoria cat = gCategoria.getCategoria(nombre);
	            System.out.println("nombre a buscar: " + nombre);
				return Response.ok(cat).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra esa Categoria");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    
	    
	}
}

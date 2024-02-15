package ec.edu.ups.transaccion.sistema.services;


import ec.edu.ups.transaccion.sistema.Modelo.Productos;
import ec.edu.ups.transaccion.sistema.business.GestionProductos;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
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

@Path("productos")
public class ProductosServices {

	@Inject
	private GestionProductos gProductos;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Productos productos) {
		try {
			gProductos.guardarProducto(productos);
			return Response.ok("Producto insertado correctamente: \n"+productos).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Productos productos) {
		try {
			gProductos.actualizarProducto(productos);
			return Response.ok("Producto actualizado correctamente: \n"+productos).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99, "Producto no encontrado");
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try {
			gProductos.borrar(codigo);
			return "Ok";
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response leer(@QueryParam("nombre") String nombre) {
	    if (nombre != null && !nombre.isEmpty()) {
	        try {
	            Productos cliente = gProductos.getProductoCodigo(nombre);
	            System.out.println("nombre a buscar: " + nombre);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese producto");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    } else {
	        try {
	            System.out.println("Listado");
	            gProductos.getProductos();
				return Response.ok(gProductos.getProductos()).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran productos");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    }
	}
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)	
	 @Path("{nombre}")
	 public Response leer2(@PathParam("nombre") String nombre) {
	        try {
	            Productos cliente = gProductos.getProductoCodigo(nombre);
	            System.out.println("nombre a buscar: " + nombre);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese producto");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	}
	 
	 
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("mujer")
	 public Response getWoman() {
	        try {
	            System.out.println("Listado mujeres");
	            gProductos.getAllWoman();
				return Response.ok(gProductos.getAllWoman()).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran productos");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        
	    }
	}
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("hombre")
	 public Response getMan() {
	        try {
	            System.out.println("Listado hombres");
	            gProductos.getAllMan();
				return Response.ok(gProductos.getAllMan()).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran productos");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        
	    }
	}
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("producto/{id}")
	 public Response getUsuario(@PathParam("id") int id) {
	        try {
	            System.out.println("Listado hombres");
	            gProductos.getProdUsuario(id);
				return Response.ok(gProductos.getProdUsuario(id)).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran productos");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        
	    }
	}
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("nombre/{nombre}")
	 public Response getProductos(@PathParam("nombre") String id) {
	        try {
	            System.out.println("Listado hombres");
	            gProductos.getProductoDetallados(id);
				return Response.ok(gProductos.getProductoDetallados(id)).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran productos");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        
	    }
	}
	 
}

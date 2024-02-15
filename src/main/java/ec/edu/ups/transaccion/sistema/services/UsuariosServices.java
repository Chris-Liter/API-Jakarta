package ec.edu.ups.transaccion.sistema.services;

import ec.edu.ups.transaccion.sistema.Modelo.Usuarios;
import ec.edu.ups.transaccion.sistema.business.GestionUsuarios;
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

@Path("usuarios")
public class UsuariosServices {

	@Inject
	private GestionUsuarios gUsuario;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Usuarios user) {
		try {
			gUsuario.guardarProducto(user);
			return Response.ok(user).build();
		}catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	/*
	 * Este metodo GET no lo utilizo para listar datos, sino mas bien para realzar una transaccion, que seria la de dinero del 
	 * cliente origen al cliente destino
	 * */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{origen}/{destino}/{monto}")
	public Response transferencia(@PathParam("origen") String origen, @PathParam("destino") int destino, @PathParam("monto") double monto) {
		try {
			Usuarios usuarioOrigen = gUsuario.getProductoCodigo(origen);
			Usuarios usuarioDestino = gUsuario.getUsuarioId(destino);
			gUsuario.transferirSaldo(usuarioOrigen, usuarioDestino, monto);
			return Response.ok("Correcto").build();
		}catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	/*
	 * Nos servira simplemente para actualizar el usuario
	 * */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Usuarios user) {
	    try {
	        gUsuario.actualizarProducto(user);
	        return Response.ok(user).build(); 
	    } catch(Exception ex) {
	        ErrorMessage error = new ErrorMessage(99, "Usuario no encontrado");
	        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	    }
	}

	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try {
			gUsuario.borrar(codigo);
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
	        	Usuarios cliente = gUsuario.getProductoCodigo(nombre);
	            System.out.println("nombre a buscar: " + nombre);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese Usuario");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    } else {
	        try {
	            System.out.println("Listado");
	            gUsuario.getProductos();
				return Response.ok(gUsuario.getProductos()).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran Usuarios");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    }
	}
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)	
	 @Path("{nombre}")
	 public Response leer2(@PathParam("nombre") String nombre) {
	    
	        try {
	        	Usuarios cliente = gUsuario.getProductoCodigo(nombre);
	            System.out.println("nombre a buscar: " + nombre);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese Usuario");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	}
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
   	 @Consumes(MediaType.APPLICATION_JSON)	
	 @Path("id/{id}")
	 public Response getUsuarioId(@PathParam("id") int id) {
	    
	        try {
	        	Usuarios cliente = gUsuario.getUsuarioId(id);
	            System.out.println("nombre a buscar: " + id);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese Usuario");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	}
	 
	 /*
	  * Este GET sera para realizar el Login del usuario, si pone sus credenciales correctas
	  * el GET devolvera el objeto completo del usuario para asi guardarlo en LocalStorage de la Web
	  * */
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("{email}/{password}")
	 public Response login(@PathParam("email") String email, @PathParam("password") String password) {
		    
	        try {
	        	Usuarios cliente = gUsuario.login(email, password);
	            System.out.println("nombre a buscar: " + email + " " + password);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese Usuario");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	} 
}

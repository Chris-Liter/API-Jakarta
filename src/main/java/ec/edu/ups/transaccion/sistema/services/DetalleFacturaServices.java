package ec.edu.ups.transaccion.sistema.services;

import ec.edu.ups.transaccion.sistema.Modelo.Detalle_Factura;
import ec.edu.ups.transaccion.sistema.business.GestionDetalleFactura;
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

@Path("detalle")
public class DetalleFacturaServices {
	
	@Inject
	private GestionDetalleFactura gDetalle;
	
	/*
	 * Este metodo servira para crear el detalle de la factura con su respectivo id de la cabecera
	 * */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response crear(Detalle_Factura detalle) {
		try {
			gDetalle.guardar(detalle);
			System.out.println("detalle insertado correctamente: \n");
			return Response.ok(detalle).build();
		}catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizar(Detalle_Factura user) {
		try {
			gDetalle.actualizar(user);
			return Response.ok("detalle actualizado correctamente: \n"+user).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99, "detalle no encontrado" + ex.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try {
			gDetalle.eliminar(codigo);
			return "Ok";
		}catch(Exception ex) {
			throw ex;
		}
	}
	/*
	 * Este metodo nos servira para listar los detelle, pero dependiendo del Id de la cabecera de la factura que lo tiene
	 * el detalle como una llave foranea
	 * */
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response leer(@QueryParam("id") int nombre) {
	    if (nombre != 0) {
	        try {
	        	gDetalle.getDetalles(nombre);
	            System.out.println("id a buscar: " + nombre);
				return Response.ok(gDetalle.getLista(nombre)).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese detalle" + ex.getMessage());
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    } else {
	        try {
	            System.out.println("Listado");
	            gDetalle.getAll();
				return Response.ok(gDetalle.getAll()).build(); 
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registran detalles" + ex.getMessage());
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    }
	}
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)	
	 @Path("{nombre}")
	 public Response leer2(@PathParam("nombre") int nombre) {
	    
	        try {
	        	Detalle_Factura cliente = gDetalle.getDetalle(nombre);
	            System.out.println("id a buscar: " + nombre);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra ese detalle" + ex.getMessage());
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	    
	    
	}

}

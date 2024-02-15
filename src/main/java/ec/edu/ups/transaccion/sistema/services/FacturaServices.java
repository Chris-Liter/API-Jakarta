package ec.edu.ups.transaccion.sistema.services;


import ec.edu.ups.transaccion.sistema.Modelo.Factura;
import ec.edu.ups.transaccion.sistema.business.GestionFactura;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("factura")
public class FacturaServices {

	@Inject
	private GestionFactura gFactura;
	
	
	/*
	 * Este POST simplemente llevara a la capa del DAO para realzar la insersion de la factura en la base de datos, con el Id
	 * del usuario al que le pertenece la factura o tambien llamada compra
	 * */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Factura factura) {
		try {
			gFactura.crear(factura);
			System.out.println("Factura insertada correctamente: \n");
			return Response.ok(factura).build();
		}catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	/*
	 * Este metodo es un actualizar, que nos permitira en este caso solo Anular la Factura o tambien llamada compra, actualizando el Tipo 
	 * de '1' a '0', siendo de activo a cancelado
	 * */
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizar(Factura factura) {
		try {
			gFactura.actualizar(factura);
			return Response.ok(factura).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99, "Factura no encontrada");
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)	
	 @Path("{nombre}")
	 public Response leer2(@PathParam("nombre") int nombre) {
	    
	        try {
	        	Factura cliente = gFactura.getFactura(nombre);
	            System.out.println("numero a buscar: " + nombre);
				return Response.ok(cliente).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra esa Factura");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        }
	 }
	 
	 /*
	  * Este metodo nos permitira Listar todas las facturas o tambien llamadas compras, dependiendo del Id del usuario al que le pertenece
	  * */
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getFacturas(@QueryParam("id_usuario") int nombre) {
	        try {
	        	gFactura.getFacturas(nombre);
	            System.out.println("numero a buscar: " + nombre);
				return Response.ok(gFactura.getFacturas(nombre)).build();
	        } catch (Exception ex) {
	        	ErrorMessage error = new ErrorMessage(6, "No se registra esa Factura");
	    		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	        
	}
	        }
}

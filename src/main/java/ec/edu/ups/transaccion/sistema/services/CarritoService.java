package ec.edu.ups.transaccion.sistema.services;

import ec.edu.ups.transaccion.sistema.Modelo.Carrito;
import ec.edu.ups.transaccion.sistema.business.GestionCarrito;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("carrito")
public class CarritoService {

	@Inject
	private GestionCarrito gCar;
	
	/*
	 * Este POST guardara el objeto entero del producto al carrito, sumado mas el id del usuario al que le pertenece
	 * */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response guardar(Carrito carrito) {
		try {
			gCar.guardar(carrito);
			return Response.ok(carrito).build();
		}catch (Exception ex) {
			ErrorMessage error = new ErrorMessage(99, ex.getMessage());
			return Response.status(Response.Status.BAD_GATEWAY).entity(error).build();
		}
	}
	
	/*
	 * Este GET me retornara un listado de productos de carrito, usando el Id del usuario como referencia para indentificar entre productos de
	 * carrito de cada usuario
	 * */
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response mostrar(@PathParam("id") int id) {
		try {
			gCar.getCarrito(id);
			return Response.ok(gCar.getCarrito(id)).build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	/*
	 * Este metodo simplemtente nos servira para eliminar los productos del carrito del usuario el cual ya no queremos que esten utilizando su
	 * Id de llave primaria
	 * */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response eliminar(@PathParam("id") int id) {
		try {
			gCar.borrar(id);
			return Response.ok(id).build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
}

package ec.edu.ups.transaccion.sistema.Modelo;

public class ListaDetallada {

	private int det_id;
	private int det_cantidad;
	private double det_precio_unitario;
	private double det_subtotal;
	private double det_iva;
	private double det_total;
	private int producto_id;
	private int factura_id;
	private int id;
	private String nombre;
	
	public ListaDetallada(int det_id, int det_cantidad, double det_precio_unitario, double det_subtotal, double det_iva,
			double det_total, int producto_id, int factura_id, int id, String nombre) {
		super();
		this.det_id = det_id;
		this.det_cantidad = det_cantidad;
		this.det_precio_unitario = det_precio_unitario;
		this.det_subtotal = det_subtotal;
		this.det_iva = det_iva;
		this.det_total = det_total;
		this.producto_id = producto_id;
		this.factura_id = factura_id;
		this.nombre = nombre;
		this.id = id;
	}

	public int getDet_id() {
		return det_id;
	}

	public void setDet_id(int det_id) {
		this.det_id = det_id;
	}

	public int getDet_cantidad() {
		return det_cantidad;
	}

	public void setDet_cantidad(int det_cantidad) {
		this.det_cantidad = det_cantidad;
	}

	public double getDet_precio_unitario() {
		return det_precio_unitario;
	}

	public void setDet_precio_unitario(double det_precio_unitario) {
		this.det_precio_unitario = det_precio_unitario;
	}

	public double getDet_subtotal() {
		return det_subtotal;
	}

	public void setDet_subtotal(double det_subtotal) {
		this.det_subtotal = det_subtotal;
	}

	public double getDet_iva() {
		return det_iva;
	}

	public void setDet_iva(double det_iva) {
		this.det_iva = det_iva;
	}

	public double getDet_total() {
		return det_total;
	}

	public void setDet_total(double det_total) {
		this.det_total = det_total;
	}

	public int getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}

	public int getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(int factura_id) {
		this.factura_id = factura_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
	
	
	
	
	
}

package ec.edu.ups.transaccion.sistema.Modelo;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Detalle_Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "det_id")
	private int det_id;
	
	private int det_cantidad;
	private double det_precio_unitario;
	private double det_subtotal;
	private double det_iva;
	private double det_total;
	private int producto_id;
	
    @Column(name = "factura_id", nullable = true, insertable = false, updatable = false)
    @JsonbTransient
	private int factura_id;
	
	
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
	
	
}

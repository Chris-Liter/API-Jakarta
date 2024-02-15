package ec.edu.ups.transaccion.sistema.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fac_id")
	private int fac_id;
	
	private int fac_tipo;
	
	private String fac_fecha;
	private double fac_subTotal;
	private double fac_total_iva;
	private double fac_total;
	private int id_cliente;
	
	
	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public int getFac_tipo() {
		return fac_tipo;
	}
	public void setFac_tipo(int fac_tipo) {
		this.fac_tipo = fac_tipo;
	}
	
	public String getFac_fecha() {
		return fac_fecha;
	}
	public void setFac_fecha(String fac_fecha) {
		this.fac_fecha = fac_fecha;
	}
	
	public double getFac_subTotal() {
		return fac_subTotal;
	}
	public void setFac_subTotal(double fac_subTotal) {
		this.fac_subTotal = fac_subTotal;
	}
	public double getFac_total_iva() {
		return fac_total_iva;
	}
	public void setFac_total_iva(double fac_total_iva) {
		this.fac_total_iva = fac_total_iva;
	}
	public double getFac_total() {
		return fac_total;
	}
	public void setFac_total(double fac_total) {
		this.fac_total = fac_total;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	
   
	
	
}

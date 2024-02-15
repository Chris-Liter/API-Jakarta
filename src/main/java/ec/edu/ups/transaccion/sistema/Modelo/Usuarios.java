package ec.edu.ups.transaccion.sistema.Modelo;

import jakarta.persistence.*;

@Entity
public class Usuarios {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private int id;

     @Column(name = "Nombre", nullable = false)
	 private String nombre;

	 @Column(name = "Email", nullable = false, unique = true)
	 private String email;

	 @Column(name = "FechaNacimiento", nullable = false)
	 private String fechaNacimiento;

	 @Column(name = "passwords", nullable = false)
	 private String passwords;
	 
	 @Column(name = "saldo")
	 private double saldo;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	

	
	
	
	
}

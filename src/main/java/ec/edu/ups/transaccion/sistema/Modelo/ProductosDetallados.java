package ec.edu.ups.transaccion.sistema.Modelo;

public class ProductosDetallados {

	private int id;
	private String codigo_producto;
	private String nombre;
	private double precio;
	private int stock;
	private double iva;
	private int id_categoria;
	private byte[] foto;
	private String fotoBase64;
	private int id_usuario;
	private String Nombre;
	
	
	public ProductosDetallados(int id, String codigo, String nombre, double precio, int stock, double iva,
			int id_categoria, byte[] foto, int id_usuario, String Nombre) {
		super();
		this.id = id;
		this.codigo_producto = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.iva = iva;
		this.id_categoria = id_categoria;
		this.foto = foto;
		this.id_usuario = id_usuario;
		this.Nombre = Nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo_producto;
	}
	public void setCodigo(String codigo) {
		this.codigo_producto = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getnombre() {
		return Nombre;
	}
	public void setnombre(String nombre) {
		Nombre = nombre;
	}
	
	
	
	
}

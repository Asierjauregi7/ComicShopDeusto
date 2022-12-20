package es.deusto.prog3.g32;

public class Carrito {
	private int id;
	private String editorial;
	private String titulo;
	private String genero;
	private int precio;
	private int cantidad;
	private int saldo;
	
	//Constructor
	public Carrito(int id, String editorial, String titulo, String genero, int precio, int cantidad, int saldo) {
		super();
		this.id = id;
		this.editorial = editorial;
		this.titulo = titulo;
		this.genero = genero;
		this.precio = precio;
		this.cantidad = cantidad;
		this.saldo = saldo;
	}

	
	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	
	//ToString
	@Override
	public String toString() {
		return "Carrito [id=" + id + ", editorial=" + editorial + ", titulo=" + titulo + ", genero=" + genero
				+ ", precio=" + precio + ", cantidad=" + cantidad + ", saldo=" + saldo + "]";
	}
	
	

}

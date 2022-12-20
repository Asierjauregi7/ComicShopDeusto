package es.deusto.prog3.g32;

public class Comic {
	private int id;
	private String editorial;
	private String titulo;
	private Genero genero;
	private int precio;
	private int cantidad;
	
	//Constructor
	public Comic(int id, String editorial, String titulo, Genero genero, int precio, int cantidad) {
		super();
		this.id = id;
		this.editorial = editorial;
		this.titulo = titulo;
		this.genero = genero;
		this.precio = precio;
		this.cantidad = cantidad;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
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

	
	//ToString
	@Override
	public String toString() {
		return "Comic [id=" + id + ", editorial=" + editorial + ", titulo=" + titulo + ", genero=" + genero
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
	
	

}

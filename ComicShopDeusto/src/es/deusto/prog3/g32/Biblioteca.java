package es.deusto.prog3.g32;

public class Biblioteca {
	private int id;
	private String editorial;
	private String titulo;
	private String genero;
	private int saldo;
	
	//Constructor
	public Biblioteca(int id, String editorial, String titulo, String genero, int saldo) {
		super();
		this.id = id;
		this.editorial = editorial;
		this.titulo = titulo;
		this.genero = genero;
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

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	//ToString
	@Override
	public String toString() {
		return "Biblioteca [id=" + id + ", editorial=" + editorial + ", titulo=" + titulo + ", genero=" + genero
				+ ", saldo=" + saldo + "]";
	}
	
	

}

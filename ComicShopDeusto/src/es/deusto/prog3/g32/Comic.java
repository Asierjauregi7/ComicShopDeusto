package es.deusto.prog3.g32;

import Practica3D.src.es.deusto.prog3.practica3d.Comic;
import Practica3D.src.es.deusto.prog3.practica3d.Personaje;
import Practica3D.src.es.deusto.prog3.practica3d.String;
import Practica3D.src.es.deusto.prog3.practica3d.StringTokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
	
	//Crea un Comic a partir de una cadena de texto separada por comas.
		public static Comic parseCSV(String csvString) {
			if (csvString != null && !csvString.isBlank()) {		
				StringTokenizer tokenizer = new StringTokenizer(csvString, ";");
				
				Comic comic = new Comic();			
				comic.setEditorial(tokenizer.nextToken());
				comic.setTitulo(tokenizer.nextToken());
				
				Personaje personaje;
				
				//Los personajes sólo tienen nombre
				while(tokenizer.hasMoreTokens()) {
					personaje = new Personaje();
					personaje.setNombre(tokenizer.nextToken());
					comic.addPersonaje(personaje);
				}

				return comic;
			} else {
				return null;
			}
		}

	
	//ToString
	@Override
	public String toString() {
		return "Comic [id=" + id + ", editorial=" + editorial + ", titulo=" + titulo + ", genero=" + genero
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
	
	

}

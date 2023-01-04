package es.deusto.prog3.g32;

public class Compra {
	private int idCompra;
	private String correoCliente;
	private int idProducto;
	private String editorialProducto;
	private String tituloProducto;
	private String genero;
	private int precio;
	private int cantidad;
	
	
	//Constructor
	public Compra(int idCompra, String correoCliente, int idProducto, String editorialProducto,
			String tituloProducto, String genero, int precio, int cantidad) {
		super();
		this.idCompra = idCompra;
		this.correoCliente = correoCliente;
		this.idProducto = idProducto;
		this.editorialProducto = editorialProducto;
		this.tituloProducto = tituloProducto;
		this.genero = genero;
		this.precio = precio;
		this.cantidad = cantidad;
	}


	//Getters & Setters
	public int getIdCompra() {
		return idCompra;
	}


	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}


	public String getCorreoCliente() {
		return correoCliente;
	}


	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}


	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public String getEditorialProducto() {
		return editorialProducto;
	}


	public void setEditorialProducto(String editorialProducto) {
		this.editorialProducto = editorialProducto;
	}


	public String getTituloProducto() {
		return tituloProducto;
	}


	public void setTituloProducto(String tituloProducto) {
		this.tituloProducto = tituloProducto;
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


	//ToString
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", correoCliente=" + correoCliente + ", idProducto=" + idProducto
				+ ", editorialProducto=" + editorialProducto + ", tituloProducto=" + tituloProducto + ", genero="
				+ genero + ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
	

}

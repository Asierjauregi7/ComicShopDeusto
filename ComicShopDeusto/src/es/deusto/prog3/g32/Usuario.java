package es.deusto.prog3.g32;

import es.deusto.prog.bbdd.GestorBD;

public class Usuario {
	private String nombre;
	private String apellidos;
	private String correo;
	private String nomUsuario;
	private String contraseña;
	private int saldo;
	
	//Constructor
	public Usuario(String nombre, String apellidos, String correo, String nomUsuario, String contraseña, int saldo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.nomUsuario = nomUsuario;
		this.contraseña = contraseña;
		this.saldo = saldo;
	}

	public Usuario() {	
		GestorBD.Conexion();
	}
	
	// Getters & Setters

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getNomUsuario() {
		return nomUsuario;
	}


	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public int getSaldo() {
			return saldo;
		}

		public void setSaldo(int saldo) {
			this.saldo = saldo;
		}




	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo + ", nomUsuario="
				+ nomUsuario + ", contraseña=" + contraseña + ", saldo=" + saldo +"]";
	}
	
	
	
	

}

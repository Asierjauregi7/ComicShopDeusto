package es.deusto.prog3.g32;

import es.deusto.prog.bbdd.GestorBD;

public class Pago {
	private int cuentaBancaria;
	private String caducidad;
	private int cvv;
	
	//Constructor
	public Pago(int cuentaBancaria, String caducidad, int cvv) {
		super();
		this.cuentaBancaria = cuentaBancaria;
		this.caducidad = caducidad;
		this.cvv = cvv;
	}

	public Pago() {
		GestorBD.Conexion();
	}
	//Getters & Setters
	public int getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(int cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	
	//ToString
	@Override
	public String toString() {
		return "Pago [cuentaBancaria=" + cuentaBancaria + ", caducidad=" + caducidad + ", cvv=" + cvv + "]";
	}
	

}

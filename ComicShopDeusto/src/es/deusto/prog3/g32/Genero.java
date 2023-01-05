package es.deusto.prog3.g32;

import es.deusto.prog.bbdd.GestorBD;

public enum Genero {
	Comedia, Terror, Accion, Aventura;
	


private Genero() {
	GestorBD.Conexion();
}
}
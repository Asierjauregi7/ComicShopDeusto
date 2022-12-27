package es.deusto.prog3.g32;

import es.deusto.prog.bbdd.GestorBD;
import es.deusto.prog3.gui.VentanaInicioSesion;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			GestorBD.crearBBDD();
			
			
			Usuario u = new Usuario();
			u.setNombre("Asier");
			u.setApellidos("Jauregi");
			u.setCorreo("asier.jauregi@opendeusto.es");
			u.setNomUsuario("asier");
			u.setContraseña("asier");
			
			

			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}

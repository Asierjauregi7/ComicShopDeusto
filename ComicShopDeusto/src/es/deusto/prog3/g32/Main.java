package es.deusto.prog3.g32;


import java.sql.Connection;
import java.util.List;

import javax.swing.SwingUtilities;

import es.deusto.prog.bbdd.GestorBD;
import es.deusto.prog3.gui.VentanaInicioSesion;

public class Main {
	
	public static void main(String[] args) {
		
		//try {
			//GestorBD.crearBBDD();

			//} catch (Exception e) {
				//e.printStackTrace();
			//}
		
		GestorBD gestorBD = new GestorBD();
				
		//Se crea la BBDD
		//GestorBD.crearBBDD();
		Connection conex = null;
		conex = GestorBD.Conexion();
		//Se cargan los datos y se inicializa la BBDD
		gestorBD.initializeFromCSV();
		
		//Se recupera la lista de Comics y Personajes
		//List<Comic> comics = gestorBD.getComics1();
		List<Comic> comics = gestorBD.loadCVSComics();
		
				
		//Lambda expression para abrir la ventana Principal
		//SwingUtilities.invokeLater(() -> new VentanaInicioSesion());
		VentanaInicioSesion ventana = new VentanaInicioSesion();
		ventana.setVisible(true);
		//Se borran los datos
		//gestorBD.borrarDatos();
				
		//Se borra la BBDD
		//gestorBD.borrarBBDD();		
	}	
		
	}


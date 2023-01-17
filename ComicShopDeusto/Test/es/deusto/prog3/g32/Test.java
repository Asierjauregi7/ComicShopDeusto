package es.deusto.prog3.g32;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import BaseDeDatos.BaseDeDatos;
import JUnit.Before;
import JUnit.Test;
import clases.Carrito;
import clases.Cliente;
import es.deusto.prog.bbdd.GestorBD;
import excepciones.MiExcepcionExplicita;

public class Test {

	@org.junit.Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Before
	public void before() throws Exception {
		Connection conex = null;
		conex = GestorBD.Conexion();
	}
	
	@Test
	public void insertarComics() {
		ArrayList<Comic> comics = GestorBD.getComics1();
		assertEquals(5, comics.size());
	}
	
	@Test
	public void testInsertarCarrito() {
		ArrayList<Carrito> ALCarritoAntes = GestorBD.getCarrito();
		GestorBD.crearCarrito(new Carrito(123, "editorial", "Mortadelo y Filemon", Genero.Comedia, 20, 2));
		GestorBD.crearCarrito(new Carrito(124, "editorial", "Superman", Genero.Aventura, 18, 1));
		ArrayList<Carrito> ALCarritoDespues = GestorBD.getCarrito();
		assertEquals(ALCarritoAntes.size()+2, ALCarritoDespues.size());

	}
	
	@Test
	public void testInsertarCliente() {
		ArrayList<Usuario> ALClientesAntes = GestorBD.getUsuarios();
		GestorBD.insertarUsuario(new Usuario("Iker", "Godoy Ubillos", "ikerg.2002@gmail.com", "Ubigoker", "12345678", 20));
		ArrayList<Usuario> ALClientesDespues = GestorBD.getUsuarios();
		assertEquals(ALClientesAntes.size()+1, ALClientesDespues.size());
	

	}
	
	



}

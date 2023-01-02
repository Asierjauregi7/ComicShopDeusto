package es.deusto.prog.bbdd;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import es.deusto.prog3.g32.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorBD {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "db/BaseDeDatos.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
	public GestorBD () {
		try {
			//Cargar el driver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println(String.format("Error al cargar el driver de BBDD: %s", e.getMessage()));
		}
	}
	
	
	public static void crearBBDD() {
		//Se abre la conexión y se obtiene el statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			 Statement stmt = con.createStatement()) {
			String comic = "CREATE TABLE IF NOT EXISTS COMIC(\n"
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
					+ "Editorial TEXT NOT NULL,\n"
					+ "Titulo TEXT NOT NULL,\n"
					+ "Genero TEXT NOT NULL,\n"
					+ "Precio INTEGER NOT NULL,\n"
					+ "Cantidad INTEGER NOT NULL);";
			
			
			String biblioteca = "CREATE TABLE IF NOT EXISTS BIBLIOTECA(\n"
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
					+ "Editorial TEXT NOT NULL,\n"
					+ "Titulo TEXT NOT NULL,\n"
					+ "Genero TEXT NOT NULL,\n"
					+ "Saldo INTEGER NOT NULL);";
			
			String carrito = "CREATE TABLE IF NOT EXISTS CARRITO(\n"
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
					+ "Editorial TEXT NOT NULL,\n"
					+ "Titulo TEXT NOT NULL,\n"
					+ "Genero TEXT NOT NULL,\n"
					+ "Precio INTEGER NOT NULL,\n"
					+ "Cantidad INTEGER NOT NULL);";
			
			
			String usuario = "CREATE TABLE IF NOT EXISTS USUARIO(\n"
					+ "Nombre TEXT NOT NULL,\n"
					+ "Apellidos TEXT NOT NULL,\n"
					+ "Correo TEXT NOT NULL,\n"
					+ "NomUsuario TEXT PRIMARY KEY NOT NULL,\n"
					+ "Contraseña TEXT NOT NULL,\n"
					+ "Saldo INTEGER NOT NULL);";
			
			String compra = "CREATE TABLE IF NOT EXISTS COMPRA(\n"
					+ "idCompra INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
					+ "correoCliente TEXT NOT NULL,\n"
					+ "idProducto INT NOT NULL,\n"
					+ "EditorialProducto TEXT NOT NULL,\n"
					+ "TituloProducto TEXT NOT NULL,\n"
					+ "Genero TEXT NOT NULL,\n"
					+ "Precio INTEGER NOT NULL,\n"
					+ "Cantidad INTEGER NOT NULL);";
			
			
			String cogerCliente = "CREATE TABLE IF NOT EXISTS COGERCLIENTE(\n"
					+ "Correo TEXT PRIMARY KEY NOT NULL);";
			
			//Se ejecuta la sentencia de la creación de la BBDD
			if (!stmt.execute(comic) && !stmt.execute(biblioteca) && !stmt.execute(carrito) && !stmt.execute(usuario) && !stmt.execute(compra) && !stmt.execute(cogerCliente)) {
				System.out.println("Se ha creado la BBDD");
			}
			
			
		} catch (Exception e) {
			System.err.println(String.format("Error al crear la BBDD: %s", e.getMessage()));
		}
	}
	
	
	
	public static void borrarBBDD() {
		//Se abre la conexión y se obtiene el statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			 Statement stmt = con.createStatement()) {
			String sql = "DROP DATABASE '"+CONNECTION_STRING+"'";
			
			//Se ejecuta la sentencia del borrado de la BBDD
			if (!stmt.execute(sql)) {
				System.out.println("Se ha borrado la BBDD");
			}
		} catch (Exception e) {
			System.err.println(String.format("Error al borrar las tablas: %s", e.getMessage()));
		}
	}
	
	
	public static void borrarComics() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "DELETE * FROM COMIC";
			
			if (!stmt.execute(sql)) {
				System.out.println("Se ha borrado la BBDD");
			}
		}catch(Exception e) {
			System.out.println(e);
			
		}
	}
	
	
	//Metodo para recivir los comics
	
	public static ArrayList<Comic> getComics() {
		ArrayList<Comic> ret = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMIC";
			ResultSet rs = ((Statement) con).executeQuery(sql);
			while (rs.next()) { // Leer el resultset
				int id = rs.getInt("id");
				String editorial = rs.getString("editorial");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				int precio = rs.getInt("precio");
				int cantidad = rs.getInt("cantidad");
				
				ret.add(new Comic(id, editorial, titulo, Genero.valueOf(genero), precio, cantidad)); 
				
				}
			}catch(Exception e) {
				System.out.println(e);
				
			}
			return ret;
			
	}
	
	
	//Metodo para recivir el carrito
	
		public static ArrayList<Carrito> getCarrito() {
			ArrayList<Carrito> ret = new ArrayList<>();
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
				String sql = "SELECT * FROM CARRITO";
				//"Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"
				ResultSet rs = ((Statement) con).executeQuery(sql);
				while (rs.next()) { // Leer el resultset
					int id = rs.getInt("id");
					String editorial = rs.getString("editorial");
					String titulo = rs.getString("titulo");
					String genero = rs.getString("genero");
					int precio = rs.getInt("precio");
					int cantidad = rs.getInt("cantidad");
					
					ret.add(new Carrito(id, editorial, titulo, Genero.valueOf(genero), precio, cantidad)); 
					
					}
				}catch(Exception e) {
					System.out.println(e);
					
				}
				return ret;
				
		}
	
	//Metodo para eliminar usuarios
	
	public static void EliminarUsuarioDeBaseDeDatos() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "DELETE * FROM COGERCLIENTE";
			((Statement) con).executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Metodo para coger el correo del usuario conectado
	

	public static String cargarCorreoUsuario() {
		String ret = "";
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "SELECT CORREO FROM COGERCLIENTE";
		ResultSet rs = ((Statement) con).executeQuery(sql);
		while (rs.next()) { // Leer el resultset
			String correo = rs.getString("correo");
			ret = correo;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		
	}
	
	
	//Metodo para coger saldo del usuario
	
	public static int cargarSaldoUsuario(String correo) {
		int ret = 0;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "SELECT SALDO FROM USUARIO WHERE CORREO = '"+correo+"';";
		ResultSet rs = ((Statement) con).executeQuery(sql);
			while (rs.next()) { // Leer el resultset
				int saldo = rs.getInt("saldo");
				ret = saldo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
			
		
	}
	
	
	//Metodo para recivir los comics por genero
	
	public static ArrayList<Comic> cargarComicsPorGenero(String genero) {
		ArrayList<Comic> ret = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMIC WHERE GENERO = '"+genero+"';";
		ResultSet rs = ((Statement) con).executeQuery(sql);
		while (rs.next()) { // Leer el resultset
			int id = rs.getInt("id");
			String editorial = rs.getString("editorial");
			String titulo = rs.getString("titulo");
			String genero1 = rs.getString("genero");
			int precio = rs.getInt("precio");
			int cantidad = rs.getInt("cantidad");
			
			ret.add(new Comic(id, editorial, titulo, Genero.valueOf(genero1), precio, cantidad)); 
			
			}
		}catch(Exception e) {
			System.out.println(e);
			
		}
		return ret;
	}
	
	
	
	//Metodo para borrar el carrito
	
	public static void EliminarCarrito() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "DELETE * FROM CARRITO";
			((Statement) con).executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Metodo que elimina de la tabla carrito un producto determinado
	
	public static void EliminarDelCarrito(String Titulo) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "DELETE * FROM CARRITO WHERE TITULO = '"+Titulo+"';";
			((Statement) con).executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	//Metodo que carga las compras del usuario
	
	public static ArrayList<Compra> cargarComprasUsuario(String correo) {
		ArrayList<Compra> ret = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMPRA WHERE CORREO = '"+correo+"';";
		ResultSet rs = ((Statement) con).executeQuery(sql);
			while (rs.next()) {
				int idCompra = rs.getInt("idCompra");
				String correoCliente = rs.getString("correoCliente");
				int idProducto = rs.getInt("idProducto");
				String editorialProducto = rs.getString("editorialProducto");
				String tituloProducto = rs.getString("tituloProducto");
				String genero = rs.getString("genero");
				int precio = rs.getInt("precio");
				int cantidad = rs.getInt("cantidad");
				
				
				ret.add(new Compra(idCompra, correoCliente, idProducto, editorialProducto, tituloProducto, genero, precio, cantidad));
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
	//Metodo para crear carrito
	
	public static void crearCarrito(Carrito carro) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "INSERT INTO CARRITO (ID, EDITORIAL, TITULO, GENERO, PRECIO, CANTIDAD) VALUES('" + carro.getId() + "','" + carro.getEditorial() + "', '" + carro.getTitulo() + "', '"
					+ carro.getGenero() + "', '" + carro.getPrecio() +"', '" + carro.getCantidad() + "')";
			((Statement) con).executeUpdate(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Metodo para crear compra
	
		public static void crearCompra(Compra compra) {
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
				String sql = "INSERT INTO COMPRA (IDCOMPRA, CORREOCLIENTE, IDPRODUCTO, EDITORIALPRODUCTO, TITULOPRODUCTO, GENERO, PRECIO, CANTIDAD) VALUES('" + compra.getIdCompra() + "','" + compra.getCorreoCliente() +"','" + compra.getIdProducto() +"','" + compra.getEditorialProducto() + "', '" + compra.getTituloProducto() + "', '"
						+ compra.getGenero() + "', '" + compra.getPrecio() +"', '" + compra.getCantidad() + "')";
				((Statement) con).executeUpdate(sql);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	
	//MÉTODOS PARA INSERTAR DATOS A LA BBDD
	
	
	
	public static void insertarComic(Comic... listaComics) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			 Statement stmt = con.createStatement()) {
			 for (Comic c : listaComics) {
				 String editorial = c.getEditorial();
				 String titulo = c.getTitulo();
				 Genero genero = c.getGenero();
				 int precio = c.getPrecio();
				 int cantidad = c.getCantidad();
				 String sql = "INSERT INTO COMIC (Editorial,Titulo,Genero,Precio,Cantidad)" + "VALUES('" + editorial + "','" + titulo
							+ "','" + genero + "','" + precio + "','" + cantidad + "');";
				 
				 stmt.executeUpdate(sql);
				 
			 }
		} catch (Exception e) {
			System.out.println(String.format("Error insertando comics: ", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
	public static void insertarUsuarios(Usuario... listaUsuariosInsertado) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); 
			 Statement stmt = con.createStatement()) {
			 for (Usuario u : listaUsuariosInsertado) {
				String nombre = u.getNombre();
				String apellidos = u.getApellidos();
				String correo = u.getCorreo();
				String nombreUsuario = u.getNomUsuario();
				String contraseña = u.getContraseña();
				String sql = "INSERT INTO USUARIO (Nombre,Apellidos,Correo,NomUsuario,Contraseña)" 
				+ "VALUES(' " + nombre + "','" + apellidos + "','" + correo + "','" + nombreUsuario + "','" + contraseña + "');";
				stmt.executeUpdate(sql);
				
				System.out.println(nombre);

			}
		} catch (Exception e) {
			System.out.println(String.format("Error insertando usuarios: ", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	//Métodos para insertar datos o devolver datos de la BBDD usando CSV
	
	
	public static ArrayList<Usuario> crearListaDeUsuariosConCSV(String ruta) {
		ArrayList<Usuario> listaUsuarios = null;
		try {

			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String strLine = "";
			StringTokenizer st = null;
			listaUsuarios = new ArrayList<>();

			while ((strLine = br.readLine()) != null) {
				// break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				Usuario u = new Usuario();

				u.setNombre(st.nextToken());

				u.setApellidos(st.nextToken());
				String f = st.nextToken();

				/*
				 * aqui cambiamos el String del csv a LocalDateTimeDateTimeFormatter formatter =
				 * DateTimeFormatter. ofPattern("yyyy-MM-dd"); LocalDateTime dateTime =
				 * LocalDateTime. parse(f); u.setFechaNacimiento(dateTime);
				 */

				
				// añadimos el usuario de esta linea a la lista
				listaUsuarios.add(u);

			}
			//ERROR: hay que cerrar el BufferedReader
			br.close();
			System.out.println("NOMBRES:");
			for (Usuario u : listaUsuarios) {
				System.out.println("#" + u.getNombre());
			}

		} catch (Exception e) {
			System.out.println(String.format("Error creando lista usuarios con CSV: ", e.getMessage()));
		}
		return listaUsuarios;

	}

	public static void crearCSVusuarios(ArrayList<Usuario> listaUsuarios, String rutaNuevoFichero) {
		try {
			// Creamos el escritor
			File file = new File(rutaNuevoFichero);
			PrintWriter pr = new PrintWriter(file);
			// Recorremos los usuarios uno por uno
			for (Usuario u : listaUsuarios) {
				pr.println("" + u.getNombre() + ";" + u.getApellidos() + ";" + u.getCorreo() + ";" + u.getNomUsuario() + ";" + u.getContraseña());
				// comprobacion
				System.out.println("#" + u.getNombre());
			}
			pr.close();
		} catch (Exception e) {
			System.out.println(String.format("Error creando CSV usuarios: ", e.getMessage()));
		}

	}
	
	
}

	
	


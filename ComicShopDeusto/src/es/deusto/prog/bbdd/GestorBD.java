package es.deusto.prog.bbdd;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import es.deusto.prog3.g32.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;


import es.deusto.prog3.g32.Comic;

import java.io.File;
import java.io.PrintWriter;
import java.lang.System.Logger.Level;
import java.sql.SQLException;

public class GestorBD {
	
	public static final String connectionString = "jdbc:sqlite:ComicShopDeusto/db/BaseDeDatos.db";
	private final String PROPERTIES_FILE = "conf/app.properties";
	private final String CSV_COMICS = "ComicShopDeusto/src/es/deusto/prog3/data/comics1.csv";
	
	private Properties properties;
	private String driverName = "org.sqlite.JDBC";
	private String databaseFile = "ComicShopDeusto/db/BaseDeDatos.db";
	private static String url = "jdbc:sqlite:ComicShopDeusto/db";
	
	private static Logger logger = Logger.getLogger(GestorBD.class.getName());
	
	//public GestorBD() {
		//try (FileInputStream fis = new FileInputStream("conf/logger.properties")) {
		//Inicialización del Logger
			//LogManager.getLogManager().readConfiguration(fis);
			
			//Lectura del fichero properties
			//properties = new Properties();
			//properties.load(new FileReader(PROPERTIES_FILE));
			
			//driverName = properties.getProperty("driver");
			//databaseFile = properties.getProperty("file");
			//url = properties.getProperty("connection");
			
			//Cargar el driver SQLite
			//Class.forName(driverName);
		//} catch (Exception ex) {
			//logger.warning(String.format("Error al cargar el driver de BBDD: %s", ex.getMessage()));
		//}
	//}
	
	/**
	 * Inicializa la BBDD leyendo los datos de los ficheros CSV 
	 */
	public void initializeFromCSV() {
		//Sólo se inicializa la BBDD si la propiedad initBBDD es true.
		//if (properties.get("loadCSV").equals("true")) {
			//Se borran los datos, si existía alguno
			//this.borrarDatos();
		    GestorBD.borrarComics();
			
			
			//Se leen los comics del CSV
			List<Comic> comics = this.loadCVSComics();				
			
	        System.out.println(comics);
			
			//Se insertan los comics en la BBDD
			GestorBD.insertarComic(comics.toArray(new Comic[comics.size()]));				
		//}
	}
	
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "ComicShopDeusto/db/BaseDeDatos.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
	public GestorBD () {
		try {
			//Cargar el driver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
		}
	}
	
	public static Connection Conexion(){
        /*Declaramos una variable para almacenar la cadena de conexión.
        Primero la iniciamos en null.*/
        Connection conex = null;
         
        //Controlamos la excepciones que puedan surgir al conectarnos a la BBDD
        try {
            //Registrar el driver
            Class.forName(DRIVER_NAME);
            //Creamos una conexión a la Base de Datos
            conex = DriverManager.getConnection(CONNECTION_STRING);
         
        // Si hay errores informamos al usuario. 
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos.\n"
                    + e.getMessage().toString());
        }
        // Devolvemos la conexión.
        return conex;
	}
	
	
	
	
	public static void crearBBDD() {
		//Se abre la conexión y se obtiene el statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			 Statement stmt = con.createStatement()){
			 
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
					+ "correoCliente TEXT NOT NULL,\n"
					+ "idProducto INT PRIMARY KEY NOT NULL,\n"
					+ "EditorialProducto TEXT NOT NULL,\n"
					+ "TituloProducto TEXT NOT NULL,\n"
					+ "Genero TEXT NOT NULL,\n"
					+ "Precio INTEGER NOT NULL,\n"
					+ "Cantidad INTEGER NOT NULL);";
			
			
			String cogerCliente = "CREATE TABLE IF NOT EXISTS COGERCLIENTE(\n"
					+ "Correo TEXT NOT NULL);";
			
			
	        //Se abre la conexión y se crea un PreparedStatement para cada tabla
			//Al abrir la conexión, si no existía el fichero, se crea la base de datos
			//try (Connection con1 = DriverManager.getConnection(connectionString);
			     //PreparedStatement pStmt1 = con.prepareStatement(comic);
				 //PreparedStatement pStmt2 = con.prepareStatement(biblioteca);
				 //PreparedStatement pStmt3 = con.prepareStatement(carrito);
				 //PreparedStatement pStmt4 = con.prepareStatement(usuario);
				 //PreparedStatement pStmt5 = con.prepareStatement(compra);
				 //PreparedStatement pStmt6 = con.prepareStatement(cogerCliente); 
				
				//Se ejecutan las sentencias de creación de las tablas
				if (!stmt.execute(comic) && !stmt.execute(biblioteca) && !stmt.execute(carrito) && !stmt.execute(usuario) && !stmt.execute(compra) && !stmt.execute(cogerCliente)) {
		        	logger.info("Se han creado las tablas");
		        }
		} catch (Exception ex) {
			logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
		
			}
		//}
					
			//Se ejecuta la sentencia de la creación de la BBDD
				//if (!stmt.execute(comic) && !stmt.execute(biblioteca) && !stmt.execute(carrito) && !stmt.execute(usuario) && !stmt.execute(compra) && !stmt.execute(cogerCliente)) {
					//System.out.println("Se ha creado la BBDD");
				//}
		//}catch (Exception ex) {
					//System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
					//ex.printStackTrace();
				//}
		
			
		}
	
	
	
	
	public static void borrarBBDD() {
		//Se abre la conexión y se obtiene el statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(connectionString);
			 Statement stmt = con.createStatement()) {
			String sql = "DROP DATABASE '"+connectionString+"'";
			
			//Se ejecuta la sentencia del borrado de la BBDD
			if (!stmt.execute(sql)) {
				System.out.println("Se ha borrado la BBDD");
			}
		} catch (Exception e) {
			System.err.println(String.format("Error al borrar las tablas: %s", e.getMessage()));
		}
	}
	
	
	public static void borrarComics() {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "DELETE FROM COMIC;";
			
			if (!stmt.execute(sql)) {
				System.out.println("Se han borrado los comics");
			}
		}catch(Exception e) {
			System.out.println(e);
			
		}
	}
	
	
	//Metodo para recibir los comics
	
	public static ArrayList<Comic> getComics() {
		ArrayList<Comic> ret = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMIC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { // Leer el resultset
				int id = rs.getInt("id");
				String editorial = rs.getString("editorial");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				int precio = rs.getInt("precio");
				int cantidad = rs.getInt("cantidad");
				
				//ret.add(new Comic(id, editorial, titulo, Genero.valueOf(genero), precio, cantidad)); 
				
				}
			}catch(Exception e) {
				System.out.println(e);
				
			}
			return ret;
			
	}
	
	
	//Otro metodo para recibir comics
	
	public static ArrayList<Comic> getComics1() {
		ArrayList<Comic> comics = new ArrayList<>();
		//String sql = "SELECT * FROM Comic";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		//try (Connection con = DriverManager.getConnection(connectionString);
		     //PreparedStatement pStmt = con.prepareStatement(sql)) {	
		
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMIC";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Comic comic;
			
			//Se recorre el ResultSet y se crean los Comics
			while (rs.next()) {
				comic = new Comic();
				
				comic.setId(rs.getInt("id"));
				comic.setEditorial(rs.getString("editorial"));
				comic.setTitulo(rs.getString("titulo"));
				comic.setGenero(Genero.valueOf(rs.getString("genero")));
				comic.setPrecio(rs.getInt("precio"));
				comic.setCantidad(rs.getInt("cantidad"));
				
				
				//Se inserta cada nuevo cliente en la lista de clientes
				comics.add(comic);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se han recuperado %d comics", comics.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los comics: %s", ex.getMessage()));						
		}		
		
		return comics;
	}
	
	
	//Metodo para recibir usuarios
	public static ArrayList<Usuario> getUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		String sql = "SELECT * FROM Usuario";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = pStmt.executeQuery();			
			Usuario usuario;
			
			//Se recorre el ResultSet y se crean los Comics
			while (rs.next()) {
				usuario = new Usuario();
				
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setNomUsuario(rs.getString("nomUsuario"));
				usuario.setContraseña(rs.getString("contraseña"));
				usuario.setSaldo(rs.getInt("saldo"));
				
				
				//Se inserta cada nuevo cliente en la lista de clientes
				usuarios.add(usuario);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se han recuperado %d comics", usuarios.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los comics: %s", ex.getMessage()));						
		}		
		
		return usuarios;
	}
	
	//Metodo para recibir el carrito
	
		public static ArrayList<Carrito> getCarrito() {
			ArrayList<Carrito> ret = new ArrayList<>();
			try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
				String sql = "SELECT * FROM CARRITO";
				//"Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"
				ResultSet rs = stmt.executeQuery(sql);
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
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "DELETE FROM COGERCLIENTE";
			stmt.executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Metodo para coger el correo del usuario conectado
	
	public static String cargarCorreoUsuario() {
		String ret = "";
		//String sql = "SELECT CORREO FROM COGERCLIENTE";
		//try (Connection con = DriverManager.getConnection(connectionString); PreparedStatement stmt = con.prepareStatement(sql)) {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {	
		String sql = "SELECT CORREO FROM COGERCLIENTE";
	    ResultSet rs = stmt.executeQuery(sql);
		
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
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
		String sql = "SELECT SALDO FROM USUARIO WHERE CORREO = '"+correo+"';";
		ResultSet rs = stmt.executeQuery(sql);
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
	
	
	//Metodo para recibir los comics por genero
	
	public static ArrayList<Comic> cargarComicsPorGenero(String genero) {
		ArrayList<Comic> ret = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
		String sql = "SELECT * FROM COMIC WHERE GENERO = '"+genero+"';";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) { // Leer el resultset
			int id = rs.getInt("id");
			String editorial = rs.getString("editorial");
			String titulo = rs.getString("titulo");
			String genero1 = rs.getString("genero");
			int precio = rs.getInt("precio");
			int cantidad = rs.getInt("cantidad");
			
			//ret.add(new Comic(id, editorial, titulo, Genero.valueOf(genero1), precio, cantidad)); 
			
			}
		}catch(Exception e) {
			System.out.println(e);
			
		}
		return ret;
	}
	
	
	
	//Otro metodo para cargar comics por genero
	
	public static ArrayList<Comic> cargarComicsPorGenero1(String genero) {
		//Comic comic = null;
		ArrayList<Comic> ret = new ArrayList<>();
		//String sql = "SELECT * FROM COMIC WHERE GENERO = '"+genero+"';";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		//try (Connection con = DriverManager.getConnection(connectionString);
		     //PreparedStatement pStmt = con.prepareStatement(sql)) {	
		
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMIC WHERE GENERO = '"+genero+"';";
			
			//Se definen los parámetros de la sentencia SQL
			//pStmt.setString(1, genero);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			//ResultSet rs = pStmt.executeQuery(sql);			
			ResultSet rs = stmt.executeQuery(sql);			
			Comic comic;
			//Se procesa el único resultado
			while (rs.next()) {
				comic = new Comic();
				
				comic.setId(rs.getInt("id"));
				comic.setEditorial(rs.getString("editorial"));
				comic.setTitulo(rs.getString("titulo"));
				comic.setGenero(Genero.valueOf(rs.getString("genero")));
				comic.setPrecio(rs.getInt("precio"));
				comic.setCantidad(rs.getInt("cantidad"));
				
				ret.add(comic);
			}
			
			
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se ha recuperado el comic %s"));//, comic));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar el comic con nombre %s: %s", genero, ex.getMessage()));						
		}		
		
		return ret;
	}
	
	
	
	//Metodo para borrar el carrito
	
	public static void EliminarCarrito() {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "DELETE FROM CARRITO;";
			stmt.execute(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Metodo que elimina de la tabla carrito un producto determinado
	
	public static void EliminarDelCarrito(String Titulo) {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "DELETE * FROM CARRITO WHERE TITULO = '"+Titulo+"';";
			stmt.executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	//Metodo que carga las compras del usuario
	
	public static ArrayList<Compra> cargarComprasUsuario(String correo) {
		ArrayList<Compra> ret = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM COMPRA WHERE CORREOCLIENTE = '"+correo+"';";
		ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int idCompra = rs.getInt("idCompra");
				String correoCliente = rs.getString("correoCliente");
				int idProducto = rs.getInt("idProducto");
				String editorialProducto = rs.getString("editorialProducto");
				String tituloProducto = rs.getString("tituloProducto");
				String genero = rs.getString("genero");
				int precio = rs.getInt("precio");
				int cantidad = rs.getInt("cantidad");
				
				
				ret.add(new Compra(correoCliente, idProducto, editorialProducto, tituloProducto, genero, precio, cantidad));
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
	//Metodo para crear carrito
	
	public static void crearCarrito(Carrito carro) {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "INSERT INTO CARRITO (ID, EDITORIAL, TITULO, GENERO, PRECIO, CANTIDAD) VALUES('" + carro.getId() + "','" + carro.getEditorial() + "', '" + carro.getTitulo() + "', '"
					+ carro.getGenero() + "', '" + carro.getPrecio() +"', '" + carro.getCantidad() + "')";
			stmt.executeUpdate(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Metodo para crear compra
	
		public static void crearCompra(Compra compra) {
			try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
				String sql = "INSERT INTO COMPRA (CORREOCLIENTE, IDPRODUCTO, EDITORIALPRODUCTO, TITULOPRODUCTO, GENERO, PRECIO, CANTIDAD) VALUES('" + compra.getCorreoCliente() +"','" + compra.getIdProducto() +"','" + compra.getEditorialProducto() + "', '" + compra.getTituloProducto() + "', '"
						+ compra.getGenero() + "', '" + compra.getPrecio() +"', '" + compra.getCantidad() + "')";
				stmt.executeUpdate(sql);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	//Metodo para editar el saldo
		
	public static void actualizarSaldo(int saldo, String correo) {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "UPDATE USUARIO SET SALDO = '" + Integer.valueOf(saldo) + "' WHERE CORREO = '"+correo+"';";
			//String sql = "INSERT INTO USUARIO (SALDO) VALUE('" + saldo + "')";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	
	//Metodo para comprobar usuario
	public static boolean existeUsuarioEnBBDD(String correo, String contrasena) {
		try {
			ArrayList<Usuario> usuarios = GestorBD.getUsuarios();
			for (Usuario u : usuarios) {
				if (u.getCorreo() == correo && u.getContraseña() == contrasena) {
					return true;
				} else {
					return false;
				}
			}

		 }catch(Exception e) {
			System.out.println(e);
			
		}

		return false;
	}
	
	//Metodo para eliminar cantidad de comic cuando se añade al carrito
	public static void actualizarComicsCarrito(int cantidad, int idComic) {
		
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String sql = "UPDATE COMIC SET CANTIDAD = '" + cantidad + "' WHERE ID = '"+idComic+"';";
			//String sql = "INSERT INTO USUARIO (SALDO) VALUE('" + saldo + "')";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//MÉTODOS PARA INSERTAR DATOS A LA BBDD
	
	
	//Metodo para insertar un solo comic
	public static void insertarComic(Comic... comic) {
		try (Connection con = DriverManager.getConnection(connectionString);
			 Statement stmt = con.createStatement()) {
			for(Comic c: comic) {
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
	
	
	public static void insertarComics(ArrayList<Comic>listaComics) {
		try (Connection con = DriverManager.getConnection(connectionString);
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
	
	//Insertar un usuario
	public static void insertarUsuario(Usuario usuario) {
		try (Connection con = DriverManager.getConnection(connectionString); 
			 Statement stmt = con.createStatement()) {
			 
			String nombre = usuario.getNombre();
			String apellidos = usuario.getApellidos();
			String correo = usuario.getCorreo();
			String nombreUsuario = usuario.getNomUsuario();
			String contraseña = usuario.getContraseña();
			int saldo = usuario.getSaldo();
			String sql = "INSERT INTO USUARIO (Nombre,Apellidos,Correo,NomUsuario,Contraseña,Saldo)" 
			+ "VALUES(' " + nombre + "','" + apellidos + "','" + correo + "','" + nombreUsuario + "','" + contraseña + "','" + saldo + "');";
			stmt.executeUpdate(sql);
			
			System.out.println(nombre);

			
		} catch (Exception e) {
			System.out.println(String.format("Error insertando usuarios: ", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
	public static void insertarUsuarios(ArrayList<Usuario>listaUsuariosInsertado) {
		try (Connection con = DriverManager.getConnection(connectionString); 
			 Statement stmt = con.createStatement()) {
			 for (Usuario u : listaUsuariosInsertado) {
				String nombre = u.getNombre();
				String apellidos = u.getApellidos();
				String correo = u.getCorreo();
				String nombreUsuario = u.getNomUsuario();
				String contraseña = u.getContraseña();
				int saldo = u.getSaldo();
				String sql = "INSERT INTO USUARIO (Nombre,Apellidos,Correo,NomUsuario,Contraseña,Saldo)" 
				+ "VALUES(' " + nombre + "','" + apellidos + "','" + correo + "','" + nombreUsuario + "','" + contraseña + "','" + saldo + "');";
				stmt.executeUpdate(sql);
				
				System.out.println(nombre);

			}
		} catch (Exception e) {
			System.out.println(String.format("Error insertando usuarios: ", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
	//Metodo para coger usuario conectado
	public static Usuario usuarioPorCorreo(String correo) throws SQLException {
		Usuario usuario = null;
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM USUARIO WHERE Correo = '"+correo+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setApellidos(rs.getString("Apellidos"));
				usuario.setCorreo(rs.getString("Correo"));
				usuario.setContraseña("Contraseña");
				usuario.setSaldo(rs.getInt("Saldo"));

			}
			return usuario;

		}
	}
	
	
	//Metodo para recivir contraseña del usuario
	public static String getContraseñaUsuario(String usuario) {
		try (Connection con = DriverManager.getConnection(connectionString); Statement stmt = con.createStatement()) {
			String contraseniaDev = "";
			String sent = "select contrasenia from cliente where nomUsuario='"+usuario+"'";
			ResultSet rs = stmt.executeQuery(sent);
			while (rs.next()) { // Leer el resultset
				String contrasenia = rs.getString("contrasenia");
				contraseniaDev = contrasenia;
			}
			
			return contraseniaDev;
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	
	//Metodo para establecer el usuario
	public static void almacenarUsuarioVentana(String correo) {
		//String sent = "insert into cogerCliente (correo) values('" + correo + "')";
		//try (Connection con = DriverManager.getConnection(connectionString); PreparedStatement stmt = con.prepareStatement(sent)) {
			
			//stmt.executeUpdate(sent);
		
		try (Connection con = DriverManager.getConnection(connectionString); 
				 Statement stmt = con.createStatement()) {
			String sent = "INSERT INTO COGERCLIENTE (CORREO) VALUES('" + correo + "')";
			stmt.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
	public List<Comic> loadCVSComics() {
		List<Comic> comics = new ArrayList<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader(CSV_COMICS))) {
			String linea = null;
			
			//Omitir la cabecera
			in.readLine();			
			
			while ((linea = in.readLine()) != null) {
				comics.add(Comic.parseCSV(linea));
			}			
			
		} catch (Exception ex) {
			logger.warning(String.format("Error leyendo comics del CSV: %s", ex.getMessage()));
		}
		
		return comics;
	}
	
	
	
	public void borrarDatos() {
		//Sólo se borran los datos si la propiedad cleanBBDD es true
			
			String sql1 = "DELETE FROM BIBLIOTECA;";
			String sql2 = "DELETE FROM COMIC;";
			String sql3 = "DELETE FROM CARRITO;";
			String sql4 = "DELETE FROM USUARIO;";
			String sql5 = "DELETE FROM COMPRA;";
			String sql6 = "DELETE FROM COGERCLIENTE;";
			
	        //Se abre la conexión y se crea un PreparedStatement para cada tabla
			//Al abrir la conexión, si no existía el fichero, se crea la base de datos
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1);
				 PreparedStatement pStmt2 = con.prepareStatement(sql2);
				 PreparedStatement pStmt3 = con.prepareStatement(sql3);
				 PreparedStatement pStmt4 = con.prepareStatement(sql4);
				 PreparedStatement pStmt5 = con.prepareStatement(sql5);
				 PreparedStatement pStmt6 = con.prepareStatement(sql6)) {
				
				//Se ejecutan las sentencias de borrado de las tablas
		        if (!pStmt2.execute()) { //&& !pStmt2.execute() && !pStmt3.execute() && !pStmt4.execute() && !pStmt5.execute() && !pStmt6.execute()) {
		        	logger.info("Se han borrado los datos");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		
}
	
	
	public static void main(String[] args) {
        
        /*Declaramos una variable para almacenar la conexión que nos va a
        devolver el método Conexion(). Primero la iniciamos en null.*/
        //Connection conex=null;
        //crearBBDD();
        /*Almacenamos lo que nos devuelve el método Conexion()
        en la variable conex*/
        //conex = Conexion();
         
        // Si la variable objeto conex es diferente de nulo
        //if(conex != null){
            // Informamos que la conexión es correcta
            //System.out.println("Conectado correctamente");
        //}else{ // Sino informamos que no nos podemos conectar.
            //System.out.println("No has podido conectarte");
        //}
        
        
    }   
	
	
}



	
	


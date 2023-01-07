package es.deusto.prog3.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import es.deusto.prog.bbdd.GestorBD;
import es.deusto.prog3.g32.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import es.deusto.prog.bbdd.GestorBD;



public class VentanaBiblioteca extends JFrame{
	
	private JLabel logo; // }<-- Panel Superior
	private JPanel superiorLogo;
	private JButton btnInicio;
	private JButton btnComedia;
	private JButton btnTerror;
	private JButton btnAccion;
	private JButton btnAventura;
	private JButton btnCuenta;
	private JPanel superiorVentanas;
	private JButton btnCarrito;
	private JButton btnAniadirACarrito;
	private JLabel totalLbl;
	private JPanel superiorCarrito;
	private JPanel superior;
	private JPanel core; // centro
	private JPanel margenIzq; // }<-- Margen
	private JPanel margenInferior;
	private DefaultTableModel mDatos; // Modelo de datos de tabla central
	private JTable tDatos;
	private int tipo1;
	private JButton realizarCompra;
	private JPanel margenSaldo;
	private JLabel saldo;
	private JButton btnRecargarSaldo;
	
	public VentanaBiblioteca() {
		
		GestorBD.Conexion();
		this.setTitle("Comic Shop Deusto");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());


		
		
		// Panel Superior Izquierda
		tipo1 = -1;
	
		// Panel Superior Centro
		// TODO setLayout(new GridBagLayout()); <-- La idea es que los botones se
		// adapten al tama�o

		superiorVentanas = new JPanel();
		superiorVentanas.setBackground(Color.WHITE);
		superiorVentanas.setPreferredSize(new Dimension(0, 0));
		superiorVentanas.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(1,30,1,30);

		btnInicio = new JButton("INICIO");
		gbc.gridx = 0;
		gbc.gridy = 0;
		btnInicio.setSize(160, 35);
		btnInicio.setFont(new Font("Roboto", Font.BOLD, 25));
		btnInicio.setForeground(new Color(255, 97, 60));
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBorderPainted(false);
		superiorVentanas.add(btnInicio,gbc);
		

		btnComedia = new JButton("COMEDIA");
		btnComedia.setSize(160, 35);
		btnComedia.setFont(new Font("Consolas", Font.BOLD, 25));
		btnComedia.setForeground(new Color(255, 97, 60));
		btnComedia.setBackground(Color.WHITE);
		btnComedia.setBorderPainted(false);
		gbc.gridx = 5;
		superiorVentanas.add(btnComedia,gbc);
		

		btnTerror = new JButton("TERROR");
		gbc.gridx = 10;
		btnTerror.setSize(160, 35);
		btnTerror.setFont(new Font("Roboto", Font.BOLD, 25));
		btnTerror.setForeground(new Color(255, 97, 60));
		btnTerror.setBackground(Color.WHITE);
		btnTerror.setBorderPainted(false);
		superiorVentanas.add(btnTerror,gbc);
		
		
		btnAccion = new JButton("ACCION");
		btnAccion.setSize(160, 35);
		btnAccion.setFont(new Font("Consolas", Font.BOLD, 25));
		btnAccion.setForeground(new Color(255, 97, 60));
		btnAccion.setBackground(Color.WHITE);
		btnAccion.setBorderPainted(false);
		gbc.gridx = 15;
		superiorVentanas.add(btnAccion,gbc);
		
		
		btnAventura = new JButton("AVENTURA");
		btnAventura.setSize(160, 35);
		btnAventura.setFont(new Font("Consolas", Font.BOLD, 25));
		btnAventura.setForeground(new Color(255, 97, 60));
		btnAventura.setBackground(Color.WHITE);
		btnAventura.setBorderPainted(false);
		gbc.gridx = 20;
		superiorVentanas.add(btnAventura,gbc);
		

		btnCuenta = new JButton();
		gbc.gridx = 30;
		
		btnCuenta.setText("MI BIBLIOTECA");
		btnCuenta.setIconTextGap(10);
		btnCuenta.setSize(250, 90);
		btnCuenta.setFont(new Font("Roboto", Font.BOLD, 24));
		btnCuenta.setForeground(new Color(255, 97, 60));
		btnCuenta.setBackground(Color.WHITE);
		btnCuenta.setBorderPainted(false);
		btnCuenta.setHorizontalTextPosition(JButton.LEFT);
		btnCuenta.setVerticalTextPosition(JButton.CENTER);
		superiorVentanas.add(btnCuenta,gbc);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0xa2d39b));
		separator.setBounds(0, 95, 2000, 7);
		superiorVentanas.add(separator);


		// Panel Superior Derecha

		totalLbl = new JLabel();
		totalLbl.setText("Total:" + getTotalCarrito() + "€");
		//Text label de total metido en el actonlistener de boton a�adir al carrito.
		totalLbl.setForeground(Color.WHITE);
		totalLbl.setFont(new Font("Consolas", Font.BOLD, 20));
		totalLbl.setPreferredSize(new Dimension(150, 50));

		btnCarrito = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("/carrito.png"));
			btnCarrito.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnCarrito.setPreferredSize(new Dimension(100, 100));
		btnCarrito.setBackground(new Color(255, 97, 60));
		btnCarrito.setForeground(new Color(255, 97, 60));
		btnCarrito.setBorderPainted(false);

		superiorCarrito = new JPanel();
		superiorCarrito.setBackground(new Color(255, 97, 60));
		superiorCarrito.setPreferredSize(new Dimension(250, 0));
		superiorCarrito.setLayout(new BorderLayout());
		superiorCarrito.add(totalLbl, BorderLayout.EAST);
		superiorCarrito.add(btnCarrito, BorderLayout.WEST);

		// Build

		superior = new JPanel();
		superior.setBackground(Color.WHITE);
		superior.setPreferredSize(new Dimension(0, 100));
		superior.setLayout(new BorderLayout());
		superior.add(superiorVentanas, BorderLayout.CENTER);
		superior.add(superiorCarrito, BorderLayout.EAST);

// Panel Central

		// Panel Inicio
		
		// Panel

		// Panel Pago

		tDatos = new JTable();
		tDatos.setFont(new Font("Arial", Font.PLAIN, 14));

// Margen Izquierdo

		
		JLabel emptyLbl = new JLabel("");
		emptyLbl.setBounds(5,190,100,20);
				
		btnAniadirACarrito = new JButton("Añadir al carrito");
		btnAniadirACarrito.setBounds(0, 0, 180, 50);
		btnAniadirACarrito.setFont(new Font("Roboto", Font.BOLD, 18));
		btnAniadirACarrito.setForeground(new Color(255, 97, 60));
		btnAniadirACarrito.setBackground(new Color(0xffffff));
		btnAniadirACarrito.setBorderPainted(true);
		btnAniadirACarrito.setVisible(true);

		
		
		logo = new JLabel();
		try {
			Image img = ImageIO.read(getClass().getResource("/comic.jpg"));
			logo.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		logo.setBounds(0, 0, 200, 95);
		
		
		margenIzq = new JPanel();
		margenIzq.setBackground(new Color(255, 97, 60));
		margenIzq.setPreferredSize(new Dimension(180, 0));
		margenIzq.setLayout(new BorderLayout());
		margenIzq.add(btnAniadirACarrito, BorderLayout.SOUTH);
		margenIzq.add(logo);
		
		
		realizarCompra = new JButton("Realizar compra");
		realizarCompra.setVisible(false);
		realizarCompra.setBounds(50, 1000, 175, 20);
		realizarCompra.setFont(new Font("Roboto", Font.BOLD, 18));
		realizarCompra.setForeground(new Color(255, 97, 60));
		realizarCompra.setBackground(new Color(0xffffff));
		realizarCompra.setBorderPainted(true);
		
		saldo = new JLabel("Saldo:"+ getSaldoUsuario() +"€", SwingConstants.CENTER);
		//saldo.setText("Saldo:"+"€");
		saldo.setForeground(Color.WHITE);
		saldo.setFont(new Font("Consolas", Font.BOLD, 20));
		saldo.setPreferredSize(new Dimension(500, 50));
		//saldo.setHorizontalTextPosition(JButton.RIGHT);
		saldo.setVisible(false);
		
		btnRecargarSaldo = new JButton("Recargar saldo");
		btnRecargarSaldo.setBounds(0, 0, 180, 50);
		btnRecargarSaldo.setFont(new Font("Roboto", Font.BOLD, 18));
		btnRecargarSaldo.setForeground(new Color(255, 97, 60));
		btnRecargarSaldo.setBackground(new Color(0xffffff));
		btnRecargarSaldo.setBorderPainted(true);
		btnRecargarSaldo.setVisible(false);
		
		margenSaldo = new JPanel();
		margenSaldo.setBackground(new Color(255, 97, 60));
		margenSaldo.setLayout(new BorderLayout());
		margenSaldo.add(saldo, BorderLayout.NORTH);
		margenSaldo.add(btnRecargarSaldo, BorderLayout.SOUTH);
		
		
		margenInferior = new JPanel();
		margenInferior.setBackground(new Color(255, 97, 60));
		margenInferior.setLayout(new BorderLayout());
		margenInferior.add(realizarCompra, BorderLayout.CENTER);
		margenInferior.add(margenSaldo, BorderLayout.NORTH);
		

		
		core = new JPanel();
		core.setBackground(new Color(255, 97, 60));
		core.setLayout(new BorderLayout());
		core.add(new JScrollPane(tDatos), BorderLayout.CENTER);

		
		this.add(superior, BorderLayout.NORTH);
		this.add(margenIzq, BorderLayout.WEST);
		this.add(margenInferior, BorderLayout.SOUTH);
		this.add(core, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			//Iniciamos conexion con la base de datos
			public void windowOpened(WindowEvent e) {
				GestorBD.crearBBDD();
			}
			//Cerramos conexion con la base de datos y vaciamos la tabla que contiene el usuario actual
			public void windowClosed(WindowEvent e) {
				GestorBD.EliminarUsuarioDeBaseDeDatos();
				GestorBD.borrarBBDD();
			}

		});
		
		
		
		//Aniade al carrito el producto seleccionado
		btnAniadirACarrito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CrearCarrito();
				totalLbl.setText("Total:" + getTotalCarrito() + "�");
				realizarCompra.setVisible(false);
				
				

			}

		});
		
		//Se carga el carrito en el modelo de la tabla
		btnCarrito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verCarrito();
				realizarCompra.setVisible(true);
				saldo.setVisible(true);
				btnRecargarSaldo.setVisible(true);
				
				
			}
			
		});
		
		
		
		
		
		//Al clicar en este boton se crea una nueva compra 
		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				realizarCompra(0);
				
				
				
				String correo = GestorBD.cargarCorreoUsuario();
				if(GestorBD.cargarSaldoUsuario(correo) > getTotalCarrito()) {
					
					realizarCompra(1);
					
					int nuevoSaldo = GestorBD.cargarSaldoUsuario(correo) - getTotalCarrito();       
					GestorBD.actualizarSaldo(nuevoSaldo);
				}
				else {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente, realice un ingreso");
					realizarCompra(0);
				}
				
				
				
				
				
			}
			
		});
		
		
		//Se cargan todas las compras del cliente quew haya iniciado sesion
		btnCuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verCompras();
				saldo.setVisible(true);
				btnRecargarSaldo.setVisible(true);
				
			}
			
		});
		
		
		//Se cargan los comics cuyo genero sea Comedia
		btnComedia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String genero = "Comedia";
				ArrayList<Comic>listaComedia = new ArrayList<Comic>();
				Comic comicsComedia = GestorBD.cargarComicsPorGenero1(genero);
				listaComedia.add(comicsComedia);
				ModeloComics(listaComedia);
				
			}
			
		});
		
		
		//Se cargan los comics cuyo genero sea Terror
				btnTerror.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String genero = "Terror";
						ArrayList<Comic>listaTerror = new ArrayList<Comic>();
						Comic comicsTerror = GestorBD.cargarComicsPorGenero1(genero);
						listaTerror.add(comicsTerror);
						ModeloComics(listaTerror);
						
					}
					
				});
				
				//Se cargan los comics cuyo genero sea Accion
				btnAccion.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String genero = "Accion";
						ArrayList<Comic>listaAccion = new ArrayList<Comic>();
						Comic comicsAccion = GestorBD.cargarComicsPorGenero1(genero);
						listaAccion.add(comicsAccion);
						ModeloComics(listaAccion);
						
					}
					
				});
				
				//Se cargan los comics cuyo genero sea Aventura
				btnAventura.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String genero = "Aventura";
						ArrayList<Comic>listaAventura = new ArrayList<Comic>();
						Comic comicsAventura = GestorBD.cargarComicsPorGenero1(genero);
						listaAventura.add(comicsAventura);
						ModeloComics(listaAventura);
						
					}
					
				});
				
				btnRecargarSaldo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						new VentanaPago();
						
					}
					
				});
		
		
		
		//Cuandop se pulse el Control y se clique se borrara todo el carrito
		btnCarrito.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.isControlDown()) {
					GestorBD.EliminarCarrito();
					totalLbl.setText("Total: 0�");
					realizarCompra.setVisible(false);
					
					
				}
			}
		});
		

		//Cuando se pulse el Control y se cliquen dos veces se borrara el ultimo producto del carrito
		btnCarrito.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.isAltDown() && e.getClickCount() == 2) {
					int rowcount = mDatos.getRowCount();
					ArrayList<Carrito> ALCarrito = GestorBD.getCarrito();
					Carrito ultimaCompra = ALCarrito.get(rowcount-1);
					int ultimaCompraTotal = ultimaCompra.getPrecio() * ultimaCompra.getCantidad();
					int totalMenosUltimo = getTotalCarrito() - ultimaCompraTotal;
					totalLbl.setText("Total:" + totalMenosUltimo + "�");
					realizarCompra.setVisible(false);
					mDatos.removeRow(rowcount-1);
					String nombre = ultimaCompra.getTitulo();
					GestorBD.EliminarDelCarrito(nombre);

					
				}
			}
		});
		
		

	}
	
	
	
	
	
	//Modelo de comics
	private void ModeloComics(ArrayList<Comic> lista) {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"));
		
		mDatos = new DefaultTableModel(
				new Vector<Vector<Object>>(),
				cabeceras
		) {
			public boolean isCellEditable(int row, int column) {
				if(column==0 || column==1 || column==2 || column==3 || column==4 || column==5) 
					return false;
				return true;
			}
		};
		
		
		ArrayList<Comic> comics = lista;
		
		for (Comic producto : comics) {
			mDatos.addRow(new Object[] { producto.getId(), producto.getEditorial(), producto.getTitulo(),
					producto.getGenero(), producto.getPrecio(), producto.getCantidad() });
		}
		
		tDatos.setModel(mDatos);
		
		
		tDatos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column == 2 && (Integer)mDatos.getValueAt(row, 2) < 35) {
					((JComponent) c).setOpaque(true);
					c.setBackground(new Color(255, 200, 200));
				}else {
					((JComponent) c).setOpaque(false);
				}
				
				return c;
			}
		});
	}

	
	//Modelo del carrito, productos seleccionados para ser comprados
	private void verCarrito() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"));
		mDatos = new DefaultTableModel( 
				new Vector<Vector<Object>>(), 
				cabeceras 
		) {
			public boolean isCellEditable(int row, int column) {
				if(column==0 || column==1 || column==2 || column==3 || column==4 || column==5)
					return false;
				return true;
			}
		};
		ArrayList<Carrito> carrito = GestorBD.getCarrito();
		System.out.println(carrito);
	
		for (Carrito carro : carrito) {
			int precio = carro.getCantidad() * carro.getPrecio();
			mDatos.addRow(new Object[] { carro.getId(), carro.getEditorial(), carro.getTitulo(), carro.getGenero(), precio, carro.getCantidad()});
		}

		tDatos.setModel(mDatos);
	}
	//Devuelve el precio total del carrito
	private int getTotalCarrito() {
		ArrayList<Carrito> carrito = GestorBD.getCarrito();
		int totalCarrito = 0;
		if(carrito == null) {
			totalCarrito = 0;
		}
		else {
			for (Carrito carro : carrito) {
				totalCarrito += carro.getCantidad() * carro.getPrecio();	
			}
		}
		return totalCarrito;
		
	}
	
	//Modelo de las compras del cliente que haya iniciado sesion
	private void verCompras() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"));
		mDatos = new DefaultTableModel( 
				new Vector<Vector<Object>>(),
				cabeceras 
		) {
			public boolean isCellEditable(int row, int column) {
				if(column==0 || column==1 || column==2 || column==3 || column==4 || column==5)
					return false;
				return true;
			}
		};
		String correo = GestorBD.cargarCorreoUsuario();
		ArrayList<Compra> compras = GestorBD.cargarComprasUsuario(correo);
		
		for (Compra compra : compras) {
			
			mDatos.addRow(new Object[] { compra.getCorreoCliente(), compra.getIdProducto(), compra.getEditorialProducto(), compra.getTituloProducto(), compra.getPrecio(), compra.getCantidad()});
		}

		tDatos.setModel(mDatos);
	}
	
	/*
	 * A�ade en la base de datos el producto seleccionado para aniadir al carrito
	 * En caso de que no quede stock salta un mensaje
	 * En caso de que la cantidad seleccionada sea menor que el stock de ese producto se puede aniadir al carrito
	 * En caso de que este roducto ya este en el carrito se sumara la cantidad deseada al producto que este ya en el carrito
	 */
	
	private void CrearCarrito() {
		
		int filaSeleccionada = tDatos.getSelectedRow();
		if(filaSeleccionada != -1) {
			int idTabla = (Integer) mDatos.getValueAt(filaSeleccionada, 0);
			String editorialTabla = (String) mDatos.getValueAt(filaSeleccionada, 1);
			String titulo =  (String) mDatos.getValueAt(filaSeleccionada, 2);
			Genero genero = (Genero) mDatos.getValueAt(filaSeleccionada, 3);
			int precio = (Integer) mDatos.getValueAt(filaSeleccionada, 4);
			int cantidadTabla = (Integer) mDatos.getValueAt(filaSeleccionada, 5);
			ArrayList<Carrito> carros = GestorBD.getCarrito();
			ArrayList<String> nombreProductos = new ArrayList();
			
			for(Carrito carro : carros) {
				nombreProductos.add(carro.getTitulo());
			}
			
			if(cantidadTabla == 0) {
				JOptionPane.showMessageDialog(null, "No queda stock de este producto");
				
				}else {
					
					GestorBD.crearCarrito(new Carrito(idTabla, editorialTabla, titulo, genero, precio, cantidadTabla));
					}
					//GestorBD.RestarCantidadComics(idTabla, cantidadTabla);
					ModeloComics(GestorBD.getComics1());
				}
				
		}
	
	//Devuelve el saldo del usuario
			private int getSaldoUsuario() {
				
				
				String correo = GestorBD.cargarCorreoUsuario();
				int saldoUsuario = GestorBD.cargarSaldoUsuario(correo);
				
				return saldoUsuario;
				
			}
	
	//Recorre todo el modelo de la tabla del carrito y crea las compras
	
	private void realizarCompra(int i) {
		
			while(i< mDatos.getRowCount()) {
				String correo = GestorBD.cargarCorreoUsuario();
				int idComic = (Integer) mDatos.getValueAt(i, 0);
				String editorialComic = (String) mDatos.getValueAt(i, 1);
				String tituloComic = (String) mDatos.getValueAt(i, 2);
				String generoComic = (String) mDatos.getValueAt(i, 3);
				int precioComic = (Integer) mDatos.getValueAt(i, 4);
				int cantidadComic = (Integer) mDatos.getValueAt(i, 5);
				
				GestorBD.crearCompra(new Compra(0, correo, idComic, editorialComic, tituloComic, generoComic, precioComic, cantidadComic));
				mDatos.removeRow(i);
				GestorBD.EliminarCarrito();
				realizarCompra(i+1);

				
				
				
			}
		}
	

	
		
	
	
		
	

	public static void main(String[] args){
		VentanaBiblioteca ventana = new VentanaBiblioteca();
		ventana.setVisible(true);
	}
	
	
}



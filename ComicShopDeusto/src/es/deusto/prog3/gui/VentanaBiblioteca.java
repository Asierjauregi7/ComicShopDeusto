package es.deusto.prog3.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import es.deusto.prog.bbdd.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
public class VentanaBiblioteca extends JFrame{
	
	private JLabel logo; // }<-- Panel Superior
	private JPanel superiorLogo;
	private JButton btnInicio;
	private JButton btnHombre;
	private JButton btnMujer;
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
	private JButton btnParteArriba;
	private JButton btnParteAbajo;
	private JButton btnCalzado;
	private DefaultTableModel mDatos; // Modelo de datos de tabla central
	private JTable tDatos;
	private int tipo1;
	private JButton realizarCompra;
	public VentanaBiblioteca() {
		this.setTitle("Comic Shop Deusto");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

// Panel Superior

		// Panel Superior Izquierda
		tipo1 = -1;
		logo = new JLabel();
		try {
			Image img = ImageIO.read(getClass().getResource("/libreria.png"));
			logo.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		logo.setBounds(0, 0, 200, 95);

		superiorLogo = new JPanel();
		superiorLogo.setPreferredSize(new Dimension(169, 96));
		superiorLogo.setBackground(Color.WHITE);
		superiorLogo.setLayout(null);
		superiorLogo.add(logo);
		
	
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
		btnInicio.setForeground(new Color(0xa8ccba));
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBorderPainted(false);
		superiorVentanas.add(btnInicio,gbc);
		

		btnHombre = new JButton("HOMBRE");
		btnHombre.setSize(160, 35);
		btnHombre.setFont(new Font("Consolas", Font.BOLD, 25));
		btnHombre.setForeground(new Color(0xa8ccba));
		btnHombre.setBackground(Color.WHITE);
		btnHombre.setBorderPainted(false);
		gbc.gridx = 10;
		superiorVentanas.add(btnHombre,gbc);
		

		btnMujer = new JButton("MUJER");
		gbc.gridx = 20;
		btnMujer.setSize(160, 35);
		btnMujer.setFont(new Font("Roboto", Font.BOLD, 25));
		btnMujer.setForeground(new Color(0xa8ccba));
		btnMujer.setBackground(Color.WHITE);
		btnMujer.setBorderPainted(false);
		superiorVentanas.add(btnMujer,gbc);
		

		btnCuenta = new JButton();
		gbc.gridx = 30;
		try {
			Image img = ImageIO.read(getClass().getResource("/cuenta.png"));
			btnCuenta.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnCuenta.setText("CUENTA: ");
		btnCuenta.setIconTextGap(10);
		btnCuenta.setSize(250, 90);
		btnCuenta.setFont(new Font("Roboto", Font.BOLD, 24));
		btnCuenta.setForeground(new Color(0xa8ccba));
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
		btnCarrito.setBackground(new Color(0xa8ccba));
		btnCarrito.setForeground(new Color(0xa8ccba));
		btnCarrito.setBorderPainted(false);

		superiorCarrito = new JPanel();
		superiorCarrito.setBackground(new Color(0xa8ccba));
		superiorCarrito.setPreferredSize(new Dimension(250, 0));
		superiorCarrito.setLayout(new BorderLayout());
		superiorCarrito.add(totalLbl, BorderLayout.EAST);
		superiorCarrito.add(btnCarrito, BorderLayout.WEST);

		// Build

		superior = new JPanel();
		superior.setBackground(Color.WHITE);
		superior.setPreferredSize(new Dimension(0, 100));
		superior.setLayout(new BorderLayout());
		superior.add(superiorLogo, BorderLayout.WEST);
		superior.add(superiorVentanas, BorderLayout.CENTER);
		superior.add(superiorCarrito, BorderLayout.EAST);

// Panel Central

		// Panel Inicio
		
		// Panel

		// Panel Pago

		tDatos = new JTable();
		tDatos.setFont(new Font("Arial", Font.PLAIN, 14));

// Margen Izquierdo

		JLabel prendasLbl = new JLabel();
		prendasLbl.setText("· Atuendo:");
		prendasLbl.setBounds(5,20,100,20);
		prendasLbl.setForeground(Color.WHITE);
		prendasLbl.setFont(new Font("Consolas", Font.PLAIN, 18));
		

		btnParteArriba = new JButton("Superior");
		btnParteArriba.setBounds(0, 50, 180, 30);
		btnParteArriba.setFont(new Font("Roboto", Font.BOLD, 18));
		btnParteArriba.setForeground(new Color(0xffffff));
		btnParteArriba.setBackground(new Color(0xa8ccba));
		btnParteArriba.setBorderPainted(false);

		btnParteAbajo = new JButton("Inferior");
		btnParteAbajo.setBounds(0, 90, 170, 30);
		btnParteAbajo.setFont(new Font("Roboto", Font.BOLD, 18));
		btnParteAbajo.setForeground(new Color(0xffffff));
		btnParteAbajo.setBackground(new Color(0xa8ccba));
		btnParteAbajo.setBorderPainted(false);
		
		JLabel calzadoLbl = new JLabel();
		calzadoLbl.setText("· Calzado:");
		calzadoLbl.setBounds(5,130,100,20);
		calzadoLbl.setForeground(Color.WHITE);
		calzadoLbl.setFont(new Font("Consolas", Font.PLAIN, 18));

		btnCalzado = new JButton("Zapatillas");
		btnCalzado.setBounds(0, 160,180, 30);
		btnCalzado.setFont(new Font("Roboto", Font.BOLD, 18));
		btnCalzado.setForeground(new Color(0xffffff));
		btnCalzado.setBackground(new Color(0xa8ccba));
		btnCalzado.setBorderPainted(false);
		btnCalzado.setVisible(true);
		
		JLabel emptyLbl = new JLabel("");
		emptyLbl.setBounds(5,190,100,20);
				
		btnAniadirACarrito = new JButton("Añadir Item");
		btnAniadirACarrito.setBounds(0, 0, 180, 80);
		btnAniadirACarrito.setFont(new Font("Roboto", Font.BOLD, 18));
		btnAniadirACarrito.setForeground(new Color(0xa8ccba));
		btnAniadirACarrito.setBackground(new Color(0xffffff));
		btnAniadirACarrito.setBorderPainted(true);
		btnAniadirACarrito.setVisible(true);

		margenIzq = new JPanel();
		margenIzq.setBackground(new Color(0xa8ccba));
		margenIzq.setPreferredSize(new Dimension(180, 0));
		margenIzq.setLayout(new BorderLayout());
		margenIzq.add(prendasLbl);
		margenIzq.add(btnParteArriba);
		margenIzq.add(btnParteAbajo);
		margenIzq.add(calzadoLbl);
		margenIzq.add(btnCalzado);
		margenIzq.add(emptyLbl);
		margenIzq.add(btnAniadirACarrito, BorderLayout.SOUTH);
		
		
		realizarCompra = new JButton("Realizar compra");
		realizarCompra.setVisible(false);
		realizarCompra.setBounds(50, 1000, 175, 20);
		
		
		margenInferior = new JPanel();
		margenInferior.setBackground(new Color(0xa8ccba));
		margenInferior.setLayout(new BorderLayout());
		margenInferior.add(realizarCompra, BorderLayout.CENTER);

		
		core = new JPanel();
		core.setBackground(new Color(0xa8ccba));
		core.setLayout(new BorderLayout());
		core.add(new JScrollPane(tDatos), BorderLayout.CENTER);

		
		this.add(superior, BorderLayout.NORTH);
		this.add(margenIzq, BorderLayout.WEST);
		this.add(margenInferior, BorderLayout.SOUTH);
		this.add(core, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			//Iniciamos conexion con la base de datos
			public void windowOpened(WindowEvent e) {
				BaseDeDatos.inicioConexion("BaseDeDatos1.db");
			}
			//Cerramos conexion con la base de datos y vaciamos la tabla que contiene el usuario actual
			public void windowClosed(WindowEvent e) {
				GestorBD.EliminarUsuarioDeBaseDeDatos();
				GestorBD.cerrarConexion();
			}

		});
		
		
		
		//Muestra los atuendos de parte arriba
		btnParteArriba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloParteArriba(GestorBD.getAtuendosParteArriba());
				realizarCompra.setVisible(false);
				
				tipo1 = 0;

			}

		});
		//Muestra los atuendos de parte abajo
		btnParteAbajo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloParteAbajo(GestorBD.getAtuendosParteAbajo());
				realizarCompra.setVisible(false);
				
				tipo1 = 1;
			}

		});
		
		//Muestra los calzados
		btnCalzado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloCalzado(GestorBD.getZapatillas());;
				realizarCompra.setVisible(false);
				
				tipo1 = 2;
				
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
				
				
				
			}
			
		});
		
		
		
		
		
		//Al clicar en este boton se crea una nueva compra y se abre una ventana para el metodo de pago
		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				realizarCompra(0);
				
				
				
				
				if(GestorBD.getSaldoUsuario() > getTotalCarrito()) {
					//Crear metodo de coger saldo del usuario en la base de datos y eldel carrito que esta hecho en el otro proyecto
					realizarCompra(1);
					//Averiguar como establecer el saldo nuevo al realizar la compra
					Biblioteca.setSaldo() = GestorBD.getSaldoUsuario() - getTotalCarrito();                        
				}
				else {
					//saldo insuficiente rellena tu saldo
					realizarCompra(0);
				}
				
				
				
				
				
			}
			
		});
		
		
		//Se cargan todas las compras del cliente quew haya iniciado sesion
		btnCuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verCompras();
				
			}
			
		});
		
		
		//Se cargan los atuendos cuyo genero sea Hombre y se actualizara en funcion de en que tipo de atuendo se este
		btnHombre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String genero = "Hombre";
				
				if(tipo1 == 0) {
					ArrayList<AtuendosParteArriba> lista = BaseDeDatos.getAtuendosParteArribaPorGenero(genero);
					ModeloParteArriba(lista);
					
				}else if(tipo1 == 1) {
					ArrayList<AtuendosParteAbajo> lista = BaseDeDatos.getAtuendosParteAbajoPorGenero(genero);
					ModeloParteAbajo(lista);
					
				}else {
					ArrayList<AtuendoZapatilla> lista = BaseDeDatos.getZapatillasPorGenero(genero);
					ModeloCalzado(lista);
				}
				
			}
			
		});
		
		//Se cargan los atuendos cuyo genero sea Mujer y se actualizara en funcion de en que tipo de atuendo se este
		btnMujer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String genero = "Mujer";
				
				if(tipo1 == 0) {
					ArrayList<AtuendosParteArriba> lista = BaseDeDatos.getAtuendosParteArribaPorGenero(genero);
					ModeloParteArriba(lista);
					
				}else if(tipo1 == 1) {
					ArrayList<AtuendosParteAbajo> lista = BaseDeDatos.getAtuendosParteAbajoPorGenero(genero);
					ModeloParteAbajo(lista);
					
				}else {
					ArrayList<AtuendoZapatilla> lista = BaseDeDatos.getZapatillasPorGenero(genero);
					ModeloCalzado(lista);
				}
				
			}
			
		});
		
		
		
		//Cuandop se pulse el Control y se clique se borrara todo el carrito
		btnCarrito.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.isControlDown()) {
					BaseDeDatos.EliminarCarrito();
					totalLbl.setText("Total: 0�");
					realizarCompra.setVisible(false);
					FechaLlegada.setVisible(false);
					
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
		String correo = GestorBD.cargarCorreoDeUnCliente();
		ArrayList<Compra> compras = GestorBD.getComprasUsuario(correo);
		
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
		//"Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"
		int filaSeleccionada = tDatos.getSelectedRow();
		if(filaSeleccionada != -1) {
			int idTabla = (Integer) mDatos.getValueAt(filaSeleccionada, 0);
			String editorialTabla = (String) mDatos.getValueAt(filaSeleccionada, 1);
			String titulo =  (String) mDatos.getValueAt(filaSeleccionada, 2);
			String genero = (String) mDatos.getValueAt(filaSeleccionada, 3);
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
				int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad del producto"));
			
			
				if(cantidad<cantidadTabla) {
					
					if(nombreProductos.contains(titulo)) {
						GestorBD.aumentarCantidadCarrito(titulo, cantidad);
					
					}else {
						String tipo = (String) mDatos.getValueAt(filaSeleccionada,10);
						BaseDeDatos.crearCarrito(new Carrito(idTabla, editorialTabla, titulo, genero, precio, cantidad));
					}
					BaseDeDatos.RestarCantidadDeProductosParteArriba(idTabla, cantidad);
					ModeloParteArriba(BaseDeDatos.getAtuendosParteArriba());

				}else if(tipo1==1){
					if(nombreProductos.contains(nombre)) {
						BaseDeDatos.aumentarCantidadCarrito(nombre, cantidad);
					
					}else {
						String tipo = (String) mDatos.getValueAt(filaSeleccionada,10).toString();
						BaseDeDatos.crearCarrito(new Carrito(nombre,cantidad, precio, tipo));
					}
					BaseDeDatos.RestarCantidadDeProductosParteAbajo(idTabla, cantidad);
					ModeloParteAbajo(BaseDeDatos.getAtuendosParteAbajo());
				
				}else {
					for(Carrito carro : carros) {
						nombreProductos.add(carro.getNombre());
					}
					if(nombreProductos.contains(nombre)) {
						BaseDeDatos.aumentarCantidadCarrito(nombre, cantidad);
					
					}else {
						String tipo = (String) mDatos.getValueAt(filaSeleccionada,11);
						BaseDeDatos.crearCarrito(new Carrito(nombre,cantidad, precio, tipo));
					}
					BaseDeDatos.RestarCantidadDeZapatillas(idTabla, cantidad);
					ModeloCalzado(BaseDeDatos.getZapatillas());
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "La cantidad seleccionada no es posible");
			}
		}

	}else {
			JOptionPane.showMessageDialog(null, "No has seleccionado ningun producto para borrar");
	}

}
	
	//Recorre todo el modelo de la tabla del carrito y crea las compras
	
	private void realizarCompra(int i) {
		
		Date f = FechaLlegada.getDate();
		long fechaLlegada = f.getTime();
		if(f.toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No has seleccionado una fecha de llegada");
		}else {
			while(i< mDatos.getRowCount()) {
				String correo = BaseDeDatos.cargarCorreoDeUnCliente();
				String nombreProducto = (String) mDatos.getValueAt(i, 0);
				int cantidad = (Integer) mDatos.getValueAt(i, 1);
				int precio = (Integer) mDatos.getValueAt(i, 2);
				String tipo = (String) mDatos.getValueAt(i, 3);
				long fechaCompra = System.currentTimeMillis();
				BaseDeDatos.crearCompra(new Pedido(0, correo, nombreProducto, cantidad, precio, tipo, fechaCompra, fechaLlegada));
				mDatos.removeRow(i);
				BaseDeDatos.EliminarCarrito();
				realizarCompra(i+1);
				
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		VentanaUsuario ventana = new VentanaUsuario();
		ventana.setVisible(true);
	}
	
	
}



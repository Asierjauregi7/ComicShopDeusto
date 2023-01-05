package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import es.deusto.prog.bbdd.GestorBD;
import es.deusto.prog3.g32.Comic;

public class VentanaPrincipal extends JFrame{

	private JLabel logo; // }<-- Panel Superior
	
	private JPanel superiorLogo;
	private JButton btnComedia;
	private JButton btnTerror;
	private JButton btnAccion;
	private JButton btnAventura;
	private JButton btnCuenta;
	private JPanel superiorVentanas;
	private JButton btnCarrito;
	private JButton btnAniadirACarrito;
	private JPanel superiorCarrito;
	private JPanel superior;
	private JPanel core; // centro
	private JPanel margenIzq; // }<-- Margen
	private JPanel margenInferior;
	private DefaultTableModel mDatos; // Modelo de datos de tabla central
	private JTable tDatos;
	private int tipo1;
	

	private JButton btnCerrarSesion;
	private JButton btnBibliotecaPersonal;
	
	
	public VentanaPrincipal() {
		this.setTitle("Comic Shop Deusto");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());


		GestorBD.Conexion();
	
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

				
				
				
				margenIzq = new JPanel();
				margenIzq.setBackground(new Color(255, 97, 60));
				margenIzq.setPreferredSize(new Dimension(180, 0));
				margenIzq.setLayout(new BorderLayout());
				margenIzq.add(btnAniadirACarrito, BorderLayout.SOUTH);
				
				
				
				margenInferior = new JPanel();
				margenInferior.setBackground(new Color(255, 97, 60));
				margenInferior.setLayout(new BorderLayout());
				

				
				core = new JPanel();
				core.setBackground(new Color(255, 97, 60));
				core.setLayout(new BorderLayout());
				core.add(new JScrollPane(tDatos), BorderLayout.CENTER);

				
				this.add(superior, BorderLayout.NORTH);
				this.add(margenIzq, BorderLayout.WEST);
				this.add(margenInferior, BorderLayout.SOUTH);
				this.add(core, BorderLayout.CENTER);

				
	}			
	
	
	
	
	
	//Modelo de comics
		private void ModeloComics(ArrayList<Comic> lista) {
			Vector<String> cabeceras = new Vector<String>(Arrays.asList("Id", "Editorial", "Titulo", "Genero", "Precio", "Cantidad"));
			
			mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras) {
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

				
				
		public static void main(String[] args){
			VentanaPrincipal ventana = new VentanaPrincipal();
			ventana.setVisible(true);
	}
		
	
}

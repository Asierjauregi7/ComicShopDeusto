package es.deusto.prog3.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Window;
import es.deusto.prog.bbdd.GestorBD;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class VentanaPago {

	private JFrame frameVentanaPago;
	private JSeparator separator;
	private JLabel lblVisa;
	private JLabel lblIngreso;
	private JLabel lblNumeroCuenta;
	private JTextField txtNumeroCuenta;
	private JLabel lblFechaCaducidad;
	private JTextField txtFechaCaducidad;
	private JLabel lblCvv;
	private JPasswordField pswdCvv;
	private JButton btnIngresar;
	private JLabel lblCantidad;
	private JTextField txtCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPago window = new VentanaPago();
					window.frameVentanaPago.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPago() {
		initialize();
		GestorBD.Conexion();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameVentanaPago = new JFrame();
		frameVentanaPago.getContentPane().setBackground(new Color(255, 97, 60));
		frameVentanaPago.setTitle("Pago");
		frameVentanaPago.setBounds(200, 200, 500, 330);
		frameVentanaPago.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVentanaPago.getContentPane().setLayout(null);
		
		
		JLabel lblComicShopDeusto = new JLabel("Comic Shop Deusto");
		lblComicShopDeusto.setBackground(new Color(0, 0, 0));
		lblComicShopDeusto.setFont(new Font("Dialog", Font.ITALIC, 22));
		lblComicShopDeusto.setBounds(29, 6, 233, 25);
		frameVentanaPago.getContentPane().add(lblComicShopDeusto);
		
		
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 32, 488, 34);
		frameVentanaPago.getContentPane().add(separator);
		
		
		
		lblVisa = new JLabel("VISA");
		lblVisa.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblVisa.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisa.setBounds(201, 53, 61, 34);
		frameVentanaPago.getContentPane().add(lblVisa);
		
		
		
		lblIngreso = new JLabel("INGRESO :");
		lblIngreso.setBounds(6, 53, 91, 16);
		frameVentanaPago.getContentPane().add(lblIngreso);
		
		
		
		lblNumeroCuenta = new JLabel("Nº de cuenta :");
		lblNumeroCuenta.setBounds(6, 108, 106, 16);
		frameVentanaPago.getContentPane().add(lblNumeroCuenta);
		
		
		
		txtNumeroCuenta = new JTextField();
		txtNumeroCuenta.setBounds(124, 103, 252, 26);
		frameVentanaPago.getContentPane().add(txtNumeroCuenta);
		txtNumeroCuenta.setColumns(16);
		
		
		
		lblFechaCaducidad = new JLabel("Fecha Caducidad :");
		lblFechaCaducidad.setBounds(6, 160, 119, 16);
		frameVentanaPago.getContentPane().add(lblFechaCaducidad);
		
		
		
		txtFechaCaducidad = new JTextField();
		txtFechaCaducidad.setBounds(124, 155, 61, 26);
		frameVentanaPago.getContentPane().add(txtFechaCaducidad);
		txtFechaCaducidad.setColumns(5);
		
		
		
		lblCvv = new JLabel("cvv :");
		lblCvv.setBounds(243, 160, 37, 16);
		frameVentanaPago.getContentPane().add(lblCvv);
		
		
		
		pswdCvv = new JPasswordField();
		pswdCvv.setBounds(292, 155, 37, 26);
		frameVentanaPago.getContentPane().add(pswdCvv);
		
		
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(177, 237, 117, 29);
		frameVentanaPago.getContentPane().add(btnIngresar);
		
		
		
		lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setBounds(6, 218, 72, 16);
		frameVentanaPago.getContentPane().add(lblCantidad);
		
		
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(90, 213, 61, 26);
		frameVentanaPago.getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		frameVentanaPago.setVisible(true);
		
		
		//Ingresar saldo 
		btnIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String correo = GestorBD.cargarCorreoUsuario();
				int saldoIngresado = Integer.valueOf(txtCantidad.getText());
				int nuevoSaldo= GestorBD.cargarSaldoUsuario(correo) + saldoIngresado;
				GestorBD.actualizarSaldo(nuevoSaldo);
				frameVentanaPago.dispose();
				JOptionPane.showMessageDialog(null, "Saldo ingresado correctamente");
				
				
			}
			
		});
		
	
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}
}

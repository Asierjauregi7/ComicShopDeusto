package es.deusto.prog3.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

public class VentanaInicioSesion {

	private JFrame frameVentanaInicioSesion;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;
	private JLabel lblNoTienesCuenta;
	private JSeparator separator;
	private JSeparator separator_1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion window = new VentanaInicioSesion();
					window.frameVentanaInicioSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicioSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameVentanaInicioSesion = new JFrame();
		frameVentanaInicioSesion.getContentPane().setBackground(new Color(255, 97, 60));
		frameVentanaInicioSesion.setTitle("Inicio sesion");
		frameVentanaInicioSesion.setBounds(200, 200, 500, 300);
		frameVentanaInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVentanaInicioSesion.getContentPane().setLayout(null);

		JLabel lblComicShopDeusto = new JLabel("Comic Shop Deusto");
		lblComicShopDeusto.setBackground(new Color(0, 0, 0));
		lblComicShopDeusto.setFont(new Font("Dialog", Font.ITALIC, 22));
		lblComicShopDeusto.setBounds(29, 6, 233, 25);
		frameVentanaInicioSesion.getContentPane().add(lblComicShopDeusto);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(29, 103, 69, 13);
		frameVentanaInicioSesion.getContentPane().add(lblUsuario);

		JLabel lblContraseña = new JLabel("Contraseña :");
		lblContraseña.setBounds(29, 149, 79, 13);
		frameVentanaInicioSesion.getContentPane().add(lblContraseña);

		textUsuario = new JTextField();
		textUsuario.setBackground(Color.WHITE);
		textUsuario.setBounds(117, 103, 119, 19);
		frameVentanaInicioSesion.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(117, 149, 119, 19);
		frameVentanaInicioSesion.getContentPane().add(passwordField);

		btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.setBackground(Color.BLACK);
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBounds(117, 198, 119, 32);

		frameVentanaInicioSesion.getContentPane().add(btnIniciarSesion);

		lblNoTienesCuenta = new JLabel("¿No tienes cuenta? \r\n");
		lblNoTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoTienesCuenta.setBounds(322, 103, 142, 32);
		frameVentanaInicioSesion.getContentPane().add(lblNoTienesCuenta);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.setBackground(SystemColor.desktop);
		btnRegistrarse.setBounds(322, 147, 124, 32);
		frameVentanaInicioSesion.getContentPane().add(btnRegistrarse);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 32, 488, 34);
		frameVentanaInicioSesion.getContentPane().add(separator);
		frameVentanaInicioSesion.setVisible(true);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(276, 91, 34, 109);
		frameVentanaInicioSesion.getContentPane().add(separator_1);
		frameVentanaInicioSesion.setVisible(true);

		
		//Action listeners
		
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaRegistro();
			}

			
		});
		
		
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}
}

package es.deusto.prog3.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import es.deusto.prog.bbdd.GestorBD;
import es.deusto.prog3.g32.Usuario;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class VentanaRegistro {

	private JFrame frameVentanaRegistro;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textCorreo;
	private JTextField textUsuario;
	private JPasswordField pswdContraseña;
	private JPasswordField pswdRepetirContraseña;
	private JSeparator separator;
	private JButton btnVolver;
	private JButton btnRegistrar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro window = new VentanaRegistro();
					window.frameVentanaRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameVentanaRegistro = new JFrame();
		frameVentanaRegistro.getContentPane().setBackground(new Color(255, 97, 60));
		frameVentanaRegistro.setTitle("Registro");
		frameVentanaRegistro.setBounds(200, 200, 500, 330);
		frameVentanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVentanaRegistro.getContentPane().setLayout(null);
		
		
		JLabel lblComicShopDeusto = new JLabel("Comic Shop Deusto");
		lblComicShopDeusto.setBackground(new Color(0, 0, 0));
		lblComicShopDeusto.setFont(new Font("Dialog", Font.ITALIC, 22));
		lblComicShopDeusto.setBounds(29, 6, 233, 25);
		frameVentanaRegistro.getContentPane().add(lblComicShopDeusto);
		
		
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(29, 57, 69, 13);
		frameVentanaRegistro.getContentPane().add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBackground(Color.WHITE);
		textNombre.setBounds(176, 57, 157, 19);
		frameVentanaRegistro.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setBounds(29, 88, 69, 13);
		frameVentanaRegistro.getContentPane().add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setBackground(Color.WHITE);
		textApellidos.setBounds(176, 88, 157, 19);
		frameVentanaRegistro.getContentPane().add(textApellidos);
		textApellidos.setColumns(10);
		
		
		
		JLabel lblCorreo = new JLabel("Correo :");
		lblCorreo.setBounds(29, 121, 69, 13);
		frameVentanaRegistro.getContentPane().add(lblCorreo);

		textCorreo = new JTextField();
		textCorreo.setBackground(Color.WHITE);
		textCorreo.setBounds(176, 121, 157, 19);
		frameVentanaRegistro.getContentPane().add(textCorreo);
		textCorreo.setColumns(10);
		
		
		
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(29, 152, 69, 13);
		frameVentanaRegistro.getContentPane().add(lblUsuario);

		textUsuario = new JTextField();
		textUsuario.setBackground(Color.WHITE);
		textUsuario.setBounds(176, 152, 157, 19);
		frameVentanaRegistro.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		
		
		
		JLabel lblContraseña = new JLabel("Contraseña :");
		lblContraseña.setBounds(29, 183, 88, 16);
		frameVentanaRegistro.getContentPane().add(lblContraseña);
		
		pswdContraseña = new JPasswordField();
		pswdContraseña.setBounds(176, 178, 157, 26);
		frameVentanaRegistro.getContentPane().add(pswdContraseña);
		
		
		
		
		JLabel lblRepetirContraseña = new JLabel("Repetir Contraseña :");
		lblRepetirContraseña.setBounds(29, 216, 135, 16);
		frameVentanaRegistro.getContentPane().add(lblRepetirContraseña);
		
		
		
		pswdRepetirContraseña = new JPasswordField();
		pswdRepetirContraseña.setBounds(176, 211, 157, 26);
		frameVentanaRegistro.getContentPane().add(pswdRepetirContraseña);
		
		
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 32, 488, 34);
		frameVentanaRegistro.getContentPane().add(separator);
		
		
		JLabel lblRequisitoContraseña = new JLabel("min. 8 carácteres");
		lblRequisitoContraseña.setBounds(345, 183, 120, 16);
		frameVentanaRegistro.getContentPane().add(lblRequisitoContraseña);
		
		
		
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(new Color(101, 183, 246));
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setBounds(135, 244, 117, 29);

		frameVentanaRegistro.getContentPane().add(btnVolver);

		
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(new Color(101, 183, 246));
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.setBounds(264, 244, 117, 29);

		frameVentanaRegistro.getContentPane().add(btnRegistrar);
		
		frameVentanaRegistro.setVisible(true);
		
		
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVentanaRegistro.dispose();
				chequearUsuario();
				
				
			}

			
		});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameVentanaRegistro.dispose();
				
				
				
			}

			
		});
	}
		
		//Listeners
		
	
	
		private void chequearUsuario(){
			ArrayList<Usuario> listaUsuarios = GestorBD.getUsuarios();
			
			
			if(textNombre.getText().equals("") || textApellidos.getText().equals("") || textCorreo.getText().equals("") || textUsuario.getText().equals("") || pswdContraseña.getText().equals("") || pswdRepetirContraseña.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Rellena todos los campos"); // Si alguno de los campos esta vacio
				}else if(listaUsuarios.contains(textCorreo.getText()) ) {
					JOptionPane.showMessageDialog(null, "Error, este usuario ya existe");
					
					}else if (pswdContraseña.getText().length() < 8){
						JOptionPane.showMessageDialog(null, "La contraseña debe tener minimo 8 caracteres.");
						
						}//else if(pswdContraseña.getText() != pswdRepetirContraseña.getText()) {
							//JOptionPane.showMessageDialog(null, "La contraseña no coincide.");
						//}

							else {
							String nombre = textNombre.getText();
							String apellidos = textApellidos.getText();
							String correo = textCorreo.getText();
							String usuario = textUsuario.getText();
							String contrasenia = pswdContraseña.getText();
							int saldo = 0;
							try {
								
								GestorBD.insertarUsuario(new Usuario(nombre, apellidos, correo, usuario, contrasenia, saldo));
								
							}catch(Exception e) {
								System.out.println(e);
								
							}
							
	
					}
			

		
	}
}

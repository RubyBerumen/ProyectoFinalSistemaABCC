package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import controlador.UsuarioDAO;
import modelo.Usuario;

public class Login extends JFrame  {
	
	JLabel lblUsuario, lblContrase�a;
	JTextField jtfUsuario;
	JPasswordField jpfContrase�a;
	JButton btnIngresar;
	BufferedImage imagen;
	JLabel imagen1;
	JComboBox <String> cmbTipo;
	Color moradoObscuro = new Color(133,69,107);
	Color grisClaro = new Color(212,212,212);
	UsuarioDAO uDAO = new UsuarioDAO();
	boolean mostrarUsuarios = false;
	public static boolean bandera;
	
	public Login() throws IOException {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(300,470);
		setBackground(new Color(165,202,210));
		setLocationRelativeTo(null);
		setTitle("Ingresar");
		setVisible(true);
		
		imagen = ImageIO.read(new File("./archivos/user.png"));
		imagen1 = new JLabel(new ImageIcon(imagen));
		imagen1.setBounds(70, 35, 150, 150);
		add(imagen1);
		
		lblUsuario = new JLabel("Nombre");
		lblUsuario.setBounds(50, 180, 100, 25);
		lblUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		add(lblUsuario);
		
		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(50, 210, 185, 25);
		add(jtfUsuario);
		
		lblContrase�a = new JLabel("Contrase�a");
		lblContrase�a.setBounds(50, 250, 100, 25);
		lblContrase�a.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		add(lblContrase�a);
		
		jpfContrase�a = new JPasswordField();
		jpfContrase�a.setBounds(50, 280, 185, 25);
		add(jpfContrase�a);
		
		String tipo[] = {"Selecciona tipo de usuario...","Gerente","Empleado"};
		cmbTipo = new JComboBox<String>(tipo);
		cmbTipo.setBounds(50, 325, 185, 30);
		cmbTipo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		cmbTipo.setBackground(moradoObscuro);
		cmbTipo.setForeground(grisClaro);
		//add(cmbTipo);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(95, 340, 100, 35);
		btnIngresar.setBackground(moradoObscuro);
		btnIngresar.setForeground(grisClaro);
		btnIngresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		btnIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (verificar()) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							if(mostrarUsuarios) {
								new Ventana();
							}else {
								new Ventana().usuarios.setVisible(false);
							}
						}
					});
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "El nombre de usuario o la contrase�a son incorrectos");
				}
			}
		});
		add(btnIngresar);	
	}
	public boolean verificar() {
		try {
			ArrayList<Usuario> listaUsuarios = uDAO.buscarUsuario("SELECT * FROM Usuarios WHERE nombre = '"+jtfUsuario.getText()+"'");
			uDAO.setFiltro("SELECT * FROM Usuarios WHERE nombre = '"+jtfUsuario.getText()+"'");
            Thread hilo=new Thread(uDAO);
            hilo.start();
			if (listaUsuarios.size()!=0 && bandera) {
				Usuario usuario = listaUsuarios.get(0);
				mostrarUsuarios = usuario.getTipo().equals("Gerente");
				return usuario.getContrase�a().equals(jpfContrase�a.getText());
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
}
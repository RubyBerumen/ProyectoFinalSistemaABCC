package vista;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class Login extends JFrame{
	
	JLabel lblUsuario, lblContraseña;
	JTextField jtfUsuario;
	JPasswordField jpfContraseña;
	JButton btnIngresar;
	BufferedImage imagen;
	JLabel imagen1;
	JComboBox <String> cmbTipo;
	
	public Login() throws IOException {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(300,500);
		setBackground(new Color(165,202,210));
		setLocationRelativeTo(null);
		setTitle("Ingresar");
		setVisible(true);
		
		
		imagen = ImageIO.read(new File("./archivos/user.png"));
		imagen1 = new JLabel(new ImageIcon(imagen));
		imagen1.setBounds(70, 35, 150, 150);
		add(imagen1);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(50, 180, 100, 25);
		lblUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		add(lblUsuario);
		
		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(50, 210, 185, 25);
		add(jtfUsuario);
		
		lblContraseña = new JLabel("Contraseña");
		lblContraseña.setBounds(50, 250, 100, 25);
		lblContraseña.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		add(lblContraseña);
		
		jpfContraseña = new JPasswordField();
		jpfContraseña.setBounds(50, 280, 185, 25);
		add(jpfContraseña);
		
		String tipo[] = {"Selecciona tipo de usuario...","Gerente","Empleado"};
		cmbTipo = new JComboBox<String>(tipo);
		cmbTipo.setBounds(50, 325, 185, 30);
		cmbTipo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		cmbTipo.setBackground(new Color(133,69,107));
		cmbTipo.setForeground(new Color(212,212,212));
		add(cmbTipo);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(95, 380, 100, 35);
		btnIngresar.setBackground(new Color(133,69,107));
		btnIngresar.setForeground(new Color(212,212,212));
		btnIngresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		add(btnIngresar);	
		
		
		
		
		
	}
	
}



public class VentanaPrincipal {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Login();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}

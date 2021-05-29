package vista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class Login extends JFrame{
	
	JLabel lblUsuario, lblContraseña;
	JTextField jtfUsuario;
	JPasswordField jpfContraseña;
	JButton btnIngresar;
	BufferedImage imagen;
	JLabel imagen1;
	JComboBox <String> cmbTipo;
	Color moradoObscuro = new Color(133,69,107);
	Color grisClaro = new Color(212,212,212);
	
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
		cmbTipo.setBackground(moradoObscuro);
		cmbTipo.setForeground(grisClaro);
		add(cmbTipo);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(95, 380, 100, 35);
		btnIngresar.setBackground(moradoObscuro);
		btnIngresar.setForeground(grisClaro);
		btnIngresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		add(btnIngresar);	
		
	}
	
}



class Ventana extends JFrame{
	JMenuBar menuBar = new JMenuBar();
	JMenu ventas, productos, usuarios;
	JMenuItem altasV,bajasV,cambiosV,consultasV;
	JMenuItem altasP,bajasP,cambiosP,consultasP;
	JMenuItem altasU,bajasU,cambiosU,consultasU;
	JInternalFrame aV,bV,cV,coV;
	JInternalFrame aP,bP,cP,coP;
	JInternalFrame aU,bU,cU,coU;
	JPanel jpAltasV, jpBajasV, jpCambiosV, jpConsultasV;
	JPanel jpAltasProd, jpBajasProd, jpCambiosProd, jpConsultasProd;
	JPanel jpAltasU, jpBajasU, jpCambiosU, jpConsultasU;
	JDesktopPane dp = new JDesktopPane();
	
	String controlador = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/proyecto_final";
	
	Color moradoObscuro = new Color(133,69,107);
	Color grisClaro = new Color(212,212,212);
	Color moradoBajito = new Color(245,220,249);


	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("Sistema ABCC");
		setVisible(true);
		
		
		ventas = new JMenu("Ventas");
		productos = new JMenu("Productos");
		usuarios = new JMenu("Usuarios");
	
	//----------------------------------------------Ventas--------------------------------------------
		
		altasV = new JMenuItem("Altas");
		ventas.add(altasV);
		bajasV = new JMenuItem("Bajas");
		ventas.add(bajasV);	
		cambiosV = new JMenuItem("Cambios");
		ventas.add(cambiosV);	
		consultasV = new JMenuItem("Consultas");
		ventas.add(consultasV);	
	
		altasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM ventas");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aV.setVisible(true);	
				bV.setVisible(false);
				cV.setVisible(false);
				coV.setVisible(false);
				}
			});
		
		bajasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM ventas");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aV.setVisible(false);	
				bV.setVisible(true);
				cV.setVisible(false);
				coV.setVisible(false);
				}
			});
		
		cambiosV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM ventas");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aV.setVisible(false);	
				bV.setVisible(false);
				cV.setVisible(true);
				coV.setVisible(false);
				}
			});
		
		consultasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM ventas");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aV.setVisible(false);	
				bV.setVisible(false);
				cV.setVisible(false);
				coV.setVisible(true);
				}
			});	
		
		
		//------------------------------------------Productos--------------------------------------------------------
		
		altasP = new JMenuItem("Altas");
		productos.add(altasP);
		bajasP = new JMenuItem("Bajas");
		productos.add(bajasP);	
		cambiosP = new JMenuItem("Cambios");
		productos.add(cambiosP);	
		consultasP = new JMenuItem("Consultas");
		productos.add(consultasP);	
	
		altasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aP.setVisible(true);	
				bP.setVisible(false);
				cP.setVisible(false);
				coP.setVisible(false);
				}
			});
		
		bajasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aP.setVisible(false);	
				bP.setVisible(true);
				cP.setVisible(false);
				coP.setVisible(false);
				}
			});
		
		cambiosP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aP.setVisible(false);	
				bP.setVisible(false);
				cP.setVisible(true);
				coP.setVisible(false);
				}
			});
		
		consultasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aP.setVisible(false);	
				bP.setVisible(false);
				cP.setVisible(false);
				coP.setVisible(true);
				}
			});	
		
		//------------------------------------------Usuarios--------------------------------------------------------
		
		altasU = new JMenuItem("Altas");
		usuarios.add(altasU);
		bajasU = new JMenuItem("Bajas");
		usuarios.add(bajasU);	
		cambiosU = new JMenuItem("Cambios");
		usuarios.add(cambiosU);	
		consultasU = new JMenuItem("Consultas");
		usuarios.add(consultasU);	
	
		altasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM usuarios");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aU.setVisible(true);	
				bU.setVisible(false);
				cU.setVisible(false);
				coU.setVisible(false);
				}
			});
		
		bajasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aU.setVisible(false);	
				bU.setVisible(true);
				cU.setVisible(false);
				coU.setVisible(false);
				}
			});
		
		cambiosU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aU.setVisible(false);	
				bU.setVisible(false);
				cU.setVisible(true);
				coU.setVisible(false);
				}
			});
		
		consultasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
											
				ResultSetTableModel modeloDatos = null;
				try {
					modeloDatos = new ResultSetTableModel(controlador, url, "SELECT * FROM productos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				aU.setVisible(false);	
				bU.setVisible(false);
				cU.setVisible(false);
				coU.setVisible(true);
				}
			});	
		
		//------------------------------------------------------------------------------------------------------------
		
		
		menuBar.add(ventas);
		menuBar.add(productos);
		menuBar.add(usuarios);
		
		setJMenuBar(menuBar);
	
		
		//VENTAS-------------------------------------------------------
		aV = crearIF(aV, "Ventas", "Agregar");
		bV = crearIF(bV, "Ventas", "Eliminar");
		cV = crearIF(cV, "Ventas", "Modificar");
		coV = crearIF(coV, "Ventas", "Buscar");
		
		
		//PRODUCTOS-------------------------------------------------------
		aP = crearIF(aP, "Productos", "Agregar");
		bP = crearIF(bP, "Productos", "Eliminar");
		cP = crearIF(cP, "Productos", "Modificar");
		coP = crearIF(coP, "Productos", "Buscar");

		
		//USUARIOS-------------------------------------------------------
		aU = crearIF(aU, "Usuarios", "Agregar");
		bU = crearIF(bU, "Usuarios", "Eliminar");
		cU = crearIF(cU, "Usuarios", "Modificar");
		coU = crearIF(coU, "Usuarios", "Buscar");
		
		
	
		dp.add(aV);
		dp.add(bV);
		dp.add(cV);
		dp.add(coV);
		dp.add(aP);
		dp.add(bP);
		dp.add(cP);
		dp.add(coP);
		dp.add(aU);
		dp.add(bU);
		dp.add(cU);
		dp.add(coU);
		dp.setBackground(moradoObscuro);
		dp.setBounds(0, 0, 750, 450);
		add(dp);
		
		
		
		
	}
	
	public JLabel titulo(String x, JLabel jl) {
		jl = new JLabel(x);
		jl.setBounds(20, 10, 300, 65);
		jl.setFont(new Font("Moonbright Demo", Font.PLAIN, 70));
		jl.setForeground(moradoObscuro);
		
		return jl;
	}
	
	
	public JInternalFrame crearIF(JInternalFrame inf, String tab, String op) {
		inf = new JInternalFrame();
		inf.getContentPane().setLayout(null);
		inf.setDefaultCloseOperation(HIDE_ON_CLOSE);
		inf.setSize(500, 300);
		inf.setTitle(tab);
		JLabel jlTitulo = new JLabel();
		jlTitulo = titulo(op,jlTitulo);
		inf.add(jlTitulo);
		
		return inf;
	
	}
	
}




public class VentanaPrincipal {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Ventana();
			}
		});
		
		/*SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Login();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});*/

	}

}

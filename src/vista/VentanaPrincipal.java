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
	
	JLabel lblUsuario, lblContrase�a;
	JTextField jtfUsuario;
	JPasswordField jpfContrase�a;
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
				aV.setVisible(true);	
				bV.setVisible(false);
				cV.setVisible(false);
				coV.setVisible(false);
				}
			});
		
		bajasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aV.setVisible(false);	
				bV.setVisible(true);
				cV.setVisible(false);
				coV.setVisible(false);
				}
			});
		
		cambiosV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aV.setVisible(false);	
				bV.setVisible(false);
				cV.setVisible(true);
				coV.setVisible(false);
				}
			});
		
		consultasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				aP.setVisible(true);	
				bP.setVisible(false);
				cP.setVisible(false);
				coP.setVisible(false);
				}
			});
		
		bajasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aP.setVisible(false);	
				bP.setVisible(true);
				cP.setVisible(false);
				coP.setVisible(false);
				}
			});
		
		cambiosP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aP.setVisible(false);	
				bP.setVisible(false);
				cP.setVisible(true);
				coP.setVisible(false);
				}
			});
		
		consultasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				aU.setVisible(true);	
				bU.setVisible(false);
				cU.setVisible(false);
				coU.setVisible(false);
				}
			});
		
		bajasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aU.setVisible(false);	
				bU.setVisible(true);
				cU.setVisible(false);
				coU.setVisible(false);
				}
			});
		
		cambiosU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aU.setVisible(false);	
				bU.setVisible(false);
				cU.setVisible(true);
				coU.setVisible(false);
				}
			});
		
		consultasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		aV = crearIF(aV, "Ventas", "Agregar", 500, 300);
		bV = crearIF(bV, "Ventas", "Eliminar", 500, 300);
		cV = crearIF(cV, "Ventas", "Modificar", 500, 300);
		coV = crearIF(coV, "Ventas", "Buscar", 500, 300);
		
		//PRODUCTOS-------------------------------------------------------
		aP = crearIF(aP, "Productos", "Agregar", 400, 335);
		componentesProductos(aP);
		bP = crearIF(bP, "Productos", "Eliminar", 400, 335);
		componentesProductos(bP);
		cP = crearIF(cP, "Productos", "Modificar", 400, 335);
		componentesProductos(cP);
		coP = crearIF(coP, "Productos", "Buscar", 400, 335);
		componentesProductos(coP);
		
		//USUARIOS-------------------------------------------------------
		aU = crearIF(aU, "Usuarios", "Agregar", 500, 300);
		componentesUsuarios(aU);
		bU = crearIF(bU, "Usuarios", "Eliminar", 500, 300);
		componentesUsuarios(bU);
		cU = crearIF(cU, "Usuarios", "Modificar", 500, 300);
		componentesUsuarios(cU);
		coU = crearIF(coU, "Usuarios", "Buscar", 500, 300);
		componentesUsuarios(coU);
		
		//-----------------------------------------------------------------
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
	
	
	public JInternalFrame crearIF(JInternalFrame inf, String tab, String op, int w, int h) {
		inf = new JInternalFrame();
		inf.getContentPane().setLayout(null);
		inf.setDefaultCloseOperation(HIDE_ON_CLOSE);
		inf.setSize(w, h);
		inf.setTitle(tab);
		JLabel jlTitulo = new JLabel();
		jlTitulo = titulo(op,jlTitulo);
		inf.add(jlTitulo);
		return inf;
	}
	
	
	public void agregarComponente (Component c, int x, int y,int w, int h, JInternalFrame inf) {
		c.setBounds(x, y, w, h);
		inf.add(c);
	}

	
	public void componentesUsuarios(JInternalFrame inf) {
		JLabel lblNoUsuario = new JLabel("no_usuario: ");
		agregarComponente(lblNoUsuario, 20, 100, 80, 25, inf);
		JTextField txtNoUsuario = new JTextField();
		agregarComponente(txtNoUsuario, 100, 100, 110, 25, inf);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		agregarComponente(lblNombre, 20, 130, 60, 25, inf);
		JTextField txtNombre = new JTextField();
		agregarComponente(txtNombre, 80, 130, 130, 25, inf);
		
		JLabel lblContrase�a = new JLabel("Contrase�a: ");
		agregarComponente(lblContrase�a, 20, 160, 80, 25, inf);
		JTextField txtContrase�a = new JTextField();
		agregarComponente(txtContrase�a, 105, 160, 105, 25, inf);
		
		JLabel lblTipo = new JLabel("Tipo de usuario:");
		agregarComponente(lblTipo, 20, 190, 150, 25, inf);
		String tipo[] = {"Selecciona tipo de usuario...","Gerente","Empleado"};
		JComboBox cmbTipo = new JComboBox<String>(tipo);
		cmbTipo.setBackground(moradoObscuro);
		cmbTipo.setForeground(grisClaro);
		agregarComponente(cmbTipo, 20, 215, 195, 30, inf);
		
		
	}
	
	
	public void componentesProductos(JInternalFrame inf){
		JLabel lblIdProd = new JLabel("id_producto: ");
		agregarComponente(lblIdProd, 20, 100, 80, 25, inf);
		JTextField txtIdProd = new JTextField();
		agregarComponente(txtIdProd, 100, 100, 110, 25, inf);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		agregarComponente(lblNombre, 20, 130, 60, 25, inf);
		JTextField txtNombre = new JTextField();
		agregarComponente(txtNombre, 80, 130, 130, 25, inf);
		
		JLabel lblPrecio = new JLabel("Precio:");
		agregarComponente(lblPrecio, 20, 160, 50, 25, inf);
		JTextField txtPrecio = new JTextField();
		agregarComponente(txtPrecio, 70, 160, 140, 25, inf);
		
		JLabel lblDescripcion = new JLabel("Descripci�n:");
		agregarComponente(lblDescripcion, 20, 190, 80, 25, inf);
		JTextArea txtDescripcion = new JTextArea();
		JScrollPane sp = new JScrollPane(txtDescripcion);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		agregarComponente(sp, 20, 215, 195, 70, inf);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(moradoObscuro);
		btnBorrar.setForeground(grisClaro);
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtIdProd.setText("");
				txtNombre.setText("");
				txtPrecio.setText("");
				txtDescripcion.setText("");
			}
		});
		agregarComponente(btnBorrar, 230, 200, 140, 25, inf);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(moradoObscuro);
		btnCancelar.setForeground(grisClaro);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				inf.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 230, 250, 140, 25, inf);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		
		if(inf==aP) {
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(moradoObscuro);
			btnAgregar.setForeground(grisClaro);
			agregarComponente(btnAgregar, 230, 100, 140, 25, inf);
		}else if(inf==bP) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(moradoObscuro);
			btnEliminar.setForeground(grisClaro);
			agregarComponente(btnEliminar, 230, 150, 140, 25, inf);
		}else if(inf==cP) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnGuardar = new JButton("Guardar cambios");
			btnGuardar.setBackground(moradoObscuro);
			btnGuardar.setForeground(grisClaro);
			agregarComponente(btnGuardar, 230, 150, 140, 25, inf);
		}else if(inf==coP) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
		}else {
		}	
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

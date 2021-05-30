package vista;
import java.awt.*;

import modelo.*;
import controlador.*;

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
		
		lblUsuario = new JLabel("Nombre");
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
		aV = crearIF(aV, "Ventas", "Agregar", 380, 365);
		componentesVentas(aV);
		bV = crearIF(bV, "Ventas", "Eliminar", 380, 365);
		componentesVentas(bV);
		cV = crearIF(cV, "Ventas", "Modificar", 380, 365);
		componentesVentas(cV);
		coV = crearIF(coV, "Ventas", "Buscar", 380, 365);
		componentesVentas(coV);
		
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
		aU = crearIF(aU, "Usuarios", "Agregar", 400, 300);
		componentesUsuarios(aU);
		bU = crearIF(bU, "Usuarios", "Eliminar", 400, 300);
		componentesUsuarios(bU);
		cU = crearIF(cU, "Usuarios", "Modificar", 400, 300);
		componentesUsuarios(cU);
		coU = crearIF(coU, "Usuarios", "Buscar", 400, 300);
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
		inf.setLocation(10, 10);
		Border border = new TitledBorder(new EtchedBorder());
		inf.setBorder(border);
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
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		agregarComponente(lblContraseña, 20, 160, 80, 25, inf);
		JTextField txtContraseña = new JTextField();
		agregarComponente(txtContraseña, 105, 160, 105, 25, inf);
		
		JLabel lblTipo = new JLabel("Tipo de usuario:");
		agregarComponente(lblTipo, 20, 190, 150, 25, inf);
		String tipo[] = {"Selecciona tipo de usuario...","Gerente","Empleado"};
		JComboBox cmbTipo = new JComboBox<String>(tipo);
		cmbTipo.setBackground(moradoObscuro);
		cmbTipo.setForeground(grisClaro);
		agregarComponente(cmbTipo, 20, 215, 195, 30, inf);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(moradoObscuro);
		btnBorrar.setForeground(grisClaro);
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNoUsuario.setText("");
				txtNombre.setText("");
				txtContraseña.setText("");
				cmbTipo.setSelectedItem("Selecciona tipo de usuario...");
			}
		});
		agregarComponente(btnBorrar, 230, 180, 140, 25, inf);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(moradoObscuro);
		btnCancelar.setForeground(grisClaro);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				inf.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 230, 220, 140, 25, inf);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		
		if(inf==aU) {
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(moradoObscuro);
			btnAgregar.setForeground(grisClaro);
			agregarComponente(btnAgregar, 230, 100, 140, 25, inf);
		}else if(inf==bU) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(moradoObscuro);
			btnEliminar.setForeground(grisClaro);
			agregarComponente(btnEliminar, 230, 140, 140, 25, inf);
		}else if(inf==cU) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnGuardar = new JButton("Guardar cambios");
			btnGuardar.setBackground(moradoObscuro);
			btnGuardar.setForeground(grisClaro);
			agregarComponente(btnGuardar, 230, 140, 140, 25, inf);
		}else if(inf==coU) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
		}else {
		}	
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
		
		JLabel lblDescripcion = new JLabel("Descripción:");
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
			btnAgregar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Producto p = new Producto(txtIdProd.getText(), txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), txtDescripcion.getText());
					
					ProductoDAO pDAO = new ProductoDAO();
					
					System.out.println(pDAO.insertarRragistro(p)?"Se agregó correctamente":"Error");
					
					if(pDAO.insertarRragistro(p)) {
						JOptionPane.showMessageDialog(rootPane,"Se agregó correctamente a la base de datos");
					}else {
						JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar agregar a la base de datos");
					}
				}
			});
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
	
	
	public void componentesVentas(JInternalFrame inf) {
		JLabel lblNoVenta = new JLabel("no_venta: ");
		agregarComponente(lblNoVenta, 20, 100, 60, 25, inf);
		JTextField txtNoVenta = new JTextField();
		agregarComponente(txtNoVenta, 85, 100, 100, 25, inf);
		
		JLabel lblProductos = new JLabel("Productos: ");
		agregarComponente(lblProductos, 20, 130, 80, 25, inf);
		JTextArea txtProductos = new JTextArea();
		JScrollPane sp = new JScrollPane(txtProductos);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		agregarComponente(sp, 20, 155, 165, 70, inf);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		agregarComponente(lblFecha, 20, 230, 40, 25, inf);
		JComboBox<String> cmbFecha[]=new JComboBox[3];
		for (int i=0;i<cmbFecha.length;i+=1) {
			cmbFecha[i]=new JComboBox<String>();
			agregarComponente(cmbFecha[i], 20+(i*55), 255, 55, 20, inf);
		}
		for (int i = 1; i <= 31; i+=1) {	
			if (i<10) {
				cmbFecha[0].addItem("0"+i);
			}else {
				cmbFecha[0].addItem(""+i);
			}
		}
		cmbFecha[1].addItem("Ene");
		cmbFecha[1].addItem("Feb");
		cmbFecha[1].addItem("Mar");
		cmbFecha[1].addItem("Abr");
		cmbFecha[1].addItem("May");
		cmbFecha[1].addItem("Jun");
		cmbFecha[1].addItem("Jul");
		cmbFecha[1].addItem("Ago");
		cmbFecha[1].addItem("Sep");
		cmbFecha[1].addItem("Oct");
		cmbFecha[1].addItem("Nov");
		cmbFecha[1].addItem("Dic");
		for (int i = 2021; i <= 2030; i+=1) {cmbFecha[2].addItem(""+i);}
		
		JLabel lbltotal = new JLabel("Total: ");
		agregarComponente(lbltotal, 20, 290, 40, 25, inf);
		JTextField txtTotal = new JTextField();
		agregarComponente(txtTotal, 65, 290, 120, 25, inf);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(moradoObscuro);
		btnBorrar.setForeground(grisClaro);
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNoVenta.setText("");
				txtProductos.setText("");
				cmbFecha[0].setSelectedItem("01");
				cmbFecha[1].setSelectedItem("Ene");
				cmbFecha[2].setSelectedItem("2021");
				txtTotal.setText("");
			}
		});
		agregarComponente(btnBorrar, 210, 226, 140, 25, inf);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(moradoObscuro);
		btnCancelar.setForeground(grisClaro);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				inf.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 210, 290, 140, 25, inf);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		
		if(inf==aV) {
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(moradoObscuro);
			btnAgregar.setForeground(grisClaro);
			agregarComponente(btnAgregar, 210, 100, 140, 25, inf);
			txtNoVenta.setEditable(false);
		}else if(inf==bV) {
			agregarComponente(btnBuscar, 210, 100, 140, 25, inf);
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(moradoObscuro);
			btnEliminar.setForeground(grisClaro);
			agregarComponente(btnEliminar, 210, 163, 140, 25, inf);
		}else if(inf==cV) {
			agregarComponente(btnBuscar, 210, 100, 140, 25, inf);
			JButton btnGuardar = new JButton("Guardar cambios");
			btnGuardar.setBackground(moradoObscuro);
			btnGuardar.setForeground(grisClaro);
			agregarComponente(btnGuardar, 210, 163, 140, 25, inf);
		}else if(inf==coV) {
			agregarComponente(btnBuscar, 210, 100, 140, 25, inf);
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

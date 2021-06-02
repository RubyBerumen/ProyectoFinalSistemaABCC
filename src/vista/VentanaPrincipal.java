package vista;
import java.awt.*;

import modelo.*;
import controlador.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



class Login extends JFrame  {
	
	JLabel lblUsuario, lblContraseña;
	JTextField jtfUsuario;
	JPasswordField jpfContraseña;
	JButton btnIngresar;
	BufferedImage imagen;
	JLabel imagen1;
	JComboBox <String> cmbTipo;
	Color moradoObscuro = new Color(133,69,107);
	Color grisClaro = new Color(212,212,212);
	UsuarioDAO uDAO = new UsuarioDAO();
	boolean mostrarUsuarios = false;
	
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
		//add(cmbTipo);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(95, 380, 100, 35);
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
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
			}
		});
		add(btnIngresar);	
	}
	public boolean verificar() {
		try {
			ArrayList<Usuario> listaUsuarios = uDAO.buscarUsuario("SELECT * FROM Usuarios WHERE nombre = '"+jtfUsuario.getText()+"'");
			if (listaUsuarios.size()!=0) {
				Usuario usuario = listaUsuarios.get(0);
				mostrarUsuarios = usuario.getTipo().equals("Gerente");
				return usuario.getContraseña().equals(jpfContraseña.getText());
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
		
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
	JInternalFrame tV, tP, tU, tP2;
	JDesktopPane dp = new JDesktopPane();
	
	String controlador = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/proyecto_final";
	String sqlProducto =  "SELECT * FROM producto";
	String sqlUsuarios =  "SELECT * FROM usuarios";
	String sqlVentas =  "SELECT * FROM ventas";
	
	JTable tablaP, tablaV, tablaU;
	JScrollPane spP = new JScrollPane();
	JScrollPane spV = new JScrollPane();
	JScrollPane spU = new JScrollPane();
	
	Color moradoObscuro = new Color(133,69,107);
	Color grisClaro = new Color(212,212,212);
	Color moradoBajito = new Color(245,220,249);


	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(800,450);
		setLocationRelativeTo(null);
		setTitle("Sistema ABCC");
		setVisible(true);
		
		
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
		componentesProductosConsultas(coP);
		
		//USUARIOS-------------------------------------------------------
		aU = crearIF(aU, "Usuarios", "Agregar", 400, 300);
		componentesUsuarios(aU);
		bU = crearIF(bU, "Usuarios", "Eliminar", 400, 300);
		componentesUsuarios(bU);
		cU = crearIF(cU, "Usuarios", "Modificar", 400, 300);
		componentesUsuarios(cU);
		coU = crearIF(coU, "Usuarios", "Buscar", 400, 300);
		componentesUsuariosConsultas(coU);
		
		//TABLAS-----------------------------------------------------------
		tV = crearIfTabla(tV, "Tabla ventas", 373, 215, 400, 10);
		tP = crearIfTabla(tP, "Tabla productos", 350, 215, 420, 10);
		tU = crearIfTabla(tU, "Tabla usuarios", 350, 215, 420, 10);
		tP2 = crearIfTabla(tP2, "Tabla productos", 350, 150, 420, 225 );
		
		//-----------------------------------------------------------------
		
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
				mostrarTablaVentas(sqlVentas);
				visibleIF(aV, bV, cV, coV, aP, bP, cP, coP, aU, bU, cU, coU);
				visibleT(tP, tU);
				}
			});
		
		bajasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaVentas(sqlVentas);
				visibleIF(bV, aV, cV, coV, aP, bP, cP, coP, aU, bU, cU, coU);
				visibleT(tP, tU);
				}
			});
		
		cambiosV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaVentas(sqlVentas);
				visibleIF(cV, bV, aV, coV, aP, bP, cP, coP, aU, bU, cU, coU);
				visibleT(tP, tU);
				}
			});
		
		consultasV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaVentas(sqlVentas);
				visibleIF(coV, bV, cV, aV, aP, bP, cP, coP, aU, bU, cU, coU);
				visibleT(tP, tU);
				
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
				mostrarTablaProductos(sqlProducto);
				visibleIF(aP, bP, cP, coP, aU, bU, cU, coU, aV, bV, coV, cV);
				visibleT(tU, tV);
				}
			});
		
		bajasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaProductos(sqlProducto);
				visibleIF(bP, aP, cP, coP, aU, bU, cU, coU, aV, bV, coV, cV);
				visibleT(tU, tV);
				}
			});
		
		cambiosP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaProductos(sqlProducto);
				visibleIF(cP, bP, aP, coP, aU, bU, cU, coU, aV, bV, coV, cV);
				visibleT(tU, tV);
				}
			});
		
		consultasP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaProductos(sqlProducto);
				visibleIF(coP, aP, bP, cP, aU, bU, cU, coU, aV, bV, coV, cV);
				visibleT(tU, tV);
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
				mostrarTablaUsuarios(sqlUsuarios);
				visibleIF(aU, bU, coU, cU, aP, bP, cP, coP, aV, bV, cV, coV);
				visibleT(tP, tV);
				}
			});
		
		bajasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaUsuarios(sqlUsuarios);
				visibleIF(bU, aU, coU, cU, aP, bP, cP, coP, aV, bV, cV, coV);
				visibleT(tP, tV);
				}
			});
		
		cambiosU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaUsuarios(sqlUsuarios);
				visibleIF(cU, bU, coU, aU, aP, bP, cP, coP, aV, bV, cV, coV);
				visibleT(tP, tV);
				}
			});
		
		consultasU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarTablaUsuarios(sqlUsuarios);
				visibleIF(coU, bU, aU, cU, aP, bP, cP, coP, aV, bV, cV, coV);
				visibleT(tP, tV);
				}
			});	
		
		
//------------------------------------------------------------------------------------------------------------	
		menuBar.add(ventas);
		menuBar.add(productos);
		menuBar.add(usuarios);
		
		setJMenuBar(menuBar);
		
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
		dp.add(tV);
		dp.add(tP);
		dp.add(tU);
		dp.setBackground(moradoObscuro);
		dp.setBounds(0, 0, 800, 400);
		add(dp);
		
	}
	
	
	
	
//===============================================MÉTODOS PRODUCTOS==================================================
	public void mostrarTablaProductos(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel(controlador,url,sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		tP.remove(spP);
		tablaP = new JTable(modeloDatos);
		tablaP.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	//obtenerRegistroTabla(tablaP);
		    }
		});
		spP = new JScrollPane(tablaP);
		spP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spP.setBounds(0,0,350,190);
		tP.add(spP);
		tP.setVisible(true);
	}
	
	
	public void componentesProductos(JInternalFrame inf){
		JLabel lblIdProd = new JLabel("id_producto: ");
		agregarComponente(lblIdProd, 20, 100, 80, 25, inf);
		JTextField txtIdProd = new JTextField();
		agregarComponente(txtIdProd, 100, 100, 110, 25, inf);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		agregarComponente(lblNombre, 20, 130, 60, 25, inf);
		JTextField txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
					txtNombre.setEditable(false);
				}else{
					txtNombre.setEditable(true);
				}
			}
		});
		agregarComponente(txtNombre, 80, 130, 130, 25, inf);
		
		JLabel lblPrecio = new JLabel("Precio:");
		agregarComponente(lblPrecio, 20, 160, 50, 25, inf);
		JTextField txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')|| ke.getKeyChar()=='.'||(code==KeyEvent.VK_BACK_SPACE)) {
					txtPrecio.setEditable(true);
				}else{
					txtPrecio.setEditable(false);
				}
			}
		});
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
				restablecerComponentes(txtIdProd, txtNombre, txtPrecio, txtDescripcion);
				mostrarTablaProductos(sqlProducto);
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
				tP.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 230, 250, 140, 25, inf);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql =  "SELECT * FROM producto WHERE idProducto = \""+txtIdProd.getText()+"\"";
				if(txtIdProd.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Debes ingresar el id del producto");
				}else {
					mostrarTablaProductos(sql);
				}
			}
		});
		
		if(inf==aP) {
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(moradoObscuro);
			btnAgregar.setForeground(grisClaro);
			btnAgregar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Producto p = new Producto(txtIdProd.getText(), txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), txtDescripcion.getText());
					ProductoDAO pDAO = new ProductoDAO();
					if(pDAO.insertarRragistro(p)) {
						JOptionPane.showMessageDialog(rootPane,"Se agregó correctamente a la base de datos");
					}else {
						JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar agregar a la base de datos");
					}
					mostrarTablaProductos(sqlProducto);
				}
			});
			agregarComponente(btnAgregar, 230, 100, 140, 25, inf);
		}else if(inf==bP) {
			txtNombre.setEditable(false);
			txtPrecio.setEditable(false);
			txtDescripcion.setEditable(false);
			spP.setEnabled(false);
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(moradoObscuro);
			btnEliminar.setForeground(grisClaro);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String id = txtIdProd.getText();
					ProductoDAO pDAO = new ProductoDAO();					
					if(pDAO.eliminarRegistro(id)) {
						JOptionPane.showMessageDialog(rootPane,"Se eliminó correctamente de la base de datos");
					}else {
						JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar eliminar a la base de datos");
					}
					mostrarTablaProductos(sqlProducto);
				}
			});
			agregarComponente(btnEliminar, 230, 150, 140, 25, inf);
		}else if(inf==cP) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnGuardar = new JButton("Guardar cambios");
			btnGuardar.setBackground(moradoObscuro);
			btnGuardar.setForeground(grisClaro);
			btnGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ProductoDAO pDAO = new ProductoDAO();
					if (txtIdProd.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "No se está especificando el id del producto");
					}else if(txtNombre.getText().equals("")||txtPrecio.getText().equals("")||txtDescripcion.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Debes completar todos los campos");
					}else {
						Producto p = new Producto(txtIdProd.getText(), txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), txtDescripcion.getText());
	
						if (pDAO.modificarRegistro(p)) {
							JOptionPane.showMessageDialog(null, "Datos de producto modificados exitosamente");
						}else {
							JOptionPane.showMessageDialog(null, "No se pudieron modificar los datos del producto");
						}
						mostrarTablaProductos(sqlProducto);
					}
				}
				
			});
			agregarComponente(btnGuardar, 230, 150, 140, 25, inf);
		}else {
		}	
	}
	
	
	public void componentesProductosConsultas(JInternalFrame inf) {
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rbTodos = new JRadioButton("Todos");
		bg.add(rbTodos);
		agregarComponente(rbTodos, 20, 75, 80, 25, inf);
		
		JRadioButton rbIdProd = new JRadioButton("id_producto: ");
		bg.add(rbIdProd);
		agregarComponente(rbIdProd, 20, 105, 100, 25, inf);
		JTextField txtIdProd = new JTextField();
		txtIdProd.setEditable(false);
		agregarComponente(txtIdProd, 120, 105, 110, 25, inf);
		
		JRadioButton rbNombre = new JRadioButton("Nombre: ");
		bg.add(rbNombre);
		agregarComponente(rbNombre, 20, 135, 80, 25, inf);
		JTextField txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
					txtNombre.setEditable(false);
				}else{
					txtNombre.setEditable(true);
				}
			}
		});
		txtNombre.setEditable(false);
		agregarComponente(txtNombre, 100, 135, 130, 25, inf);
		
		JRadioButton rbPrecio = new JRadioButton("Precio:");
		bg.add(rbPrecio);
		agregarComponente(rbPrecio, 20, 165, 70, 25, inf);
		JTextField txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')|| ke.getKeyChar()=='.'||(code==KeyEvent.VK_BACK_SPACE)) {
					txtPrecio.setEditable(true);
				}else{
					txtPrecio.setEditable(false);
				}
			}
		});
		txtPrecio.setEditable(false);
		agregarComponente(txtPrecio, 90, 165, 140, 25, inf);
		
		JRadioButton rbDescripcion = new JRadioButton("Descripción:");
		bg.add(rbDescripcion);
		agregarComponente(rbDescripcion, 20, 195, 100, 25, inf);
		JTextArea txtDescripcion = new JTextArea();
		txtDescripcion.setEditable(false);
		JScrollPane sp = new JScrollPane(txtDescripcion);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		agregarComponente(sp, 20, 220, 210, 70, inf);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(moradoObscuro);
		btnBorrar.setForeground(grisClaro);
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restablecerComponentes(txtIdProd, txtNombre, txtPrecio, txtDescripcion);
				mostrarTablaProductos(sqlProducto);
			}
		});
		agregarComponente(btnBorrar, 250, 190, 120, 25, inf);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(moradoObscuro);
		btnCancelar.setForeground(grisClaro);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				inf.setVisible(false);
				tP.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 250, 260, 120, 25, inf);
		
		rbIdProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtIdProd.setEditable(true);
				editableF(txtNombre,txtPrecio,txtDescripcion);
			}
		});
		rbNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNombre.setEditable(true);
				editableF(txtIdProd,txtPrecio,txtDescripcion);
			}
		});
		rbPrecio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtPrecio.setEditable(true);
				editableF(txtIdProd,txtNombre,txtDescripcion);
			}
		});
		rbDescripcion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtDescripcion.setEditable(true);
				editableF(txtIdProd,txtNombre,txtPrecio);
			}
		});
		rbTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editableT(txtIdProd,txtNombre,txtPrecio,txtDescripcion);
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql;
				if(rbTodos.isSelected()) {
					sql = "SELECT * FROM producto WHERE idProducto='"+txtIdProd.getText()+"' AND Nombre='"+txtNombre.getText()+
							"' AND precio='"+txtPrecio.getText()+"' AND Descripcion = '"+txtDescripcion.getText()+"';";
					if(txtIdProd.getText().equals("")||txtNombre.getText().equals("")||txtPrecio.getText().equals("")||txtDescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes llenar todos los campos");
					}else {
						mostrarTablaProductos(sql);
					}
				}else if(rbIdProd.isSelected()) {
					sql =  "SELECT * FROM producto WHERE idProducto = \""+txtIdProd.getText()+"\"";
					if(txtIdProd.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar el id del producto");
					}else {
						mostrarTablaProductos(sql);
					}
				}else if(rbNombre.isSelected()) {
					sql =  "SELECT * FROM producto WHERE Nombre = \""+txtNombre.getText()+"\"";
					if(txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar un nombre");
					}else {
						mostrarTablaProductos(sql);
					}
				}else if(rbPrecio.isSelected()) {
					sql =  "SELECT * FROM producto WHERE Precio = \""+txtPrecio.getText()+"\"";
					if(txtPrecio.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar un precio");
					}else {
						mostrarTablaProductos(sql);
					}
				}else if(rbDescripcion.isSelected()) {
					sql =  "SELECT * FROM producto WHERE Descripcion = \""+txtDescripcion.getText()+"\"";
					if(txtDescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar una descripcion");
					}else {
						mostrarTablaProductos(sql);
					}
				}else {
				}
			}//action preformed
		});
		agregarComponente(btnBuscar, 250, 85, 120, 30, inf);
		
	}
	
	
//===========================================MÉTODOS USUARIOS===========================================
	public void mostrarTablaUsuarios(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel(controlador,url,sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		tU.remove(spU);
		tablaU = new JTable(modeloDatos);
		tablaU.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	//obtenerRegistroTabla(tablaP);
		    }
		});
		spU = new JScrollPane(tablaU);
		spU.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spU.setBounds(0,0,350,190);
		tU.add(spU);
		tU.setVisible(true);
	}
	
	
	public void componentesUsuarios(JInternalFrame inf) {
		JLabel lblNoUsuario = new JLabel("no_usuario: ");
		agregarComponente(lblNoUsuario, 20, 100, 80, 25, inf);
		JTextField txtNoUsuario = new JTextField();
		txtNoUsuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')||(code==KeyEvent.VK_BACK_SPACE)) {
					txtNoUsuario.setEditable(true);
				}else{
					txtNoUsuario.setEditable(false);
				}
			}
		});
		agregarComponente(txtNoUsuario, 100, 100, 110, 25, inf);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		agregarComponente(lblNombre, 20, 130, 60, 25, inf);
		JTextField txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
					txtNombre.setEditable(false);
				}else{
					txtNombre.setEditable(true);
				}
			}
		});
		agregarComponente(txtNombre, 80, 130, 130, 25, inf);
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		agregarComponente(lblContraseña, 20, 160, 80, 25, inf);
		JPasswordField txtContraseña = new JPasswordField();
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
				restablecerComponentes(txtNoUsuario, txtNombre, txtContraseña, cmbTipo);
				mostrarTablaUsuarios(sqlUsuarios);
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
				tU.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 230, 220, 140, 25, inf);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql =  "SELECT * FROM usuarios WHERE noUsuario = \""+txtNoUsuario.getText()+"\"";
				if(txtNoUsuario.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Debes ingresar el numero del usuario");
				}else {
					mostrarTablaUsuarios(sql);
				}
			}
		});
		
		if(inf==aU) {
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(moradoObscuro);
			btnAgregar.setForeground(grisClaro);
			btnAgregar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					UsuarioDAO uDAO = new UsuarioDAO();
					if(txtNoUsuario.getText().equals("") || txtNombre.getText().equals("") || txtContraseña.getText().equals("") ){
						JOptionPane.showMessageDialog(rootPane,"Debes llenar todos los campos");
					}else if((cmbTipo.getSelectedItem().equals("Selecciona tipo de usuario..."))) {
						JOptionPane.showMessageDialog(rootPane,"Debes seleccionar el tipo de usuario");
					}else if(cmbTipo.getSelectedItem()=="Gerente") {
						Usuario u = new Usuario(Byte.parseByte(txtNoUsuario.getText()), txtNombre.getText(), txtContraseña.getText(), "Gerente");
						if(uDAO.insertarRragistro(u)) {
							JOptionPane.showMessageDialog(rootPane,"Se agregó correctamente a la base de datos");
						}else {
							JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar agregar a la base de datos");
						}
					}else if(cmbTipo.getSelectedItem()=="Empleado") {
						Usuario u = new Usuario(Byte.parseByte(txtNoUsuario.getText()), txtNombre.getText(), txtContraseña.getText(), "Empleado");
						if(uDAO.insertarRragistro(u)) {
							JOptionPane.showMessageDialog(rootPane,"Se agregó correctamente a la base de datos");
						}else {
							JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar agregar a la base de datos");
						}
					}else {
						
					}
					mostrarTablaUsuarios(sqlUsuarios);
				}
			});
			agregarComponente(btnAgregar, 230, 100, 140, 25, inf);
		}else if(inf==bU) {
			editableF(txtNombre, txtContraseña, cmbTipo);
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(moradoObscuro);
			btnEliminar.setForeground(grisClaro);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String no = txtNoUsuario.getText();
					UsuarioDAO uDAO = new UsuarioDAO();	
					if(no.equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar el numero de usuario");
					}else if(uDAO.eliminarRegistro(no)) {
						JOptionPane.showMessageDialog(rootPane,"Se eliminó correctamente de la base de datos");
					}else {
						JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar eliminar a la base de datos");
					}
					mostrarTablaUsuarios(sqlUsuarios);
				}
			});
			agregarComponente(btnEliminar, 230, 140, 140, 25, inf);
		}else if(inf==cU) {
			agregarComponente(btnBuscar, 230, 100, 140, 25, inf);
			JButton btnGuardar = new JButton("Guardar cambios");
			btnGuardar.setBackground(moradoObscuro);
			btnGuardar.setForeground(grisClaro);
			btnGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					UsuarioDAO uDAO = new UsuarioDAO();
					if (txtNoUsuario.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debes ingresar el numero de usuario");
					}else if(txtNombre.getText().equals("")||txtNombre.getText().equals("")||txtContraseña.getText().equals("")||cmbTipo.getSelectedItem().equals("Selecciona tipo de usuario...")){
						JOptionPane.showMessageDialog(null, "Debes completar todos los campos");
					}else if(cmbTipo.getSelectedItem().equals("Gerente")) {
						Usuario u = new Usuario(Byte.parseByte(txtNoUsuario.getText()), txtNombre.getText(), txtContraseña.getText(), "Gerente");
						if (uDAO.modificarRegistro(u)) {
							JOptionPane.showMessageDialog(null, "Datos de usuario modificados exitosamente");
						}else {
							JOptionPane.showMessageDialog(null, "No se pudieron modificar los datos del usuario");
						}
					}else if(cmbTipo.getSelectedItem().equals("Empleado")) {
						Usuario u = new Usuario(Byte.parseByte(txtNoUsuario.getText()), txtNombre.getText(), txtContraseña.getText(), "Empleado");
						if(uDAO.modificarRegistro(u)) {
							JOptionPane.showMessageDialog(rootPane,"Datos de usuario modificados exitosamente");
						}else {
							JOptionPane.showMessageDialog(rootPane,"No se pudieron modificar los datos del usuario");
						}
					}else {
						
					}
					mostrarTablaUsuarios(sqlUsuarios);
				}
				
			});
			agregarComponente(btnGuardar, 230, 140, 140, 25, inf);
		}else {
		}	
	}
	
	
	public void componentesUsuariosConsultas(JInternalFrame inf) {
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rbTodos = new JRadioButton("Todos");
		bg.add(rbTodos);
		agregarComponente(rbTodos, 20, 75, 80, 25, inf);
		
		JRadioButton rbNoUsuario = new JRadioButton("no_usuario: ");
		bg.add(rbNoUsuario);
		agregarComponente(rbNoUsuario, 20, 105, 100, 25, inf);
		JTextField txtNoUsuario = new JTextField();
		txtNoUsuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')||(code==KeyEvent.VK_BACK_SPACE)) {
					txtNoUsuario.setEditable(true);
				}else{
					txtNoUsuario.setEditable(false);
				}
			}
		});
		txtNoUsuario.setEditable(false);
		agregarComponente(txtNoUsuario, 120, 105, 110, 25, inf);
		
		JRadioButton rbNombre = new JRadioButton("Nombre: ");
		bg.add(rbNombre);
		agregarComponente(rbNombre, 20, 135, 80, 25, inf);
		JTextField txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
					txtNombre.setEditable(false);
				}else{
					txtNombre.setEditable(true);
				}
			}
		});
		txtNombre.setEditable(false);
		agregarComponente(txtNombre, 100, 135, 130, 25, inf);
		
		JRadioButton rbContraseña = new JRadioButton("Contraseña:");
		bg.add(rbContraseña);
		agregarComponente(rbContraseña, 20, 165, 100, 25, inf);
		JPasswordField txtContraseña = new JPasswordField();
		txtContraseña.setEditable(false);
		agregarComponente(txtContraseña, 120, 165, 110, 25, inf);
		
		JRadioButton rbTipo = new JRadioButton("Tipo: ");
		bg.add(rbTipo);
		agregarComponente(rbTipo, 20, 195, 100, 25, inf);
		String tipo[] = {"Selecciona tipo de usuario...","Gerente","Empleado"};
		JComboBox cmbTipo = new JComboBox<String>(tipo);
		cmbTipo.setBackground(moradoObscuro);
		cmbTipo.setForeground(grisClaro);
		agregarComponente(cmbTipo, 20, 220, 195, 30, inf);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(moradoObscuro);
		btnBorrar.setForeground(grisClaro);
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restablecerComponentes(txtNoUsuario, txtNombre, txtContraseña, cmbTipo);
				mostrarTablaUsuarios(sqlUsuarios);
			}
		});
		agregarComponente(btnBorrar, 250, 165, 120, 25, inf);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(moradoObscuro);
		btnCancelar.setForeground(grisClaro);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				inf.setVisible(false);
				tU.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 250, 225, 120, 25, inf);
		
		rbNoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNoUsuario.setEditable(true);
				editableF(txtNombre,txtContraseña, cmbTipo);
			}
		});
		rbNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNombre.setEditable(true);
				editableF(txtNoUsuario,txtContraseña, cmbTipo);
			}
		});
		rbContraseña.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtContraseña.setEditable(true);
				editableF(txtNoUsuario, txtNombre, cmbTipo);
			}
		});
		rbTipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbTipo.setEnabled(true);
				editableF(txtNoUsuario, txtNombre, txtContraseña);
			}
		});
		rbTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editableT(txtNoUsuario, txtNombre, txtContraseña, cmbTipo);
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql;
				if(rbTodos.isSelected()&&cmbTipo.getSelectedItem().equals("Selecciona tipo de usuario...")){
					JOptionPane.showMessageDialog(rootPane,"Debes seleccionar el tipo de usuario");
				}else if(rbTodos.isSelected()&&cmbTipo.getSelectedItem().equals("Gerente")){
					sql = "SELECT * FROM usuarios WHERE noUsuario='"+txtNoUsuario.getText()+"' AND Nombre='"+txtNombre.getText()+
							"' AND Contraseña='"+txtContraseña.getText()+"' AND Tipo = 'Gerente';";
					if(txtNoUsuario.getText().equals("")||txtNombre.getText().equals("")||txtContraseña.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes llenar todos los campos");
					}else {
						mostrarTablaUsuarios(sql);
					}
				}else if(rbTodos.isSelected()&&cmbTipo.getSelectedItem().equals("Empleado")){
					sql = "SELECT * FROM usuarios WHERE noUsuario='"+txtNoUsuario.getText()+"' AND Nombre='"+txtNombre.getText()+
							"' AND Contraseña='"+txtContraseña.getText()+"' AND Tipo = 'Empleado';";
					if(txtNoUsuario.getText().equals("")||txtNombre.getText().equals("")||txtContraseña.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes llenar todos los campos");
					}else {
						mostrarTablaUsuarios(sql);
					}
				}else if(rbNoUsuario.isSelected()) {
					sql =  "SELECT * FROM usuarios WHERE noUsuario = '"+txtNoUsuario.getText()+"';";
					if(txtNoUsuario.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar el numero del usuario");
					}else {
						mostrarTablaUsuarios(sql);
					}
				}else if(rbNombre.isSelected()) {
					sql =  "SELECT * FROM usuarios WHERE Nombre = \""+txtNombre.getText()+"\"";
					if(txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar un nombre");
					}else {
						mostrarTablaUsuarios(sql);
					}
				}else if(rbContraseña.isSelected()) {
					sql =  "SELECT * FROM usuarios WHERE Contraseña = \""+txtContraseña.getText()+"\"";
					if(txtContraseña.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar una contraseña");
					}else {
						mostrarTablaUsuarios(sql);
					}
				}else if(rbTipo.isSelected()) {
					if(cmbTipo.getSelectedItem().equals("Selecciona tipo de usuario...")) {
						JOptionPane.showMessageDialog(rootPane,"Debes selecionar un tipo de usuario");
					}else if(cmbTipo.getSelectedItem().equals("Gerente")){
						sql =  "SELECT * FROM usuarios WHERE Tipo = 'Gerente';";
						mostrarTablaUsuarios(sql);
					}else if(cmbTipo.getSelectedItem().equals("Empleado")){
						sql =  "SELECT * FROM usuarios WHERE Tipo = 'Empleado';";
						mostrarTablaUsuarios(sql);
					}
				}
			}//action preformed
		});
		agregarComponente(btnBuscar, 250, 85, 120, 30, inf);
		
		
		
	}
	

//=============================================METODOS VENTAS======================================
	public void mostrarTablaVentas(String sql) {
		ResultSetTableModel modeloDatos =null;
		try {
			modeloDatos = new ResultSetTableModel(controlador,url,sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		tV.remove(spV);
		tablaV = new JTable(modeloDatos);
		tablaV.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	//obtenerRegistroTabla(tablaP);
		    }
		});
		spV = new JScrollPane(tablaV);
		spV.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spV.setBounds(0,0,370,190);
		tV.add(spV);
		tV.setVisible(true);
	}
	
	
	public void componentesVentas(JInternalFrame inf) {
		JLabel lblNoVenta = new JLabel("no_venta: ");
		agregarComponente(lblNoVenta, 20, 100, 60, 25, inf);
		JTextField txtNoVenta = new JTextField();
		txtNoVenta.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')||(code==KeyEvent.VK_BACK_SPACE)) {
					txtNoVenta.setEditable(true);
				}else{
					txtNoVenta.setEditable(false);
				}
			}
		});
		agregarComponente(txtNoVenta, 85, 100, 100, 25, inf);
		
		JLabel lblProductos = new JLabel("Productos (id): ");
		agregarComponente(lblProductos, 20, 130, 90, 25, inf);
		JTextArea txtProductos = new JTextArea();
		JScrollPane sp = new JScrollPane(txtProductos);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		agregarComponente(sp, 20, 155, 165, 70, inf);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		agregarComponente(lblFecha, 20, 230, 40, 25, inf);
		JComboBox<String> cmbFecha[]=new JComboBox[3];
		
		for (int i=0;i<cmbFecha.length;i+=1) {
			cmbFecha[i]=new JComboBox<String>();
			agregarComponente(cmbFecha[i], 20+(i*55), 255, 55, 25, inf);
		}
		for (int i = 1; i <= 31; i+=1) {	
			if (i<10) {
				cmbFecha[0].addItem("0"+i);
			}else {
				cmbFecha[0].addItem(""+i);
			}
		}
		cmbFecha[0].setBackground(moradoObscuro);
		cmbFecha[0].setForeground(grisClaro);
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
		cmbFecha[1].setBackground(moradoObscuro);
		cmbFecha[1].setForeground(grisClaro);
		for (int i = 2021; i <= 2030; i+=1) {cmbFecha[2].addItem(""+i);}
		cmbFecha[2].setBackground(moradoObscuro);
		cmbFecha[2].setForeground(grisClaro);
		
		JLabel lbltotal = new JLabel("Total: ");
		agregarComponente(lbltotal, 20, 290, 40, 25, inf);
		JTextField txtTotal = new JTextField();
		txtTotal.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				int code=ke.getKeyCode();
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')|| ke.getKeyChar()=='.'||(code==KeyEvent.VK_BACK_SPACE)) {
					txtTotal.setEditable(true);
				}else{
					txtTotal.setEditable(false);
				}
			}
		});
		agregarComponente(txtTotal, 65, 290, 120, 25, inf);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(moradoObscuro);
		btnBorrar.setForeground(grisClaro);
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restablecerComponentes(txtNoVenta, txtProductos, cmbFecha[0], cmbFecha[1], cmbFecha[2], txtTotal);
				mostrarTablaVentas(sqlVentas);
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
				tV.setVisible(false);
			}
		});
		agregarComponente(btnCancelar, 210, 290, 140, 25, inf);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(moradoObscuro);
		btnBuscar.setForeground(grisClaro);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql =  "SELECT * FROM ventas WHERE noVenta = \""+txtNoVenta.getText()+"\"";
				if(txtNoVenta.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Debes ingresar el numero de la venta");
				}else {
					mostrarTablaVentas(sql);
				}
			}
		});
		
		if(inf==aV) {
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(moradoObscuro);
			btnAgregar.setForeground(grisClaro);
			btnAgregar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String fecha = cmbFecha[0].getSelectedItem().toString()+"/"+cmbFecha[1].getSelectedItem().toString()+"/"+cmbFecha[2].getSelectedItem().toString();
					Venta v = new Venta(Integer.parseInt(txtNoVenta.getText()),txtProductos.getText(),fecha,Double.parseDouble(txtTotal.getText()));
					VentaDAO vDAO = new VentaDAO();
					if (txtNoVenta.getText().equals("")||txtProductos.getText().equals("")||txtTotal.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debes completar los campos");
					}else if(vDAO.insertarRragistro(v)) {
						JOptionPane.showMessageDialog(rootPane,"Se agregó correctamente a la base de datos");
					}else {
						JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar agregar a la base de datos");
					}
					mostrarTablaVentas(sqlVentas);
				}
				
			});
			agregarComponente(btnAgregar, 210, 100, 140, 25, inf);
		}else if(inf==bV) {
			txtProductos.setEditable(false);
			txtTotal.setEditable(false);
			cmbFecha[0].setEnabled(false);
			cmbFecha[1].setEnabled(false);
			cmbFecha[2].setEnabled(false);
			agregarComponente(btnBuscar, 210, 100, 140, 25, inf);
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(moradoObscuro);
			btnEliminar.setForeground(grisClaro);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String no = txtNoVenta.getText();
					VentaDAO vDAO = new VentaDAO();
					if(txtNoVenta.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane,"Debes ingresar el numero de venta");
					}else if(vDAO.eliminarRegistro(no)) {
						JOptionPane.showMessageDialog(rootPane,"Se eliminó correctamente de la base de datos");
					}else {
						JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar eliminar a la base de datos");
					}
					mostrarTablaVentas(sqlVentas);
				}
			});
			agregarComponente(btnEliminar, 210, 163, 140, 25, inf);
		}else if(inf==cV) {
			agregarComponente(btnBuscar, 210, 100, 140, 25, inf);
			JButton btnGuardar = new JButton("Guardar cambios");
			btnGuardar.setBackground(moradoObscuro);
			btnGuardar.setForeground(grisClaro);
			btnGuardar.addActionListener(new ActionListener() {
				String fecha;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					VentaDAO vDAO = new VentaDAO();
					if (txtNoVenta.getText().equals("")||txtProductos.getText().equals("")||txtTotal.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debes completar los campos");
					}else {
						fecha = cmbFecha[0].getSelectedItem().toString()+"/"+cmbFecha[1].getSelectedItem().toString()+"/"+cmbFecha[2].getSelectedItem().toString();
						Venta v = new Venta(Integer.parseInt(txtNoVenta.getText()),txtProductos.getText(),fecha,Double.parseDouble(txtTotal.getText()));
						if(vDAO.modificarRegistro(v)) {
							JOptionPane.showMessageDialog(rootPane, "Datos de venta modificados exitosamente");
						}else {
							JOptionPane.showMessageDialog(rootPane, "No se pudieron modificar los datos de la venta");
						}
						mostrarTablaVentas(sqlVentas);
					}
				}
			});
			agregarComponente(btnGuardar, 210, 163, 140, 25, inf);
		}else {
		}	
	}
	
	
//----------------------------------------------METODOS GENERALES------------------------------------------------	
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
	
	
	public JInternalFrame crearIfTabla(JInternalFrame inf, String tab, int w, int h, int x, int y) {
		inf = new JInternalFrame();
		inf.getContentPane().setLayout(null);
		inf.setLocation(x, y);
		Border border = new TitledBorder(new EtchedBorder());
		inf.setBorder(border);
		inf.setDefaultCloseOperation(HIDE_ON_CLOSE);
		inf.setSize(w, h);
		inf.setTitle(tab);
		return inf;
	}
	
	
	public void visibleT(JInternalFrame x, JInternalFrame y ) {
		x.setVisible(false);
		y.setVisible(false);
	}
	
	
	public void visibleIF(JInternalFrame principal, JInternalFrame a, JInternalFrame b, JInternalFrame c, JInternalFrame d,
			JInternalFrame e, JInternalFrame f, JInternalFrame g, JInternalFrame h, JInternalFrame i, JInternalFrame j,
			JInternalFrame k) {
		principal.setVisible(true);
		a.setVisible(false);
		b.setVisible(false);
		c.setVisible(false);
		d.setVisible(false);
		e.setVisible(false);
		f.setVisible(false);
		g.setVisible(false);
		h.setVisible(false);
		i.setVisible(false);
		j.setVisible(false);
		k.setVisible(false);
	}
	
	
	public JLabel titulo(String x, JLabel jl) {
		jl = new JLabel(x);
		jl.setBounds(20, 10, 300, 65);
		jl.setFont(new Font("Moonbright Demo", Font.PLAIN, 70));
		jl.setForeground(moradoObscuro);
		return jl;
	}
	
	
	public void agregarComponente (Component c, int x, int y,int w, int h, JInternalFrame inf) {
		c.setBounds(x, y, w, h);
		inf.add(c);
	}
	
	
	public void restablecerComponentes(Component...componentesGraficos) {
		for (Component c: componentesGraficos) {
			if (c instanceof JComboBox) {
				((JComboBox<?>)c).setSelectedIndex(0);
			}else if (c instanceof JTextField) {
				((JTextField)c).setText("");
			}else if (c instanceof JTextArea) {
				((JTextArea)c).setText("");
			}
		}
	}
	
	
	public void editableF(Component...components) {
		for (Component c: components) {
			if (c instanceof JTextField) {
				((JTextField)c).setEditable(false);
			}else if (c instanceof JTextArea) {
				((JTextArea)c).setEditable(false);
			}else if (c instanceof JPasswordField) {
				((JPasswordField)c).setEditable(false);
			}else if (c instanceof JComboBox) {
				((JComboBox<?>)c).setEnabled(false);
			}
		}
	}
	
	
	public void editableT(Component...components) {
		for (Component c: components) {
			if (c instanceof JTextField) {
				((JTextField)c).setEditable(true);
			}else if (c instanceof JTextArea) {
				((JTextArea)c).setEditable(true);
			}else if (c instanceof JPasswordField) {
				((JPasswordField)c).setEditable(true);
			}else if (c instanceof JComboBox) {
				((JComboBox<?>)c).setEnabled(true);
			}
		}
	}
	
	
	
	
	
}//Ventana

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

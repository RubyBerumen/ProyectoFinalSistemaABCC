package controlador;
import modelo.Producto;
import conexionBD.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;

//DAO - Data Access Object

public class ProductoDAO {

	ConexionBD conexion;
	
	public ProductoDAO() {
		conexion = new ConexionBD();
	}
	
	
	//Metodos para ALTAS, BJAS, CAMBIOS, CONSULTAS
	public boolean insertarRragistro(Producto p) {
		boolean resultado = false;
		
		String sql="INSERT INTO producto VALUES('"+p.getIdProducto()+"', '"+p.getNombre()+"', '"+p.getPrecio()+"', '"+p.getDescripcion()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		
		String sql =  "DELETE FROM producto WHERE idProducto = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(Producto p) {
		
		boolean resultado = false;
		
		
		String sql = "UPDATE producto SET idProducto='"+p.getIdProducto()+"', Nombre='"+p.getNombre()+"', Precio='"+p.getPrecio()+"', Descripcion='"+p.getDescripcion()+"');";
		
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<Producto> buscarProducto (String filtro){
		ArrayList<Producto> listaProductos = new ArrayList<>();
		
		String sql = "SELECT * FROM producto";
		
		ResultSet rs = conexion.ejecutarConsulta(sql);
		
		try {
			if(rs.next()) {
				do {
					listaProductos.add(new Producto(rs.getString(1),
							rs.getString(2),
							rs.getDouble(3),
							rs.getString(4)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaProductos;
	}
	
	
	
}

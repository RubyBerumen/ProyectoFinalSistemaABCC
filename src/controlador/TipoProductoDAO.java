package controlador;
import modelo.TipoProducto;
import conexionBD.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;

//DAO - Data Access Object

public class TipoProductoDAO {

	ConexionBD conexion;
	
	public TipoProductoDAO() {
		conexion = new ConexionBD();
	}
	
	
	//Metodos para ALTAS, BJAS, CAMBIOS, CONSULTAS
	public boolean insertarRragistro(TipoProducto tp) {
		boolean resultado = false;
		
		String sql="INSERT INTO TipoProducto VALUES('"+tp.getIdTipoProducto()+"', '"+tp.getDescripcion()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		String sql =  "DELETE FROM TipoProducto WHERE IdTipoProducto = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(TipoProducto tp) {
		
		boolean resultado = false;
		
		String sql = "UPDATE TipoProducto SET idTipoProducto='"+tp.getIdTipoProducto()+"', Descripción='"+tp.getDescripcion()+"';";
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<TipoProducto> buscarTipoProducto (String filtro){
		ArrayList<TipoProducto> listaTiposProductos = new ArrayList<>();
		
		String sql = "SELECT * FROM TipoProducto";
		
		ResultSet rs = conexion.ejecutarConsulta(sql);
		
		try {
			if(rs.next()) {
				do {
					listaTiposProductos.add(new TipoProducto(rs.getString(1),
							rs.getString(2)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaTiposProductos;
	}
	
	
	
}

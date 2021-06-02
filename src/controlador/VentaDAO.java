package controlador;
import modelo.Venta;
import conexionBD.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO - Data Access Object

public class VentaDAO {

	ConexionBD conexion;
	
	public VentaDAO() {
		conexion = new ConexionBD();
	}
	
	
	//Metodos para ALTAS, BJAS, CAMBIOS, CONSULTAS
	public boolean insertarRragistro(Venta v) {
		boolean resultado = false;
		
		String sql="INSERT INTO Ventas VALUES('"+v.getNoVenta()+"', '"+v.getProductos()+"', '"+v.getFecha()+"', '"+v.getTotal()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		String sql =  "DELETE FROM Ventas WHERE noVenta = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(Venta v) {
		
		boolean resultado = false;
		
		String sql = "UPDATE Ventas SET noVenta='"+v.getNoVenta()+"', Productos='"+v.getProductos()+"', Fecha='"+v.getFecha()+"', Total = "+v.getTotal()+"'"
				+"WHERE noVenta = '"+v.getNoVenta()+"';";
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<Venta> buscarVentas (String filtro){
		ArrayList<Venta> listaVentas = new ArrayList<>();
		
		ResultSet rs = conexion.ejecutarConsulta(filtro);
		
		try {
			if(rs.next()) {
				do {
					listaVentas.add(new Venta(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getDouble(4)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaVentas;
	}
	
	
	
}

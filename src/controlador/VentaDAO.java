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
		
		String sql="INSERT INTO Ventas VALUES('"+v.getIdVenta()+"', '"+v.getProductos()+"', '"+v.getFecha()+"', '"+v.getHora()+"', '"+v.getCantidad()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		String sql =  "DELETE FROM Ventas WHERE idVenta = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(Venta v) {
		
		boolean resultado = false;
		
		String sql = "UPDATE Ventas SET idVenta='"+v.getIdVenta()+"', Productos='"+v.getProductos()+"', Fecha='"+v.getFecha()+"',  Hora= "+v.getHora()+", Cantidad = "+v.getCantidad()+"';";
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<Venta> buscarVentas (String filtro){
		ArrayList<Venta> listaVentas = new ArrayList<>();
		
		String sql = "SELECT * FROM Ventas";
		
		ResultSet rs = conexion.ejecutarConsulta(sql);
		
		try {
			if(rs.next()) {
				do {
					listaVentas.add(new Venta(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getDouble(5)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaVentas;
	}
	
	
	
}

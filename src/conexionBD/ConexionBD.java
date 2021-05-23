package conexionBD;

import java.sql.*;

public class ConexionBD {
	
	private Connection conexion;
	private Statement stm;
	private ResultSet rs;
	
	public ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/proyecto_final";
			
			conexion = DriverManager.getConnection(URL,"root","aguacate");
			
			System.out.println("Conexion establecida");
			
		} catch (ClassNotFoundException e) {
			System.out.printf("Error de Driver");
		} catch (SQLException e) {
			System.out.printf("Error de conexion a MySQL o de la BD");
		}
	}
	
	
	public void cerrarConexion(){
		try {
			stm.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}
	
	//Metodo para operaciones DDL y DML (ABC)
	public boolean ejecutarInstruccion(String sql) {
		try {
			stm = conexion.createStatement();
			int resultado = stm.executeUpdate(sql);
			return resultado==1?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se pudo ejecutar la instuccion");
			return false;
		}
	}
		
		
	//Metodo para operaciones de consulta
	public ResultSet ejecutarConsulta(String sql) {
		try {
			stm = conexion.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("No se pudo ejecutar la instruccion");
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public static void main(String[] args) {
		
		new ConexionBD();
		
	}
	
}
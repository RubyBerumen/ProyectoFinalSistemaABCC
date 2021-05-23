package controlador;
import modelo.Proveedor;
import conexionBD.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;

//DAO - Data Access Object

public class ProveedorDAO {

	ConexionBD conexion;
	
	public ProveedorDAO() {
		conexion = new ConexionBD();
	}
	
	
	//Metodos para ALTAS, BJAS, CAMBIOS, CONSULTAS
	public boolean insertarRragistro(Proveedor p) {
		boolean resultado = false;
		
		String sql="INSERT INTO Proveedores VALUES('"+p.getIdProveedor()+"', '"+p.getNombre()+"', '"+p.getApellidos()+"', '"+p.getNoTelefono()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		String sql =  "DELETE FROM proveedores WHERE idProveedor = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(Proveedor p) {
		
		boolean resultado = false;
		
		String sql = "UPDATE Proveedores SET idProveedor='"+p.getIdProveedor()+"', Nombre='"+p.getNombre()+"', Apellidos='"+p.getApellidos()+"', noTelefono="+p.getNoTelefono()+"';";
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<Proveedor> buscarProveedor (String filtro){
		ArrayList<Proveedor> listaProveedores = new ArrayList<>();
		
		String sql = "SELECT * FROM Proveedores";
		
		ResultSet rs = conexion.ejecutarConsulta(sql);
		
		try {
			if(rs.next()) {
				do {
					listaProveedores.add(new Proveedor(rs.getByte(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaProveedores;
	}
	
	
	
}

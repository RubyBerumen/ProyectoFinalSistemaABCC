package controlador;
import modelo.Usuario;
import conexionBD.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO - Data Access Object

public class UsuarioDAO {

	ConexionBD conexion;
	
	public UsuarioDAO() {
		conexion = new ConexionBD();
	}
	
	
	//Metodos para ALTAS, BJAS, CAMBIOS, CONSULTAS
	public boolean insertarRragistro(Usuario u) {
		boolean resultado = false;
		
		String sql="INSERT INTO usuarios VALUES('"+u.getNoUsuario()+"', '"+u.getNombre()+"', '"+u.getContraseña()+"', '"+u.getTipo()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		
		String sql =  "DELETE FROM Usuarios WHERE noUsuario = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(Usuario u) {
		
		boolean resultado = false;

		String sql = "UPDATE usuarios SET Nombre='"+u.getNombre()+"', Contraseña='"+u.getContraseña()+"', Tipo='"+u.getTipo()+"'"
				+" WHERE noUsuario = '" + u.getNoUsuario()+"';";
		                
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<Usuario> buscarUsuario (String filtro){
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		
		ResultSet rs = conexion.ejecutarConsulta(filtro);
		
		try {
			if(rs.next()) {
				do {
					listaUsuarios.add(new Usuario(rs.getByte(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaUsuarios;
	}
	
	
	
}

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
		
		String sql="INSERT INTO usuarios VALUES('"+u.getIdUsuario()+"', '"+u.getNombre()+"', '"+u.getApellidos()+"', '"+u.getContraseña()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean eliminarRegistro(String id){
		
		boolean resultado = false;
		
		// DELETE FROM alumnos WHERE numControl = 'S19070070';
		
		String sql =  "DELETE FROM Usuarios WHERE idUsuario = \""+id+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	public boolean modificarRegistro(Usuario u) {
		
		boolean resultado = false;

		
		String sql = "UPDATE Usuarios SET idusuario='"+u.getIdUsuario()+"', Nombre='"+u.getNombre()+"', Apellidos='"+u.getApellidos()+"', Contraseña="+u.getContraseña()+"';";
		
		
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	//CONSULTAS
	public ArrayList<Usuario> buscarUsuario (String filtro){
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		
		String sql = "SELECT * FROM Usuarios";
		
		ResultSet rs = conexion.ejecutarConsulta(sql);
		
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

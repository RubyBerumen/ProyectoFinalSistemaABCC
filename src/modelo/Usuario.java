package modelo;

public class Usuario {
	private byte idUsuario;
	private String nombre;
	private String apellidos;
	private String contrase�a;
	
	public Usuario(byte idUsuario, String nombre, String apellidos, String contrase�a) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrase�a = contrase�a;
	}


	public byte getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(byte idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

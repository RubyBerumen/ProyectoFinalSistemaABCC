package modelo;

public class Usuario {
	private byte idUsuario;
	private String nombre;
	private String apellidos;
	private String contraseña;
	
	public Usuario(byte idUsuario, String nombre, String apellidos, String contraseña) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
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

	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

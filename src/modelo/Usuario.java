package modelo;

public class Usuario {
	private byte noUsuario;
	private String nombre;
	private String contrase�a;
	private String tipo;
	
	public Usuario(byte noUsuario, String nombre, String contrase�a, String tipo) {
		this.noUsuario = noUsuario;
		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.tipo = tipo;
	}
	


	public byte getNoUsuario() {
		return noUsuario;
	}
	public void setNoUsuario(byte noUsuario) {
		this.noUsuario = noUsuario;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

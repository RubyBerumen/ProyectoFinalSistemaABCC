package modelo;

public class Proveedor {
	private byte idProveedor;
	private String nombre;
	private String apellidos;
	private String noTelefono;

	public Proveedor(byte idProveedor, String nombre, String apellidos, String noTelefono) {
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.noTelefono = noTelefono;
	}


	public byte getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(byte idProveedor) {
		this.idProveedor = idProveedor;
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

	public String getNoTelefono() {
		return noTelefono;
	}
	public void setNoTelefono(String noTelefono) {
		this.noTelefono = noTelefono;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

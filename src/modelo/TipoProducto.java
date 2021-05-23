package modelo;

public class TipoProducto {
	private String idTipoProducto;
	private String descripcion;

	public TipoProducto(String idTipoProducto, String descripcion) {
		this.idTipoProducto = idTipoProducto;
		this.descripcion = descripcion;
	}


	public String getIdTipoProducto() {
		return idTipoProducto;
	}
	public void setIdTipoProducto(String idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

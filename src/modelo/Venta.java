package modelo;

public class Venta {
	private int idVenta;
	private String productos;
	private int fecha;
	private String hora;
	private double cantidad;
	
	public Venta(int idVenta, String productos, int fecha, String hora, double cantidad) {
		super();
		this.idVenta = idVenta;
		this.productos = productos;
		this.fecha = fecha;
		this.hora = hora;
		this.cantidad = cantidad;
	}
	
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}

	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

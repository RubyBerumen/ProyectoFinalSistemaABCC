package modelo;

public class Venta {
	private int noVenta;
	private String productos;
	private String fecha;
	private double total;

	public Venta(int noVenta, String productos, String fecha, double total) {
		this.noVenta = noVenta;
		this.productos = productos;
		this.fecha = fecha;
		this.total = total;
	}
	

	public int getNoVenta() {
		return noVenta;
	}
	public void setNoVenta(int noVenta) {
		this.noVenta = noVenta;
	}


	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}


	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

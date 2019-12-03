package dto;

public class DetalleVenta {
	
	private int idVenta;
	private String codigoProducto;
	private int cantidad;
	
	public DetalleVenta(int idVenta, String codigoProducto, int cantidad) {
		this.idVenta = idVenta;
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

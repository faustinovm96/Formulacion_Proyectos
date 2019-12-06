package dto;

public class DetalleVenta {
	
	private Long idVenta;
	private String codigoProducto;
	private int cantidad;
	
	public DetalleVenta(Long idVenta, String codigoProducto, int cantidad) {
		this.idVenta = idVenta;
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
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

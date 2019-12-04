package dto;

import java.io.File;

public class Producto {
	
	private String codigoProducto;
	private String descripcionProducto;
	private File fotoProducto;
	private double precioCompraProducto;
	private double precioVentaProducto;
	private int cantidadStockProducto;
	private int idProveedor;
	private int idCategoria;
	
	public Producto(String codigoProducto, String descripcionProducto, File fotoProducto, double precioCompraProducto,
			double precioVentaProducto, int cantidadStock, int idProveedor, int idCategoria) {
		this.codigoProducto = codigoProducto;
		this.descripcionProducto = descripcionProducto;
		this.fotoProducto = fotoProducto;
		this.precioCompraProducto = precioCompraProducto;
		this.precioVentaProducto = precioVentaProducto;
		this.cantidadStockProducto = cantidadStock;
		this.idProveedor = idProveedor;
		this.idCategoria = idCategoria;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public File getFotoProducto() {
		return fotoProducto;
	}

	public void setFotoProducto(File fotoProducto) {
		this.fotoProducto = fotoProducto;
	}

	public double getPrecioCompraProducto() {
		return precioCompraProducto;
	}

	public void setPrecioCompraProducto(double precioCompraProducto) {
		this.precioCompraProducto = precioCompraProducto;
	}

	public double getPrecioVentaProducto() {
		return precioVentaProducto;
	}

	public void setPrecioVentaProducto(double precioVentaProducto) {
		this.precioVentaProducto = precioVentaProducto;
	}

	public int getCantidadStockProducto() {
		return cantidadStockProducto;
	}

	public void setCantidadStockProducto(int cantidadStockProducto) {
		this.cantidadStockProducto = cantidadStockProducto;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}

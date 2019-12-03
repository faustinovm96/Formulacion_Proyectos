package dao;

import dto.DetalleVenta;
import dto.Venta;

public interface VentaDAO {
	
	public Long insertarVenta(Venta venta);
	
	public void insertarDetalleVenta(DetalleVenta detalleVenta);
	
	
}

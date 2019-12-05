package dto;

import java.sql.Date;

public class Venta {
	private int idVenta;
	private Date fecha;
	private double montoVenta;
	
	public Venta(int idVenta, Date fecha, double montoVenta) {
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.montoVenta = montoVenta;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getMontoVenta() {
		return montoVenta;
	}

	public void setMontoVenta(double montoVenta) {
		this.montoVenta = montoVenta;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

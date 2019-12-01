package dao;

import java.util.ArrayList;

import dto.Proveedor;

public interface ProveedorDAO {
	
	public void insertarProveedor(Proveedor proveedor);
	
	public ArrayList<Proveedor> obtenerProveedores();
	
}

package dao;

import java.io.InputStream;
import java.util.ArrayList;

import dto.Producto;

public interface ProductoDAO {
	
	public void insertarProducto(Producto producto);
	
	public void borrarProducto(Producto producto);
	
	public InputStream buscarFoto(Producto producto);
	
	public void actualizarProducto(Producto producto, boolean cambiarFoto);
	
	public ArrayList<Producto> obtenerProductos();
	
	public ArrayList<Producto> obtenerProductosPorCriterios(String criterio);
	
	public void actualizarInventario(Producto producto, int cantidad);
}

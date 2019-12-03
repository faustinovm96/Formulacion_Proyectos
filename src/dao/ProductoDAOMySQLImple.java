package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Producto;
import jdbc.Conexion;

public class ProductoDAOMySQLImple implements ProductoDAO {

	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	@Override
	public void insertarProducto(Producto producto) {
		 
		try {
	            conn = Conexion.getConnection();
	            File fileFoto = producto.getFotoProducto();
	            FileInputStream fis = new FileInputStream(fileFoto);
	            
	            String sql = "INSERT INTO productos (codigo_producto, descripcion_producto, foto, precio_compra, precio_venta, "
	                    + "cantidad_stock, id_proveedor, id_categoria) values (?,?,?,?,?,?,?,?)";
	            
	            pstm = conn.prepareStatement(sql);
	            
	            pstm.setString(1, producto.getCodigoProducto());
	            pstm.setString(2, producto.getDescripcionProducto());
	            
	            long tamanoFoto = producto.getFotoProducto().length();
	            pstm.setBinaryStream(3, fis , tamanoFoto); 
	            
	            pstm.setDouble(4, producto.getPrecioCompraProducto());
	            pstm.setDouble(5, producto.getPrecioVentaProducto());
	            pstm.setInt(6, producto.getCantidadStockProducto());
	            
	            pstm.setInt(7, producto.getIdCategoria());
	            pstm.setInt(8, producto.getIdProveedor());
	            
	            pstm.executeUpdate();
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        }
	        finally{
	            Conexion.close(conn);
	            Conexion.close(pstm);
	        }
	}

	@Override
	public void borrarProducto(Producto producto) {
		try {
            conn = Conexion.getConnection();
            
            
            String sql = "DELETE FROM productos WHERE codigo_producto= ?";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, producto.getCodigoProducto());
            
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        finally{
            Conexion.close(conn);
            Conexion.close(pstm);
        }
	}

	@Override
	public InputStream buscarFoto(Producto producto) {
		 InputStream streamFoto = null;
	        
	        try {
	            conn = Conexion.getConnection();
	            
	            String sql = "Select foto from productos where codigo_producto = ?";
	            
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, producto.getCodigoProducto());
	            rs = pstm.executeQuery();
	            
	            while(rs.next()){
	                streamFoto = rs.getBinaryStream("foto");
	            }
	            
	           
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } 
	        finally{
	            Conexion.close(conn);
	            Conexion.close(pstm);
	        }
	        
	        return streamFoto;
	}

	@Override
	public void actualizarProducto(Producto producto, boolean cambiarFoto) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Producto> obtenerProductosPorCriterios(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarInventario(Producto producto, int cantidad) {
		// TODO Auto-generated method stub

	}

}

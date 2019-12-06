package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.Producto;
import jdbc.Conexion;

public class ProductoDAOMySQLImple implements ProductoDAO {

	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Statement st = null;
	
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
	            
	            String sql = "SELECT foto FROM productos WHERE codigo_producto = ?";
	            
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
		 try {
	            conn = Conexion.getConnection();
	            
	            if(cambiarFoto==true){
	                File fileFoto = producto.getFotoProducto();
	                FileInputStream fis = new FileInputStream(fileFoto);
	                
	                String sql = "UPDATE productos SET descripcion_producto = ? , foto= ? , precio_compra = ? , precio_venta = ?, "
	                        + "cantidad_stock = ? , id_proveedor = ?, id_categoria = ? "
	                        + "WHERE codigo_producto = ?";
	                
	                pstm = conn.prepareStatement(sql);
	                
	                pstm.setString(1, producto.getDescripcionProducto());
	                long tamanoFoto = producto.getFotoProducto().length();
	                pstm.setBinaryStream(2, fis , tamanoFoto);
	                
	                
	                pstm.setDouble(3, producto.getPrecioCompraProducto());
	                pstm.setDouble(4, producto.getPrecioVentaProducto());
	                pstm.setInt(5, producto.getCantidadStockProducto());
	                pstm.setInt(6, producto.getIdCategoria());
	                pstm.setInt(7, producto.getIdProveedor());
	                
	                pstm.setString(8, producto.getCodigoProducto());
	                
	            }else{
	            	String sql = "UPDATE productos SET descripcion_producto = ?, precio_compra = ? , precio_venta = ?, "
	                        + "cantidad_stock = ? , id_proveedor = ?, id_categoria = ? "
	                        + "WHERE codigo_producto = ?";
	                
	                pstm = conn.prepareStatement(sql);
	                
	                pstm.setString(1, producto.getDescripcionProducto());
	                pstm.setDouble(2, producto.getPrecioCompraProducto());
	                pstm.setDouble(3, producto.getPrecioVentaProducto());
	                pstm.setDouble(4, producto.getCantidadStockProducto());
	                
	                pstm.setInt(5, producto.getIdCategoria());
	                pstm.setInt(6, producto.getIdProveedor());
	                
	                pstm.setString(7, producto.getCodigoProducto());
	                
	            }
	            pstm.executeUpdate(); 
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	        finally{
	            Conexion.close(conn);
	            Conexion.close(pstm);
	        }
	}

	@Override
	public ArrayList<Producto> obtenerProductos() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {
            conn = Conexion.getConnection();
            
            String sql = "SELECT * FROM productos ORDER BY descripcion_producto ";
            
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String codigoProducto = rs.getString("codigo_producto");
                String descripcionProducto = rs.getString("descripcion_producto");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                int cantidadStock = rs.getInt("cantidad_stock");                
                int idCategoria = rs.getInt("id_categoria");
                int idProveedor = rs.getInt("id_proveedor");
                
                Producto producto = new Producto(codigoProducto, descripcionProducto, null, precioCompra, precioVenta, cantidadStock, idProveedor, idCategoria);
                listaProductos.add(producto);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            Conexion.close(conn);
            Conexion.close(pstm);
            Conexion.close(rs);
        }
        return listaProductos;
	}

	@Override
	public ArrayList<Producto> obtenerProductosPorCriterios(String criterio) {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {
            conn = Conexion.getConnection();
            
            String sql = "SELECT * FROM productos WHERE codigo_producto LIKE '%" + criterio + "%' "
                    + "OR descripcion_producto LIKE '%" + criterio + "%' ORDER BY descripcion_producto";
            
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                String codigoProducto = rs.getString("codigo_producto");
                String descripcionProducto= rs.getString("descripcion_producto");
                
                int cantidadStock= rs.getInt("cantidad_stock");
                
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                int idCategoria = rs.getInt("id_categoria");
                int idProveedor = rs.getInt("id_proveedor");
                
                
                
                Producto producto = new Producto(codigoProducto, descripcionProducto, null, precioCompra, precioVenta, cantidadStock, idProveedor, idCategoria);
                
                listaProductos.add(producto);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            Conexion.close(conn);
            Conexion.close(pstm);
            Conexion.close(rs);
        }
        
        return listaProductos;
	}

	@Override
	public void actualizarInventario(Producto producto, int cantidad) {
		// TODO Auto-generated method stub

	}

}

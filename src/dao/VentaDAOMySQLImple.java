package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.DetalleVenta;
import dto.Venta;
import jdbc.Conexion;

public class VentaDAOMySQLImple implements VentaDAO {

	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	@Override
	public Long insertarVenta(Venta venta) {
		Long lastVal = 0l;
        
        try {
                   
            conn = Conexion.getConnection();
            
            String sql = "INSERT INTO ventas (fecha_venta, monto_venta) values (?,?)";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setDouble(1, venta.getMontoVenta());
            pstm.setDate(2, venta.getFecha());
            
            pstm.executeUpdate();
            
            pstm.close();
            
            pstm = this.conn.prepareStatement("SELECT last_insert_id()");
            
            rs = pstm.executeQuery();
            
            while(rs.next()){
                lastVal = rs.getLong("last_insert_id()");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
           Conexion.close(conn);
           Conexion.close(pstm);
           Conexion.close(rs);
        }
        return lastVal;
	}

	@Override
	public void insertarDetalleVenta(DetalleVenta detalleVenta) {
		try {
            conn = Conexion.getConnection();
            
            String sql = "INSERT INTO detalle_venta (id_venta, codigo_producto, cantidad) values (?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setLong(1, detalleVenta.getIdVenta());
            pstm.setString(2, detalleVenta.getCodigoProducto());
            pstm.setInt(3, detalleVenta.getCantidad());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            Conexion.close(conn);
            Conexion.close(pstm);
        }
	}

}

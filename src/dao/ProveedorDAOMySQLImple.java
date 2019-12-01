package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Proveedor;
import jdbc.Conexion;

public class ProveedorDAOMySQLImple implements ProveedorDAO {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	@Override
	public void insertarProveedor(Proveedor proveedor) {
		try {
			
            conn = Conexion.getConnection();
            
            String sql = "INSERT INTO proveedores (cedula_ruc, nombres, apellidos, direccion, telefono, email) VALUES (?,?,?,?,?,?);";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, proveedor.getCedularuc());
            pstm.setString(2, proveedor.getNombres());
            pstm.setString(3, proveedor.getApellidos());
            pstm.setString(4, proveedor.getDireccion());
            pstm.setString(5, proveedor.getTelefono());
            pstm.setString(6, proveedor.getEmail());          
            
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
	public ArrayList<Proveedor> obtenerProveedores() {
		 ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();
	        
		 try {
	            conn = Conexion.getConnection();
	            
	            String sql = "SELECT * FROM proveedores";
	            
	            pstm = conn.prepareStatement(sql);
	            rs = pstm.executeQuery();
	            
	            while(rs.next()){

	            	int id = rs.getInt("id_proveedor");
	                String cedula = rs.getString("cedula_ruc");
	                String nombres = rs.getString("nombres");
	                String apellidos = rs.getString("apellidos");
	                String direccion = rs.getString("direccion");
	                String telefono = rs.getString("telefono");
	                String email = rs.getString("email");
	                
	                Proveedor proveedor = new Proveedor(id, nombres, apellidos, direccion, telefono, email);
	                
	                listaProveedores.add(proveedor);
	            }
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        finally{
	            Conexion.close(conn);
	            Conexion.close(pstm);
	            Conexion.close(rs);
	        }
		 
	        return listaProveedores;
	}

}
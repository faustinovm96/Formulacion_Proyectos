package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Categoria;
import jdbc.Conexion;

public class CategoriaDAOMySQLImple implements CategoriaDAO {
	
	Connection conn = null;
	
	PreparedStatement pstm = null;
	
	Statement stmt = null;
	
	ResultSet rs = null;	
	
	@Override
	public void insertarCategoriaProducto(Categoria categoria) {
		
		try {
			
			conn = Conexion.getConnection();
			
			String sql = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?);";
		
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, categoria.getNombreCategoria());
			
			pstm.setString(2, categoria.getDescripcionCategoria());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			Conexion.close(pstm);
			
			Conexion.close(conn);
		
		}
	}

	@Override
	public ArrayList<Categoria> obtenerCategorias() {
		
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
		
		try {
			
			conn = Conexion.getConnection();
			
			String sql = "SELECT * FROM categorias;";
			
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id_categoria");
				
				String nombre = rs.getString("nombre");
				
				String descripcion = rs.getString("descripcion");
				
				Categoria categoria = new Categoria(id, nombre, descripcion);
				
				listaCategorias.add(categoria);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
		
			Conexion.close(conn);
			
			Conexion.close(rs);
		
		}
		
		return listaCategorias;
		
	}

}
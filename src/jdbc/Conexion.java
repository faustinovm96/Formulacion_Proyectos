package jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sistema_ventas2?useSSL=false";
	
	private static final String JDBC_USER = "root";
	
	private static final String JDBC_PASSWORD = "holamundo96";
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private static Driver driver = null;
	
	public static synchronized Connection getConnection() throws SQLException{
		if(driver == null) {
			try {
				Class<?> jdbcClassName = Class.forName(JDBC_DRIVER);
				driver = (Driver)jdbcClassName.newInstance();
				DriverManager.registerDriver(driver);
				//System.out.println("Conexion Exitosa...");
			} catch (SQLException e) {
				// TODO: handle exception
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	
	/**Cerrar Conexion...*/
	public static void close(Connection conn) {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Cerrar Objeto PreparedStatement...*/
	public static void close(PreparedStatement pstm) {
		try {
			if(pstm != null) pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Cerrar Objeto PreparedStatement...*/
	public static void close(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
package dto;

public class Proveedor {
	
	private int idProveedor;
	private String cedularuc;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String email;
	
	public Proveedor(int idProveedor, String nombres, String apellidos, String direccion, String telefono,
			String email) {
		this.idProveedor = idProveedor;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + "]";
	}
	
	public String getCedularuc() {
		return cedularuc;
	}

	public void setCedularuc(String cedularuc) {
		this.cedularuc = cedularuc;
	}


	
}
package dao;

import java.util.ArrayList;

import dto.Categoria;

public interface CategoriaDAO {
	
	public void insertarCategoriaProducto(Categoria categoria);
	
	public ArrayList<Categoria> obtenerCategorias();
	
}

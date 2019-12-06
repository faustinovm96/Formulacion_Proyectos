package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.mysql.fabric.xmlrpc.base.Array;

import dao.ProductoDAO;
import dao.ProductoDAOMySQLImple;
import dto.Producto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InventarioFrame extends JInternalFrame {
	private JTable table;
	private JButton btnAgregarProducto;
	private JButton btnCategoria;
	private JButton btnProveedores;

	
	private DefaultTableModel modeloTabla = new DefaultTableModel();
	private ProductoDAO productoDAO = new ProductoDAOMySQLImple();
	Producto productoSeleccionado = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventarioFrame frame = new InventarioFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public InventarioFrame() {
		initGUI();
		cargarColumnasTablas();
		cargarModeloTabla();
	}

	private void cargarColumnasTablas() {
		modeloTabla.addColumn("Codigo Producto");
		modeloTabla.addColumn("Descripcion Producto");
		modeloTabla.addColumn("Precio Compra");
		modeloTabla.addColumn("Precio Venta");
		modeloTabla.addColumn("Stock");
	}

	private void cargarModeloTabla() {
		ArrayList<Producto> listaProductos = productoDAO.obtenerProductos();
		int numeroProductos = listaProductos.size();
		modeloTabla.setNumRows(numeroProductos);
		
		int i;
		
		for(i = 0; i < numeroProductos; i++) {
			Producto producto = listaProductos.get(i);
			
			String codigoProducto = producto.getCodigoProducto();
			String descripcionProducto = producto.getDescripcionProducto();
			double precioCompra = producto.getPrecioCompraProducto();
			double precioVenta = producto.getPrecioVentaProducto();
			int cantidadStock = producto.getCantidadStockProducto();
			
			modeloTabla.setValueAt(codigoProducto, i, 0);
			modeloTabla.setValueAt(descripcionProducto, i, 1);
			modeloTabla.setValueAt(precioCompra, i, 2);
			modeloTabla.setValueAt(precioVenta, i, 3);
			modeloTabla.setValueAt(cantidadStock, i, 4);
			
		}
		

	}
	
	private void initGUI() {
		setBounds(100, 100, 884, 467);
		
		btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProductosActionPerformed();
			}

			
		});
		btnAgregarProducto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		btnCategoria = new JButton("Agregar Categor\u00EDa");
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCategoriaActionPerformed();
			}
		});
		btnCategoria.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		btnProveedores = new JButton("Agregar Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProveedorFrame proveedor = new ProveedorFrame(null, true);
				proveedor.setVisible(true);
				proveedor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				proveedor.setLocationRelativeTo(null);
				proveedor.setAlwaysOnTop(true);
			}
		});
		btnProveedores.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAgregarProducto)
							.addGap(49)
							.addComponent(btnCategoria, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnProveedores, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 829, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregarProducto, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCategoria, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProveedores, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
	}

	private void btnProductosActionPerformed() {
		ProductoFrame producto = new ProductoFrame(null, true, null, null, "Nuevo Producto", false);
		producto.setVisible(true);
		producto.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		producto.setLocationRelativeTo(null);
		producto.setAlwaysOnTop(true);
		cargarModeloTabla();
	}



	private void btnCategoriaActionPerformed() {
		CategoriaFrame categoria = new CategoriaFrame(null, true);
		categoria.setVisible(true);
		categoria.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		categoria.setLocationRelativeTo(null);
		categoria.setAlwaysOnTop(true);
	}
}

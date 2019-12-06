package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dao.ProductoDAO;
import dao.ProductoDAOMySQLImple;
import dao.VentaDAO;
import dao.VentaDAOMySQLImple;
import dto.DetalleVenta;
import dto.Producto;
import dto.Venta;

import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentasFrame extends JInternalFrame {
	private JTextField txtBuscarProducto;
	private JList<Producto> listaBusqueda;
	private JTable tablaVentas;
	private JButton btnQuitarProducto;
	private JPanel panel;
	private JLabel labelImagen;
	private JPanel panel_1;
	private JLabel lblVentaTotal;
	private JLabel lblPagoCon;
	private JLabel labelSumatoria;
	private JTextField txtPagarCon;
	private JButton btnRealizarVenta;

	DefaultTableModel modeloTablaProductos = new DefaultTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	DefaultListModel<Producto> modeloListaProductos = new DefaultListModel<Producto>();
	private ProductoDAO productoDAO = new ProductoDAOMySQLImple();
	private VentaDAO ventaDAO = new VentaDAOMySQLImple();
	
	
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentasFrame frame = new VentasFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentasFrame() {
		initGUI();
		
		cargarColumnasTabla();
		cargarListenerModeloTablas();

	}

	private void cargarListenerModeloTablas() {
		modeloTablaProductos.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int numFilas = modeloTablaProductos.getRowCount();
				double sumatoria = 0;
				
				for (int i = 0; i < numFilas; i++) {
					String importe = (String)modeloTablaProductos.getValueAt(i, 4);
					sumatoria += Double.parseDouble(importe);
				}
				
				labelSumatoria.setText(sumatoria + "");
				
			}
		});
	}

	private void cargarColumnasTabla() {
		modeloTablaProductos.addColumn("Código");
		modeloTablaProductos.addColumn("Descripción");
		modeloTablaProductos.addColumn("Precio Venta");
		modeloTablaProductos.addColumn("Cantidad");
		modeloTablaProductos.addColumn("Importe");
	}

	private void initGUI() {
		setTitle("Venta");
		setBounds(100, 100, 1033, 576);
		
		panel_1 = new JPanel();
		
		JLabel lblBuscarProducto = new JLabel("Buscar Producto:");
		lblBuscarProducto.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtBuscarProducto = new JTextField();
		txtBuscarProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				campoBusquedaKeyReleasedEvent();
			}
		});
		txtBuscarProducto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		btnQuitarProducto = new JButton("Quitar Producto");
		btnQuitarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActionPerformedEvent();
			}
		});
		btnQuitarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnCancelarVenta = new JButton("Cancelar Venta");
		btnCancelarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionPerformed();
			}

			
		});
		btnCancelarVenta.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(100, 149, 237)));
		
		
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, true));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
								.addComponent(lblBuscarProducto)
								.addComponent(txtBuscarProducto, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancelarVenta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnQuitarProducto, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
							.addGap(50))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 382, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblBuscarProducto)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtBuscarProducto, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnQuitarProducto, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCancelarVenta, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		
		lblVentaTotal = new JLabel("Venta Total:");
		lblVentaTotal.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		lblPagoCon = new JLabel("Pago con:");
		lblPagoCon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		labelSumatoria = new JLabel("0.00");
		labelSumatoria.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		txtPagarCon = new JTextField();
		txtPagarCon.setEditable(false);
		txtPagarCon.setColumns(10);
		
		btnRealizarVenta = new JButton("Realizar Venta");
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRealizarVentaActionPerformed();
			}
		});
		btnRealizarVenta.setFont(new Font("Tahoma", Font.BOLD, 42));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRealizarVenta, GroupLayout.PREFERRED_SIZE, 362, Short.MAX_VALUE)
						.addComponent(txtPagarCon, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPagoCon)
								.addComponent(lblVentaTotal))
							.addGap(18)
							.addComponent(labelSumatoria, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblVentaTotal)
							.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
							.addComponent(lblPagoCon))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(35)
							.addComponent(labelSumatoria, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(16)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtPagarCon, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnRealizarVenta, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		labelImagen = new JLabel("");
		panel.add(labelImagen);
		
		tablaVentas = new JTable();
		tablaVentas.setModel(modeloTablaProductos);
		tablaVentas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableKeyReleasedEvent(e);
			}

			
		});
		scrollPane_1.setViewportView(tablaVentas);
		
		listaBusqueda = new JList();
		listaBusqueda.setModel(modeloListaProductos);
		listaBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				listMousePressedEvent(e);
			}
		});
		scrollPane.setViewportView(listaBusqueda);
		getContentPane().setLayout(groupLayout);
	}

	private void tableKeyReleasedEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F2) {
			int filaSeleccionada = tablaVentas.getSelectedColumn();
			String cantidad = JOptionPane.showInputDialog("Modificar Cantidad");
			String precioVenta = (String)modeloTablaProductos.getValueAt(filaSeleccionada, 2);
			double importe = Double.parseDouble(cantidad) * Double.parseDouble(precioVenta);
			String importeString = importe + "";
			
			modeloTablaProductos.setValueAt(cantidad, filaSeleccionada, 3);
			modeloTablaProductos.setValueAt(importeString, filaSeleccionada, 4);
		}
	}
	
	private void anadirProductoAVenta(Producto producto) {
		String codigo = producto.getCodigoProducto();
		String descripcion = producto.getDescripcionProducto() + "";
		String precioVenta = producto.getPrecioVentaProducto() + "";
		String importe = producto.getPrecioVentaProducto() + "";
		
		String []datosProducto = {codigo, descripcion, precioVenta, "1", importe};
		modeloTablaProductos.addRow(datosProducto);
	}

	private void desplegarFoto(Producto producto) {
		ImageIcon imagenProducto = null;
		
		try {
			InputStream is = productoDAO.buscarFoto(producto);
			BufferedImage bi = ImageIO.read(is);
			imagenProducto = new ImageIcon(bi);
			
			Image imgProd = imagenProducto.getImage();
			int anchoEtiqueta = labelImagen.getWidth();
			int altoEtiqueta = labelImagen.getHeight();
			
			Image imgRedimensionada = imgProd.getScaledInstance(anchoEtiqueta, altoEtiqueta, Image.SCALE_DEFAULT);
			
			ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);
			
			labelImagen.setIcon(iconRedimensionado);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void campoBusquedaKeyReleasedEvent() {
		
		limpiarListaProductos();
		
		String cadenaBusqueda = txtBuscarProducto.getText();
		
		if(cadenaBusqueda.equalsIgnoreCase("")) {
			limpiarListaProductos();
		}else {
			ArrayList<Producto> listaProductos = productoDAO.obtenerProductosPorCriterios(cadenaBusqueda);
			
			for(Producto producto : listaProductos) {
				//System.out.println(producto);
				modeloListaProductos.addElement(producto);
			}
		}
	}
	
	private void limpiarListaProductos() {
		modeloListaProductos.clear();
	}

	private void listMousePressedEvent(MouseEvent e) {
		@SuppressWarnings("unchecked")
		JList<Producto> list = (JList<Producto>)e.getSource();
		
		if(e.getClickCount() == 2) {
			int index = list.locationToIndex(e.getPoint());
			
			Producto producto = (Producto)list.getSelectedValue();
			anadirProductoAVenta(producto);
			
			desplegarFoto(producto);
		}
	}

	private void btnActionPerformedEvent() {
		int filaSeleccionada = tablaVentas.getSelectedRow();
		
		int opcion = JOptionPane.showConfirmDialog(null, "Estas Seguro de borrar el producto de la Venta?");
		
		if(opcion == 0) {
			modeloTablaProductos.removeRow(filaSeleccionada);
		}
	}
	
	private void btnCancelarActionPerformed() {
		int cantidadFilas = modeloTablaProductos.getRowCount();
		
		int opcion = JOptionPane.showConfirmDialog(null, "Estas SEguro de cancelar toda la venta?");
		
		if(opcion == 0) {
			for(int i = cantidadFilas - 1; i >= 0; i--) {
				modeloTablaProductos.removeRow(i);
			}
		}
	}

	private void btnRealizarVentaActionPerformed() {
		ArrayList<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
		
		String sumatoriaStr = labelSumatoria.getText();
		double montoVenta = Double.parseDouble(sumatoriaStr);
		
		String pagoConStr = txtPagarCon.getText();
		double cambio = 0;
		
		if(!pagoConStr.isEmpty()) {
			cambio = Double.parseDouble(pagoConStr) - montoVenta;
		}
		
		Calendar calendarioLocal = Calendar.getInstance();
		Date fechaActual = calendarioLocal.getTime();
		long fechaMilisegundos = fechaActual.getTime();
		java.sql.Date fecha = new java.sql.Date(fechaMilisegundos);
		
		Venta venta = new Venta(fecha, montoVenta);
		
		Long idVenta = ventaDAO.insertarVenta(venta);
		
		int numRows = modeloTablaProductos.getRowCount();
		
		for(int i = 0; i < numRows; i++) {
			String codigoProducto = (String)modeloTablaProductos.getValueAt(i, 0);
			String cantidadStr =(String)modeloTablaProductos.getValueAt(i, 3);
			int cantidad = Integer.parseInt(cantidadStr);
			DetalleVenta detalle = new DetalleVenta(idVenta, codigoProducto, cantidad);
			
			ventaDAO.insertarDetalleVenta(detalle);
			detalles.add(detalle);
		}
		
		for(int i = numRows - 1; i >= 0; i--) {
			modeloTablaProductos.removeRow(i);
		}
		
		labelSumatoria.setText("0");
		
		if(!pagoConStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "<html><h1 style='font-size:200 px; color: blue'>"+cambio+"</h1></html>", "El cambio es: " , 1);
		}
		
		txtPagarCon.setText("");
		
	}
}

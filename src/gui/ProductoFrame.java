package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.CategoriaDAO;
import dao.CategoriaDAOMySQLImple;
import dao.ProductoDAO;
import dao.ProductoDAOMySQLImple;
import dao.ProveedorDAO;
import dao.ProveedorDAOMySQLImple;
import dto.Categoria;
import dto.Producto;
import dto.Proveedor;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductoFrame extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7064063783425057747L;
	private JTextField txtCodigoProducto;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JComboBox<Categoria> comboCategoria;
	private JComboBox<Proveedor> comboProveedor;
	private CategoriaDAO categoriaDAO;
	private ProveedorDAO proveedorDAO;
	private ProductoDAO productoDAO;
	
	JLabel labelImagen;
	
	DefaultComboBoxModel<Categoria> modeloCategorias;
	DefaultComboBoxModel<Proveedor> modeloProveedores;
	boolean estaActualizado;
	String informacion = "";
	File imgArticleFile;
	private JTextArea txtDescripcionProducto;
	private JTextField txtStockCantidad;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductoFrame dialog = new ProductoFrame(new JFrame(), true, null, null, "Titulo", false);
					
					
					dialog.addWindowListener(new WindowAdapter() {
						
						@Override
						public void windowClosing(WindowEvent arg0) {
							System.exit(0);
						}
						
					});
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ProductoFrame(Frame parent, boolean modal, Producto producto, ImageIcon icon, String titulo, boolean actualizando) {
		
		super(parent, modal);
		
		
		modeloCategorias = new DefaultComboBoxModel<Categoria>();
		modeloProveedores = new DefaultComboBoxModel<Proveedor>();
		categoriaDAO = new CategoriaDAOMySQLImple();
		proveedorDAO = new ProveedorDAOMySQLImple();
		productoDAO = new ProductoDAOMySQLImple();
		
		cargarModeloCat();
		cargarModeloProv();
		
		initGUI();
		
		this.estaActualizado = actualizando;
		this.setTitle(titulo);
		
		if(producto != null) {
			cargarProducto(producto, icon);
		}

		setLocationRelativeTo(null);
	}

	

	private void initGUI() {
		setTitle("Nuevo Producto");
		setBounds(100, 100, 751, 652);
		
		JLabel lblCodigoProducto = new JLabel("Codigo del Producto");
		lblCodigoProducto.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setColumns(10);
		
		JLabel lblDescripcinDelProducto = new JLabel("Descripci\u00F3n del Producto");
		lblDescripcinDelProducto.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		txtStockCantidad = new JTextField();
		txtStockCantidad.setColumns(10);
		
		JLabel lblPrecioCompra = new JLabel("Precio Compra");
		lblPrecioCompra.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		comboProveedor = new JComboBox();
		comboProveedor.setModel(modeloProveedores);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		comboCategoria = new JComboBox();
		comboCategoria.setModel(modeloCategorias);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnGuardarActionPerformed();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelarActionPerformed();
			}

		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addGap(148)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(txtStockCantidad, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblCodigoProducto)
												.addComponent(lblDescripcinDelProducto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
												.addComponent(txtPrecioCompra, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
												.addComponent(txtPrecioVenta, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
												.addComponent(txtCodigoProducto, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
											.addComponent(lblPrecioCompra, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
										.addGap(83))
									.addComponent(lblPrecioVenta, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblStock, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(comboCategoria, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCategora, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(comboProveedor, Alignment.LEADING, 0, 286, Short.MAX_VALUE)
												.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
													.addComponent(panel, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
													.addComponent(lblProveedor, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
											.addGap(124))))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCodigoProducto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCodigoProducto, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDescripcinDelProducto, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(lblPrecioCompra, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(30)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPrecioCompra, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProveedor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPrecioVenta, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(comboProveedor, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPrecioVenta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategora, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStock, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboCategoria, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtStockCantidad, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		
		
		labelImagen = new JLabel("");
		labelImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnMousePressedEvent();			
			}
			
		});
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(labelImagen);
		
		txtDescripcionProducto = new JTextArea();
		scrollPane.setViewportView(txtDescripcionProducto);
		getContentPane().setLayout(groupLayout);
	}
	
	private void cargarProducto(Producto producto, ImageIcon icono) {
		Image imgProd = icono.getImage();
		int anchoEtiqueta = labelImagen.getWidth();
		int altoEtiqueta = labelImagen.getHeight();
		
		Image imgRedimensionada = imgProd.getScaledInstance(anchoEtiqueta, altoEtiqueta, Image.SCALE_DEFAULT);
		ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);
		
		labelImagen.setIcon(iconRedimensionado);
		String clave = producto.getCodigoProducto();
		String descripcion = producto.getDescripcionProducto();
		double precioCompra = producto.getPrecioCompraProducto();
		double precioVenta = producto.getPrecioVentaProducto();
		int cantidadStock = producto.getCantidadStockProducto();
		
		txtCodigoProducto.setText(clave);
		txtDescripcionProducto.setText(descripcion);
		txtStockCantidad.setText(cantidadStock + "");
		txtPrecioCompra.setText(precioCompra + "");
		txtPrecioVenta.setText(precioVenta + "");
		
		txtCodigoProducto.setEnabled(false);	
	}
	
	private void btnCancelarActionPerformed() {
		this.dispose();
	}

	private void btnGuardarActionPerformed(){
		String codigoProducto = txtCodigoProducto.getText().toLowerCase();
		String descripcionProducto = txtDescripcionProducto.getText().toLowerCase();
		double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
		double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
		int stock = Integer.parseInt(txtStockCantidad.getText());
		
		Categoria categoria = (Categoria)comboCategoria.getSelectedItem();
		Proveedor proveedor = (Proveedor)comboProveedor.getSelectedItem();
		
		if(estaActualizado) {
			if(imgArticleFile == null) {
				Producto producto = new Producto(codigoProducto, descripcionProducto, null, precioCompra, precioVenta, stock, proveedor.getIdProveedor(), categoria.getIdCategoria());
				productoDAO.actualizarProducto(producto, false);
			}else {
				Producto producto = new Producto(codigoProducto, descripcionProducto, imgArticleFile, precioCompra, precioVenta, stock, proveedor.getIdProveedor(), categoria.getIdCategoria());
				productoDAO.actualizarProducto(producto, true);
			}
			
			JOptionPane.showMessageDialog(null, "Se ha guardado el producto");
			this.dispose();
			informacion = "1";
			
			if(imgArticleFile == null) {
				
			}else {
				Producto producto = new Producto(codigoProducto, descripcionProducto, imgArticleFile, precioCompra, precioVenta, stock, proveedor.getIdProveedor(), categoria.getIdCategoria());
				
				productoDAO.insertarProducto(producto);
				JOptionPane.showConfirmDialog(null, "SE ha guardado el producto");
				this.dispose();
			}
		}else {
			if(imgArticleFile == null) {
				JOptionPane.showMessageDialog(null, "No ha ingresadso una imagen");
			}else {
				Producto producto = new Producto(codigoProducto, descripcionProducto, imgArticleFile, precioCompra, precioVenta, stock, proveedor.getIdProveedor(), categoria.getIdCategoria());
				
				productoDAO.insertarProducto(producto);
				JOptionPane.showMessageDialog(null, "SE ha guardado el producto");
				this.dispose();
			}
		}
		
	}
	
	private void btnMousePressedEvent() {
		JFileChooser chooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de imagen jpg", "jpg");
		chooser.setFileFilter(filter);
		
		int returnVal = chooser.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			int anchoImagen = labelImagen.getWidth();
			int altoImagen = labelImagen.getHeight();
			
			imgArticleFile = chooser.getSelectedFile();
			
			ImageIcon icono = new ImageIcon(imgArticleFile.getAbsolutePath());
			Image imagen = icono.getImage();
			Image imagenRedimensionada = imagen.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_DEFAULT);
			
			ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
			
			labelImagen.setIcon(iconoRedimensionado);
		}
	}
	
	private void cargarModeloCat() {
		ArrayList<Categoria> listaCategorias;
		
		listaCategorias = categoriaDAO.obtenerCategorias();
		
		for(Categoria categoria : listaCategorias) {
			modeloCategorias.addElement(categoria);
			System.out.println(categoria.toString());
		}
	}
	
	private void cargarModeloProv() {
		ArrayList<Proveedor> listaProveedores;
		listaProveedores = proveedorDAO.obtenerProveedores();
		
		for(Proveedor proveedor : listaProveedores) {
			modeloProveedores.addElement(proveedor);
		}
	}
	
	public String getInformacion() {
		return informacion;
	}
	
	
}

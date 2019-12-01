package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CategoriaDAO;
import dao.CategoriaDAOMySQLImple;
import dto.Categoria;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CategoriaFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3853083936936034367L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JLabel lblDescripcin;
	
	private CategoriaDAO categoriaDAO;
	private JTextArea txtDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CategoriaFrame dialog = new CategoriaFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CategoriaFrame() {
		setTitle("Nueva Categor\u00EDa");
		initGUI();
		
		setLocationRelativeTo(null);
	}

	private void initGUI() {
		setBounds(100, 100, 510, 402);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				categoriaDAO = new CategoriaDAOMySQLImple();
				
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				
				Categoria categoria = new Categoria(0, nombre, descripcion);
				
				categoriaDAO.insertarCategoriaProducto(categoria);
				
				JOptionPane.showMessageDialog(null, "La Categoría se Ingreso Correctamente");
				
				dispose();
			}
		});
		
		btnGuardar.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
					.addGap(40))
		);
		
		txtDescripcion = new JTextArea();
		scrollPane.setViewportView(txtDescripcion);
		contentPanel.setLayout(gl_contentPanel);
	}
}

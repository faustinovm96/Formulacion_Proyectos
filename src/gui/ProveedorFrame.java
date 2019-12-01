package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ProveedorDAO;
import dao.ProveedorDAOMySQLImple;
import dto.Proveedor;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProveedorFrame extends JDialog {
	private JTextField txtCedulaRUC;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextArea txtDireccion;
	
	private ProveedorDAO proveedorDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProveedorFrame dialog = new ProveedorFrame();
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
	public ProveedorFrame() {
		initGUI();
		
		setLocationRelativeTo(null);
	}

	private void initGUI() {
		setTitle("Nuevo Proveedor");
		setBounds(100, 100, 513, 657);
		
		JLabel lblCdulaORuc = new JLabel("C\u00E9dula o RUC:");
		lblCdulaORuc.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtCedulaRUC = new JTextField();
		txtCedulaRUC.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cedulaRUC = txtCedulaRUC.getText();
				String nombres = txtNombres.getText();
				String apellidos = txtApellidos.getText();
				String direccion = txtDireccion.getText();
				String telefono = txtTelefono.getText();
				String email = txtEmail.getText();
				
				Proveedor proveedor = new Proveedor(0, cedulaRUC, nombres, apellidos, direccion, telefono, email);
				
				proveedorDAO = new ProveedorDAOMySQLImple();
				
				proveedorDAO.insertarProveedor(proveedor);
				
				JOptionPane.showMessageDialog(null, "El Proveedor se ingreso correctamente...");
				
				dispose();
				
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
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
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtApellidos, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
						.addComponent(txtCedulaRUC, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
						.addComponent(lblCdulaORuc)
						.addComponent(lblNombres, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNombres, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelfono, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCdulaORuc)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtCedulaRUC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNombres, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNombres, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTelfono, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		
		txtDireccion = new JTextArea();
		scrollPane.setViewportView(txtDireccion);
		getContentPane().setLayout(groupLayout);
	}
}

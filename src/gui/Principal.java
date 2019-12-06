package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		initGUI();
		
		setLocationRelativeTo(null);
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		
		contenedorPrincipal = new JDesktopPane();
		contenedorPrincipal.setBackground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(contenedorPrincipal, GroupLayout.PREFERRED_SIZE, 977, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(panelMenu, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panelMenu, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(contenedorPrincipal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnVentasActionPerformed();
			}

		});
		btnVentas.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panelMenu = new GroupLayout(panelMenu);
		gl_panelMenu.setHorizontalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVentas, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelMenu.setVerticalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVentas, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(556, Short.MAX_VALUE))
		);
		panelMenu.setLayout(gl_panelMenu);
		contentPane.setLayout(gl_contentPane);
	}
	
	VentasFrame ventas;
	private JDesktopPane contenedorPrincipal;
	
	private void btnVentasActionPerformed() {
		if(ventas == null) {
			ventas = new VentasFrame();
			contenedorPrincipal.add(ventas);
			contenedorPrincipal.getDesktopManager().maximizeFrame(ventas);
			ventas.setVisible(true);
		}else {
			contenedorPrincipal.getDesktopManager().maximizeFrame(ventas);
		}
	}
}

/**
 * 
 */
package vistas;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author elena
 *
 */
public class MenuLogin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton modoEmpleado;
	private JButton modoGerente;
	private JPasswordField contrasena; 
	
	
	public MenuLogin() {
		

		/* PANTALLA PRINCIPAL*/
		
		/*Crear componentes*/
		JLabel gTexto = new JLabel("Modo gerente");
		contrasena = new JPasswordField(10);
		modoEmpleado = new JButton("Modo empleado");
	
		modoGerente = new JButton("Login");
		
		
		/*Modificar componentes*/
		modoEmpleado.setBorder(BorderFactory.createEmptyBorder(60, 40, 60, 50));
		modoEmpleado.setFont(new Font("Georgia", Font.CENTER_BASELINE, 20));
		
		
		/*Asignar nombres para Action Listener*/
		modoEmpleado.setActionCommand("modoEmpleado");
		modoGerente.setActionCommand("modoGerente");

		/*Crear paneles internos*/
		JPanel empleado = new JPanel();
		JPanel gerente = new JPanel();
		JPanel gerente1 = new JPanel();
		JPanel gerente2 = new JPanel();
		JPanel gerente3 = new JPanel();
		JPanel panel = new JPanel();
			
		/*Modoficar paneles*/
		empleado.setLayout(new BoxLayout(empleado, BoxLayout.X_AXIS));
		empleado.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));

		gerente.setLayout(new BoxLayout(gerente, BoxLayout.Y_AXIS));
		gerente.setBorder(BorderFactory.createEmptyBorder(30, 20, 00, 20));
		gerente.setSize(40, 40);
		gTexto.setFont(new Font("Georgia", Font.CENTER_BASELINE, 20));
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/*Fondo de la aplicacion*/
		//this.setBackground(new Color(0xBCF5A9));
		
		
		/* Añadir componentes al panel Empleado */
		empleado.add(modoEmpleado);
		
		/* Añadir componentes al panel Gerente*/
		gerente1.add(gTexto);
		gerente2.add(contrasena);
		gerente3.add(modoGerente);
		gerente.add(gerente1);
		gerente.add(gerente2);
		gerente.add(gerente3);
		
		/* Añadir Empleado y Gernete al panel principal*/
		panel.add(empleado, BorderLayout.NORTH);
		panel.add(gerente, BorderLayout.SOUTH);
	
	
		/*Anadir el panel PRINCIPAL al cardLAyout PRINCIPAL*/
		this.add(panel, "PLogin");
	
	}
	
	
	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {
		modoEmpleado.addActionListener(c);
		modoGerente.addActionListener(c);
	}
	
	public JTextField getCajaContrasena() {
		return contrasena;
	}

}

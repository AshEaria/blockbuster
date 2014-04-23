/**
 * 
 */
package vistas;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author elena
 *
 */
public class MenuEmpleado extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JButton menuAlquilar;
	private JButton menuContrTarifa;
	private JButton menuReponer;
	private JButton menuDevolver;
	private JButton menuNewSocio;
	private JButton menuSalir;
	private JLabel LAlquilar;
	private JLabel LContrTarifa;
	private JLabel LReponer;
	private JLabel LDevolver;
	private JLabel LNewSocio;
	private JLabel LSalir;
	
	public MenuEmpleado() {
		

		/* PANTALLA PRINCIPAL*/
		
		/*Crear componentes*/

		/*Iconos*/
		ImageIcon imAlquilar = new ImageIcon("alquilar.png");
		ImageIcon imContrTarifa = new ImageIcon("contrTarifa.png"); 
		ImageIcon imDevolver = new ImageIcon("devolver.png"); 
		ImageIcon imReponer = new ImageIcon("reponer.png"); 
		ImageIcon imNewSocio = new ImageIcon("newSocio.png"); 
		ImageIcon imSalir = new ImageIcon("salir.png"); 
		
		/*Botones*/
		menuAlquilar = new JButton(imAlquilar);
		menuContrTarifa = new JButton(imContrTarifa);
		menuReponer = new JButton(imReponer);
		menuDevolver = new JButton(imDevolver);
		menuNewSocio = new JButton(imNewSocio);
		menuSalir = new JButton(imSalir);
		
		/*Texto*/
		LAlquilar = new JLabel("Alquilar");
		LContrTarifa = new JLabel("Contratar tarifa");
		LReponer = new JLabel("Reponer articulo");
		LDevolver = new JLabel("Devolver");
		LNewSocio = new JLabel("Nuevo Socio");
		LSalir = new JLabel("Salir");
		
		/*Modificar componentes*/
		
		menuAlquilar.setBorder(null);
		menuContrTarifa.setBorder(null);
		menuReponer.setBorder(null);
		menuDevolver.setBorder(null);
		menuNewSocio.setBorder(null);
		menuSalir.setBorder(null);
		
		//LAlquilar.setHorizontalAlignment(HEIGHT);
	

		
		
		/*Asignar nombres para Action Listener*/
		menuAlquilar.setActionCommand("MenuAlquilar");
		menuContrTarifa.setActionCommand("MenuContrTarifa");
		menuReponer.setActionCommand("MenuReponer");
		menuDevolver.setActionCommand("MenuDevolver");
		menuNewSocio.setActionCommand("MenuNewSocio");
		menuSalir.setActionCommand("MenuSalir");
	
		/*CREAR PANELES*/
		
		/*Crear paneles externos*/
		JPanel fila1 = new JPanel();
		JPanel fila2 = new JPanel();
		JPanel panel = new JPanel();
		
		/*Crear paneles internos*/
		JPanel PALquilar = new JPanel();
		JPanel PContrTarifa = new JPanel();
		JPanel PReponer = new JPanel();
		JPanel PDevolver = new JPanel();
		JPanel PNewSocio = new JPanel();
		JPanel PSalir = new JPanel();
		
			
		/*Modoficar paneles externos*/
		fila1.setLayout(new BoxLayout(fila1, BoxLayout.X_AXIS));
		fila2.setLayout(new BoxLayout(fila2, BoxLayout.X_AXIS));

		
		/*Modificas paneles internos*/
	 
		PALquilar.setLayout(new BoxLayout(PALquilar, BoxLayout.Y_AXIS));
		PContrTarifa.setLayout(new BoxLayout(PContrTarifa, BoxLayout.Y_AXIS));
		PReponer.setLayout(new BoxLayout(PReponer, BoxLayout.Y_AXIS));
		PDevolver.setLayout(new BoxLayout(PDevolver, BoxLayout.Y_AXIS));
		PNewSocio.setLayout(new BoxLayout(PNewSocio, BoxLayout.Y_AXIS));
		PSalir.setLayout(new BoxLayout(PSalir, BoxLayout.Y_AXIS));
		
		PALquilar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PContrTarifa.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PReponer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PDevolver.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PNewSocio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PSalir.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/*Fondo de la aplicacion*/
		//this.setBackground(new Color(0xBCF5A9));
		
		/*AÑADIR COMPONENTES*/
		/*Añadir componentes al panel de cada boton*/
		PALquilar.add(menuAlquilar);
		PALquilar.add(LAlquilar);
		
		PContrTarifa.add(menuContrTarifa);
		PContrTarifa.add(LContrTarifa);
		
		PReponer.add(menuReponer);
		PReponer.add(LReponer);
		
		PDevolver.add(menuDevolver);
		PDevolver.add(LDevolver);
		
		PNewSocio.add(menuNewSocio);
		PNewSocio.add(LNewSocio);
		
		PSalir.add(menuSalir);
		PSalir.add(LSalir);
		
		/* Añadir componentes al panel FILA1 */
		fila1.add(PALquilar);
		fila1.add(PContrTarifa);
		fila1.add(PReponer);
		
		/* Añadir componentes al panel FILA 2*/
		fila2.add(PDevolver);
		fila2.add(PNewSocio);
		fila2.add(PSalir);
		
		/* Añadir ambas filas al panel principal*/
		panel.add(fila1, BorderLayout.NORTH);
		panel.add(fila2, BorderLayout.SOUTH);

	
		/*Anadir el panel MenuEmpleado al cardLAyout PRINCIPAL*/
		this.add(panel, "MenuEmpleado");
	
	
		
	}
	
	
	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {
		menuAlquilar.addActionListener(c);
		menuContrTarifa.addActionListener(c);
		menuReponer.addActionListener(c);
		menuDevolver.addActionListener(c);
		menuNewSocio.addActionListener(c);
		menuSalir.addActionListener(c);
	}

}

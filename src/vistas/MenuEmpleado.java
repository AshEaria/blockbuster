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
	
	private JButton menuBuscar;
	private JButton menuAlquilar;
	private JButton menuContrTarifa;
	private JButton menuReponer;
	private JButton menuBuscarSocio; 
	private JButton menuDevolver;
	private JButton menuNewSocio;
	private JButton menuSalir;
	private JLabel LAlquilar;
	private JLabel LContrTarifa;
	private JLabel LReponer;
	private JLabel LBuscarSocio;
	private JLabel LDevolver;
	private JLabel LNewSocio;
	private JLabel LSalir;
	private JLabel LBuscar;
	
	public MenuEmpleado() {
		

		/* PANTALLA PRINCIPAL*/
		
		/*Crear componentes*/

		/*Iconos*/
		ImageIcon imAlquilar = new ImageIcon("alquilar.png");
		ImageIcon imContrTarifa = new ImageIcon("contrTarifa.png"); 
		ImageIcon imDevolver = new ImageIcon("devolver.png"); 
		ImageIcon imReponer = new ImageIcon("reponer.png"); 
		ImageIcon imBuscarSocio = new ImageIcon("buscarSocio.png");
		ImageIcon imNewSocio = new ImageIcon("newSocio.png"); 
		ImageIcon imSalir = new ImageIcon("salir.png"); 
		ImageIcon imBuscar = new ImageIcon("buscar.png");
		
		/*Botones*/
		menuAlquilar = new JButton(imAlquilar);
		menuContrTarifa = new JButton(imContrTarifa);
		menuReponer = new JButton(imReponer);
		menuBuscarSocio = new JButton(imBuscarSocio);
		menuDevolver = new JButton(imDevolver);
		menuNewSocio = new JButton(imNewSocio);
		menuSalir = new JButton(imSalir);
		menuBuscar = new JButton(imBuscar);
		
		/*Texto*/
		LAlquilar = new JLabel("Alquilar");
		LContrTarifa = new JLabel("Contratar tarifa");
		LReponer = new JLabel("Reponer articulo");
		LBuscarSocio = new JLabel("Buscar socio");
		LDevolver = new JLabel("Devolver");
		LNewSocio = new JLabel("Nuevo socio");
		LSalir = new JLabel("Salir");
		LBuscar = new JLabel("Buscar articulo");
		
		/*Modificar componentes*/
		
		menuAlquilar.setBorder(null);
		menuContrTarifa.setBorder(null);
		menuReponer.setBorder(null);
		menuBuscarSocio.setBorder(null);
		menuDevolver.setBorder(null);
		menuNewSocio.setBorder(null);
		menuSalir.setBorder(null);
		menuBuscar.setBorder(null);
		
		//LAlquilar.setHorizontalAlignment(HEIGHT);
	

		
		
		/*Asignar nombres para Action Listener*/
		menuAlquilar.setActionCommand("MenuAlquilar");
		menuContrTarifa.setActionCommand("MenuContrTarifa");
		menuReponer.setActionCommand("MenuReponer");
		menuBuscarSocio.setActionCommand("MenuBuscarSocio");
		menuDevolver.setActionCommand("MenuDevolver");
		menuNewSocio.setActionCommand("MenuNewSocio");
		menuSalir.setActionCommand("MenuSalir");
		menuBuscar.setActionCommand("MenuBuscar");
	
		/*CREAR PANELES*/
		
		/*Crear paneles externos*/
		JPanel fila1 = new JPanel();
		JPanel fila2 = new JPanel();
		JPanel panel = new JPanel();
		
		/*Crear paneles internos*/
		JPanel PALquilar = new JPanel();
		JPanel PContrTarifa = new JPanel();
		JPanel PReponer = new JPanel();
		JPanel PBuscarSocio = new JPanel();
		JPanel PDevolver = new JPanel();
		JPanel PNewSocio = new JPanel();
		JPanel PSalir = new JPanel();
		JPanel PBuscar = new JPanel();
		
			
		/*Modoficar paneles externos*/
		fila1.setLayout(new BoxLayout(fila1, BoxLayout.X_AXIS));
		fila2.setLayout(new BoxLayout(fila2, BoxLayout.X_AXIS));

		
		/*Modificas paneles internos*/
	 
		PALquilar.setLayout(new BoxLayout(PALquilar, BoxLayout.Y_AXIS));
		PContrTarifa.setLayout(new BoxLayout(PContrTarifa, BoxLayout.Y_AXIS));
		PReponer.setLayout(new BoxLayout(PReponer, BoxLayout.Y_AXIS));
		PBuscarSocio.setLayout(new BoxLayout(PBuscarSocio, BoxLayout.Y_AXIS));
		PDevolver.setLayout(new BoxLayout(PDevolver, BoxLayout.Y_AXIS));
		PNewSocio.setLayout(new BoxLayout(PNewSocio, BoxLayout.Y_AXIS));
		PSalir.setLayout(new BoxLayout(PSalir, BoxLayout.Y_AXIS));
		PBuscar.setLayout(new BoxLayout(PBuscar, BoxLayout.Y_AXIS));
		
		PALquilar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PContrTarifa.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PReponer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PBuscarSocio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PDevolver.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PNewSocio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PSalir.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		PBuscar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//fila2.setBorder(BorderFactory.createEmptyBorder(0, 105, 0, 0));
		
		/*Fondo de la aplicacion*/
		//this.setBackground(new Color(0xBCF5A9));
		
		/*AÑADIR COMPONENTES*/
		/*Añadir componentes al panel de cada boton*/
		PBuscar.add(menuBuscar);
		PBuscar.add(LBuscar);
		
		PALquilar.add(menuAlquilar);
		PALquilar.add(LAlquilar);
		
		PContrTarifa.add(menuContrTarifa);
		PContrTarifa.add(LContrTarifa);
		
		PReponer.add(menuReponer);
		PReponer.add(LReponer);
		
		PBuscarSocio.add(menuBuscarSocio);
		PBuscarSocio.add(LBuscarSocio);
		
		PDevolver.add(menuDevolver);
		PDevolver.add(LDevolver);
		
		PNewSocio.add(menuNewSocio);
		PNewSocio.add(LNewSocio);
		
		PSalir.add(menuSalir);
		PSalir.add(LSalir);
		
		/* Añadir componentes al panel FILA1 */
		fila1.add(PBuscar);
		fila1.add(PALquilar);
		fila1.add(PContrTarifa);
		fila1.add(PReponer);
		
		/* Añadir componentes al panel FILA 2*/
		fila2.add(PBuscarSocio);
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
		
		menuBuscar.addActionListener(c);
		menuAlquilar.addActionListener(c);
		menuContrTarifa.addActionListener(c);
		menuReponer.addActionListener(c);
		menuBuscarSocio.addActionListener(c);
		menuDevolver.addActionListener(c);
		menuNewSocio.addActionListener(c);
		menuSalir.addActionListener(c);
	}

}

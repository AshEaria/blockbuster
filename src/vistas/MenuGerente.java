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
public class MenuGerente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JButton menuBuscar;
	private JButton menuTopTen;
	private JButton menuAjustes;
	private JButton menuDarAlta;
	private JButton menuMorosos;
	private JButton menuSalir;
	private JLabel LBuscar;
	private JLabel LTopTen;
	private JLabel LAjustes;
	private JLabel LDarAlta;
	private JLabel LMorosos;
	private JLabel LSalir;
	
	public MenuGerente() {
		

		/* PANTALLA PRINCIPAL*/
		
		/*Crear componentes*/

		/*Iconos*/
		ImageIcon imBuscar = new ImageIcon("buscar.png");
		ImageIcon imTopTen = new ImageIcon("topten.png"); 
		ImageIcon imAjustes = new ImageIcon("ajustes.png"); 
		ImageIcon imDarAlta = new ImageIcon("alta.png"); 
		ImageIcon imMorosos = new ImageIcon("morosos.png"); 
		ImageIcon imSalir = new ImageIcon("salir.png"); 
		
		/*Botones*/
		menuBuscar = new JButton(imBuscar);
		menuTopTen = new JButton(imTopTen);
		menuAjustes = new JButton(imAjustes);
		menuDarAlta = new JButton(imDarAlta);
		menuMorosos = new JButton(imMorosos);
		menuSalir = new JButton(imSalir);
		
		/*Texto*/
		LBuscar = new JLabel("Buscar articulo");
		LTopTen = new JLabel("Top Ten");
		LAjustes = new JLabel("Ajustes");
		LDarAlta = new JLabel("Dar de alta");
		LMorosos = new JLabel("Morosos");
		LSalir = new JLabel("Salir");
		
		/*Modificar componentes*/
		
		menuBuscar.setBorder(null);
		menuTopTen.setBorder(null);
		menuAjustes.setBorder(null);
		menuDarAlta.setBorder(null);
		menuMorosos.setBorder(null);
		menuSalir.setBorder(null);
		
		//LAlquilar.setHorizontalAlignment(HEIGHT);
	

		
		
		/*Asignar nombres para Action Listener*/
		menuBuscar.setActionCommand("MenuBuscar");
		menuTopTen.setActionCommand("MenuTopTen");
		menuAjustes.setActionCommand("MenuAjustes");
		menuDarAlta.setActionCommand("MenuDarAlta");
		menuMorosos.setActionCommand("MenuMorosos");
		menuSalir.setActionCommand("MenuSalir");
	
		/*CREAR PANELES*/
		
		/*Crear paneles externos*/
		JPanel fila1 = new JPanel();
		JPanel fila2 = new JPanel();
		JPanel panel = new JPanel();
		
		/*Crear paneles internos*/
		JPanel PBuscar = new JPanel();
		JPanel PTopTen = new JPanel();
		JPanel PAjustes = new JPanel();
		JPanel PDarAlta = new JPanel();
		JPanel PMorosos = new JPanel();
		JPanel PSalir = new JPanel();
		
			
		/*Modoficar paneles externos*/
		fila1.setLayout(new BoxLayout(fila1, BoxLayout.X_AXIS));
		fila2.setLayout(new BoxLayout(fila2, BoxLayout.X_AXIS));

		
		/*Modificas pameles internos*/
	 
		PBuscar.setLayout(new BoxLayout(PBuscar, BoxLayout.Y_AXIS));
		PTopTen.setLayout(new BoxLayout(PTopTen, BoxLayout.Y_AXIS));
		PAjustes.setLayout(new BoxLayout(PAjustes, BoxLayout.Y_AXIS));
		PDarAlta.setLayout(new BoxLayout(PDarAlta, BoxLayout.Y_AXIS));
		PMorosos.setLayout(new BoxLayout(PMorosos, BoxLayout.Y_AXIS));
		PSalir.setLayout(new BoxLayout(PSalir, BoxLayout.Y_AXIS));
		
		PBuscar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PTopTen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PAjustes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PDarAlta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PMorosos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PSalir.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/*Fondo de la aplicacion*/
		//this.setBackground(new Color(0xBCF5A9));
		
		/*AÑADIR COMPONENTES*/
		/*Añadir componentes al panel de cada boton*/
		PBuscar.add(menuBuscar);
		PBuscar.add(LBuscar);
		
		PTopTen.add(menuTopTen);
		PTopTen.add(LTopTen);
		
		PAjustes.add(menuAjustes);
		PAjustes.add(LAjustes);
		
		PDarAlta.add(menuDarAlta);
		PDarAlta.add(LDarAlta);
		
		PMorosos.add(menuMorosos);
		PMorosos.add(LMorosos);
		
		PSalir.add(menuSalir);
		PSalir.add(LSalir);
		
		/* Añadir componentes al panel FILA1 */
		fila1.add(PBuscar);
		fila1.add(PTopTen);
		fila1.add(PAjustes);
		
		/* Añadir componentes al panel FILA 2*/
		fila2.add(PDarAlta);
		fila2.add(PMorosos);
		fila2.add(PSalir);
		
		/* Añadir ambas filas al panel principal*/
		panel.add(fila1, BorderLayout.NORTH);
		panel.add(fila2, BorderLayout.SOUTH);

	
		/*Anadir el panel MenuEmpleado al cardLAyout PRINCIPAL*/
		this.add(panel, "MenuGerente");
	
	
		
	}
	
	
	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {
		
		menuBuscar.addActionListener(c);
		menuTopTen.addActionListener(c);
		menuAjustes.addActionListener(c);
		menuDarAlta.addActionListener(c);
		menuMorosos.addActionListener(c);
		menuSalir.addActionListener(c);
	}

}

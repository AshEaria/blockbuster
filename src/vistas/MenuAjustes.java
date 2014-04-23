/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

import aplicacion.Ajustes;

/**
 * @author elena
 * 
 */
public class MenuAjustes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton atras;
	private JButton atrasAju;
	private JButton confirmar1;
	/* Botones menu */
	private JButton Agenerales;
	private JButton Aprestamo;
	private JButton Atarifa;

	/* Paneles */
	private JPanel panel;
	private JPanel botones;
	private JPanel Patras;
	
	/* Campos Agenerales */
	private JTextField nombre;
	private JPasswordField cActualT;
	private JPasswordField cNuevaT;

	public MenuAjustes() {

		/* Menu reponer */
		this.setLayout(new CardLayout());

		/* Crear componentes */

		/* Botones */
		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);
		
		ImageIcon imAtras2 = new ImageIcon("atras.png");
		atrasAju = new JButton(imAtras2);
		atrasAju.setBorder(null);
		

		/* Componentes MENU INICIAL */

		ImageIcon imP = new ImageIcon("Agenerales.png");
		Agenerales = new JButton(imP);
		Agenerales.setBorder(null);

		ImageIcon imM = new ImageIcon("Aprestamo.png");
		Aprestamo = new JButton(imM);
		Aprestamo.setBorder(null);

		ImageIcon imS = new ImageIcon("Atarifa.png");
		Atarifa = new JButton(imS);
		Atarifa.setBorder(null);

		/* Modificar componentes */

		/* Asignar nombres para Action Listener */
		atrasAju.setActionCommand("modoAjustes");
		atras.setActionCommand("modoGerente");
		Agenerales.setActionCommand("modoAgenerales");
		Aprestamo.setActionCommand("modoAprestamo");
		Atarifa.setActionCommand("modoAtarifa");

		/* Crear paneles */

		/* Inicializacion */
		panel = new JPanel();
		botones = new JPanel();
		Patras = new JPanel();

		/* Modificacion */
		// botones.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Patras.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 550));
		//botones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		botones.add(Agenerales);
		botones.add(Aprestamo);
		botones.add(Atarifa);
		Patras.add(atras);
		panel.add(Patras, BorderLayout.NORTH);
		panel.add(botones);

		/* Anadir el panel PRINCIPAL al cardLAyout PRINCIPAL */
		this.add(panel, "PAjustes");
		this.add(ajustesGenerales(), "modoAgenerales");
		
		/* Mostrar menu inicialmente */
		((CardLayout) this.getLayout()).show(this, "PAjustes");

	}

	/* Panel AJUSTES GENERALES */

	public JPanel ajustesGenerales() {
		/* Inicializacion de componentes */

		/* Texto */
		JLabel titulo1 = new JLabel("Cambio de nombre del videoblub");
		JLabel titulo2 = new JLabel("Cambio de contrasena");
		JLabel nActual = new JLabel(Ajustes.getInstance().getNombreVideoclub());
		JLabel nNuevo = new JLabel("Nombre nuevo:");
		JLabel cActual = new JLabel("Contrasena antigua:");
		JLabel cNueva = new JLabel("Contrasena nueva:");
		
		/* Botones */
		
		ImageIcon imConf = new ImageIcon("confirmar.png");
		confirmar1 = new JButton(imConf);
		confirmar1.setBorder(null);

		/* Campos de texto */
		nombre = new JTextField(10);
		cActualT = new JPasswordField(10);
		cNuevaT = new JPasswordField(10);

		/* Paneles */
		JPanel Pnombre = new JPanel();
		JPanel Pcontrasena = new JPanel();
		JPanel Pcentro = new JPanel();
		JPanel panel = new JPanel();
		
		/* Asignar nombres para Action Listener */
		confirmar1.setActionCommand("modoConfirmar1");

		/* Modificar componentes */
	
		titulo1.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		titulo1.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));

		titulo2.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 0));
		titulo2.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
		nActual.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		nActual.setFont(new Font("Georgia", 40, 40));
		nNuevo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		cActual.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
		cNueva.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	    Pcentro.setBorder(BorderFactory.createEmptyBorder(25, 25, 5, 55));
	    confirmar1.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
	
		Pnombre.setLayout(new BoxLayout(Pnombre, BoxLayout.Y_AXIS));
		Pcontrasena.setLayout(new BoxLayout(Pcontrasena, BoxLayout.Y_AXIS));
		Pcentro.setLayout(new BoxLayout(Pcentro, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		/* Añadir componentes a sus respectivos paneles */
		Pnombre.add(titulo1);
		Pnombre.add(nActual);
		Pnombre.add(nNuevo);
		Pnombre.add(nombre);
		Pnombre.add(confirmar1);

		Pcontrasena.add(titulo2);
		Pcontrasena.add(cActual);
		Pcontrasena.add(cActualT);
		Pcontrasena.add(cNueva);
		Pcontrasena.add(cNuevaT);
		Pcontrasena.add(confirmar1);

		Pcentro.add(Pnombre);
		Pcentro.add(Pcontrasena);

		panel.add(atrasAju);
		panel.add(Pcentro);
		
		return panel;

	}

	public JTextField getCajaNnuevo(){
		return nombre;	
	}
	
	public JTextField getCajacNueva(){
		return cNuevaT;	
	}
	
	public JTextField getCajacAntigua(){
		return cActualT;	
	}
	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {

		confirmar1.addActionListener(c);
		atrasAju.addActionListener(c);
		atras.addActionListener(c);
		Agenerales.addActionListener(c);
		Aprestamo.addActionListener(c);
		Atarifa.addActionListener(c);
	}

}

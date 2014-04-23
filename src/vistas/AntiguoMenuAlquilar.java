/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Jaime
 *
 */
@SuppressWarnings("serial")
public class AntiguoMenuAlquilar extends JPanel {
	private JButton botonAceptar1;
	private JButton botonCancelar1;
	private JButton botonPagar2;
	private JButton botonCancelar2;
	private JTextField elemento1; /*Alquiler 1*/
	private JTextField elemento2; /*Alquiler 2*/
	private JTextField elemento3; /*Alquiler 3*/
	
	public AntiguoMenuAlquilar() {
		
		/* Crear paneles */
		this.setLayout(new CardLayout());
		
		
		/*PANEL 1*/
		
		/* Crear componentes */
		JLabel datosSocio = new JLabel("Aqui los datos del socio");
		elemento1 = new JTextField(10);
		elemento2 = new JTextField(10);
		elemento3 = new JTextField(10);
		botonCancelar1 = new JButton("Cancelar");
		botonAceptar1 = new JButton("Aceptar");
		
		/*Asignar nombres para Action Listener*/
		botonCancelar1.setActionCommand("CancelarP1");
		botonAceptar1.setActionCommand("AceptarP1");

		JPanel botonera = new JPanel();
		JPanel campos = new JPanel();
		JPanel botoneraP1 = new JPanel();
		JPanel camposP1 = new JPanel();
		JPanel panel1 = new JPanel();

		botoneraP1.setLayout(new GridBagLayout());
		camposP1.setLayout(new GridBagLayout());
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
		campos.setLayout(new BoxLayout(campos, BoxLayout.PAGE_AXIS));
		
		camposP1.add(campos);
		botoneraP1.add(botonera);
		
		panel1.setLayout(new BorderLayout());
		
		/* Anadir componentes al panel 1 */
		campos.add(datosSocio);
		campos.add(elemento1);
		campos.add(elemento2);
		campos.add(elemento3);
		botonera.add(botonAceptar1);
		botonera.add(botonCancelar1);
		panel1.add(botoneraP1, BorderLayout.SOUTH);
		panel1.add(camposP1, BorderLayout.CENTER);
		
		/*PANEL 2*/
		
		/* Crear componentes */
		JLabel datosSocioAmpli = new JLabel("Aqui los datos del socio ampliados");
		elemento1 = new JTextField(10);
		elemento2 = new JTextField(10);
		elemento3 = new JTextField(10);
		botonCancelar2 = new JButton("Cancelar");
		botonPagar2 = new JButton("Pagar");
		
		/*Asignar nombres para Action Listener*/
		botonCancelar2.setActionCommand("CancelarP2");
		botonPagar2.setActionCommand("PagarP2");

		JPanel botoneraP2 = new JPanel();
		JPanel camposP2 = new JPanel();
		JPanel botonera2 = new JPanel();
		JPanel campos2 = new JPanel();
		JPanel panel2 = new JPanel();
			

		botoneraP1.setLayout(new GridBagLayout());
		camposP1.setLayout(new GridBagLayout());
		botonera2.setLayout(new BoxLayout(botonera2, BoxLayout.X_AXIS));
		campos2.setLayout(new BoxLayout(campos2, BoxLayout.Y_AXIS));

		camposP2.add(campos2);
		botoneraP2.add(botonera2);
		
		panel2.setLayout(new BorderLayout());
		
		/* Anadir componentes */
		campos2.add(datosSocioAmpli);
		campos2.add(elemento1);
		campos2.add(elemento2);
		campos2.add(elemento3);
		botonera2.add(botonPagar2);
		botonera2.add(botonCancelar2);
		panel2.add(botoneraP2, BorderLayout.SOUTH);
		panel2.add(camposP2, BorderLayout.CENTER);
		
		//panel2.add(botonera2);
		//panel2.add(campos2);
		
		/*Anadir los paneles al panel PRINCIPAL*/
		this.add(panel1, "P1");
		this.add(panel2, "P2");

		((CardLayout) this.getLayout()).show(this, "P1");
	  //  ((CardLayout) paneles.getLayout()).show(panel2, "P2");
	}
	
	public void setControlador(ActionListener c) {
		botonAceptar1.addActionListener(c);
		botonCancelar1.addActionListener(c);
		botonPagar2.addActionListener(c);
		botonCancelar2.addActionListener(c);
	}
	
	public void actionPerformedAntiguo(ActionEvent boton) {
		JPanel vista  = new JPanel(); //Solo para quitar los marcadores de error
		
		if (boton.getActionCommand().equals("CancelarP1")) {
			// get out of Alquilar
		} else if (boton.getActionCommand().equals("AceptarP1")) {
			// do things on the second screen to reflect what happened on the first screen
			((CardLayout) vista.getLayout()).show(vista, "P2");
			
		} else if (boton.getActionCommand().equals("CancelarP2")) {
			((CardLayout) vista.getLayout()).show(vista, "P1");
		} else if (boton.getActionCommand().equals("PagarP2")) {
			// change to payment
		}
		
	}
}

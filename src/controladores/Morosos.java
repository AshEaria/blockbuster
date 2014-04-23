/**
 * 
 */
package controladores;

import vistas.MenuMorosos;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import aplicacion.Aplicacion;
/**
 * Esta clase es el controlador de el arranque de la aplicacion.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class Morosos extends Opcion {

	private MenuMorosos vista;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alquiler.
	 */
	
	public Morosos() {
		vista = new MenuMorosos();

		/* Asociamos controlador a la vista */
		((MenuMorosos) vista).setControlador(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoGerente")) {
			/* Volvemo al panel MenuGerente */
			Aplicacion.vuelveMenuGerente();

		}

	}

	public JPanel getVista() {
		return (JPanel) vista;
	}

}
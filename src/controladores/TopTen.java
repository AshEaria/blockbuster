/**
 * 
 */
package controladores;

import vistas.MenuTopTen;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import aplicacion.Aplicacion;
/**
 * Esta clase es el controlador de el arranque de la aplicacion.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class TopTen extends Opcion {

	private MenuTopTen vista;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alquiler.
	 */
	
	public TopTen() {

		//catalogo.getInstance();
		/* Accedemos a los datos de la base de datos */
		vista = new MenuTopTen();

		/* Asociamos controlador a la vista */
		((MenuTopTen) vista).setControlador(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoPeliculas")) {
			vista.muestraPeliculas();

		} else if (boton.getActionCommand().equals("modoMusica")) {

			vista.muestraMusica();

		} else if (boton.getActionCommand().equals("modoSeries")) {

			vista.muestraSeries();
			
		} else if (boton.getActionCommand().equals("modoEmpleado")) {
			/* Volvemo al panel MenuEmpleado */
			Aplicacion.vuelveMenuGerente();

		}

	}

	public JPanel getVista() {
		return (JPanel) vista;
	}

}
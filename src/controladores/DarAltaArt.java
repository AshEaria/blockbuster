/**
 * 
 */
package controladores;

import vistas.MenuDarAlta;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;

/**
 * Esta clase es el controlador del menu de alta de articulos nuevos. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class DarAltaArt extends Opcion {

	private MenuDarAlta vista;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alta de articulo nuevo. 
	 */
	public DarAltaArt() {

		/* Accedemos a los datos de la base de datos */
		vista = new MenuDarAlta();

		/* Asociamos controlador a la vista */
		((MenuDarAlta) vista).setControlador(this);

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoPelicula")) {

			PeliculaAlta cPelicula = new PeliculaAlta();
			vista.add(cPelicula.getVista(), "MenuPeliculaAlta");
			((CardLayout) vista.getLayout()).show(vista, "MenuPeliculaAlta");

		} else if (boton.getActionCommand().equals("modoMusica")) {
			MusicaAlta cMusica = new MusicaAlta();
			vista.add(cMusica.getVista(), "MenuMusicaAlta");
			((CardLayout) vista.getLayout()).show(vista, "MenuMusicaAlta");

		} else if (boton.getActionCommand().equals("modoSerie")) {
			SerieAlta cSerie = new SerieAlta();
			vista.add(cSerie.getVista(), "MenuSerieAlta");
			((CardLayout) vista.getLayout()).show(vista, "MenuSerieAlta");

		} else if (boton.getActionCommand().equals("modoEmpleado")) {
			/* Volvemo al panel MenuGerente */
			Aplicacion.vuelveMenuGerente();

		}

	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return (JPanel) vista;
	}
	
}
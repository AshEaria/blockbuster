/**
 * 
 */
package controladores;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import vistas.MenuBuscarArt;
import vistas.MenuMorosos;

/**
 * Esta clase representa la opcion de busqueda de articulos por tipo y categoria. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class BuscarArt extends Opcion {
	private MenuBuscarArt vista; 
	
	public BuscarArt() {
		vista = new MenuBuscarArt(this);

		/* Asociamos controlador a la vista */
		((MenuBuscarArt) vista).setControlador(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoPeliculas")) {
			vista.muestraCats("Pelicula");

		} else if (boton.getActionCommand().equals("modoMusica")) {

			vista.muestraCats("Musica");

		} else if (boton.getActionCommand().equals("modoSeries")) {

			vista.muestraCats("Serie");
			
		} else if (boton.getActionCommand().equals("modoEmpleado")) {
			/* Volvemo al panel MenuGerente */
			Aplicacion.vuelveMenuGerente();

		} else { /* Boton de categoria */
			
			vista.muestraPanel(boton.getActionCommand());
			
		}
		
	}

	/* (non-Javadoc)
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}

}

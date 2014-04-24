/**
 * 
 */
package controladores;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import aplicacion.Aplicacion;
import vistas.MenuBuscarArt;

/**
 * Esta clase representa la opcion de busqueda de articulos por tipo y categoria. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class BuscarArt extends Opcion {
	private MenuBuscarArt vista; 
	
	/**
	 * Este constructor crea una vista del menu Buscar Articulo y se asocia a ella como controlador. 
	 */
	public BuscarArt() {
		vista = new MenuBuscarArt(this);

		/* Asociamos controlador a la vista */
		((MenuBuscarArt) vista).setControlador(this);
	}

	/**
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
			/* Volvemo al panel MenuEmpleado */
			Aplicacion.vuelveMenuEmpleado();

		} else { /* Boton de categoria */
			
			vista.muestraPanel(boton.getActionCommand());
			
		}
		
	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}

}

/**
 * 
 */
package controladores;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import catalogo.Catalogo;
import aplicacion.Aplicacion;
import vistas.MenuBuscarArtG;

/**
 * Esta clase representa la opcion de busqueda de articulos por tipo y
 * categoria.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class BuscarArtG extends Opcion {
	private MenuBuscarArtG vista;
	private Catalogo cat;

	/**
	 * Este constructor crea una vista del menu Buscar Articulo y se asocia a
	 * ella como controlador.
	 */
	public BuscarArtG() {
		vista = new MenuBuscarArtG(this);

		/* Asociamos controlador a la vista */
		((MenuBuscarArtG) vista).setControlador(this);
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

		} else if (boton.getActionCommand().equals("modoGerente")) {
			/* Volvemo al panel MenuGerente */
			Aplicacion.vuelveMenuGerente();

		} else if (boton.getActionCommand().equals("modoBorrar")) {
			if (vista.getCajaTabla().getSelectedRow() == -1)
				JOptionPane.showMessageDialog(vista,
						"Debe seleccionar algun articulo.");
			else {
				/* Borramos el articulo seleccionado de la base de datos */
				borrarArticulo(vista.getCajaTabla().getSelectedRow());
				JOptionPane.showMessageDialog(vista,
						"Articulo eliminado correctamente.");
				vista.muestraPanel(boton.getActionCommand());


			}
		} else { /* Boton de categoria */

			vista.muestraPanel(boton.getActionCommand());

		}

	}

	/**
	 * Metodo encargado de eliminar un articulo del catalogo.
	 * 
	 * @param id
	 *            Identificador del articulo a eliminar.
	 */
	public void borrarArticulo(int id) {

		cat = Catalogo.getInstance();
		
		cat.getArticulos().remove(cat.encuentra((int)vista.getCajaTabla().getValueAt(id, 3)));

	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}

}

/**
 * 
 */
package controladores;

import vistas.MenuReponer;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import aplicacion.Aplicacion;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;

/**
 * Esta clase es el controlador del menu para reponer articulos. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class Reponer extends Opcion {

	private MenuReponer vista;
	private ListaEjemplares ejemplares;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de reposicion de articulos y se asocia a el como controlador.
	 */
	public Reponer() {

		/* Accedemos a los datos de la base de datos */
		vista = new MenuReponer();
		ejemplares = ListaEjemplares.getInstance();

		/* Asociamos controlador a la vista */
		((MenuReponer) vista).setControlador(this);

	}
	
	// TODO Arreglar problema checkboxes Reponer. 

	/** 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
			Aplicacion.vuelveMenuEmpleado();

		} else if (boton.getActionCommand().equals("modoRepuestos")) {

			if (getIdMarcado().length != 0) {
				for (int i = 0; i < getIdMarcado().length; i++) {
					int id = getIdMarcado()[i];
					ejemplares.encuentra(id).setEstadoEjem(
							EstadoEjemplar.DISPONIBLE);
				}
				JOptionPane.showMessageDialog(null,
						"Articulo marcado como Disponible.");

			} else
				JOptionPane.showMessageDialog(null,
						"No has seleccionado ningun articulo.");

		}

	}
	
	/**
	 * @see controladores.Opcion#getVista()
	 */
	public JPanel getVista() {
		return (JPanel) vista;
	}

	/**
	 * Devuelve los id de las entradas de la lista que hay que reponer. 
	 * 
	 * @return array con los ids. 
	 */
	public int[] getIdMarcado() {
		int numIds=0;
		/*Creamos un array del tamano de las filas seleccionadas*/
		for (int i = 0; i < vista.getJTabla().getRowCount(); i++) 
			if (vista.getJTabla().getValueAt(i, 0).equals(true))	numIds++;
		
		int[] Ids = new int [numIds];	
		for (int i = 0; i < vista.getJTabla().getRowCount(); i++) {
			if (vista.getJTabla().getValueAt(i, 0).equals(true))
				Ids[i] = (int) vista.getJTabla().getValueAt(i, 1);
		}
		return Ids;
	}

}
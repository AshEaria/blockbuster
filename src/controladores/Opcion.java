/**
 * 
 */
package controladores;

import java.awt.event.ActionListener;

import javax.swing.JPanel;


/**
 * Esta clase modeliza los controladores de las opciones disponibles para el usuario. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public abstract class Opcion implements ActionListener {
	/**
	 * Metodo de acceso a la vista controlada por este controlador. 
	 * 
	 * @return JPanel relacionado con el controlador. 
	 */
	public abstract JPanel getVista();
}

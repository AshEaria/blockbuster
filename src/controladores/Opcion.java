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
	public abstract JPanel getVista();
}

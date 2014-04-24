/**
 * 
 */
package controladores;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

/**
 * Esta clase representa la opcion de dar de baja ejemplares o articulos. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class DarBajaArt extends Opcion {
	
	private JPanel vista;

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO DarBajaArt
		
	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}

}

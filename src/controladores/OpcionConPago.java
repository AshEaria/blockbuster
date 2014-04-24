/**
 * 
 */
package controladores;

import java.awt.event.ActionListener;

/**
 * Las clases que implementen esta interfaz podran ser utilizadas por la opcion Pagar. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public interface OpcionConPago extends ActionListener {
	/**
	 * Metodo al que lama la clase Pagar para indicar que el pago se ha hecho correctamente, dando paso al controlador correspondiente para que finalice su operacion. 
	 */
	public void pagoOK();
}

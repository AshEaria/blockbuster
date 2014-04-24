/**
 * 
 */
package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.visa.tpv.PasarelaDePago;
import org.visa.tpv.excepciones.ExcepcionClaveIncorrecta;
import org.visa.tpv.excepciones.ExcepcionCuantiaNegativa;
import org.visa.tpv.excepciones.ExcepcionIntentosExcedidos;
import org.visa.tpv.excepciones.ExcepcionPagoRechazado;
import org.visa.tpv.excepciones.ExcepcionTarjetaNoReconocida;
import org.visa.tpv.excepciones.ExcepcionTimeout;
import org.visa.tpv.excepciones.ExcepcionVISA;

import vistas.MenuPagar;

/**
 * Esta clase representa el controlador de la interfaz de pago. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class Pagar extends Opcion {
	
	private MenuPagar vista;
	private OpcionConPago parent;
	private double precio;
	
	/**
	 * Este constructor crea la interfaz de pago y se asocia a ella como controlador. 
	 * 
	 * @param precio Cantidad a pagar. 
	 * @param caller ControladorConPago que ha llamado a Pagar. 
	 */
	public Pagar(double precio, OpcionConPago caller) {
		parent = caller;
		vista = new MenuPagar(precio);
		vista.setControlador(this, caller);
		this.precio = precio; 
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("Efectivo")) {
			String[] opciones = { "Cancelar", "Pago completado" };
			int pagado = JOptionPane.showOptionDialog(vista, "Importe a cobrar: " + precio + "€\n\nHacer clic en 'Pago completado' cuando el pago se haya efectuado,\no en 'Cancelar' para cancelar la operacion.", "Pago en efectivo",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
			if (pagado == 1) {
				parent.pagoOK();
			} else return; 
		} else if (boton.getActionCommand().equals("Tarjeta")) {
			((CardLayout) vista.getLayout()).show(vista, "TARJETA");
		} else if (boton.getActionCommand().equals("AtrasT")) {
			vista.borrarCamposTarjeta();
			((CardLayout) vista.getLayout()).show(vista, "PRECIO");
		} else if (boton.getActionCommand().equals("AceptarT")) {
			String num = vista.getNumTarjeta(); 
			char[] con = vista.getContraTarjeta();
			if (num == null || con == null) {
				JOptionPane.showMessageDialog(vista, "Los datos de tarjeta no han sido introducidos correctamente.");
				return; 
			}
			if (pagoTarjeta(num, con, this.precio)) {
				parent.pagoOK();
			} else return; 
		}
	}
	
	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}
	
	/**
	 * Gestiona las excepciones que pueden saltar al usar la PasarelaDePago, y muestra mensajes de error de manera acorde. 
	 * 
	 * @param num String con el numero de la tarjeta. 
	 * @param con Cadena con la contrasena. 
	 * @param precio Cantidad a pagar. 
	 * @return true si se ha efectuado la operacion correctamente. Devuelve false y lanza un popup si no. 
	 */
	public boolean pagoTarjeta(String num, char[] con, double precio) {
		PasarelaDePago pasarela = PasarelaDePago.getPasarela();
		
		try {
			pasarela.pagar(num, String.valueOf(con), precio);
		} catch (ExcepcionClaveIncorrecta ci) {
			JOptionPane.showMessageDialog(vista, "La contrasena introducida es incorrecta.\nIntentelo de nuevo.");
			return false; 
		} catch (ExcepcionCuantiaNegativa cn) {
			JOptionPane.showMessageDialog(vista, "No se puede pagar un importe negativo.");
			return false; 
		} catch (ExcepcionIntentosExcedidos ie) {
			JOptionPane.showMessageDialog(vista, "Se ha excedido el numero de intentos de acceso a esta tarjeta.\nIntentelo con otra tarjeta.");
			return false; 
		} catch (ExcepcionPagoRechazado pr) {
			JOptionPane.showMessageDialog(vista, "La entidad bancaria ha rechazado el pago.\nEsto puede haber ocurrido por saldo o credito insuficiente.\nIntentelo con otra tarjeta.");
			return false; 
		} catch (ExcepcionTarjetaNoReconocida tnr) {
			JOptionPane.showMessageDialog(vista, "Este numero de tarjeta no es valido.\nIntentelo de nuevo.");
			return false; 
		} catch (ExcepcionTimeout to) {
			JOptionPane.showMessageDialog(vista, "Se ha producido un error en la conexion con la entidad bancaria.\nIntentelo de nuevo.");
			return false; 
		} catch (ExcepcionVISA vi) {
			JOptionPane.showMessageDialog(vista, "Se ha producido un error desconocido.\nIntentelo de nuevo.");
			return false; 
		}
		
		return true; 
	}

}

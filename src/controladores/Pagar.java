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
 * @author Jaime
 *
 */
public class Pagar implements ActionListener {
	
	private MenuPagar vista;
	private OpcionConPago parent;
	private double precio;
	
	public Pagar(double precio, OpcionConPago caller) {
		parent = caller;
		vista = new MenuPagar(precio);
		vista.setControlador(this, caller);
		this.precio = precio; 
	}

	/* (non-Javadoc)
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
	
	public JPanel getVista() {
		return vista;
	}
	
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

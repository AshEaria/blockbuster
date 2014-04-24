/**
 * 
 */
package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import socios.ListaContratos;
import socios.ListaSocios;
import socios.Socio;
import socios.Tarifa;
import vistas.MenuContratarTarifa;

/**
 * Esta clase representa la opcion de contratar una tarifa plana. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class ContratarTarifa extends Opcion implements OpcionConPago {

	private MenuContratarTarifa vista; 
	private ListaSocios socios;
	private ListaContratos listaContratos; 
	private Pagar cPagar;
	private Socio socio; 
	
	/** 
	 * Este constructor crea el panel del menu de contratacion de tarifa segun el numero de socio que pide por dialogo. 
	 */
	public ContratarTarifa() {
		
		/* Accedemos a los datos de la base de datos */
		socios = ListaSocios.getInstance();
		listaContratos = ListaContratos.getInstance();
		
		int numSocio;
		Socio socio = null;
		boolean numOK = false;
		
		while(numOK == false) {
			String input = JOptionPane.showInputDialog("Numero de socio: "); 
			if (input == null) {
				break;
			}
			try {
				numSocio = Integer.parseInt(input);
			}
			catch (NumberFormatException ex) {
				String str = ("El numero de socio no ha sido bien introducido.");
				JOptionPane.showMessageDialog(null, str);
				continue;
			}
			socio = socios.encuentra(numSocio);
			if (socio == null) {
				String str = ("El socio " + numSocio + " no existe. ");
				JOptionPane.showMessageDialog(null, str);
				continue;
			} else if (socio.getTarifa() != null) {
				String str = ("El socio " + numSocio + " ya tiene una tarifa contratada. ");
				JOptionPane.showMessageDialog(null, str);
				continue;
			} else numOK = true;
		}
		
		if(numOK == true) {
			this.socio = socio; 
			/* Creamos la vista */
			vista = new MenuContratarTarifa(socio);
			/* Asociamos controlador a la vista */
			((MenuContratarTarifa) vista).setControlador(this);
		} else vista = null;
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		
		if (boton.getActionCommand().equals("Cancelar")) {
			Aplicacion.vuelveMenuEmpleado();
		} else if (boton.getActionCommand().equals("Continuar")) {
			double precio = calcularPrecio();
			if (precio != 0) {
				cPagar = new Pagar(precio, this);
				vista.add(cPagar.getVista(), "PAGAR");
				((CardLayout) vista.getLayout()).show(vista, "PAGAR");
			}
		} else if (boton.getActionCommand().equals("AtrasPrecio")) {
			((CardLayout) vista.getLayout()).removeLayoutComponent(cPagar.getVista());
			cPagar = null;
			((CardLayout) vista.getLayout()).show(vista, "TARIFAS");
		}
	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista; 
	}
	
	public double calcularPrecio() {
		Tarifa dummyTar = vista.getConfigTarifa();
		return dummyTar.getPrecio() + ((dummyTar.isPlus()) ? Tarifa.getExtraPlus() : 0);
	}

	/**
	 * @see controladores.OpcionConPago#pagoOK()
	 */
	@Override
	public void pagoOK() {
		/* Crear la tarifa */
		Tarifa dummyTar = vista.getConfigTarifa(); 
		String strTarifa = dummyTar.toString(); 
		String[] tokens = strTarifa.split(",");
		listaContratos.crearTarifa(socio, tokens[0], dummyTar.isPlus());
		
		JOptionPane.showMessageDialog(vista, "Operacion realizada correctamente.");
		
		/* Volver al menu inicial */
		Aplicacion.vuelveMenuEmpleado();
	}

}

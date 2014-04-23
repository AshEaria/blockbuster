/**
 * 
 */
package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import socios.EstadoSocio;
import socios.ListaAlquileres;
import socios.ListaSocios;
import socios.Socio;
import socios.Alquiler;
import vistas.MenuDevolver;
import aplicacion.Ajustes;
import aplicacion.Aplicacion;

/**
 * Esta clase representa la opcion de devolucion de articulos. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class Devolver extends Opcion implements OpcionConPago {
	private MenuDevolver vista;
	private ListaSocios socios;
	private Ajustes ajustes;
	private ListaAlquileres alquileres; 
	private Pagar cPagar;
	private ArrayList<Alquiler> alqDevolviendo;
	private boolean[] ndDev; 
	
	public Devolver() {

		/* Accedemos a los datos de la base de datos */
		socios = ListaSocios.getInstance();
		ajustes = Ajustes.getInstance();
		alquileres = ListaAlquileres.getInstance();
		
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
			} else if (socio.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES) {
				String str = ("El socio " + numSocio + " no tiene articulos alquilados.");
				JOptionPane.showMessageDialog(null, str);
				continue;
			} else numOK = true;
		}
		
		if(numOK == true) {
			/* Creamos la vista */
			vista = new MenuDevolver(socio);
			/* Asociamos controlador a la vista */
			((MenuDevolver) vista).setControlador(this);
		} else vista = null;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {

		if (boton.getActionCommand().equals("Cancelar")) {
			Aplicacion.vuelveMenuEmpleado();
		} else if (boton.getActionCommand().equals("Continuar")) {
			alqDevolviendo = vista.getDevolviendo();
			ndDev = vista.getNoDispDev();
			if (alqDevolviendo.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "No se ha seleccionado ningun articulo.");
				return;
			}
			double precio = calcularPrecio();
			if (precio != 0) {
				JOptionPane.showMessageDialog(vista, "Alguno de los articulos esta retrasado. Se debe pagar una penalizacion\npor retraso de " + precio + "€ antes de devolver el articulo. ");
				cPagar = new Pagar(precio, this);
				vista.add(cPagar.getVista(), "PAGAR");
				((CardLayout) vista.getLayout()).show(vista, "PAGAR");
			} else pagoOK();
		} else if (boton.getActionCommand().equals("AtrasPrecio")) {
			((CardLayout) vista.getLayout()).removeLayoutComponent(cPagar.getVista());
			cPagar = null;
			((CardLayout) vista.getLayout()).show(vista, "ARTICULOS");
		}
	}

	/* (non-Javadoc)
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}
	
	public double calcularPrecio() {
		if (alqDevolviendo == null) return 0;
		if (alqDevolviendo.isEmpty()) return 0;
		
		double precio = 0;
		
		for (Alquiler a : alqDevolviendo) {
			if (a.diasRetraso() != 0) {
				if (a.diasRetraso() > ajustes.getDiasNivelRetraso()) {
					precio += ajustes.getPenalRetraso1() * ajustes.getDiasNivelRetraso();
					precio += ajustes.getPenalRetraso2() * (a.diasRetraso() - ajustes.getDiasNivelRetraso());
				} else precio += ajustes.getPenalRetraso1() * a.diasRetraso();
			}
		}
		
		return precio;
	}

	/* (non-Javadoc)
	 * @see controladores.OpcionConPago#pagoOK()
	 */
	@Override
	public void pagoOK() {
		/* Devolver los ejemplares, marcandolos como disponible o no disponible segun corresponda */
		int i = 0; 
		for (Alquiler a : alqDevolviendo) {
			alquileres.terminarAlquiler(a, ndDev[i]); 
			i++;
		}
		JOptionPane.showMessageDialog(vista, "Operacion realizada correctamente.");
		
		/* Volver al menu inicial */
		Aplicacion.vuelveMenuEmpleado();
	}

}

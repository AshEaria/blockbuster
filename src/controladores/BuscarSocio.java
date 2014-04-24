/**
 * 
 */
package controladores;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.Aplicacion;
import socios.EstadoSocio;
import socios.ListaSocios;
import socios.Socio;
import vistas.MenuAlquilar;
import vistas.MenuBuscarSocio;

/**
 * @author e280219
 *
 */
public class BuscarSocio extends Opcion {

	private Socio socio; 
	private ListaSocios socios;
	private MenuBuscarSocio vista; 
	
	public BuscarSocio() {
		int numSocio;
		Socio socio = null;
		boolean numOK = false;
		
		socios = ListaSocios.getInstance();
		
		while(numOK == false) {
			String input = JOptionPane.showInputDialog("DNI del socio: "); 
			if (input == null) {
				break;
			}
			socio = socios.encuentra(input);
			if (socio == null) {
				String str = ("El socio con DNI " + input + " no existe. ");
				JOptionPane.showMessageDialog(null, str);
				continue;
			}  else numOK = true;
		}
		
		if(numOK == true) {
			this.socio = socio;
			/* Creamos la vista */
			vista = new MenuBuscarSocio(socio);
			/* Asociamos controlador a la vista */
			((MenuBuscarSocio) vista).setControlador(this);
		} else vista = null;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoEmpleado")) {
			/* Volvemo al panel MenuEmpleado */
			Aplicacion.vuelveMenuEmpleado();

		} else if (boton.getActionCommand().equals("modoSocioCreado")) {
			/* Modificamos el socio y nos vamos */
				if (!modificaSocio()) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos marcados con (*) esta vacio. ", null, JOptionPane.ERROR_MESSAGE);
					return; 
				}
				JOptionPane.showMessageDialog(vista, "Socio modificado correctamente.");
				Aplicacion.vuelveMenuEmpleado();
		}
	}

	/** 
	 * Modifica el socio segun el contenido de los campos de la vista. 
	 * 
	 * @return true si la modificacion ha sido exitosa, false si las cajas de Nombre o DNI estaban vacias. 
	 */
	private boolean modificaSocio() {
		if ((vista.getCajaNombre().getText().isEmpty()) || (vista.getCajaDNI().getText().isEmpty())) return false; 
		
		socio.setNombre(vista.getCajaNombre().getText());
		socio.setDni(vista.getCajaDNI().getText());
		socio.setDireccion(vista.getCajaDireccion().getText());
		socio.setEmail(vista.getCajaEmail().getText());
		socio.setTelefono(vista.getCajaTelefono().getText());
		return true;
	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return vista;
	}

}

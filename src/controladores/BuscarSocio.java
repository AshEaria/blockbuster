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
				}
				JOptionPane.showMessageDialog(vista, "Socio modificado correctamente.");
				Aplicacion.vuelveMenuEmpleado();
		}
	}

	private boolean modificaSocio() {
		if ((vista.getCajaNombre().getText().isEmpty()) && (vista.getCajaDNI().getText().isEmpty())) return false; 
		
		socio.setNombre(vista.getCajaNombre().getText());
		// TODO terminar esto
		return true;
	}

	/* (non-Javadoc)
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}

}

/**
 * 
 */
package controladores;

import aplicacion.Aplicacion;
import socios.ListaSocios;
import vistas.MenuNuevoSocio;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Esta clase es el controlador del menu de creacion de nuevo socio. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class NuevoSocio extends Opcion {

	private MenuNuevoSocio vista;
	private ListaSocios socios;
	private JTextField nombre;
	private JTextField dni;
	private JTextField telefono;
	private JTextField email;
	private JTextField direccion;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de creacion de nuevo socio y se asocia a el como controlador. 
	 */
	public NuevoSocio() {

		/* Accedemos a los datos de la base de datos */
		vista = new MenuNuevoSocio();
		socios = ListaSocios.getInstance();

		/* Asociamos controlador a la vista */
		((MenuNuevoSocio) vista).setControlador(this);

		// MenuNuevoSocio mNewSocio = new MenuNuevoSocio();
		// vista.add(mNewSocio, "PNuevoSocio");
		this.nombre = vista.getCajaNombre();
		this.dni = vista.getCajaDNI();
		this.telefono = vista.getCajaTelefono();
		this.email = vista.getCajaEmail();
		this.direccion = vista.getCajaDireccion();

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoEmpleado")) {
			/* Volvemo al panel MenuEmpleado */
			Aplicacion.vuelveMenuEmpleado();

		} else if (boton.getActionCommand().equals("modoSocioCreado")) {
			/* Cambiamos al panel Socio creado */
			if ((!nombre.getText().isEmpty()) && (!dni.getText().isEmpty())) {
				creaNuevoSocio();
				limpiaCampos();
			} else
				JOptionPane.showMessageDialog(null,
						"Rellene todos los campos marcados con (*)");
		}

	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	@Override
	public JPanel getVista() {
		return (JPanel) vista;
	}

	/**
	 * Efectua la operacion de creacion del socio, anadiendolo a la base de datos. 
	 */
	public void creaNuevoSocio() {

		JOptionPane.showMessageDialog(
				null,
				"El numero del nuevo socio es "
						+ socios.crearSocio(dni.getText(), nombre.getText(),
								telefono.getText(), email.getText(),
								direccion.getText()));
	}

	/** 
	 * Limpia los campos del formulario. 
	 */
	public void limpiaCampos() {
		nombre.setText(null);
		dni.setText(null);
		direccion.setText(null);
		email.setText(null);
		telefono.setText(null);
	}

}
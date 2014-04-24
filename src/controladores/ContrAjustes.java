/**
 * 
 */
package controladores;

import socios.EstadoSocio;
import socios.Tarifa;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaPremium;
import socios.TarifaSM;
import socios.TarifaSerie;
import vistas.MenuAjustes;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacion.Ajustes;
import aplicacion.Aplicacion;

/**
 * Esta clase es el controlador de el arranque de la aplicacion.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class ContrAjustes extends Opcion {

	private MenuAjustes vista;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alquiler.
	 */

	public ContrAjustes() {
		/* Accedemos a los datos de la base de datos */
		vista = new MenuAjustes();

		/* Asociamos controlador a la vista */
		((MenuAjustes) vista).setControlador(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoGerente")) {
			/* Volvemo al panel MenuEmpleado */
			Aplicacion.vuelveMenuGerente();

		} else if (boton.getActionCommand().equals("modoAgenerales")) {
			((CardLayout) vista.getLayout()).show(vista, "modoAgenerales");

		} else if (boton.getActionCommand().equals("modoAtarifa")) {
			((CardLayout) vista.getLayout()).show(vista, "modoAtarifa");

		} else if (boton.getActionCommand().equals("modoAjustes")) {
			((CardLayout) vista.getLayout()).show(vista, "PAjustes");

		} else if (boton.getActionCommand().equals("modoConfirmar1")) {

			if (vista.getCajaNnuevo().getText().isEmpty()
					&& (vista.getCajacNueva().getText().isEmpty()))
				JOptionPane.showMessageDialog(null, "Campos vacios.", null,
						JOptionPane.INFORMATION_MESSAGE);
			else {
				cambioDeNombre();
				if (!vista.getCajaNnuevo().getText().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Nombre modificado correctamente.", null,
							JOptionPane.INFORMATION_MESSAGE);

				if (cambioDeContrasena() == false) {
					limpiaCampos();
					JOptionPane.showMessageDialog(null,
							"La contrasena no coincide", null,
							JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null,
							"Contrasena modificada correctamente.", null,
							JOptionPane.INFORMATION_MESSAGE);

				ContrAjustes cAjustes = new ContrAjustes();
				vista.add(cAjustes.getVista(), "c");
				((CardLayout) vista.getLayout()).show(vista, "c");

			}

		} else if (boton.getActionCommand().equals("modoConfirmar2")) {
			
			JTextField[] cajasPrecios = vista.getPreciosTarifas();
			double[] precios = new double[8];
			
			boolean numOK = false;
			
			for (int i = 0; i < 8; i++) {
				String input = cajasPrecios[i].getText(); 
				if (input == null) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.", null, JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					precios[i] = Double.parseDouble(input);
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Alguno de los numeros ha sido mal introducido.", null, JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			TarifaPelicula.setPrecio(precios[0]);
			TarifaSerie.setPrecio(precios[1]);
			TarifaMusica.setPrecio(precios[2]);
			TarifaPS.setPrecio(precios[3]);
			TarifaPM.setPrecio(precios[4]);
			TarifaSM.setPrecio(precios[5]);
			TarifaPremium.setPrecio(precios[6]);
			Tarifa.setExtraPlus(precios[7]);
			
			JOptionPane.showMessageDialog(null, "Precios modificados correctamente.", null, JOptionPane.INFORMATION_MESSAGE);

			ContrAjustes cAjustes = new ContrAjustes();
			vista.add(cAjustes.getVista(), "c");
			((CardLayout) vista.getLayout()).show(vista, "c");
		}
	}

	public JPanel getVista() {
		return (JPanel) vista;
	}

	public void cambioDeNombre() {

		if (!vista.getCajaNnuevo().getText().isEmpty())
		Ajustes.getInstance().setNombreVideoclub(
				vista.getCajaNnuevo().getText());

	}

	public boolean cambioDeContrasena() {
		
		if (vista.getCajacAntigua().getText().equals(
				Ajustes.getInstance().getContrasena())) {
			Ajustes.getInstance().setContrasena(
					(vista.getCajacNueva().getText()));
			return true;
		} else
			return false;

	}

	public void limpiaCampos() {
		vista.getCajaNnuevo().setText(null);
		vista.getCajacNueva().setText(null);
		vista.getCajacAntigua().setText(null);
	}

}
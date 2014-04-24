/**
 * 
 */
package controladores;

import vistas.MenuSerieAlta;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import catalogo.Catalogo;
import catalogo.Categoria;
import catalogo.Ejemplar;
import aplicacion.Aplicacion;

/**
 * Esta clase es el controlador del menu de creacion de articulos serie. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class SerieAlta extends Opcion {

	private MenuSerieAlta vista;
	private Catalogo articulos;
	private JTextField titulo;
	@SuppressWarnings("rawtypes")
	private JComboBox temporada;
	private JTextField volumen;
	private JCheckBox[] categorias;
	@SuppressWarnings("rawtypes")
	private JComboBox numEjem;
	private JRadioButton DVD;
	private JRadioButton bluray;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alquiler.
	 */
	public SerieAlta() {

		/* Accedemos a los datos de la base de datos */
		vista = new MenuSerieAlta();
		articulos = Catalogo.getInstance();

		numEjem = vista.getCajaNumEjem();
		DVD = vista.getCajaDVD();
		bluray = vista.getCajaBluray();
		titulo = vista.getCajaTitulo();
		temporada = vista.getCajaTemporada();
		volumen = vista.getCajaVolumen();
		categorias = vista.getCajaCategoria();

		/* Asociamos controlador a la vista */
		((MenuSerieAlta) vista).setControlador(this);

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoSerieAlta")) {

			if ((DVD.isSelected() || bluray.isSelected())
					&& (!titulo.getText().isEmpty())
					&& ((!volumen.getText().isEmpty()))) {
				altaSerie();
				JOptionPane.showMessageDialog(null, "Serie dada de alta.");
				limpiaCampos();
			} else {
				JOptionPane.showMessageDialog(null,
						"Es imprescindible que rellene todos los campos",
						"Inane Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (boton.getActionCommand().equals("modoGerente")) {
			/* Volvemo al panel MenuDarAlta */
			Aplicacion.vuelveDarAlta();

		}

	}

	/**
	 * @see controladores.Opcion#getVista()
	 */
	public JPanel getVista() {
		return (JPanel) vista;
	}

	/**
	 * Efectua la operacion de creacion de la serie, anadiendola a la base de datos. 
	 */
	public void altaSerie() {
		/* Añadir articulo al catalogo */
		int id = articulos.crearSerie(titulo.getText(),
				temporada.getComponentCount(),
				Integer.parseInt(volumen.getText()));
		/* Añadir articulo a la lista de categorias */
		for (int i = 0; i < categorias.length; i++) {
			if (categorias[i].isSelected()) {
				Categoria cat = articulos.crearCategoria(
						categorias[i].getText(), categorias[i].getText());
				cat.anadirArticulo(articulos.encuentra(id));
				/* Añadir categoria al articulo */
				articulos.encuentra(id).anadirCategoria(cat);
			}
		}

		int i = 0;
		Ejemplar ejem = null;
		while (i < numEjem.getComponentCount()) {
			if (DVD.isSelected())
				ejem = new Ejemplar(articulos.encuentra(id), false);
			else if (bluray.isSelected())
				ejem = new Ejemplar(articulos.encuentra(id), true);
			articulos.encuentra(id).anadirEjemplar(ejem);

			i++;
		}

	}

	/**
	 * Limpia los campos del formulario. 
	 */
	public void limpiaCampos() {
		DVD.setSelected(false);
		bluray.setSelected(false);
		numEjem.setSelectedIndex(0);
		temporada.setSelectedIndex(0);
		titulo.setText(null);
		volumen.setText(null);
		for (int i = 0; i < categorias.length; i++)
			categorias[i].setSelected(false);
	}

}
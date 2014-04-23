/**
 * 
 */
package controladores;

import vistas.MenuPeliculaAlta;
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
 * Esta clase es el controlador de el arranque de la aplicacion.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class PeliculaAlta extends Opcion {

	private MenuPeliculaAlta vista;
	private Catalogo articulos;
	private JTextField titulo;
	@SuppressWarnings("rawtypes")
	private JComboBox ano;
	private JTextField director;
	private JCheckBox[] categorias;
	@SuppressWarnings("rawtypes")
	private JComboBox numEjem;
	private JRadioButton DVD;
	private JRadioButton bluray;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alquiler.
	 */
	public PeliculaAlta() {

		/* Accedemos a los datos de la base de datos */
		vista = new MenuPeliculaAlta();
		articulos = Catalogo.getInstance();

		numEjem = vista.getCajaNumEjem();
		DVD = vista.getCajaDVD();
		bluray = vista.getCajaBluray();
		titulo = vista.getCajaTitulo();
		ano = vista.getCajaAno();
		director = vista.getCajaInterprete();
		categorias = vista.getCajaCategoria();

		/* Asociamos controlador a la vista */
		((MenuPeliculaAlta) vista).setControlador(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoPeliculaAlta")) {

			if ((DVD.isSelected() || bluray.isSelected())
					&& (!titulo.getText().isEmpty())
					&& ((!director.getText().isEmpty()))) {
				altaPelicula();
				JOptionPane.showMessageDialog(null, "Pelicula dada de alta.");
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

	public JPanel getVista() {
		return (JPanel) vista;
	}

	public void altaPelicula() {
		/* Añadir articulo al catalogo */
		int id = articulos.crearPelicula(titulo.getText(),
				ano.getComponentCount(), director.getText());
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

	public void limpiaCampos() {
		DVD.setSelected(false);
		bluray.setSelected(false);
		numEjem.setSelectedIndex(0);
		ano.setSelectedIndex(0);
		titulo.setText(null);
		director.setText(null);
		for (int i = 0; i < categorias.length; i++)
			categorias[i].setSelected(false);
	}

}
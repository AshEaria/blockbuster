/**
 * 
 */
package controladores;

import vistas.MenuMusicaAlta;

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
 * Esta clase es el controlador del menu de creacion de nuevos articulos de musica. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */

public class MusicaAlta extends Opcion {

	private MenuMusicaAlta vista;
	private Catalogo articulos;
	private JTextField titulo;
	@SuppressWarnings("rawtypes")
	private JComboBox ano;
	private JTextField interprete;
	private JCheckBox[] categorias;
	@SuppressWarnings("rawtypes")
	private JComboBox numEjem;
	private JRadioButton CD;
	private JRadioButton vinilo;

	/* CONSTRUCTOR */

	/**
	 * Este constructor crea el panel del menu de alta de articulos de musica y se asocia a el como controlador.
	 */
	public MusicaAlta() {

		/* Accedemos a los datos de la base de datos */
		vista = new MenuMusicaAlta();
		articulos = Catalogo.getInstance();

		numEjem = vista.getCajaNumEjem();
		CD = vista.getCajaCD();
		vinilo = vista.getCajaVinilo();
		titulo = vista.getCajaTitulo();
		ano = vista.getCajaAno();
		interprete = vista.getCajaInterprete();
		categorias = vista.getCajaCategoria();

		/* Asociamos controlador a la vista */
		((MenuMusicaAlta) vista).setControlador(this);

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		if (boton.getActionCommand().equals("modoMusicaAlta")) {

			if ((CD.isSelected() || vinilo.isSelected())
					&& (!titulo.getText().isEmpty())
					&& ((!interprete.getText().isEmpty()))) {
				altaMusica();
				JOptionPane.showMessageDialog(null, "Musica dada de alta.");
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
	@Override
	public JPanel getVista() {
		return (JPanel) vista;
	}
	
	/**
	 * Efectua la operacion de creacion de la pelicula, anadiendola a la base de datos. 
	 */
	public void altaMusica() {
		/* Añadir articulo al catalogo */
		int id = articulos.crearMusica(titulo.getText(),
				ano.getComponentCount(), interprete.getText());
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
			if (CD.isSelected())
				ejem = new Ejemplar(articulos.encuentra(id), false);
			else if (vinilo.isSelected())
				ejem = new Ejemplar(articulos.encuentra(id), true);
			articulos.encuentra(id).anadirEjemplar(ejem);

			i++;
		}

	}

	/** 
	 * Limpia los campos del formulario. 
	 */
	public void limpiaCampos() {

		CD.setContentAreaFilled(false);
		vinilo.setContentAreaFilled(false);
		numEjem.setSelectedIndex(0);
		ano.setSelectedIndex(0);
		titulo.setText(null);
		interprete.setText(null);
		for (int i = 0; i < categorias.length; i++)
			categorias[i].setSelected(false);

	}

}
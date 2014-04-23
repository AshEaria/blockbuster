/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import socios.ListaSocios;
import socios.Socio;
/**
 * @author elena
 * 
 */
public class MenuMorosos extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton atras;
	private JTable Jtabla;
	private JScrollPane scroll;
	private JLabel Ltitulo;
	private JPanel titulo;
	private JPanel Patras;
	/* Paneles */
	private JPanel articulos;
	private JPanel tabla;
	private JPanel panel;
	/**/
	private ListaSocios ejem;
	private Tabla tReponer;

	public MenuMorosos() {

		/* Menu reponer */
		this.setLayout(new CardLayout());

		/* Crear componentes */
		Ltitulo = new JLabel("Listado de morosos");

		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);
		
		/* Modificar componentes */
		Ltitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		Ltitulo.setFont(new Font("Georgia", 40, 25));

		/* Asignar nombres para Action Listener */
		atras.setActionCommand("modoGerente");

		/* Panel por defecto */
		panel = new JPanel();

		tabla();
		muestraPanel();

		/* Anadir el panel Reponer Musica al cardLAyout PRINCIPAL */
		this.add(panel, "PReponerM");
		((CardLayout) this.getLayout()).show(this, "PReponerM");

	}

	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {

		atras.addActionListener(c);
	}

	public void muestraPanel() {

		/* Inicializacion */
		titulo = new JPanel();
		Patras = new JPanel();
		articulos = new JPanel();
		panel = new JPanel();

		/* Modificacion */
		Patras.setLayout(new BoxLayout(Patras, BoxLayout.Y_AXIS));
		Patras.setBorder(BorderFactory.createEmptyBorder(100, 1, 0, 50));
		articulos.setBorder(BorderFactory.createEmptyBorder(1, 1, 40, 100));
		articulos.setLayout(new BoxLayout(articulos, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		titulo.add(Ltitulo);
		articulos.add(titulo, BorderLayout.NORTH);

		articulos.add(tabla);
		Patras.add(atras);

		panel.add(Patras);
		panel.add(articulos);

	}

	public void tabla() {
		ejem = ListaSocios.getInstance();

		String[] titulos = { "#Socio","Nombre","Articulos", "Dias retraso","Penalizacion" };
		Object[][] filas = new Object[ejem.getMorosos().size()][titulos.length];

		int i = 0;
		for (Socio e : ejem.getMorosos()) {	
					for (int x = 0; x < titulos.length; x++) {

						if (x == 0)
							filas[i][x] = e.getNumSocio();
						if (x == 1)
							filas[i][x] = e.getNombre();
						if (x == 2)
							filas[i][x] = e.getAlquileres().size();
						if (x == 3)
							filas[i][x] = e.getAlquileres().get(0).diasRetraso();
						if (x == 4)
							filas[i][x] = "Falta este campo";
					}
					i++;

		}
		tReponer = new Tabla(filas, titulos);

		//DefaultTableModel tReopner = new DefaultTableModel(filas, titulos);
		Jtabla = new JTable(tReponer);
		Jtabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Jtabla.setPreferredScrollableViewportSize(new Dimension(400, 200));
		scroll = new JScrollPane(Jtabla);
		tabla = new JPanel();
		tabla.add(scroll);

	}
}

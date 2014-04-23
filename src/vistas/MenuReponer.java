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
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * @author elena
 * 
 */
public class MenuReponer extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton aceptar;
	private JButton atras;
	/* Botones menu */
	private JButton peliculas;
	private JButton musica;
	private JButton series;
	/* Boton seleccionado */
	private JButton SelectP;
	private JButton SelectM;
	private JButton SelectS;
	/* Componentes */
	private JTable Jtabla;
	private JScrollPane scroll;
	private JLabel Ltitulo;
	private JPanel titulo;
	private JPanel Patras;
	private JPanel Paceptar;
	/* Paneles */
	private JPanel articulos;
	private JPanel botones;
	private JPanel tabla;
	private JPanel panel;
	/**/
	private ListaEjemplares ejem;
	private Tabla tReponer;
	//private JCheckBox[] REPONER;

	public MenuReponer() {

		/* Menu reponer */
		this.setLayout(new CardLayout());

		/* Crear componentes */

		Ltitulo = new JLabel("Reponer articulos no disponibles");

		ImageIcon imAceptar = new ImageIcon("aceptar.png");
		aceptar = new JButton(imAceptar);
		aceptar.setBorder(null);

		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);

		/* Componentes MENU INICIAL */

		ImageIcon imPeliculas = new ImageIcon("peliculas.png");
		peliculas = new JButton(imPeliculas);
		peliculas.setBorder(null);

		ImageIcon imMusica = new ImageIcon("musica.png");
		musica = new JButton(imMusica);
		musica.setBorder(null);

		ImageIcon imSeries = new ImageIcon("series.png");
		series = new JButton(imSeries);
		series.setBorder(null);

		/* Componentes PELICULAS */

		ImageIcon imSelectP = new ImageIcon("Peliculas.png");
		SelectP = new JButton(imSelectP);
		SelectP.setBorder(null);

		/* Componentes MUSICA */

		ImageIcon imSelectM = new ImageIcon("Musicas.png");
		SelectM = new JButton(imSelectM);
		SelectM.setBorder(null);

		/* Componentes SERIES */
		ImageIcon imSelectS = new ImageIcon("Series.png");
		SelectS = new JButton(imSelectS);
		SelectS.setBorder(null);

		/* Modificar componentes */
		Ltitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		Ltitulo.setFont(new Font("Georgia", 40, 25));

		/* Asignar nombres para Action Listener */
		aceptar.setActionCommand("modoRepuestos");
		atras.setActionCommand("modoEmpleado");
		peliculas.setActionCommand("modoPeliculas");
		musica.setActionCommand("modoMusica");
		series.setActionCommand("modoSeries");

		SelectP.setActionCommand("modoPeliculas");
		SelectM.setActionCommand("modoMusica");
		SelectS.setActionCommand("modoSeries");

		/* Panel por defecto */
		muestraPeliculas();

	}

	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {

		atras.addActionListener(c);
		aceptar.addActionListener(c);
		peliculas.addActionListener(c);
		musica.addActionListener(c);
		series.addActionListener(c);
		SelectP.addActionListener(c);
		SelectS.addActionListener(c);
		SelectM.addActionListener(c);
	}

	public void muestraPanel() {

		/* Inicializacion */
		titulo = new JPanel();
		Patras = new JPanel();
		Paceptar = new JPanel();
		articulos = new JPanel();
		panel = new JPanel();

		/* Modificacion */
		Paceptar.setLayout(new BoxLayout(Paceptar, BoxLayout.Y_AXIS));
		Patras.setLayout(new BoxLayout(Patras, BoxLayout.Y_AXIS));
		Paceptar.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 10));
		Patras.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 10));

		articulos.setLayout(new BoxLayout(articulos, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		titulo.add(Ltitulo);

		articulos.add(titulo, BorderLayout.NORTH);
		articulos.add(botones);

		articulos.add(tabla);
		Paceptar.add(aceptar);
		Patras.add(atras);

		panel.add(Patras);
		panel.add(articulos);
		panel.add(Paceptar);

	}

	public void muestraPeliculas() {
		panel = new JPanel();
		botones = new JPanel();
		tabla = new JPanel();

		botones.add(SelectP);
		botones.add(series);
		botones.add(musica);

		tabla("Pelicula");
		muestraPanel();

		/* Anadir el panel Reponer Peliculas al cardLAyout PRINCIPAL */
		this.add(panel, "PReponerP");
		((CardLayout) this.getLayout()).show(this, "PReponerP");

	}

	public void muestraMusica() {
		panel = new JPanel();
		botones = new JPanel();

		botones.add(peliculas);
		botones.add(series);
		botones.add(SelectM);

		tabla("Musica");
		muestraPanel();

		/* Anadir el panel Reponer Musica al cardLAyout PRINCIPAL */
		this.add(panel, "PReponerM");
		((CardLayout) this.getLayout()).show(this, "PReponerM");

	}

	public void muestraSeries() {
		panel = new JPanel();
		botones = new JPanel();

		botones.add(peliculas);
		botones.add(SelectS);
		botones.add(musica);

		tabla("Serie");
		muestraPanel();

		/* Anadir el panel Reponer Series al cardLAyout PRINCIPAL */
		this.add(panel, "PReponerS");
		((CardLayout) this.getLayout()).show(this, "PReponerS");

	}

	public void tabla(String tipo) {

		ejem = ListaEjemplares.getInstance();

		String[] titulos = { "Reponer", "Codigo", "Titulo" };
		Object[][] filas = null;
		if (tipo.matches("Pelicula"))
			filas = new Object[ejem.getNumEjemP(EstadoEjemplar.NO_DISPONIBLE)][titulos.length];
	
		else if (tipo.matches("Musica"))
			filas = new Object[ejem.getNumEjemM(EstadoEjemplar.NO_DISPONIBLE)][titulos.length];
		else if (tipo.matches("Serie"))
			filas = new Object[ejem.getNumEjemS(EstadoEjemplar.NO_DISPONIBLE)][titulos.length];

		/*Inicializar array de CheckBoxes*/
		
		int i = 0;
		for (Ejemplar e : ejem.getEjemplares()) {
			if (e.getEstadoEjem().equals(EstadoEjemplar.NO_DISPONIBLE)) {

				if (tipo.matches("Pelicula")) {	
					if (e.getArticulo() instanceof Pelicula) {
						for (int x = 0; x < titulos.length; x++) {

							if (x == 0)
								filas[i][x] = false;
							if (x == 1)
								filas[i][x] = e.getId();
							if (x == 2)
								filas[i][x] = e.getArticulo().getTitulo();
						}
						i++;
					}
				
				} else if (tipo.matches("Musica")) {
					if (e.getArticulo() instanceof Musica) {
						for (int x = 0; x < titulos.length; x++) {

							if (x == 0)
								filas[i][x] = false;
							if (x == 1)
								filas[i][x] = e.getId();
							if (x == 2)
								filas[i][x] = e.getArticulo().getTitulo();
						}
						i++;
					}
				
				} else if (tipo.matches("Serie")) {
					if (e.getArticulo() instanceof Serie) {
						for (int x = 0; x < titulos.length; x++) {

							if (x == 0)
								filas[i][x] = false;
							if (x == 1)
								filas[i][x] = e.getId();
							if (x == 2)
								filas[i][x] = e.getArticulo().getTitulo();
						}
						i++;
					}
				}

			}
		}
		tReponer = new Tabla(filas, titulos);
		//JCheckBox REPONER = new JCheckBox();

		Jtabla = new JTable(tReponer);
		/*Meter CheckBox en la primera columna*/
		//TableColumn sportColumn = Jtabla.getColumnModel().getColumn(0);
		//sportColumn.setCellEditor(new DefaultCellEditor(REPONER));
		//Jtabla.addColumn(sportColumn);
	//	Jtabla.getColumn(0).setCellEditor((TableCellEditor) new JCheckBox());
		
		//Jtabla.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Jtabla.setPreferredScrollableViewportSize(new Dimension(400, 200));
		scroll = new JScrollPane(Jtabla);
		tabla = new JPanel();
		tabla.add(scroll);

	}

	public Tabla getJTabla() {
		return tReponer;
	}

}
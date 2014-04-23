/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import catalogo.Articulo;
import catalogo.Catalogo;
import catalogo.Categoria;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class MenuBuscarArt extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton atras;
	/* Botones menu */
	private JButton peliculas;
	private JButton musica;
	private JButton series;
	/* Componentes */
	private JTable Jtabla;
	private JScrollPane scroll;
	private JLabel Ltitulo;
	private JPanel titulo;
	private JPanel Patras;
	/* Paneles */
	private JPanel articulos;
	private JPanel botones;
	private JPanel tabla;
	private JPanel panel;
	/**/
	private Tabla tReponer;
	private String tipoActual; 
	private ActionListener controlador;
	
	public MenuBuscarArt(ActionListener c) {

		/* Menu General */
		this.setLayout(new CardLayout());

		/* Crear componentes */
		Ltitulo = new JLabel("Busqueda de articulos");

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

		/* Modificar componentes */
		Ltitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		Ltitulo.setFont(new Font("Georgia", 40, 25));

		/* Asignar nombres para Action Listener */
		atras.setActionCommand("modoEmpleado");
		peliculas.setActionCommand("modoPeliculas");
		musica.setActionCommand("modoMusica");
		series.setActionCommand("modoSeries");

		botones = new JPanel();

		botones.add(peliculas);
		botones.add(series);
		botones.add(musica);
		
		setControlador(c);

		/* Panel por defecto */
		muestraCats("Pelicula");

	}
	
	// metodo para asignar un controlador al boton
	public void setControlador(ActionListener c) {

		controlador = c; 
		atras.addActionListener(c);
		peliculas.addActionListener(c);
		musica.addActionListener(c);
		series.addActionListener(c);
	}

	public void muestraPanel(String cat) {

		/* Inicializacion */
		tabla(cat);
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
		articulos.add(botones);

		articulos.add(tabla);
		Patras.add(atras);

		panel.add(Patras);
		panel.add(articulos);
		
		/* Anadir el panel con la tabla al cardLayout PRINCIPAL */
		this.add(panel, "PTabla");
		((CardLayout) this.getLayout()).show(this, "PTabla");
	}
	
	public void muestraCats(String tipo) {
		tipoActual = tipo;

		titulo = new JPanel();
		
		Patras = new JPanel();
		Patras.setLayout(new BoxLayout(Patras, BoxLayout.Y_AXIS));
		Patras.setBorder(BorderFactory.createEmptyBorder(100, 1, 0, 50));
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel pCategorias = new JPanel(); 
		pCategorias.setSize(new Dimension(400, 200));
		pCategorias.setLayout(new GridBagLayout()); 
		
		JPanel centro = new JPanel(); 
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		
		JPanel categorias = new JPanel(); 
		categorias.setLayout(new GridLayout(0,4)); 
		
		JPanel contenido = new JPanel();
		contenido.setLayout(new BorderLayout()); 
		contenido.setBorder(BorderFactory.createEmptyBorder(1, 1, 40, 100));
		
		Catalogo catalogo = Catalogo.getInstance();
		ArrayList<Categoria> cats = (tipoActual.equals("Pelicula")) ? catalogo.getCategoriasPelicula() : ((tipoActual.equals("Serie")) ? catalogo.getCategoriasSerie() : catalogo.getCategoriasMusica());
		
		for (Categoria c : cats) {
			JButton boton = new JButton(c.toStringSinC()); 
			boton.addActionListener(controlador);
			categorias.add(boton); 
		}
		
		titulo.add(Ltitulo);

		contenido.add(titulo, BorderLayout.NORTH);
		centro.add(botones);
		pCategorias.add(categorias);
		centro.add(pCategorias);
		contenido.add(centro, BorderLayout.CENTER);

		Patras.add(atras);
		panel.add(Patras);
		panel.add(contenido);
		
		/* Anadir el panel con las categorias al cardLayout PRINCIPAL */
		this.add(panel, "PCats");
		((CardLayout) this.getLayout()).show(this, "PCats");
		
	}

	public void tabla(String nombre) {
		Catalogo catalogo = Catalogo.getInstance();
		
		ArrayList<Articulo> arts = catalogo.encuentra(nombre, tipoActual).getArticulos();
		
		String[] titulos = { "Puesto", "Titulo", "Veces alquilado" };
		Object[][] filas = new Object[arts.size()][titulos.length];

		int i = 0;
		for (Articulo e : arts) {
			for (int x = 0; x < titulos.length; x++) {

				if (x == 0)
					filas[i][x] = i+1;
				if (x == 1)
					filas[i][x] = e.getTitulo();
				if (x == 2)
					filas[i][x] = e.getVeces();
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

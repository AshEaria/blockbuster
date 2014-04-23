/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author elena
 * 
 */
public class MenuDarAlta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton atras;
	
	/*Botones menu*/
	private JButton Peliculas;
	private JButton Musica;
	private JButton Series;
	
	/*Paneles*/
	private JPanel articulos;
	private JPanel botones;
	private JPanel tabla;
	private JPanel panel;
	/*Componentes*/
	private JLabel Ltitulo;
	private JPanel titulo;
	private JPanel Patras;

	public MenuDarAlta() {

		/*Menu reponer*/
		this.setLayout(new CardLayout());

		/*Crear componentes*/

		
		Ltitulo= new JLabel("Selecciona el articulo que deseas aniadir");

		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);

		/* Componentes MENU INICIAL */
		
		ImageIcon imP = new ImageIcon("Pelicula.png");
		Peliculas = new JButton(imP);
		Peliculas.setBorder(null);
		
		ImageIcon imM = new ImageIcon("Musica.png");
		Musica = new JButton(imM);
		Musica.setBorder(null);
	
		ImageIcon imS = new ImageIcon("Serie.png");
		Series = new JButton(imS);
		Series.setBorder(null);
		

		/* Modificar componentes */
		Ltitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		Ltitulo.setFont(new Font("Georgia", 40, 25));

		/* Asignar nombres para Action Listener */
		atras.setActionCommand("modoEmpleado");
		
		Peliculas.setActionCommand("modoPelicula");
		Musica.setActionCommand("modoMusica");
		Series.setActionCommand("modoSerie");
		
		/* Crear paneles */
		
		/*Inicializacion*/
		titulo = new JPanel();
		Patras = new JPanel();
		articulos = new JPanel();
		tabla = new JPanel();
		panel = new JPanel();
		botones = new JPanel();

		/* Modificacion*/
		Patras.setLayout(new BoxLayout(Patras, BoxLayout.Y_AXIS));
		Patras.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 10));

		articulos.setLayout(new BoxLayout(articulos, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		

		botones.add(Peliculas);
		botones.add(Musica);
		botones.add(Series);
		
		titulo.add(Ltitulo);

		articulos.add(titulo, BorderLayout.NORTH);
		articulos.add(botones);
		
		articulos.add(tabla);
		Patras.add(atras);

		panel.add(Patras);
		panel.add(articulos);
		
		
		/* Anadir el panel PRINCIPAL al cardLAyout PRINCIPAL */
		this.add(panel, "PDarAlta");

		/* Mostrar menu inicialmente */
		((CardLayout) this.getLayout()).show(this, "PDarAlta");
		

	}

	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {

		atras.addActionListener(c);
		Peliculas.addActionListener(c);
		Musica.addActionListener(c);
		Series.addActionListener(c);
	}


}

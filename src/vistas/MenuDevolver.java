/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import catalogo.Ejemplar;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;
import aplicacion.Ajustes;
import socios.Alquiler;
import socios.Socio;
import socios.Tarifa;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaSM;
import socios.TarifaSerie;

/**
 * @author e280219
 *
 */
public class MenuDevolver extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton cancelar;
	private JButton continuar;
	private JCheckBox[] boxes;
	private JCheckBox[] boxesND; 
	private ArrayList<Alquiler> alqSocio; 
	
	/**
	 * @param socio
	 */
	public MenuDevolver(Socio socio) {
		Ajustes ajustes = Ajustes.getInstance();
		
		/* Crear paneles */
		JPanel card1 = new JPanel();
		JPanel panelSocio = new JPanel();
		JPanel datosSocio = new JPanel();
		JPanel panelCancelar = new JPanel();
		JPanel panelArticulos = new JPanel();
		JPanel titArticulo = new JPanel();
		JPanel artDatos = new JPanel();
		JPanel panelContinuar = new JPanel();
		// JPanel pagar = new JPanel(); ...........
		
		/* Asignar layouts a paneles */
		this.setLayout(new CardLayout());
		card1.setLayout(new GridLayout(1,2));
		panelSocio.setLayout(new BorderLayout());
		datosSocio.setLayout(new GridLayout(7,1));
		// panelCancelar.setLayout(new FlowLayout());
		panelArticulos.setLayout(new BorderLayout());
		titArticulo.setLayout(new GridBagLayout());
		// panelContinuar.setLayout(new FlowLayout());
		artDatos.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		/* Crear componentes */
		
		JLabel titSocio = new JLabel("Socio");
		titSocio.setFont(titSocio.getFont().deriveFont(24f));
		JLabel nombreSocio = new JLabel("#" + socio.getNumSocio() + " - " + socio.getNombre());
		JLabel titTarifa = new JLabel("Tarifa plana");
		titTarifa.setFont(titTarifa.getFont().deriveFont(24f));
		JLabel nombreTarifa;
		JLabel infoTarifa;
		Tarifa tar = socio.getTarifa();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (tar == null) {
			nombreTarifa = new JLabel("Ninguna");
			infoTarifa = new JLabel("");
		} else {
			if(tar instanceof TarifaPelicula) nombreTarifa = new JLabel("Sencilla - Peliculas");
			else if(tar instanceof TarifaSerie) nombreTarifa = new JLabel("Sencilla - Series");
			else if(tar instanceof TarifaMusica) nombreTarifa = new JLabel("Sencilla - Musica");
			else if(tar instanceof TarifaPS) nombreTarifa = new JLabel("Combinada - Peliculas + Series");
			else if(tar instanceof TarifaPM) nombreTarifa = new JLabel("Combinada - Peliculas + Musica");
			else if(tar instanceof TarifaSM) nombreTarifa = new JLabel("Combinada - Series + Musica");
			else nombreTarifa = new JLabel("Premium");
			
			infoTarifa = new JLabel("Fin de tarifa: " + sdf.format(tar.getFechaFin().getTime()));
		}
		
		cancelar = new JButton("Cancelar");
		
		JLabel titArticulos = new JLabel("Articulos");
		titArticulos.setFont(titArticulos.getFont().deriveFont(24f));
		JLabel titDevolver = new JLabel("Devolver");
		JLabel titND = new JLabel("No disponible");

		boxes = new JCheckBox[ajustes.getMaxArticulos()];
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			boxes[i] = new JCheckBox();
		}
		
		boxesND = new JCheckBox[ajustes.getMaxArticulos()];
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			boxesND[i] = new JCheckBox();
		}
		
		/* Los datos de los ejemplares se crean en forma de ArrayList de JLabel y se escriben los JLabel con la informacion correspondiente */
		ArrayList<JLabel[]> datos = new ArrayList<JLabel[]>();
		alqSocio = socio.getAlquileres();
		for (int i = 0; i < alqSocio.size(); i++) {
			JLabel[] artData = new JLabel[4];
			
			Ejemplar currEjem = alqSocio.get(i).getEjemplar();
			String[] currArt;
			if (currEjem.getArticulo() instanceof Pelicula) currArt = escribeDatosPelicula(currEjem);
			else if (currEjem.getArticulo() instanceof Serie) currArt = escribeDatosSerie(currEjem);
			else if (currEjem.getArticulo() instanceof Musica) currArt = escribeDatosMusica(currEjem);
			else continue;
			
			for (int j = 0; j < 3; j++) {
				artData[j] = new JLabel(currArt[j]);
			}
			artData[3] = new JLabel("F. devolucion: " + sdf.format(alqSocio.get(i).getFechaFin().getTime()));
			datos.add(artData);
		}
		
		continuar = new JButton("Continuar");
		
		/* Anadir componentes a los paneles y meter los paneles uno dentro de otro */
		
		datosSocio.add(titSocio);
		datosSocio.add(nombreSocio);
		datosSocio.add(new JLabel(""));
		datosSocio.add(titTarifa);
		datosSocio.add(nombreTarifa);
		datosSocio.add(infoTarifa);
		datosSocio.add(new JLabel(""));
		panelSocio.add(datosSocio, BorderLayout.CENTER);
		panelCancelar.add(cancelar);
		panelSocio.add(panelCancelar, BorderLayout.SOUTH);
		card1.add(panelSocio);
		
		titArticulo.add(titArticulos);
		panelArticulos.add(titArticulo, BorderLayout.NORTH);
		
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		artDatos.add(titDevolver, c); 
		c.gridx = 2; 
		artDatos.add(titND, c);
		
		for (int i = 0; i < datos.size(); i++) {
			c.anchor = GridBagConstraints.PAGE_START;
			c.weightx = 0;
			c.weighty = 0;
			c.gridx = 0;
			c.gridy = (i*4) + 1;
			artDatos.add(boxes[i], c);
			c.weightx = 0.3;
			c.gridx = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			artDatos.add(datos.get(i)[0], c);
			c.anchor = GridBagConstraints.PAGE_START;
			c.gridx++; 
			artDatos.add(boxesND[i], c); 
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.gridx--; 
			c.gridy = (i*4) + 2;
			artDatos.add(datos.get(i)[1], c);
			c.gridy = (i*4) + 3;
			artDatos.add(datos.get(i)[2], c);
			c.gridy = (i*4) + 4;
			c.weighty = 0.2;
			artDatos.add(datos.get(i)[3], c);
		}
		
		panelArticulos.add(artDatos, BorderLayout.CENTER);
		panelContinuar.add(continuar);
		panelArticulos.add(panelContinuar, BorderLayout.SOUTH);
		
		card1.add(panelArticulos);
		this.add(card1, "ARTICULOS");
		// this.add(pagaMetodowhatever, "PAGAR");
		
		((CardLayout) this.getLayout()).show(this, "ARTICULOS");
		
	}

	public void setControlador(ActionListener c) {
		cancelar.addActionListener(c);
		continuar.addActionListener(c);
	}
	
	/**
	 * Devuelve los datos de una pelicula, organizados en tres strings. 
	 * 
	 * @param ejem Ejemplar del que se quieren obtener los datos. 
	 * @return Array de tres strings con los datos del ejemplar. 
	 */
	public String[] escribeDatosPelicula(Ejemplar ejem) {
		if (ejem == null) return null; 
		Pelicula art = (Pelicula) ejem.getArticulo();
		if (art == null) return null;
		
		String[] datos = new String[3];
		datos[0] = ("#" + ejem.getId() + " - " + art.getTitulo());
		datos[1] = (art.getDirector() + " (" + art.getAno() + ")");
		datos[2] = "Pelicula";
		
		return datos;
	}
	
	/**
	 * Devuelve los datos de una serie, organizados en tres strings. 
	 * 
	 * @param ejem Ejemplar del que se quieren obtener los datos. 
	 * @return Array de tres strings con los datos del ejemplar. 
	 */
	public String[] escribeDatosSerie(Ejemplar ejem) {
		if (ejem == null) return null; 
		Serie art = (Serie) ejem.getArticulo();
		if (art == null) return null;
		
		String[] datos = new String[3];
		datos[0] = ("#" + ejem.getId() + " - " + art.getTitulo());
		datos[1] = ("Temporada " + art.getTemporada() + " - Volumen " + art.getVolumen());
		datos[2] = "Serie";
		
		return datos;
	}
	
	/**
	 * Devuelve los datos de un articulo de musica, organizados en tres strings. 
	 * 
	 * @param ejem Ejemplar del que se quieren obtener los datos. 
	 * @return Array de tres strings con los datos del ejemplar. 
	 */
	public String[] escribeDatosMusica(Ejemplar ejem) {
		if (ejem == null) return null; 
		Musica art = (Musica) ejem.getArticulo();
		if (art == null) return null;
		
		String[] datos = new String[3];
		datos[0] = ("#" + ejem.getId() + " - " + art.getTitulo());
		datos[1] = (art.getInterprete() + " (" + art.getAno() + ")");
		datos[2] = "Musica";
		
		return datos;
	}
	
	public ArrayList<Alquiler> getDevolviendo() {
		ArrayList<Alquiler> alqDevolviendo = new ArrayList<Alquiler>();
		if (alqSocio == null) return alqDevolviendo;
		if (alqSocio.isEmpty()) return alqDevolviendo; 
		
		int i = 0;
		
		for (Alquiler a : alqSocio) {
			if (boxes[i].isSelected()) alqDevolviendo.add(a);
			i++;
		}
		
		return alqDevolviendo;
	}

	/**
	 * @return
	 */
	public boolean[] getNoDispDev() {
		boolean[] ndDev = new boolean[boxes.length]; 
		int j = 0; 
		for (int i = 0; i < boxes.length; i++) {
			if (boxes[i].isSelected()) {
				ndDev[j] = boxesND[i].isSelected(); 
				j++;
			}
		}
		return ndDev;
	}

}

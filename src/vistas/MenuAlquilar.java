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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import aplicacion.Ajustes;
import socios.Socio;
import socios.Tarifa;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaSM;
import socios.TarifaSerie;

/**
 * Vista del menu de alquiler de articulos. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class MenuAlquilar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField[] arts;
	private JButton buscArticulos;
	private JButton cancelar;
	private JButton atras;
	private JButton pagar;
	private JPanel selector;
	private JLabel[][] datos;
	
	/**
	 * Constructor del panel. 
	 * 
	 * @param socio Socio del cual se deben mostrar los datos. 
	 */
	public MenuAlquilar(Socio socio) {
		Ajustes ajustes = Ajustes.getInstance();
		
		/* Crear paneles */
		
		JPanel card1 = new JPanel();
		JPanel panelSocio = new JPanel();
		JPanel datosSocio = new JPanel();
		JPanel panelCancelar = new JPanel();
		JPanel panelArticulos = new JPanel();
		selector = new JPanel();
		JPanel selectorC1 = new JPanel();
		JPanel titArticulo = new JPanel();
		JPanel cajasID = new JPanel();
		JPanel panelBuscar = new JPanel();
		JPanel selectorC2 = new JPanel();
		JPanel datosID = new JPanel();
		JPanel botonesC2 = new JPanel();
		// JPanel pagar = new JPanel(); ...........
		
		
		/* Asignar Layouts a los paneles */
		
		this.setLayout(new CardLayout());
		card1.setLayout(new GridLayout(1,2));
		panelSocio.setLayout(new BorderLayout());
		datosSocio.setLayout(new GridLayout(7,1));
		// panelCancelar.setLayout(new FlowLayout());
		panelArticulos.setLayout(new BorderLayout());
		selector.setLayout(new CardLayout());
		selectorC1.setLayout(new BorderLayout());
		titArticulo.setLayout(new GridBagLayout());
		cajasID.setLayout(new GridBagLayout());
		selectorC2.setLayout(new BorderLayout());
		datosID.setLayout(new GridBagLayout());
		// botonesC2.setLayout(new FlowLayout());
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			infoTarifa = new JLabel("Fin de tarifa: " + sdf.format(tar.getFechaFin().getTime()));
		}
		
		cancelar = new JButton("Cancelar");
		
		JLabel titArticulos = new JLabel("Articulos");
		titArticulos.setFont(titArticulos.getFont().deriveFont(24f));

		JLabel[] nums = new JLabel[ajustes.getMaxArticulos()];
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			nums[i] = new JLabel((i+1) + ". ");
		}
		
		arts = new JTextField[ajustes.getMaxArticulos()];
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			arts[i] = new JTextField(8);
		}
		
		buscArticulos = new JButton("Buscar Articulos");
		
		/* Los datos de los ejemplares se crean en forma de tabla */
		datos = new JLabel[ajustes.getMaxArticulos()][3];
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			for (int j = 0; j < 3; j++) {
				datos[i][j] = new JLabel();
			}
		}
		
		atras = new JButton("Atras");
		pagar = new JButton("Pagar");
		
		
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

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weighty = 0.2;
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			c.gridy = i;
			c.gridx = 0;
			cajasID.add(nums[i], c);
			c.gridx = 1;
			cajasID.add(arts[i], c);
		}
		
		selectorC1.add(cajasID, BorderLayout.CENTER);
		panelBuscar.add(buscArticulos);
		selectorC1.add(panelBuscar, BorderLayout.SOUTH);
		selector.add(selectorC1, "CAJAS");
		
		/* Los JLabel de ejemplar se anaden directamente y se cambian mas tarde */
		JLabel[] nums2 = new JLabel[ajustes.getMaxArticulos()];
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			nums2[i] = new JLabel((i+1) + ". ");
		}
		for (int i = 0; i < ajustes.getMaxArticulos(); i++) {
			c.weightx = 0;
			c.weighty = 0;
			c.gridx = 0;
			c.gridy = i*3;
			datosID.add(nums2[i], c);
			c.weightx = 0.3;
			c.gridx = 1;
			datosID.add(datos[i][0], c);
			c.gridy = (i*3) + 1;
			datosID.add(datos[i][1], c);
			c.gridy = (i*3) + 2;
			c.weighty = 0.2;
			datosID.add(datos[i][2], c);
		}
		

		selectorC2.add(datosID, BorderLayout.CENTER);
		botonesC2.add(atras);
		botonesC2.add(pagar);
		selectorC2.add(botonesC2, BorderLayout.SOUTH);
		selector.add(selectorC2, "DATOS");
		((CardLayout) selector.getLayout()).show(selector, "CAJAS");
		
		panelArticulos.add(selector);
		card1.add(panelArticulos);
		this.add(card1, "ARTICULOS");
		// this.add(pagaMetodowhatever, "PAGAR");
		
		((CardLayout) this.getLayout()).show(this, "ARTICULOS");
		

	}
	
	public void setControlador(ActionListener c) {
		cancelar.addActionListener(c);
		buscArticulos.addActionListener(c);
		atras.addActionListener(c);
		pagar.addActionListener(c);
	}
	
	public JPanel getSelector() { return selector; }
	
	public ArrayList<String> getCajas() {
		ArrayList<String> cajas = new ArrayList<String>();
		boolean vacio = true;
		
		for (int i = 0; i < arts.length; i++) {
			if (!arts[i].getText().equals("")) vacio = false;
		}

		if (vacio) return null;
		
		for (int i = 0; i < arts.length; i++) {
			if (!arts[i].getText().equals("")) cajas.add(arts[i].getText());
		}
		
		return cajas;
	}
	
	public boolean setDatos(String[][] datosArt) {
		if (datosArt == null) return false;
		
		for (int i = 0; (i < datosArt.length) && (i < Ajustes.getInstance().getMaxArticulos()); i++) {
			for (int j = 0; (j < datosArt[i].length) && (j < 3); j++) {
				datos[i][j].setText(datosArt[i][j]);
			}
		}
		
		return true;
	}

}

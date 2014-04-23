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
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aplicacion.Ajustes;
import socios.Socio;
import socios.Tarifa;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaPremium;
import socios.TarifaSM;
import socios.TarifaSerie;

/**
 * @author Jaime
 *
 */
public class MenuContratarTarifa extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton cancelar;
	private JButton continuar;
	private JCheckBox[] boxes;
	
	public MenuContratarTarifa(Socio socio) {
		Ajustes ajustes = Ajustes.getInstance();
		
		/* Crear paneles */
		JPanel card1 = new JPanel();
		JPanel panelSocio = new JPanel();
		JPanel datosSocio = new JPanel();
		JPanel panelCancelar = new JPanel();
		JPanel panelOpciones = new JPanel();
		JPanel titTarifas = new JPanel();
		JPanel opcTarifas = new JPanel();
		JPanel panelContinuar = new JPanel();
		// JPanel pagar = new JPanel(); ...........
		
		/* Asignar layouts a paneles */
		this.setLayout(new CardLayout());
		card1.setLayout(new GridLayout(1,2));
		panelSocio.setLayout(new BorderLayout());
		datosSocio.setLayout(new GridLayout(7,1));
		// panelCancelar.setLayout(new FlowLayout());
		panelOpciones.setLayout(new BorderLayout());
		titTarifas.setLayout(new GridBagLayout());
		// panelContinuar.setLayout(new FlowLayout());
		opcTarifas.setLayout(new GridBagLayout());
		
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
		
		JLabel tipTarifas = new JLabel("Tipo de tarifa");
		tipTarifas.setFont(tipTarifas.getFont().deriveFont(24f));

		boxes = new JCheckBox[4];
		boxes[0] = new JCheckBox("Peliculas");
		boxes[1] = new JCheckBox("Series");
		boxes[2] = new JCheckBox("Musica");
		boxes[3] = new JCheckBox("Tarifa Plus");
		
		JLabel descPlus = new JLabel("<html><div WIDTH=250>La tarifa Plus extiende el alquiler de todos los articulos cubiertos por la tarifa durante " + (ajustes.getDiasPlus() - ajustes.getDias()) + " dias, por el precio de " + Tarifa.getExtraPlus() + "€</div></html>");
		descPlus.setFont(descPlus.getFont().deriveFont(10f));

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
		
		titTarifas.add(tipTarifas);
		panelOpciones.add(titTarifas, BorderLayout.NORTH);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		
		c.weightx = 0;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		opcTarifas.add(Box.createVerticalStrut(5), c);
		c.weighty = 0;
		c.gridy++;
		opcTarifas.add(boxes[0], c);
		c.gridy++;
		opcTarifas.add(boxes[1], c);
		c.gridy++;
		opcTarifas.add(boxes[2], c);
		c.gridy++;
		c.weighty = 0.5;
		opcTarifas.add(Box.createVerticalStrut(10), c);
		c.gridy++;
		c.weighty = 0;
		opcTarifas.add(descPlus, c);
		c.gridy++;
		opcTarifas.add(boxes[3], c);
		c.gridy++;
		c.weighty = 0.5;
		opcTarifas.add(Box.createVerticalStrut(5), c);
		
		panelOpciones.add(opcTarifas, BorderLayout.CENTER);
		panelContinuar.add(continuar);
		panelOpciones.add(panelContinuar, BorderLayout.SOUTH);
		
		card1.add(panelOpciones);
		this.add(card1, "TARIFAS");
		// this.add(pagaMetodowhatever, "PAGAR");
		
		((CardLayout) this.getLayout()).show(this, "TARIFAS");
		
	}
	
	public void setControlador(ActionListener boton) {
		cancelar.addActionListener(boton);
		continuar.addActionListener(boton);
	}
	
	public Tarifa getConfigTarifa() {
		boolean plus = boxes[3].isSelected();
		Tarifa tar;
		Calendar cal = Calendar.getInstance();
		
		if (boxes[0].isSelected() && !boxes[1].isSelected() && !boxes[2].isSelected()) tar = new TarifaPelicula(0, 1, cal, cal, plus);
		else if (!boxes[0].isSelected() && boxes[1].isSelected() && !boxes[2].isSelected()) tar = new TarifaSerie(0, 1, cal, cal, plus);
		else if (!boxes[0].isSelected() && !boxes[1].isSelected() && boxes[2].isSelected()) tar = new TarifaMusica(0, 1, cal, cal, plus);
		else if (boxes[0].isSelected() && boxes[1].isSelected() && !boxes[2].isSelected()) tar = new TarifaPS(0, 1, cal, cal, plus);
		else if (boxes[0].isSelected() && !boxes[1].isSelected() && boxes[2].isSelected()) tar = new TarifaPM(0, 1, cal, cal, plus);
		else if (!boxes[0].isSelected() && boxes[1].isSelected() && boxes[2].isSelected()) tar = new TarifaSM(0, 1, cal, cal, plus);
		else tar = new TarifaPremium(0, 0, cal, cal, plus);
		
		return tar; 
	}
}

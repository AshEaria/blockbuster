/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Jaime
 *
 */
public class MenuPagar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton efectivo;
	private JButton tarjeta;
	private JButton atrasP;
	private JButton atrasT;
	private JButton aceptarT;
	private JTextField cajaNumero;
	private JPasswordField cajaContra;

	public MenuPagar(double precio) {
		
		/* Crear paneles */
		JPanel pantPrecio = new JPanel();
		JPanel pantTarjeta = new JPanel();
		
		JPanel centradorP = new JPanel();
		JPanel botoneraP = new JPanel();
		JPanel panelPrecio = new JPanel();
		
		JPanel contenidoT = new JPanel();
		JPanel datosTarjT = new JPanel();
		JPanel botoneraT = new JPanel();
		
		/* Se asigna un layout a cada panel */
		this.setLayout(new CardLayout());
		pantPrecio.setLayout(new BorderLayout());
		pantTarjeta.setLayout(new GridBagLayout());
		
		centradorP.setLayout(new GridBagLayout());
		//botoneraP.setLayout(new FlowLayout());
		panelPrecio.setLayout(new GridBagLayout());
		
		contenidoT.setLayout(new BorderLayout());
		datosTarjT.setLayout(new GridBagLayout());
		// botoneraT.setLayout(new FlowLayout());
		
		
		/* Crear la pantalla de precio */
		
		JLabel titTotal = new JLabel("TOTAL:");
		titTotal.setFont(titTotal.getFont().deriveFont(24f));
		JLabel cantPrecio = new JLabel(precio + " €");
		cantPrecio.setFont(cantPrecio.getFont().deriveFont(30f));
		efectivo = new JButton("Pagar en efectivo");
		efectivo.setActionCommand("Efectivo");
		tarjeta = new JButton("Pagar con tarjeta");
		tarjeta.setActionCommand("Tarjeta");
		atrasP = new JButton("Atras");
		atrasP.setActionCommand("AtrasPrecio");

		GridBagConstraints c = new GridBagConstraints();
		
		c.weighty = 0.2;
		panelPrecio.add(titTotal, c);
		c.gridy++;
		panelPrecio.add(cantPrecio, c);
		c.gridy++;
		panelPrecio.add(Box.createVerticalStrut(5), c);
		c.gridy++;
		panelPrecio.add(efectivo, c);
		c.gridy++;
		panelPrecio.add(tarjeta, c);
		centradorP.add(panelPrecio);
		pantPrecio.add(centradorP, BorderLayout.CENTER);
		
		botoneraP.add(atrasP);
		pantPrecio.add(botoneraP, BorderLayout.SOUTH);
		
		this.add(pantPrecio, "PRECIO");
		
		
		/* Crear la pantalla de pago con tarjeta */
		
		JLabel titTarjeta = new JLabel("Introducir datos de tarjeta");
		titTarjeta.setFont(titTarjeta.getFont().deriveFont(24f));
		JLabel labNumero = new JLabel("Numero de tarjeta: ");
		cajaNumero = new JTextField(20);
		JLabel labContra = new JLabel("Contrasena: "); 
		cajaContra = new JPasswordField(6);
		
		atrasT = new JButton("Atras");
		atrasT.setActionCommand("AtrasT");
		aceptarT = new JButton("Aceptar");
		aceptarT.setActionCommand("AceptarT");
		
		contenidoT.add(titTarjeta, BorderLayout.NORTH);
		
		c.weighty = 0; 
		c.weightx = 0.2; 
		c.anchor = GridBagConstraints.LAST_LINE_END; 
		c.gridx = 0; 
		c.gridy = 0; 
		datosTarjT.add(Box.createVerticalStrut(20), c);
		c.gridy++;
		c.weighty = 0.2;
		datosTarjT.add(labNumero, c);
		c.gridx++;
		c.weightx = 0;
		datosTarjT.add(Box.createHorizontalStrut(5), c);
		c.gridx++;
		c.anchor = GridBagConstraints.LAST_LINE_START; 
		c.weightx = 0.2;
		datosTarjT.add(cajaNumero, c); 
		c.gridx = 0; 
		c.gridy++;
		c.weighty = 0; 
		datosTarjT.add(Box.createVerticalStrut(5), c); 
		c.anchor = GridBagConstraints.FIRST_LINE_END; 
		c.gridy++; 
		c.weighty = 0.2; 
		datosTarjT.add(labContra, c);
		c.gridx++;
		c.weightx = 0; 
		datosTarjT.add(Box.createHorizontalStrut(5), c);
		c.gridx++; 
		c.weightx = 0.2; 
		c.anchor = GridBagConstraints.FIRST_LINE_START; 
		datosTarjT.add(cajaContra, c);
		c.gridy++; 
		datosTarjT.add(Box.createVerticalStrut(20), c);
		
		contenidoT.add(datosTarjT);
		
		botoneraT.add(atrasT);
		botoneraT.add(Box.createHorizontalStrut(10));
		botoneraT.add(aceptarT);
		contenidoT.add(botoneraT, BorderLayout.SOUTH);
		
		pantTarjeta.add(contenidoT);
		
		this.add(pantTarjeta, "TARJETA");
		
		((CardLayout) this.getLayout()).show(this, "PRECIO");
		
	}
	
	public void setControlador(ActionListener c, ActionListener parent) {
		efectivo.addActionListener(c);
		tarjeta.addActionListener(c);
		atrasP.addActionListener(parent);
		atrasT.addActionListener(c);
		aceptarT.addActionListener(c);
	}
	
	public void borrarCamposTarjeta() {
		cajaNumero.setText("");
		cajaContra.setText("");
	}
	
	public String getNumTarjeta() {
		return cajaNumero.getText();
	}
	
	public char[] getContraTarjeta() {
		return cajaContra.getPassword();
	}
}

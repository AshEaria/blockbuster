/**
 * 
 */
package vistas;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import socios.Socio;

/**
 * Esta clase modeliza el menu de busqueda de socio. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class MenuBuscarSocio extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton aceptar;
	private JButton atras;
    private JTextField Tnombre;
    private JTextField Tdni;
    private JTextField Ttelefono;
	private JTextField Temail;
	private JTextField Tdireccion;
	
	public MenuBuscarSocio(Socio socio) {
		

		/* PANTALLA PRINCIPAL*/
		
		/*Crear componentes*/
		JLabel Ltitulo = new JLabel("Socio #" + socio.getNumSocio()); 
		JLabel Lnombre =new JLabel("(*)Nombre:");
		Lnombre.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Ldni = new JLabel("(*)DNI:");
		Ldni.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Ltelefono = new JLabel("Teléfono:");
		Ltelefono.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Lemail = new JLabel("e-mail:");
		Lemail.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
	    JLabel Ldireccion = new JLabel("Dirección:");
	    Ldireccion.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
	    @SuppressWarnings("unused")
		JLabel LObligatorio = new JLabel("Los campos marcados con (*) son obligatorios.");
	
		Tnombre= new JTextField("" + socio.getNombre());	
		Tdni= new JTextField("" + socio.getDni());	
		Ttelefono= new JTextField("" + socio.getTelefono());	
		Temail= new JTextField("" + socio.getEmail());
		Tdireccion= new JTextField("" + socio.getDireccion());
		
		ImageIcon imAceptar = new ImageIcon("Aceptar.png");
		aceptar = new JButton(imAceptar);	
		aceptar.setBorder(null);	
		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);
		
		/*Modificar componentes*/
		Ltitulo.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		Ltitulo.setFont(new Font("Georgia", Font.CENTER_BASELINE, 30));
		
		/*Asignar nombres para Action Listener*/
		aceptar.setActionCommand("modoSocioCreado");
		atras.setActionCommand("modoEmpleado");

		/*Crear paneles*/
		
		JPanel columna1 = new JPanel();
		JPanel columna2 = new JPanel();
		JPanel columna = new JPanel();
		JPanel pSocio = new JPanel();
		JPanel panel = new JPanel();
		JPanel Paceptar = new JPanel();
		JPanel Patras = new JPanel();
		
		/*Crear paneles para cada JTextField*/
		JPanel PTnombre = new JPanel();
		JPanel PTdni = new JPanel();
		JPanel PTtelefono = new JPanel();
		JPanel PTemail = new JPanel();
		JPanel PTdireccion = new JPanel();
		
			
		/*Modoficar paneles*/
		PTnombre.setLayout(new BoxLayout(PTnombre, BoxLayout.LINE_AXIS));
		PTdni.setLayout(new BoxLayout(PTdni, BoxLayout.LINE_AXIS));
		PTtelefono.setLayout(new BoxLayout(PTtelefono, BoxLayout.LINE_AXIS));
		PTemail.setLayout(new BoxLayout(PTemail, BoxLayout.LINE_AXIS));
		PTdireccion.setLayout(new BoxLayout(PTdireccion, BoxLayout.LINE_AXIS));
		
		columna1.setLayout(new BoxLayout(columna1, BoxLayout.Y_AXIS));	
		columna2.setLayout(new BoxLayout(columna2, BoxLayout.Y_AXIS));
		columna.setLayout(new BoxLayout(columna, BoxLayout.X_AXIS));
		Paceptar.setLayout(new BoxLayout(Paceptar, BoxLayout.Y_AXIS));
		Patras.setLayout(new BoxLayout(Patras, BoxLayout.Y_AXIS));
		pSocio.setLayout(new BoxLayout(pSocio, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		PTnombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 15));
		PTdni.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 60));
		PTtelefono.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 15));
		PTemail.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 60));	
		PTdireccion.setBorder(BorderFactory.createEmptyBorder(10, 5, 40, 15));	
		
		columna.setBorder(BorderFactory.createEmptyBorder(1, 25, 25, 25));
		Paceptar.setBorder(BorderFactory.createEmptyBorder(250, 1, 0, 1));
		Patras.setBorder(BorderFactory.createEmptyBorder(250, 1, 0, 1));

		/*Fondo de la aplicacion*/
		//this.setBackground(new Color(0xBCF5A9));
		
		
		/* Añadir componentes sus respectivos paneles */
		PTnombre.add(Tnombre);
		PTdni.add(Tdni);
		PTtelefono.add(Ttelefono);
		PTemail.add(Temail);
		PTdireccion.add(Tdireccion);
		
		columna2.add(PTnombre);
		columna2.add(PTdni);
		columna2.add(PTtelefono);
		columna2.add(PTemail);
		columna2.add(PTdireccion);
		
		columna1.add(Lnombre);
		columna1.add(Ldni);
		columna1.add(Ltelefono);
		columna1.add(Lemail);
		columna1.add(Ldireccion);
		columna.add(columna1);
		columna.add(columna2);
		
		pSocio.add(Ltitulo);
		pSocio.add(columna);
		Paceptar.add(aceptar);
		Patras.add(atras);
		panel.add(Patras);
		panel.add(pSocio);
		panel.add(Paceptar);
		
		/*Anadir el panel PRINCIPAL al cardLAyout PRINCIPAL*/
		this.add(panel, "PNuevoSocio");
	
	}
	
	public JTextField getCajaNombre() {
		return Tnombre;
	}

	public JTextField getCajaDNI() {
		return Tdni;
	}

	public JTextField getCajaTelefono() {
		return Ttelefono;
	}

	public JTextField getCajaEmail() {
		return Temail;
	}

	public JTextField getCajaDireccion() {
		return Tdireccion;
	}	
	
	/** 
	 * Limpia los campos del formulario. 
	 */
	public void limpiaCampos() {
		Tnombre.setText(null);
		Tdni.setText(null);
		Tdireccion.setText(null);
		Temail.setText(null);
		Ttelefono.setText(null);
	}

	
	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {
		
		atras.addActionListener(c);
		aceptar.addActionListener(c);
	}
	
}

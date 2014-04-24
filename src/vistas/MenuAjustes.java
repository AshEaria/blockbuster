/**
 * 
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

import socios.Tarifa;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaPremium;
import socios.TarifaSM;
import socios.TarifaSerie;
import aplicacion.Ajustes;

/**
 * @author elena
 * 
 */
public class MenuAjustes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton atras;
	private JButton atrasAju;
	private JButton confirmar1;
	private JButton confirmarT;
	/* Botones menu */
	private JButton Agenerales;
	private JButton Aprestamo;
	private JButton Atarifa;

	/* Paneles */
	private JPanel panel;
	private JPanel botones;
	private JPanel Patras;
	
	/* Campos Agenerales */
	private JTextField nombre;
	private JPasswordField cActualT;
	private JPasswordField cNuevaT;
	
	/* Campos Atarifa */
	private JTextField cPeliculas; 
	private JTextField cSeries; 
	private JTextField cMusica; 
	private JTextField cPS; 
	private JTextField cPM; 
	private JTextField cSM; 
	private JTextField cPremium; 
	private JTextField cPlus;

	public MenuAjustes() {

		/* Menu reponer */
		this.setLayout(new CardLayout());

		/* Crear componentes */

		/* Botones */
		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);
		
		ImageIcon imAtras2 = new ImageIcon("atras.png");
		atrasAju = new JButton(imAtras2);
		atrasAju.setBorder(null);
		

		/* Componentes MENU INICIAL */

		ImageIcon imP = new ImageIcon("Agenerales.png");
		Agenerales = new JButton(imP);
		Agenerales.setBorder(null);

		ImageIcon imM = new ImageIcon("Aprestamo.png");
		Aprestamo = new JButton(imM);
		Aprestamo.setBorder(null);

		ImageIcon imS = new ImageIcon("Atarifa.png");
		Atarifa = new JButton(imS);
		Atarifa.setBorder(null);

		/* Modificar componentes */

		/* Asignar nombres para Action Listener */
		atrasAju.setActionCommand("modoAjustes");
		atras.setActionCommand("modoGerente");
		Agenerales.setActionCommand("modoAgenerales");
		Aprestamo.setActionCommand("modoAprestamo");
		Atarifa.setActionCommand("modoAtarifa");

		/* Crear paneles */

		/* Inicializacion */
		panel = new JPanel();
		botones = new JPanel();
		Patras = new JPanel();

		/* Modificacion */
		// botones.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Patras.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 550));
		//botones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		botones.add(Agenerales);
		botones.add(Aprestamo);
		botones.add(Atarifa);
		Patras.add(atras);
		panel.add(Patras, BorderLayout.NORTH);
		panel.add(botones);

		/* Anadir el panel PRINCIPAL al cardLAyout PRINCIPAL */
		this.add(panel, "PAjustes");
		this.add(ajustesGenerales(), "modoAgenerales");
		this.add(ajustesTarifa(), "modoAtarifa");
		
		/* Mostrar menu inicialmente */
		((CardLayout) this.getLayout()).show(this, "PAjustes");

	}

	/* Panel AJUSTES GENERALES */

	public JPanel ajustesGenerales() {
		/* Inicializacion de componentes */

		/* Texto */
		JLabel titulo1 = new JLabel("Cambio de nombre del videoblub");
		JLabel titulo2 = new JLabel("Cambio de contrasena");
		JLabel nActual = new JLabel(Ajustes.getInstance().getNombreVideoclub());
		JLabel nNuevo = new JLabel("Nombre nuevo:");
		JLabel cActual = new JLabel("Contrasena antigua:");
		JLabel cNueva = new JLabel("Contrasena nueva:");
		
		/* Botones */
		
		ImageIcon imConf = new ImageIcon("confirmar.png");
		confirmar1 = new JButton(imConf);
		confirmar1.setBorder(null);

		/* Campos de texto */
		nombre = new JTextField(10);
		cActualT = new JPasswordField(10);
		cNuevaT = new JPasswordField(10);

		/* Paneles */
		JPanel Pnombre = new JPanel();
		JPanel Pcontrasena = new JPanel();
		JPanel Pcentro = new JPanel();
		JPanel panel = new JPanel();
		
		/* Asignar nombres para Action Listener */
		confirmar1.setActionCommand("modoConfirmar1");

		/* Modificar componentes */
	
		titulo1.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		titulo1.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));

		titulo2.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 0));
		titulo2.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
		nActual.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		nActual.setFont(new Font("Georgia", 40, 40));
		nNuevo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		cActual.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
		cNueva.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	    Pcentro.setBorder(BorderFactory.createEmptyBorder(25, 25, 5, 55));
	    confirmar1.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
	
		Pnombre.setLayout(new BoxLayout(Pnombre, BoxLayout.Y_AXIS));
		Pcontrasena.setLayout(new BoxLayout(Pcontrasena, BoxLayout.Y_AXIS));
		Pcentro.setLayout(new BoxLayout(Pcentro, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		/* Anadir componentes a sus respectivos paneles */
		Pnombre.add(titulo1);
		Pnombre.add(nActual);
		Pnombre.add(nNuevo);
		Pnombre.add(nombre);
		Pnombre.add(confirmar1);

		Pcontrasena.add(titulo2);
		Pcontrasena.add(cActual);
		Pcontrasena.add(cActualT);
		Pcontrasena.add(cNueva);
		Pcontrasena.add(cNuevaT);
		Pcontrasena.add(confirmar1);

		Pcentro.add(Pnombre);
		Pcentro.add(Pcontrasena);

		panel.add(atrasAju);
		panel.add(Pcentro);
		
		return panel;

	}
	
	public JPanel ajustesTarifa() {
		/* Inicializacion de componentes: Tarifas dummy */
		Calendar cal = Calendar.getInstance();
		TarifaPelicula p = new TarifaPelicula(0, 0, cal, cal, false);
		TarifaSerie s = new TarifaSerie(0, 0, cal, cal, false);
		TarifaMusica m = new TarifaMusica(0, 0, cal, cal, false);
		TarifaPM pm = new TarifaPM(0, 0, cal, cal, false);
		TarifaPS ps = new TarifaPS(0, 0, cal, cal, false);
		TarifaSM sm = new TarifaSM(0, 0, cal, cal, false);
		TarifaPremium pre = new TarifaPremium(0, 0, cal, cal, false);

		/* Texto */
		JLabel titPrecio = new JLabel("Precios de tarifas planas");
		JLabel titulo1 = new JLabel("Sencilla");
		JLabel titulo2 = new JLabel("Combinada");
		JLabel titulo3 = new JLabel("Premium");
		JLabel[] eurosMes = new JLabel[8];
		for (int i = 0; i < 8; i++) {
			eurosMes[i] = new JLabel(" €/mes");
		}
		JLabel peliculas = new JLabel("Peliculas ");
		JLabel series = new JLabel("Series ");
		JLabel musica = new JLabel("Musica "); 
		JLabel Cps = new JLabel("Peliculas + Series ");
		JLabel Cpm = new JLabel("Peliculas + Musica "); 
		JLabel Csm = new JLabel("   Musica + Series ");
		JLabel plus = new JLabel("Tarifa Plus"); 
		
		/* Botones */
		
		ImageIcon imConf = new ImageIcon("confirmar.png");
		confirmarT = new JButton(imConf);
		confirmarT.setBorder(null);

		/* Campos de texto */
		cPeliculas = new JTextField("" + p.getPrecio()); 
		cSeries = new JTextField("" + s.getPrecio()); 
		cMusica = new JTextField("" + m.getPrecio()); 
		cPS = new JTextField("" + ps.getPrecio()); 
		cPM = new JTextField("" + pm.getPrecio()); 
		cSM = new JTextField("" + sm.getPrecio()); 
		cPremium = new JTextField("" + pre.getPrecio()); 
		cPlus = new JTextField("" + Tarifa.getExtraPlus());

		/* Paneles */
		JPanel Ptit = new JPanel(); 
		JPanel Ps1 = new JPanel(); 
		JPanel Ps2 = new JPanel(); 
		JPanel Ps3 = new JPanel(); 
		JPanel Psencilla = new JPanel(); 
		JPanel Pc1 = new JPanel(); 
		JPanel Pc2 = new JPanel(); 
		JPanel Pc3 = new JPanel(); 
		JPanel Pcombinada = new JPanel();
		JPanel Psc = new JPanel(); 
		JPanel Ppremium = new JPanel();
		JPanel Pplus = new JPanel();
		JPanel Pconfirmar = new JPanel(); 
		JPanel Pcentro = new JPanel();
		JPanel panel = new JPanel();
		
		/* Asignar nombres para Action Listener */
		confirmarT.setActionCommand("modoConfirmar2");

		/* Modificar componentes */
		
		titPrecio.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		titPrecio.setFont(new Font("Georgia", 40, 40));
		
		titulo1.setBorder(BorderFactory.createEmptyBorder(20, 0, 25, 47));
		titulo1.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));

		titulo2.setBorder(BorderFactory.createEmptyBorder(20, 0, 25, 80));
		titulo2.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
		titulo3.setBorder(BorderFactory.createEmptyBorder(20, 15, 15, 15));
		titulo3.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
		plus.setBorder(BorderFactory.createEmptyBorder(5, 15, 20, 15));
		plus.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
	    Pcentro.setBorder(BorderFactory.createEmptyBorder(25, 25, 5, 55));
	    confirmarT.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

	    Ptit.setLayout(new GridBagLayout());
		Psencilla.setLayout(new BoxLayout(Psencilla, BoxLayout.Y_AXIS));
		Pcombinada.setLayout(new BoxLayout(Pcombinada, BoxLayout.Y_AXIS));
	    Psc.setLayout(new BoxLayout(Psc, BoxLayout.X_AXIS));
		Pcentro.setLayout(new BoxLayout(Pcentro, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		/* Anadir componentes a sus respectivos paneles */
		
		Ptit.add(titPrecio);

		Ps1.add(peliculas);
		Ps1.add(cPeliculas);
		Ps1.add(eurosMes[0]);
		
		Ps2.add(series);
		Ps2.add(cSeries);
		Ps2.add(eurosMes[1]);
		
		Ps3.add(musica);
		Ps3.add(cMusica); 
		Ps3.add(eurosMes[2]);
		
		Psencilla.add(titulo1);
		Psencilla.add(Ps1); 
		Psencilla.add(Ps2); 
		Psencilla.add(Ps3); 
		
		Pc1.add(Cps);
		Pc1.add(cPS);
		Pc1.add(eurosMes[3]);
		
		Pc2.add(Cpm);
		Pc2.add(cPM);
		Pc2.add(eurosMes[4]);
		
		Pc3.add(Csm);
		Pc3.add(cSM); 
		Pc3.add(eurosMes[5]);
		
		Pcombinada.add(titulo2); 
		Pcombinada.add(Pc1); 
		Pcombinada.add(Pc2);
		Pcombinada.add(Pc3);
		
		Psc.add(Psencilla);
		Psc.add(Pcombinada);
		
		Ppremium.add(titulo3);
		Ppremium.add(cPremium);
		Ppremium.add(eurosMes[6]);
		
		Pplus.add(plus); 
		Pplus.add(cPlus); 
		Pplus.add(eurosMes[7]);

		Pconfirmar.add(confirmarT);
		
		Pcentro.add(Ptit);
		Pcentro.add(Psc);
		Pcentro.add(Ppremium); 
		Pcentro.add(Pplus);
		Pcentro.add(Pconfirmar);

		panel.add(atrasAju);
		panel.add(Pcentro);
		
		return panel;
	}

	public JTextField getCajaNnuevo(){
		return nombre;	
	}
	
	public JTextField getCajacNueva(){
		return cNuevaT;	
	}
	
	public JTextField getCajacAntigua(){
		return cActualT;	
	}
	
	public JTextField[] getPreciosTarifas(){
		JTextField[] precios = { cPeliculas, cSeries, cMusica, cPS, cPM, cSM, cPremium, cPlus };
		return precios; 
	}
	
	// metodo para asignar un controlador al boton
	public void setControlador(ActionListener c) {

		confirmar1.addActionListener(c);
		confirmarT.addActionListener(c);
		atrasAju.addActionListener(c);
		atras.addActionListener(c);
		Agenerales.addActionListener(c);
		Aprestamo.addActionListener(c);
		Atarifa.addActionListener(c);
	}

}

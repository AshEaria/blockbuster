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
import catalogo.Pelicula;
import catalogo.Serie;
import catalogo.Musica;
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
	private JButton atrasAju1;
	private JButton atrasAju2;
	private JButton atrasAju3;
	private JButton confirmarG;
	private JButton confirmarT;
	private JButton confirmarP; 
	
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
	
	/* Campos Aprestamo */
	private JTextField pPeliculas; 
	private JTextField pSeries; 
	private JTextField pMusica; 
	private JTextField pDias; 
	private JTextField pDiasPlus; 
	private JTextField pPenaDias;
	private JTextField pPenaPrec1; 
	private JTextField pPenaPrec2; 
	private JTextField pAlqs;
	
	

	public MenuAjustes() {

		/* Menu reponer */
		this.setLayout(new CardLayout());

		/* Crear componentes */

		/* Botones */
		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);
		
		ImageIcon imAtras2 = new ImageIcon("atras.png");
		atrasAju1 = new JButton(imAtras2);
		atrasAju1.setBorder(null);
		atrasAju2 = new JButton(imAtras2);
		atrasAju2.setBorder(null);
		atrasAju3 = new JButton(imAtras2);
		atrasAju3.setBorder(null);
		

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
		atrasAju1.setActionCommand("modoAjustes");
		atrasAju2.setActionCommand("modoAjustes");
		atrasAju3.setActionCommand("modoAjustes");
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
		this.add(ajustesPrestamo(), "modoAprestamo");
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
		confirmarG = new JButton(imConf);
		confirmarG.setBorder(null);

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
		confirmarG.setActionCommand("modoConfirmar1");

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
	    confirmarG.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
	
		Pnombre.setLayout(new BoxLayout(Pnombre, BoxLayout.Y_AXIS));
		Pcontrasena.setLayout(new BoxLayout(Pcontrasena, BoxLayout.Y_AXIS));
		Pcentro.setLayout(new BoxLayout(Pcentro, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		/* Anadir componentes a sus respectivos paneles */
		Pnombre.add(titulo1);
		Pnombre.add(nActual);
		Pnombre.add(nNuevo);
		Pnombre.add(nombre);
		Pnombre.add(confirmarG);

		Pcontrasena.add(titulo2);
		Pcontrasena.add(cActual);
		Pcontrasena.add(cActualT);
		Pcontrasena.add(cNueva);
		Pcontrasena.add(cNuevaT);
		Pcontrasena.add(confirmarG);

		Pcentro.add(Pnombre);
		Pcentro.add(Pcontrasena);

		panel.add(atrasAju1);
		panel.add(Pcentro);
		
		return panel;

	}
	
	public JPanel ajustesPrestamo() {
		/* Inicializacion de componentes: Ajustes y Articulos dummy*/
		Ajustes ajustes = Ajustes.getInstance();
		Pelicula p = new Pelicula(0, "", 0, "", 0);
		Serie s = new Serie(0, "", 0, 0, 0);
		Musica m = new Musica(0, "", 0, "", 0);

		/* Texto */
		JLabel titAjustes = new JLabel("Ajustes de prestamos");
		JLabel titulo1 = new JLabel("Precios por uso");
		JLabel titulo2 = new JLabel("Penalizaciones por retraso");
		JLabel titulo3 = new JLabel("Dias de alquiler");
		JLabel[] eurosMes = { new JLabel(" €/ud."), new JLabel(" €/ud."), new JLabel(" €/ud."), new JLabel(" €/dia"), new JLabel(" dias"), new JLabel(" €/dia"), new JLabel(" dias"), new JLabel(" dias"), new JLabel(" articulos") };
		JLabel peliculas = new JLabel("Peliculas: ");
		JLabel series = new JLabel("Series: ");
		JLabel musica = new JLabel("Musica: "); 
		JLabel prec1 = new JLabel("Penalizacion de primer nivel: ");
		JLabel diasPena = new JLabel("Dias hasta cambio de nivel: "); 
		JLabel prec2 = new JLabel("Penalizacion de segundo nivel: ");
		JLabel sinPlus = new JLabel("Sin tarifa Plus: ");
		JLabel plus = new JLabel("Con tarifa plus: "); 
		JLabel maxAlq = new JLabel("Maximo de alquileres simultaneos: ");
		
		/* Botones */
		
		ImageIcon imConf = new ImageIcon("confirmar.png");
		confirmarP = new JButton(imConf);
		confirmarP.setBorder(null);

		/* Campos de texto */
		pPeliculas = new JTextField("" + p.getPrecio()); 
		pSeries = new JTextField("" + s.getPrecio()); 
		pMusica = new JTextField("" + m.getPrecio()); 
		pPenaPrec1 = new JTextField("" + ajustes.getPenalRetraso1());
		pPenaDias = new JTextField("" + ajustes.getDiasNivelRetraso());
		pPenaPrec2 = new JTextField("" + ajustes.getPenalRetraso2());   
		pDias = new JTextField("" + ajustes.getDias()); 
		pDiasPlus = new JTextField("" + ajustes.getDiasPlus()); 
		pAlqs = new JTextField("" + ajustes.getMaxArticulos());

		/* Paneles */
		JPanel Ptit = new JPanel(); 
		JPanel Ps1 = new JPanel(); 
		JPanel Ps2 = new JPanel(); 
		JPanel Ps3 = new JPanel(); 
		JPanel Pprecios = new JPanel(); 
		JPanel Pc1 = new JPanel(); 
		JPanel Pc2 = new JPanel(); 
		JPanel Pc3 = new JPanel(); 
		JPanel Ppenalizacion = new JPanel();
		JPanel Ppp = new JPanel(); 
		JPanel PpDias = new JPanel();
		JPanel Pplus = new JPanel();
		JPanel Pconfirmar = new JPanel(); 
		JPanel Pcentro = new JPanel();
		JPanel panel = new JPanel();
		
		/* Asignar nombres para Action Listener */
		confirmarP.setActionCommand("modoConfirmar2");

		/* Modificar componentes */
		
		titAjustes.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		titAjustes.setFont(new Font("Georgia", 40, 40));
		
		titulo1.setBorder(BorderFactory.createEmptyBorder(20, 0, 25, 47));
		titulo1.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));

		titulo2.setBorder(BorderFactory.createEmptyBorder(20, 0, 25, 80));
		titulo2.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
		titulo3.setBorder(BorderFactory.createEmptyBorder(20, 15, 7, 15));
		titulo3.setFont(new Font("Georgia", Font.HANGING_BASELINE, 30));
		
	    Pcentro.setBorder(BorderFactory.createEmptyBorder(25, 25, 5, 55));
	    confirmarP.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

	    Ptit.setLayout(new GridBagLayout());
		Pprecios.setLayout(new BoxLayout(Pprecios, BoxLayout.Y_AXIS));
		Ppenalizacion.setLayout(new BoxLayout(Ppenalizacion, BoxLayout.Y_AXIS));
	    Ppp.setLayout(new BoxLayout(Ppp, BoxLayout.X_AXIS));
		Pcentro.setLayout(new BoxLayout(Pcentro, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		/* Anadir componentes a sus respectivos paneles */
		
		Ptit.add(titAjustes);

		Ps1.add(peliculas);
		Ps1.add(pPeliculas);
		Ps1.add(eurosMes[0]);
		
		Ps2.add(series);
		Ps2.add(pSeries);
		Ps2.add(eurosMes[1]);
		
		Ps3.add(musica);
		Ps3.add(pMusica); 
		Ps3.add(eurosMes[2]);
		
		Pprecios.add(titulo1);
		Pprecios.add(Ps1); 
		Pprecios.add(Ps2); 
		Pprecios.add(Ps3); 
		
		Pc1.add(prec1);
		Pc1.add(pPenaPrec1);
		Pc1.add(eurosMes[3]);
		
		Pc2.add(diasPena);
		Pc2.add(pPenaDias);
		Pc2.add(eurosMes[4]);
		
		Pc3.add(prec2);
		Pc3.add(pPenaPrec2); 
		Pc3.add(eurosMes[5]);
		
		Ppenalizacion.add(titulo2); 
		Ppenalizacion.add(Pc1); 
		Ppenalizacion.add(Pc2);
		Ppenalizacion.add(Pc3);
		
		Ppp.add(Pprecios);
		Ppp.add(Ppenalizacion);
		
		PpDias.add(titulo3);
		
		Pplus.add(sinPlus);
		Pplus.add(pDias);
		Pplus.add(eurosMes[6]);
		Pplus.add(Box.createHorizontalStrut(40));
		Pplus.add(plus); 
		Pplus.add(pDiasPlus); 
		Pplus.add(eurosMes[7]);
		Pplus.add(Box.createHorizontalStrut(60));
		Pplus.add(maxAlq);
		Pplus.add(pAlqs);
		Pplus.add(eurosMes[8]);

		Pconfirmar.add(confirmarP);
		
		Pcentro.add(Ptit);
		Pcentro.add(Ppp);
		Pcentro.add(PpDias); 
		Pcentro.add(Pplus);
		Pcentro.add(Pconfirmar);

		panel.add(atrasAju2);
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
		JLabel peliculas = new JLabel("Peliculas: ");
		JLabel series = new JLabel("Series: ");
		JLabel musica = new JLabel("Musica: "); 
		JLabel Cps = new JLabel("Peliculas + Series: ");
		JLabel Cpm = new JLabel("Peliculas + Musica: "); 
		JLabel Csm = new JLabel("   Musica + Series: ");
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
		confirmarT.setActionCommand("modoConfirmar3");

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

		panel.add(atrasAju3);
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
	
	public JTextField[] getCajasPrestamo() {
		JTextField[] cajas = { pPeliculas, pSeries, pMusica, pPenaPrec1, pPenaDias, pPenaPrec2, pDias, pDiasPlus, pAlqs };
		return cajas; 
	}
	
	// metodo para asignar un controlador al boton
	public void setControlador(ActionListener c) {

		confirmarG.addActionListener(c);
		confirmarT.addActionListener(c);
		confirmarP.addActionListener(c);
		atrasAju1.addActionListener(c);
		atrasAju2.addActionListener(c);
		atrasAju3.addActionListener(c);
		atras.addActionListener(c);
		Agenerales.addActionListener(c);
		Aprestamo.addActionListener(c);
		Atarifa.addActionListener(c);
	}

}

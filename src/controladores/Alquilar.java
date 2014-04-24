/**
 * 
 */
package controladores;

import vistas.MenuAlquilar;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.Ajustes;
import aplicacion.Aplicacion;
import catalogo.Articulo;
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;
import socios.EstadoSocio;
import socios.ListaAlquileres;
import socios.ListaSocios;
import socios.Socio;
import socios.Tarifa;

/**
 * Esta clase es el controlador de la opcion de alquiler de articulos. 
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class Alquilar extends Opcion implements OpcionConPago {
	private MenuAlquilar vista;
	private ListaSocios socios;
	private ListaEjemplares ejemplares;
	private Ajustes ajustes;
	private ListaAlquileres alquileres; 
	private ArrayList<Ejemplar> ejemAlquilando;
	private Socio socio;
	private Pagar cPagar;
	
	/* CONSTRUCTOR */
	
	/** 
	 * Este constructor crea el panel del menu de alquiler segun el numero de socio que pide por dialogo. 
	 */
	public Alquilar() {
		
		/* Accedemos a los datos de la base de datos */
		socios = ListaSocios.getInstance();
		ejemplares = ListaEjemplares.getInstance();
		ajustes = Ajustes.getInstance();
		alquileres = ListaAlquileres.getInstance();
		
		int numSocio;
		Socio socio = null;
		boolean numOK = false;
		
		while(numOK == false) {
			String input = JOptionPane.showInputDialog("Numero de socio: "); 
			if (input == null) {
				break;
			}
			try {
				numSocio = Integer.parseInt(input);
			}
			catch (NumberFormatException ex) {
				String str = ("El numero de socio no ha sido bien introducido.");
				JOptionPane.showMessageDialog(null, str);
				continue;
			}
			socio = socios.encuentra(numSocio);
			if (socio == null) {
				String str = ("El socio " + numSocio + " no existe. ");
				JOptionPane.showMessageDialog(null, str);
				continue;
			} else if (socio.getEstadoSocio() != EstadoSocio.SIN_ALQUILERES) {
				String str = ("El socio " + numSocio + " ya tiene articulos alquilados.\nNo se pueden alquilar mas articulos hasta que se devuelvan los alquilados anteriormente. ");
				JOptionPane.showMessageDialog(null, str);
				continue;
			} else numOK = true;
		}
		
		if(numOK == true) {
			this.socio = socio;
			/* Creamos la vista */
			vista = new MenuAlquilar(socio);
			/* Asociamos controlador a la vista */
			((MenuAlquilar) vista).setControlador(this);
		} else vista = null;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		
		if (boton.getActionCommand().equals("Cancelar")) {
			Aplicacion.vuelveMenuEmpleado();
		} else if (boton.getActionCommand().equals("Atras")) {
			((CardLayout) vista.getSelector().getLayout()).show(vista.getSelector(), "CAJAS");
		} else if (boton.getActionCommand().equals("Buscar Articulos")) {
			buscarArticulos();
		} else if (boton.getActionCommand().equals("Pagar")) {
			double precio = calcularPrecio();
			if (precio != 0) {
				cPagar = new Pagar(precio, this);
				vista.add(cPagar.getVista(), "PAGAR");
				((CardLayout) vista.getLayout()).show(vista, "PAGAR");
			} else pagoOK();
		} else if (boton.getActionCommand().equals("AtrasPrecio")) {
			((CardLayout) vista.getLayout()).removeLayoutComponent(cPagar.getVista());
			cPagar = null;
			((CardLayout) vista.getLayout()).show(vista, "ARTICULOS");
		}
	}
	
	/**
	 * @see controladores.Opcion#getVista()
	 */
	public JPanel getVista() {
		return (JPanel) vista;
	}
	
	/**
	 * Transforma los numeros de articulo introducidos en los JTextField correspondientes en datos de articulo. 
	 */
	public void buscarArticulos() {
		ArrayList<String> cajas = vista.getCajas();
		String[][] datos = new String[ajustes.getMaxArticulos()][3];
		ejemAlquilando = new ArrayList<Ejemplar>();
		
		if (cajas == null) { 
			JOptionPane.showMessageDialog(vista, "Introduzca los numeros de articulo.");
			return;
		}
		
		/* Encontramos los datos de cada uno de los articulos */
		int i = 0;
		for (String c : cajas) {
			int numEjemplar;
			try {
				numEjemplar = Integer.parseInt(c);
			}
			catch (NumberFormatException ex) {
				String str = ("El numero del " + ((i == 0) ? "primer" : ((i == 1) ? "segundo" : "tercer")) + " ejemplar no ha sido bien introducido.");
				JOptionPane.showMessageDialog(vista, str);
				return;
			}
			Ejemplar ejem = ejemplares.encuentra(numEjemplar);
			if (ejem == null) {
				String str = ("El ejemplar " + numEjemplar + " no existe. ");
				JOptionPane.showMessageDialog(vista, str);
				return;
			}
			if (ejem.getEstadoEjem() != EstadoEjemplar.DISPONIBLE) {
				String str = ("El ejemplar " + numEjemplar + " no esta disponible para alquiler. ");
				JOptionPane.showMessageDialog(vista, str);
				return;
			}
			
			ejemAlquilando.add(ejem);
			
			if (ejem.getArticulo() instanceof Pelicula) datos[i] = escribeDatosPelicula(ejem);
			else if (ejem.getArticulo() instanceof Serie) datos[i] = escribeDatosSerie(ejem);
			else if (ejem.getArticulo() instanceof Musica) datos[i] = escribeDatosMusica(ejem);
			
			i++;
		}
		
		/* Rellenamos los articulos vacios con blancos */
		for (; i < ajustes.getMaxArticulos(); i++) {
			for (int j = 0; j < 3; j++) {
				datos[i][j] = "";
			}
		}
		
		vista.setDatos(datos);
		
		((CardLayout) vista.getSelector().getLayout()).show(vista.getSelector(), "DATOS");
		
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
	
	/**
	 * Calcula el precio de los alquileres segun la configuracion que se haya elegido en la vista. 
	 * 
	 * @return Precio total de la operacion. 
	 */
	public double calcularPrecio() {
		Tarifa tar = socio.getTarifa();
		double precioTotal = 0;
		
		for (Ejemplar e : ejemAlquilando) {
			Articulo a = e.getArticulo();
			if (tar != null) {
				if (!tar.cubre(a.getClass().getSimpleName())) {
					precioTotal += a.getPrecio();
				}
			} else precioTotal += a.getPrecio();
		}
		
		return precioTotal;
	}

	/**
	 * @see controladores.OpcionConPago#pagoOK()
	 */
	@Override
	public void pagoOK() {
		/* Alquilar todos los ejemplares */
		for (Ejemplar e : ejemAlquilando) {
			alquileres.crearAlquiler(e, socio);
		}
		JOptionPane.showMessageDialog(vista, "Operacion realizada correctamente.");
		
		/* Volver al menu inicial */
		Aplicacion.vuelveMenuEmpleado();
	}
}

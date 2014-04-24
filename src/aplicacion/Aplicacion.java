/**
 * 
 */
package aplicacion;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.Alquilar;
import controladores.BuscarArt;
import controladores.ContrAjustes;
import controladores.ContratarTarifa;
import controladores.DarAltaArt;
import controladores.Morosos;
import controladores.Devolver;
import controladores.NuevoSocio;
import controladores.Reponer;
import controladores.TopTen;
import catalogo.Articulo;
import catalogo.Catalogo;
import catalogo.Categoria;
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;
import socios.Alquiler;
import socios.EstadoSocio;
import socios.ListaAlquileres;
import socios.ListaContratos;
import socios.ListaSocios;
import socios.Socio;
import socios.Tarifa;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaPremium;
import socios.TarifaSM;
import socios.TarifaSerie;
import vistas.MenuEmpleado;
import vistas.MenuGerente;
import vistas.MenuLogin;

/**
 * Esta clase representa la aplicacion en si. Contiene todos los listados y actua de controlador para los menus de login, principal de gerente y principal de empleado. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Aplicacion implements ActionListener, WindowListener {
	private static Aplicacion INSTANCE = null;
	private static JPanel vista;
	private JTextField cajaContrasena;

	/* PATRON SINGLETON */

	private Aplicacion() {
		/* Inicializar la aplicacion */
		inicializaListas();

		/* Crear paneles */
		vista = new JPanel();
		vista.setLayout(new CardLayout());
		JPanel centradorVista = new JPanel();
		centradorVista.setLayout(new GridBagLayout());
		centradorVista.add(vista);

		MenuLogin mLogin = new MenuLogin();
		vista.add(mLogin, "PLogin");
		mLogin.setControlador(this);
		this.cajaContrasena = mLogin.getCajaContrasena();
		MenuGerente mGerente = new MenuGerente();
		vista.add(mGerente, "MenuGerente");
		mGerente.setControlador(this);
		MenuEmpleado mEmpleado = new MenuEmpleado();
		vista.add(mEmpleado, "MenuEmpleado");
		mEmpleado.setControlador(this);

		((CardLayout) vista.getLayout()).show(vista, "PLogin");

		/* Crear ventana */
		JFrame ventana = new JFrame(Ajustes.getInstance().getNombreVideoclub());
		ventana.setContentPane(centradorVista);
		ventana.getContentPane().setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		ventana.addWindowListener(this); /* Hacemos que esta clase escuche el cierre de la ventana */
		ventana.setSize(750, 700);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}

	/**
	 * Parte del patron Singleton. 
	 * 
	 * @return Instancia unica del objeto Aplicacion. 
	 */
	public static Aplicacion getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Aplicacion();
		return INSTANCE;
	}

	/* MAIN */

	/** 
	 * Punto de entrada al programa. Solo se encarga de activar la aplicacion. 
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Aplicacion apli = getInstance();
	}

	/**
	 * Main de prueba de la entrega anterior. 
	 * 
	 */
	public static void mainDePrueba(String[] args) {

		Aplicacion aplicacion = Aplicacion.getInstance();

		System.out.println("Pruebas de conjunto de la aplicacion: ");
		System.out.println();
		System.out.println("00. Recuperar la base de datos de los ficheros. ");
		System.out.println("01. Crear un nuevo articulo. ");
		System.out.println("02. Crear un ejemplar de ese articulo. ");
		System.out.println("03. Anadir el articulo a una categoria. ");
		System.out.println("04. Crear un nuevo socio. ");
		System.out.println("05. Contratar una tarifa para el socio. ");
		System.out.println("06. Hacer que este socio alquile el ejemplar. ");
		System.out.println("07. Devolver el ejemplar. ");
		System.out.println("08. Eliminar el socio, y con el la tarifa. ");
		System.out.println("09. Eliminar el ejemplar. ");
		System.out.println("10. Eliminar el articulo. ");
		System.out.println("11. Guardar la base de datos en archivo. ");
		System.out.println();
		System.out.println();
		System.out.println("Resultados:");
		System.out.println();

		boolean checker = false;

		checker = aplicacion.inicializaListas();

		if (checker == false)
			System.out.println("00: ERROR al inicializar la base de datos. ");
		else
			System.out.println("00: OK al inicializar la base de datos. ");

		Catalogo catalogo = Catalogo.getInstance();

		Articulo art = catalogo.encuentra(catalogo.crearSerie("Fringe", 3, 1));

		if (art == null)
			System.out.println("01: ERROR al crear un nuevo articulo. ");
		else
			System.out.println("01: OK al crear un nuevo articulo. ");

		ListaEjemplares listaEjemplares = ListaEjemplares.getInstance();

		Ejemplar ejem = listaEjemplares.encuentra(listaEjemplares
				.anadirEjemplar(art, false, EstadoEjemplar.DISPONIBLE));

		if (ejem == null)
			System.out.println("02: ERROR al crear un nuevo ejemplar. ");
		else
			System.out.println("02: OK al crear un nuevo ejemplar. ");

		checker = art.anadirCategoria("Ciencia ficcion");

		if (checker == false)
			System.out
					.println("03: ERROR al anadir articulo a una categoria. ");
		else
			System.out.println("03: OK al anadir articulo a una categoria. ");

		ListaSocios listaSocios = ListaSocios.getInstance();

		Socio soc = listaSocios.encuentra(listaSocios.crearSocio("54169551H",
				"Jose de la Vera", "684523169", "jose@vera.es",
				"Paseo del Pardo 3"));

		if (soc == null)
			System.out.println("04: ERROR al crear un nuevo socio. ");
		else
			System.out.println("04: OK al crear un nuevo socio. ");

		ListaContratos listaContratos = ListaContratos.getInstance();

		Tarifa tar = listaContratos.encuentra(listaContratos.crearTarifa(soc,
				"TS", true));

		if (tar == null)
			System.out.println("05: ERROR al contratar una nueva tarifa. ");
		else
			System.out.println("05: OK al contratar una nueva tarifa. ");

		ListaAlquileres listaAlquileres = ListaAlquileres.getInstance();

		Alquiler alq = listaAlquileres.encuentra(listaAlquileres.crearAlquiler(
				ejem, soc));

		if (alq == null)
			System.out.println("06: ERROR al efectuar un alquiler. ");
		else
			System.out.println("06: OK al efectuar un alquiler. ");

		checker = listaAlquileres.terminarAlquiler(alq, false);

		checker = (checker && !(listaAlquileres.getAlquileres().contains(alq)));
		checker = (checker && !(soc.getAlquileres().contains(alq)));
		checker = (checker && soc.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		checker = (checker && ejem.getEstadoEjem() == EstadoEjemplar.DISPONIBLE);

		if (checker == false)
			System.out.println("07: ERROR al devolver un articulo. ");
		else
			System.out.println("07: OK al devolver un articulo. ");

		checker = listaSocios.darBajaSocio(soc);

		checker = (checker && !(listaSocios.getSocios().contains(soc)));
		checker = (checker && !(listaContratos.getContratos().contains(tar)));

		if (checker == false)
			System.out.println("08: ERROR al dar de baja a un socio. ");
		else
			System.out.println("08: OK al dar de baja a un socio. ");

		checker = listaEjemplares.eliminarEjemplar(ejem);

		checker = (checker && !(listaEjemplares.getEjemplares().contains(ejem)));
		checker = (checker && !(art.getEjemplares().contains(ejem)));

		if (checker == false)
			System.out.println("09: ERROR al eliminar un ejemplar. ");
		else
			System.out.println("09: OK al eliminar un ejemplar. ");

		checker = catalogo.eliminarArticulo(art);

		checker = (checker && !(catalogo.getArticulos().contains(art)));
		checker = (checker && !(catalogo.encuentra("Ciencia ficcion", "Serie")
				.getArticulos().contains(art)));

		if (checker == false)
			System.out.println("10: ERROR al eliminar un articulo. ");
		else
			System.out.println("10: OK al eliminar un articulo. ");

		checker = aplicacion.cierraAplicacion();

		if (checker == false)
			System.out
					.println("11: ERROR al guardar la base de datos en archivo. ");
		else
			System.out
					.println("11: OK al guardar la base de datos en archivo. ");

		System.out.println();
		System.out.println();

		System.out.println("Fin de las pruebas");

	}

	/* METODOS COMPLEJOS */

	/**
	 * Inicializa todas las bases de datos a partir de ficheros, llamando a los
	 * constructores de cada una, y actualiza las bases de datos segun la fecha. 
	 * 
	 * @return true si se inicializan correctamente, false si hay algun error.
	 */
	public boolean inicializaListas() {
		Catalogo.getInstance();
		ListaSocios.getInstance();
		ListaAlquileres.getInstance();
		ListaContratos.getInstance();
		Ajustes.getInstance();
		
		actualizaDatos();

		return true;
	}

	/**
	 * Guarda todos los datos en ficheros y cierra la aplicacion.
	 * 
	 * @return true si el guardado se hace correctamente, false en caso
	 *         contrario.
	 */
	public boolean cierraAplicacion() {

		boolean error = true;

		try {
			guardaCatalogo();
			guardaListaAlquileres();
			guardaListaContratos();
			guardaListaSocios();
			guardaAjustes();
		} catch (Exception ex) {
			error = false;
		}

		return error;
	}

	/**
	 * Guarda el catalogo en el archivo que le corresponde.
	 * 
	 * @return true si se ha ejecutado correctamente.
	 * @throws IOException
	 */
	public boolean guardaCatalogo() throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				"articulos.txt"))) {

			bw.write(Catalogo.getNumArticulos() + ","
					+ Catalogo.getNumCategorias() + "," + Articulo.getLastId()
					+ "," + Ejemplar.getLastId());
			bw.newLine();
			/*Puesto que el metodo getPrecio no es estatico, usamos objetos dummy */
			Pelicula p = new Pelicula(0, "", 0, "", 0);
			Serie s = new Serie(0, "", 0, 0, 0);
			Musica m = new Musica(0, "", 0, "", 0);
			bw.write(p.getPrecio() + "," + s.getPrecio() + ","
					+ m.getPrecio());
			bw.newLine();
			for (Articulo a : Catalogo.getInstance().getArticulos()) {
				bw.write(a.toString());
				//bw.newLine();
				for (Ejemplar e : a.getEjemplares()) {
					bw.write(e.toString());
					bw.newLine();
				}
				for (Categoria c : a.getCategorias()) {
					bw.write(c.toString());
					bw.newLine();
				}
			}
			bw.close();
		}

		return true;
	}

	/**
	 * Guarda la lista de socios en el archivo que le corresponde.
	 * 
	 * @return true si se ha ejecutado correctamente.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public boolean guardaListaSocios() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter("socios.txt"))) {

			bw.write(ListaSocios.getNumSocios() + "," + Socio.getLastId());
			bw.newLine();
			for (Socio s : ListaSocios.getInstance().getSocios()) {
				bw.write(s.toString());
				bw.newLine();
			}
			bw.close();
		}

		return true;
	}

	/**
	 * Guarda la lista de alquileres en el archivo que le corresponde.
	 * 
	 * @return true si se ha ejecutado correctamente.
	 * @throws IOException
	 */
	public boolean guardaListaAlquileres() throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				"alquileres.txt"))) {

			bw.write(ListaAlquileres.getNumAlquileres() + ","
					+ Alquiler.getLastId());
			bw.newLine();
			for (Alquiler s : ListaAlquileres.getInstance().getAlquileres()) {
				bw.write(s.toString());
				bw.newLine();
			}
			bw.close();
		}

		return true;
	}

	/**
	 * Guarda la lista de contratos en el archivo que le corresponde.
	 * 
	 * @return true si se ha ejecutado correctamente.
	 */
	public boolean guardaListaContratos() throws IOException {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				"contratos.txt"))) {

			bw.write(ListaContratos.getNumTarifas() + "," + Tarifa.getLastId()
					+ "," + Tarifa.getExtraPlus());
			bw.newLine();
			/* Puesto que el metodo getPrecio no es estatico, usamos objetos dummy */
			Calendar cal = Calendar.getInstance();
			TarifaPelicula p = new TarifaPelicula(0, 0, cal, cal, false);
			TarifaSerie s = new TarifaSerie(0, 0, cal, cal, false);
			TarifaMusica m = new TarifaMusica(0, 0, cal, cal, false);
			TarifaPM pm = new TarifaPM(0, 0, cal, cal, false);
			TarifaPS ps = new TarifaPS(0, 0, cal, cal, false);
			TarifaSM sm = new TarifaSM(0, 0, cal, cal, false);
			TarifaPremium pre = new TarifaPremium(0, 0, cal, cal, false);
			
			bw.write(p.getPrecio() + "," + s.getPrecio()
					+ "," + m.getPrecio() + ","
					+ ps.getPrecio() + "," + pm.getPrecio() + ","
					+ sm.getPrecio() + "," + pre.getPrecio());
			bw.newLine();
			for (Tarifa t : ListaContratos.getInstance().getContratos()) {
				bw.write(t.toString());
				bw.newLine();
			}
			bw.close();
		}

		return true;
	}

	/**
	 * Guarda los ajustes del videoclub.
	 * 
	 * @return true si se ha ejecutado correctamente.
	 */
	public boolean guardaAjustes() throws IOException {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				"ajustes.txt"))) {
			
			bw.write(Ajustes.getInstance().getDias()+","+Ajustes.getInstance().getDiasPlus()+","+
					Ajustes.getInstance().getDiasNivelRetraso()+","+Ajustes.getInstance().getPenalRetraso1()+","+
					Ajustes.getInstance().getPenalRetraso2()+","+Ajustes.getInstance().getMaxArticulos()+","+Ajustes.getInstance().getNombreVideoclub()
					+","+ Ajustes.getInstance().getContrasena());
		
			bw.close();
		}

		return true;
	}
	
	/**
	 * Metodo encargado de devolver el panel principal de la aplicacion.
	 * @return
	 */
	public JPanel getVista() {
		return (JPanel) vista;
	}

	/**
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent boton) {
		/* De Empleado */

		if (boton.getActionCommand().equals("MenuAlquilar")) {
			/* Creamos el panel MenuAlquilar, lo anadimos y lo mostramos */
			Alquilar cAlquilar = new Alquilar();
			if (cAlquilar.getVista() == null) return; /* El numero de socio era erroneo */
			vista.add(cAlquilar.getVista(), "MenuAlquilar");
			((CardLayout) vista.getLayout()).show(vista, "MenuAlquilar");

		} else if (boton.getActionCommand().equals("MenuContrTarifa")) {
			/* Cambiamos al panel ContratarTarifa */
			ContratarTarifa cTarifa = new ContratarTarifa();
			if (cTarifa.getVista() == null) return; /* El numero de socio era erroneo */
			vista.add(cTarifa.getVista(), "MenuContrTarifa");
			((CardLayout) vista.getLayout()).show(vista, "MenuContrTarifa");

		} else if (boton.getActionCommand().equals("MenuReponer")) {
			/* Cambiamos al panel MenuGerente */
			Reponer cReponer = new Reponer();
			vista.add(cReponer.getVista(), "MenuReponer");
			((CardLayout) vista.getLayout()).show(vista, "MenuReponer");

		} else if (boton.getActionCommand().equals("MenuDevolver")) {
			/* Cambiamos al panel MenuGerente */
			Devolver cDevolver = new Devolver();
			if (cDevolver.getVista() == null) return;  /* El numero de socio era erroneo */
			vista.add(cDevolver.getVista(), "MenuDevolver");
			((CardLayout) vista.getLayout()).show(vista, "MenuDevolver");

		} else if (boton.getActionCommand().equals("MenuNewSocio")) {
			/* Creamos el panel MenuNuevoSocio, lo anadimos y lo mostramos */
			NuevoSocio cSocio = new NuevoSocio();
			vista.add(cSocio.getVista(), "MenuNewSocio");
			((CardLayout) vista.getLayout()).show(vista, "MenuNewSocio");

		} else if (boton.getActionCommand().equals("MenuSalir")) {
			/* Cambiamos al panel MenuGerente */
			((CardLayout) vista.getLayout()).show(vista, "PLogin");

		}

		/* De Gerente */

		if (boton.getActionCommand().equals("MenuBuscar")) {
			/* Cambiamos al panel MenuEmpleado */
			BuscarArt cBuscarArt = new BuscarArt();
			vista.add(cBuscarArt.getVista(), "MenuBuscar");
			((CardLayout) vista.getLayout()).show(vista, "MenuBuscar");

		} else if (boton.getActionCommand().equals("MenuTopTen")) {
			/*Creamos y mostramos el panel dar de alta*/
			TopTen cTopTen = new TopTen();
			vista.add(cTopTen.getVista(), "MenuTopTen");
			((CardLayout) vista.getLayout()).show(vista, "MenuTopTen");

		} else if (boton.getActionCommand().equals("MenuAjustes")) {
			/* Cambiamos al panel MenuGerente */
			ContrAjustes cAjustes = new ContrAjustes();
			vista.add(cAjustes.getVista(), "MenuAjustes");
			((CardLayout) vista.getLayout()).show(vista, "MenuAjustes");

		} else if (boton.getActionCommand().equals("MenuDarAlta")) {
			/*Creamos y mostramos el panel dar de alta*/
			DarAltaArt cDarAlta = new DarAltaArt();
			vista.add(cDarAlta.getVista(), "MenuDarAlta");
			((CardLayout) vista.getLayout()).show(vista, "MenuDarAlta");

		} else if (boton.getActionCommand().equals("MenuMorosos")) {
			/*Creamos y mostramos el panel de los clientes morosos*/
			Morosos cMorosos = new Morosos();
			vista.add(cMorosos.getVista(), "MenuMorosos");
			((CardLayout) vista.getLayout()).show(vista, "MenuMorosos");

		} else if (boton.getActionCommand().equals("MenuSalir")) {
			/* Cambiamos al panel MenuGerente */
			((CardLayout) vista.getLayout()).show(vista, "PLogin");

		}

		/* De Login */

		if (boton.getActionCommand().equals("modoEmpleado")) {
			/* Cambiamos al panel MenuEmpleado */
			((CardLayout) vista.getLayout()).show(vista, "MenuEmpleado");

		} else if (boton.getActionCommand().equals("modoGerente")) {
			if (cajaContrasena.getText().equals(Ajustes.getInstance().getContrasena())){
			/* Cambiamos al panel MenuGerente */
			((CardLayout) vista.getLayout()).show(vista, "MenuGerente");
			cajaContrasena.setText(null);
			}

		}

	}
	
	
	
	/**
	 * Chequea las tarifas, eliminando las finalizadas; y chequea todos los alquileres, marcando como morosos y tarde a los socios y articulos correspondientes. 
	 */
	public void actualizaDatos() {
		ListaContratos listaContratos = ListaContratos.getInstance(); 
		ListaAlquileres listaAlquileres = ListaAlquileres.getInstance(); 
		
		/* Tarifas */
		Calendar today = Calendar.getInstance(); 
		
		ArrayList<Tarifa> terminadas = new ArrayList<Tarifa>(); 
		
		for (Tarifa tar : listaContratos.getContratos()) {
			if (today.after(tar.getFechaFin())) {
				terminadas.add(tar); 
			}
		}
		
		for (Tarifa tar : terminadas) {
			listaContratos.terminarTarifa(tar); 
		}
		
		/* Alquileres */
		for (Alquiler alq : listaAlquileres.getAlquileres()) {
			if (alq.diasRetraso() > 0) {
				listaAlquileres.marcarRetrasado(alq);
			}
		}
		
	}
	
	/**
	 * Metodo encargado de mostrar el panel MenuEmpleado
	 */
	public static void vuelveMenuEmpleado() {
		((CardLayout) vista.getLayout()).show(vista, "MenuEmpleado");
	}

	/**
	 * Metodo encargado de mostrar el panel MenuGerente
	 */
	public static void vuelveMenuGerente() {
		((CardLayout) vista.getLayout()).show(vista, "MenuGerente");
	}
	
	/**
	 * Metodo encargado de mostrar el panel MenuDarAlta
	 */
	public static void vuelveDarAlta() {
		/*Creamos y mostramos el panel dar de alta*/
		DarAltaArt cDarAlta = new DarAltaArt();
		vista.add(cDarAlta.getVista(), "MenuDarAlta");
		((CardLayout) vista.getLayout()).show(vista, "MenuDarAlta");
	}


	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	/** 
	 * Guarda las bases de datos de la aplicacion y sale del programa cuando detecta que se ha cerrado la ventana. 
	 * 
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {
		cierraAplicacion();
		System.exit(0);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}

}

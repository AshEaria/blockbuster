/**
 * 
 */
package socios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;

/**
 * Este objeto, del que solo existira una instancia, lista todos los alquileres en curso.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class ListaAlquileres {
	private static ListaAlquileres INSTANCE;
	private static int numAlquileres = 0;
	private ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();

	/* PATRON SINGLETON */
	
	/**
	 * Constructor de inicializacion: Lee el archivo de alquileres y lo vuelca en el objeto ListaAlquileres creado.
	 * 
	 */
	private ListaAlquileres() {
		try {
			this.parseListaAlquileres("alquileres.txt");
		}
		catch(IOException ioex) {
			System.out.println(ioex);
		}
		catch(ParseException pex) {
			System.out.println(pex);
		}
	
	}

	/**
	 * Devuelve la instancia unica de la lista de alquileres.
	 * 
	 * @return Instancia de ListaAlquileres.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ParseException 
	 */
	public static ListaAlquileres getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ListaAlquileres();
		return INSTANCE;
	}
	
	/**
	 * Parsea el archivo de lista de alquileres. 
	 * 
	 * @param filename Nombre del archivo. 
	 * @throws IOException
	 * @throws ParseException
	 */
	private void parseListaAlquileres(String filename) throws IOException,
	ParseException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)))) {
			
			String p;
			
			/* La primera linea contiene atributos estaticos de Alquiler y atributos de ListaAlquileres */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				numAlquileres = Integer.parseInt(token.nextToken());
				Alquiler.setLastId(Integer.parseInt(token.nextToken()));
			}
			
			while ((p = buffer.readLine()) != null) {

				if (!p.equals("")) {

					StringTokenizer token = new StringTokenizer(p, ",");
				
					int idAlquiler = Integer.parseInt(token.nextToken());
					int idEjemplar = Integer.parseInt(token.nextToken());
		            int socio = Integer.parseInt(token.nextToken());
					
					//Adquirir fechas
					DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
					
					Calendar calI = Calendar.getInstance();
					Calendar calF = Calendar.getInstance();
					
					calI.setTime(df.parse(token.nextToken()));
					calI.set(Calendar.HOUR_OF_DAY, 0);
					calI.set(Calendar.MINUTE, 0);
					calI.set(Calendar.SECOND, 0);
					calI.set(Calendar.MILLISECOND, 0);

					calF.setTime(df.parse(token.nextToken()));
					calF.set(Calendar.HOUR_OF_DAY, 0);
					calF.set(Calendar.MINUTE, 0);
					calF.set(Calendar.SECOND, 0);
					calF.set(Calendar.MILLISECOND, 0);
					calF.setLenient(true);
					calF.add(Calendar.MILLISECOND, -1);
					calF.add(Calendar.DAY_OF_MONTH, 1);
					
					Alquiler alquiler = new Alquiler(idAlquiler, idEjemplar, socio, calI, calF); 
					
					alquileres.add(alquiler);
				}

			}

			buffer.close(); 
		}
	}
	
	
	

	/* METODOS COMPLEJOS */

	/**
	 * Crea un nuevo alquiler, lo anade a la base de datos, lo asigna al socio correspondiente y marca el ejemplar como alquilado.
	 * 
	 * @param soc Socio que efectua el alquiler
	 * @param ejem Ejemplar que se alquila
	 * @return ID del nuevo alquiler. 
	 */
	public int crearAlquiler(Ejemplar ejem, Socio soc) {
		if (ejem == null || soc == null) return -1;
		
		Alquiler alq;
		
		if (ejem.getEstadoEjem() == EstadoEjemplar.DISPONIBLE) {
			alq = new Alquiler(ejem, soc);
			
			if (!(soc.asignarAlquiler(alq)))
				return -1;
			if (!(ejem.alquilar())) {
				soc.finAlquiler(alq);
				return -1;
			}
			if (!(alquileres.add(alq))) {
				soc.finAlquiler(alq);
				ejem.devolver(false);
				return -1;
			}
		} else return -1;
		
		int veces = ejem.getArticulo().getVeces();
		veces++;
		ejem.getArticulo().setVeces(veces);
		
		return alq.getId();
	}

	/**
	 * Elimina el alquiler de la base de datos, marca el articulo como disponible o no disponible, y desasocia al socio del alquiler.
	 * 
	 * @param alq Alquiler a finalizar
	 * @param nd true si se quiere marcar el ejemplar como no disponible, false si se quiere que vuelva a estar disponible
	 * @return true si la operacion se ha realizado correctamente, false si hay algun problema.
	 */
	public boolean terminarAlquiler(Alquiler alq, boolean nd) {
		if (alq == null) return false;
		if (!(alq.getSocio().finAlquiler(alq)))
			return false;
		if (!(alq.getEjemplar().devolver(nd))) {
			alq.getSocio().asignarAlquiler(alq);
			return false;
		}
		alquileres.remove(alq);

		return true;
	}
	
	/**
	 * Metodo que encuentra un alquiler a traves de su identificador.
	 * 
	 * @param id Identificador del alquiler
	 * @return El objeto Alquiler si existe, null si no existe.
	 */
	public Alquiler encuentra(int id) {
		if (id == -1) return null;
		for (Alquiler a : alquileres) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}

	/**
	 * Hace los cambios necesarios para marcar un alquiler como retrasado, comprobando si realmente se ha pasado de fecha. 
	 * 
	 * @param alq Alquiler a marcar como retrasado. 
	 */
	public void marcarRetrasado(Alquiler alq) {
		if (alq.diasRetraso() > 0) {
			alq.getEjemplar().setEstadoEjem(EstadoEjemplar.RETRASADO);
			alq.getSocio().calculaEstado();
		}
	}
	
	
	
	/* GETTERS */

	/**
	 * Metodo de acceso a la lista de alquileres.
	 * 
	 * @return Lista de alquileres actuales.
	 */
	public ArrayList<Alquiler> getAlquileres() {
		return alquileres;
	}
	
	/**
	 * Metodo de acceso al numero de alquileres actuales. 
	 * 
	 * @return Numero de alquileres vigentes. 
	 */
	public static int getNumAlquileres() {
		return numAlquileres;
	}
}

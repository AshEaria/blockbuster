/**
 * 
 */
package socios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * El objeto unico de esta clase actua como un listado de los socios actuales.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class ListaSocios {
	private static ListaSocios INSTANCE;
	private static int numSocios = 0;
	private ArrayList<Socio> socios = new ArrayList<Socio>();

	/* PATRON SINGLETON */
	
	/**
	 * Constructor de inicializacion: Lee el archivo de socios y lo vuelca en el objeto ListaSocios creado.
	 * 
	 */
	private ListaSocios() {
		try {
			this.parseListaSocios("socios.txt");
		}
		catch(IOException ioex) {
			System.out.println(ioex);
		}
		catch(ParseException pex) {
			System.out.println(pex);
		}
	
	}

	/**
	 * Devuelve la instancia unica de la lista de socios.
	 * 
	 * @return Instancia de ListaSocios.
	 */
	public static ListaSocios getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ListaSocios();
		return INSTANCE;
	}
	
	/**
	 * Parsea el archivo de lista de socios. 
	 * 
	 * @param filename Nombre del archivo. 
	 * @throws IOException
	 * @throws ParseException
	 */
	private void parseListaSocios(String filename) throws IOException, ParseException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)))) {

			String p;
			
			/* La primera linea contiene atributos estaticos de Socio y atributos de ListaSocios */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				numSocios = Integer.parseInt(token.nextToken());
				Socio.setLastId(Integer.parseInt(token.nextToken()));
			}

			ListaAlquileres listaAlq = ListaAlquileres.getInstance();
			
			while ((p = buffer.readLine()) != null) {

				if (!p.equals("")) {
					String[] tokens = p.split(",");
					
					/* Calcular EstadoSocio */
					EstadoSocio estado;
					if (tokens[7].equals("CON_ALQUILERES")) estado = EstadoSocio.CON_ALQUILERES;
					else if (tokens[7].equals("MOROSO")) estado = EstadoSocio.MOROSO;
					else estado = EstadoSocio.SIN_ALQUILERES;
					
					/* Recuperar los alquileres */
					int numAlq = Integer.parseInt(tokens[8]);
					ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
					
					for (int i = 0; i < numAlq; i++) {
						alquileres.add(listaAlq.encuentra(Integer.parseInt(tokens[9+i])));
					}
					
					Socio socio = new Socio(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], 
							tokens[5], Integer.parseInt(tokens[6]), estado, alquileres);

					socios.add(socio);
				}
			}
			buffer.close();
		}
	}
	
	

	/* METODOS COMPLEJOS */

	/**
	 * Crea un nuevo socio y lo anade a la base de datos.
	 * 
	 * @param dni DNI del nuevo cliente
	 * @param nombre Nombre del socio
	 * @param telefono Telefono del socio
	 * @param email Direccion de correo electronico del socio
	 * @param direccion Direccion postal del cliente
	 * @return Numero del socio recien creado. 
	 */

	public int crearSocio(String dni, String nombre, String telefono, String email, String direccion) {
		numSocios++;
		Socio soc = new Socio(dni, nombre, telefono, email, direccion);
		socios.add(soc);
		return soc.getNumSocio();
	}

	/**
	 * Retira el socio seleccionado de la base de datos.
	 * 
	 * @param socio El socio a eliminar
	 * @return true si la eliminacion se ha hecho correctamente, false si el socio aun tiene alquileres.
	 */
	public boolean darBajaSocio(Socio socio) {
		
		if(socio.darBaja()) {
			socios.remove(socio);
			numSocios--;
			return true;
		} else return false;

	}

	/**
	 * Metodo encargado de encontrar un socio a traves de su numero de Socio
	 * 
	 * @param numSocio id del socio que se busca
	 * @return Objeto Socio correspondiente si ha encontrado su numSocio, null en caso contrario
	 */

	public Socio encuentra(int numSocio) {
		if (numSocio == -1) return null;
		for (Socio s : socios) {
			if (s.getNumSocio() == numSocio)
				return s;
		}
		return null;
	}
	
	/**
	 * Encuentra todos los socios morosos. 
	 * 
	 * @return Una lista con los socios morosos. 
	 */
	public ArrayList<Socio> getMorosos() {
		ArrayList<Socio> morosos = new ArrayList<Socio>();
		for (Socio s : socios) {
			s.calculaEstado();
			if (s.getEstadoSocio() == EstadoSocio.MOROSO) {
				morosos.add(s);
			}
		}
		return morosos; 
	}

	
	
	
	/* GETTERS */

	/**
	 * Metodo de acceso al numero de socios actuales.
	 * 
	 * @return Numero de socios.
	 */
	public static int getNumSocios() {
		return numSocios;
	}

	/**
	 * Metodo de acceso a la lista de socios.
	 * 
	 * @return ArrayList con los socios.
	 */
	public ArrayList<Socio> getSocios() {
		return socios;
	}

}

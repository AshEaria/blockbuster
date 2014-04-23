/**
 * 
 */
package catalogo;

import java.util.ArrayList;


/**
 * Esta clase representa el conjunto de todos sus alquileres. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */

public class ListaEjemplares {
	
	private static ListaEjemplares INSTANCE;
	private ArrayList<Ejemplar> ejemplares=new ArrayList<Ejemplar>();
	
	/* PATRON SINGLETON */
	
	/**
	 * Constructor de inicializacion: Llama al constructor de Catalogo para que inicialice la instancia. 
	 * 
	 */
	private ListaEjemplares() {
		
		//Catalogo.getInstance();
	}
	
	/**
	 * Devuelve la instancia unica de la lista de ejemplares.
	 * 
	 * @return Instancia de ListaEjemplares.
	 */
	public static ListaEjemplares getInstance(){
		if (INSTANCE == null)
			INSTANCE = new ListaEjemplares();
		return INSTANCE;
	}
	
	
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Creador de ejemplares de inicializacion. Crea el ejemplar y lo anade a la base de datos y a la lista de ejemplares del articulo correspondiente. 
	 * 
	 * @param idEjem Identificador del ejemplar
	 * @param estado Estado actual del ejemplar
	 * @param art Articulo al que pertenece el ejemplar
	 * @param formato Formato del ejemplar; ver documentacion de la clase Ejemplar para mas detalles
	 * @return true si la operacion se realiza correctamente, false si hay algun error. 
	 */
	public boolean anadirEjemplar(int idEjem, EstadoEjemplar estado, Articulo art, boolean formato) {
		Ejemplar ejem = new Ejemplar(idEjem, estado, art.getId(), formato);
		
		if(!(art.anadirEjemplar(ejem))) return false;
		if(!(ejemplares.add(ejem))) return false;
		return true;
	}
	
	/**
	 * Creador de ejemplares nuevos. Crea el ejemplar y lo anade a la base de datos y a la lista de ejemplares del articulo correspondiente. 
	 * 
	 * @param idArt Identificador del articulo al que pertenece el ejemplar
	 * @param formato Formato del ejemplar; ver documentacion de la clase Ejemplar para mas detalles
	 * @param estado Estado inicial del articulo
	 * @return ID del articulo recien creado. 
	 */
	public int anadirEjemplar(Articulo art, boolean formato, EstadoEjemplar estado) {
		
		Ejemplar ejem = new Ejemplar(art, formato);
		ejem.setEstadoEjem(estado);
		
		if(!(art.anadirEjemplar(ejem))) return -1;
		if(!(ejemplares.add(ejem))) return -1;
		return ejem.getId();
	}
	
	/**
	 * Elimina el ejemplar de la base de datos y de la lista de ejemplares del articulo correspondiente. 
	 * 
	 * @param ejem Ejemplar a eliminar
	 * @return true si la operacion se realiza correctamente, false si hay algun error. 
	 */
	public boolean eliminarEjemplar(Ejemplar ejem) {
		if (ejem == null) return false; 
		
		Articulo art = ejem.getArticulo();
		
		if (!(art.quitarEjemplar(ejem))) return false;
		if (!(ejemplares.remove(ejem))) return false;
		return true;
	}
	
	/**
	 * Metodo que encuentra un ejemplar a traves de su identificador. 
	 * 
	 * @param id Identificador del ejemplar,
	 * @return El ejemplar si existe, null si no existe.
	 */
	public Ejemplar encuentra(int id){
		if (id == -1) return null;
		for (Ejemplar e: ejemplares){
			if (e.getId() == id)
				return e;
		}
		return null;
	}
	
	
	
	/* GETTERS */
	
	/**
	 * Metodo de acceso a la lista de ejemplares. 
	 * 
	 * @return ArrayList con todos los ejemplares. 
	 */
	public ArrayList<Ejemplar> getEjemplares() {
		return ejemplares;
	}
	
	
	/**
	 * Metodo de acceso al numero de ejemplares de tipo Pelicula y el estado indicado. 
	 * @param  Estado del ejemplar: DISPONIBLE, NO_DISPONIBLE, ALQUILADO o RETRASADO
	 * @return Numero de ejemplares del estado indicado.
	 */
	public int getNumEjemP(EstadoEjemplar estado) {
		int i=0;
		
		if (estado.equals(EstadoEjemplar.NO_DISPONIBLE)) {
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.NO_DISPONIBLE) && eAux.getArticulo() instanceof Pelicula)i++;
			}
		}
		if (estado.equals(EstadoEjemplar.DISPONIBLE) ) 
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.DISPONIBLE)  && eAux.getArticulo() instanceof Pelicula)i++;
			}
		if (estado.equals(EstadoEjemplar.RETRASADO)) 
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.RETRASADO)  && eAux.getArticulo() instanceof Pelicula)i++;
			}
		if (estado.equals(EstadoEjemplar.ALQUILADO))
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.ALQUILADO)  && eAux.getArticulo() instanceof Pelicula)i++;
			}
		
		return i;
	}
	
	/**
	 * Metodo de acceso al numero de ejemplares de Musica y del estado indicado. 
	 * @param  Estado del ejemplar: DISPONIBLE, NO_DISPONIBLE, ALQUILADO o RETRASADO
	 * @return Numero de ejemplares del estado indicado.
	 */
	public int getNumEjemM(EstadoEjemplar estado) {
		int i=0;
		
		if (estado.equals(EstadoEjemplar.NO_DISPONIBLE)) {
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.NO_DISPONIBLE)  && eAux.getArticulo() instanceof Musica)i++;
			}
		}
		if (estado.equals(EstadoEjemplar.DISPONIBLE)) 
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.DISPONIBLE)  && eAux.getArticulo() instanceof Musica)i++;
			}
		if (estado.equals(EstadoEjemplar.RETRASADO)) 
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.RETRASADO)  && eAux.getArticulo() instanceof Musica)i++;
			}
		if (estado.equals(EstadoEjemplar.ALQUILADO))
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.ALQUILADO)  && eAux.getArticulo() instanceof Musica)i++;
			}

		return i;
	}
	
	/**
	 * Metodo de acceso al numero de ejemplares de Series y del estado indicado. 
	 * @param  Estado del ejemplar: DISPONIBLE, NO_DISPONIBLE, ALQUILADO o RETRASADO
	 * @return Numero de ejemplares del estado indicado.
	 */
	public int getNumEjemS(EstadoEjemplar estado) {
		int i=0;
		
		if (estado.equals(EstadoEjemplar.NO_DISPONIBLE)) {
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.NO_DISPONIBLE)  && eAux.getArticulo() instanceof Serie)i++;
			}
		}
		if (estado.equals(EstadoEjemplar.DISPONIBLE)) 
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.DISPONIBLE)  && eAux.getArticulo() instanceof Serie)i++;
			}
		if (estado.equals(EstadoEjemplar.RETRASADO)) 
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.RETRASADO)  && eAux.getArticulo() instanceof Serie)i++;
			}
		if (estado.equals(EstadoEjemplar.ALQUILADO))
			for (Ejemplar eAux: getEjemplares()){
				if (eAux.getEstadoEjem().equals(EstadoEjemplar.ALQUILADO)  && eAux.getArticulo() instanceof Serie)i++;
			}
		
		return i;
	}
	
	
	/* SETTERS */
	
	/**
	 * Setter de la lista de ejemplares para su uso durante la inicializacion. 
	 * 
	 * @param alquileres ArrayList de los ejemplares. 
	 */
	public void setEjemplares(ArrayList<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

}

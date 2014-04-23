/**
 * 
 */
package catalogo;

/**
 * Cada objeto de esta clase representa un ejemplar unico de cualquier articulo. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Ejemplar {
	private int idEjemplar;
	private EstadoEjemplar estadoEjem;
	private int articulo; 
	private boolean formato; 
	private static int lastId = 0;
	/* Nota sobre la variable formato: 
	 * - en musica, false = CD, true = vinilo
	 * - en peliculas y series, false = DVD, true = Blu-Ray
	 */
	
	
	/* CONSTRUCTOR */
	
	/**
	 * Constructor de inicializacion. Los parametros se han recuperado de archivo. 
	 * 
	 * @param id Identificador unico del ejemplar.
	 * @param estadoEjem Estado de disponibilidad en el que se encuentra dicho ejemplar.
	 * @param articulo Articulo al que pertenece el ejemplar. 
	 * @param formato Si el ejemplar es de tipo musica, sera false si es "CD" y true si es "vinilo", si el ejemplar es de tipo serie o pelicula,
	 *        sera true si es "bluray" y false si es "DVD"
	 */
	
	public Ejemplar(int id, EstadoEjemplar estadoEjem, int articulo, boolean formato) {
		this.idEjemplar = id;
		this.estadoEjem = estadoEjem;
		this.articulo = articulo;
		this.formato = formato;
	
	}
	
	/**
	 * Constructor que crea nuevos ejemplares.
	 * 
	 * @param articulo Articulo al que pertenece el ejemplar. 
	 * @param formato Si el ejemplar es de tipo musica, sera false si es "CD" y true si es "vinilo", si el ejemplar es de tipo serie o pelicula,
	 *        sera true si es "bluray" y false si es "DVD"
	 */
	public Ejemplar (Articulo articulo, boolean formato){
		
		lastId++;
		this.idEjemplar = lastId;
		this.estadoEjem = EstadoEjemplar.DISPONIBLE;
		this.articulo = articulo.getId();
		this.formato = formato;
	}
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Marca el ejemplar como alquilado si este esta disponible. 
	 * 
	 * @return true si el alquiler se ha efectuado correctamente, false si el articulo esta no disponible, alquilado o retrasado. 
	 */
	public boolean alquilar() {
		 if (estadoEjem != EstadoEjemplar.DISPONIBLE) return false;
		 
	    estadoEjem = EstadoEjemplar.ALQUILADO;
		return true;
	}

	/**
	 * Marca el ejemplar como disponible o no disponible al devolverlo si estaba alquilado o retrasado. 
	 * 
	 * @param nd si vale false, el articulo pasara a estado disponible; si vale true, a no disponible
	 * @return true si la devolucion se ha efectuado correctamente, false si el articulo esta en estado disponible o no disponible. 
	 */
	public boolean devolver(boolean nd) {
		if (estadoEjem == EstadoEjemplar.ALQUILADO || estadoEjem == EstadoEjemplar.RETRASADO){
			if (nd == true) estadoEjem = EstadoEjemplar.NO_DISPONIBLE;
			else estadoEjem = EstadoEjemplar.DISPONIBLE;
			return true;
		}
		return false;
	}
	
	/**
	 * Elimina el ejemplar si este no esta alquilado o retrasado. 
	 * 
	 * @return true si la eliminacion se ha hecho correctamente, false si el articulo esta en posesion de un cliente. 
	 */
	public boolean darBaja() {
		
		if (estadoEjem == EstadoEjemplar.ALQUILADO || estadoEjem == EstadoEjemplar.RETRASADO) return false;
		return true;
	}
	
	/**
	 * Pasa el ejemplar del estado no disponible al estado disponible. 
	 * 
	 * @return true si el cambio de estado se ha efectuado correctamente, false si el ejemplar no estaba en estado no disponible. 
	 */
	public boolean reponer() {
		
		if (estadoEjem == EstadoEjemplar.NO_DISPONIBLE) { 
			
			estadoEjem = EstadoEjemplar.DISPONIBLE;
			return true;
		}
		
		return false;
	}

	
	
	
	
	/* GETTERS */
	
	
	/**
	 * Metodo de acceso al identificador unico del ejemplar. 
	 * 
	 * @return id del ejemplar. 
	 */
	public int getId() {
		return idEjemplar;
	}

	/**
	 * Metodo de acceso al articulo al que pertenece el ejemplar. 
	 * 
	 * @return Objeto de tipo Articulo correspondiente al ejemplar. 
	 */
	public Articulo getArticulo() {
		Catalogo cat = Catalogo.getInstance();
		return cat.encuentra(articulo);
	}
	
	/**
	 * Metodo de acceso al estado del ejemplar. 
	 * 
	 * @return Estado actual del ejemplar: DISPONIBLE, NO_DISPONIBLE, ALQUILADO o RETRASADO
	 */
	public EstadoEjemplar getEstadoEjem() {
		return estadoEjem;
	}
	
	
	/**
	 * Metodo de acceso al formato del ejemplar. 
	 * 
	 * @return false si el ejemplar esta en CD (musica) o DVD (peliculas/series); true si esta en vinilo (musica) o Blu-Ray (peliculas/series)
	 */
	public boolean isFormato() {
		return formato;
	}

	/**
	 * Metodo de acceso al ultimo id asignado. 
	 * 
	 * @return id del ultimo ejemplar creado. 
	 */
	public static int getLastId() {
		return lastId;
	}
	
	
	
	/* SETTERS */
	

	/**
	 * Setter del ultimo id asignado, a utilizar durante la inicializacion. 
	 * 
	 * @param lastId Nuevo "ultimo id asignado", sacado de archivo
	 */
	public static void setLastId(int lastId) {
		Ejemplar.lastId = lastId;
	}

	/**
	 * @param estadoEjem the estadoEjem to set
	 */
	public void setEstadoEjem(EstadoEjemplar estadoEjem) {
		this.estadoEjem = estadoEjem;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (formato == true)
		return "E," + idEjemplar + ","+ estadoEjem + "," + "S";
		else return "E," + idEjemplar + ","+ estadoEjem + "," + "N";
	}
	
	
	
}
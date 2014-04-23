/**
 * 
 */
package catalogo;

import java.util.ArrayList;

/**
 * Esta clase representa un producto disponible en el videoclub, que puede tener varios ejemplares. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public abstract class Articulo {
	private int idArticulo;
	private String titulo;
	private int veces;
	private ArrayList<Categoria> categorias= new ArrayList<Categoria>();
	private ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
	private static int lastId = 0;

	/* CONSTRUCTORES */
	
	/**
	 * Constructor de inicializacion. Los parametros han sido recuperados de fichero. 
	 * 
	 * @param idArticulo identificador del articulo
	 * @param titulo Titulo del producto
	 * @param veces Numero de veces que se ha alquilado este articulo
	 */
	public Articulo(int idArticulo, String titulo, int veces) {
		this.idArticulo = idArticulo;
		this.titulo = titulo;
		this.veces = veces;
	}
	
	/**
	 * Constructor de articulo nuevo. Crea un articulo con titulo y categorias dadas, y ningun ejemplar asociado. 
	 * 
	 * @param titulo Titulo del articulo
	 * @param categorias Lista de categorias a las que pertenece el articulo
	 */
	public Articulo(String titulo) {
		lastId++;
		this.idArticulo = lastId;
		this.titulo = titulo; 
		this.veces = 0;
		this.categorias = new ArrayList<Categoria>();
		this.ejemplares = new ArrayList<Ejemplar>();
	}
	
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Anade una categoria a la ArrayList de categorias del articulo y se anade a si mismo a la lista en la categoria. Si no existe, la crea, con el mismo tipo que el articulo.
	 * 
	 * @param nombre Nombre de la categoria a anadir. 
	 * @return true si se ha anadido correctamente, false si ha habido algun error. 
	 */
	public boolean anadirCategoria(String nombre) {
		Catalogo catalogo = Catalogo.getInstance();
		Categoria cat = catalogo.encuentra(nombre, this.getClass().getSimpleName());
		
		if (categorias.contains(cat) && cat.getArticulos().contains(this)) return true;
		
		if (categorias.contains(cat)) return false;
		return (categorias.add(cat) && cat.anadirArticulo(this));
	}
	
	/**
	 * [DEPRECADO: usar el otro metodo]
	 * Anade una categoria a la ArrayList de categorias del articulo y se anade a si mismo a la lista en la categoria. 
	 * 
	 * @param cat Categoria a anadir 
	 * @return true si se ha anadido correctamente, false si la categoria ya estaba en el ArrayList de categorias del articulo o si la categoria no es del mismo tipo que el articulo. 
	 */
	public boolean anadirCategoria(Categoria cat) {
		if (cat == null) return false;
		//if (!(cat.getTipoCategoria().equals(this.getClass().getSimpleName()))) return false;
		if (categorias.contains(cat)) return false;
		return (categorias.add(cat) && cat.anadirArticulo(this));
	}
	
	/**
	 * Quita la categoria correspondiente de la lista de categorias del articulo y se quita a si mismo de la categoria. 
	 * 
	 * @param cat Categoria a eliminar
	 * @return true si la operacion se ha efectuado correctamente, false si el articulo no contenia la categoria. 
	 */
	public boolean quitarCategoria(Categoria cat) {
		if (cat == null) return false;
		for (Categoria c : categorias) {
			if (c == cat) {
				categorias.remove(cat);
				cat.quitarArticulo(this);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Anade un ejemplar a la ArrayList de ejemplares del articulo. 
	 * 
	 * @param ejem ejemplar a anadir. 
	 * @return true si se ha anadido correctamente, false si el ejemplar ya estaba en el ArrayList de ejemplares del articulo. 
	 */
	public boolean anadirEjemplar(Ejemplar ejem) {
		if (ejem == null) return false;
	    if (ejemplares.contains(ejem)) return false;
		return ejemplares.add(ejem);
	}
	
	/**
	 * Quita el ejemplar correspondiente de la lista de ejemplares del articulo. 
	 * 
	 * @param ejem Ejemplar a eliminar
	 * @return true si la operacion se ha efectuado correctamente, false si el articulo no estaba asociado al ejemplar. 
	 */
	public boolean quitarEjemplar(Ejemplar ejem) {
		if (ejem == null) return false;
		for (Ejemplar e : ejemplares) {
			if (e == ejem) {
				ejemplares.remove(ejem);
				return true;
			}
		}
		return false;
	}
	
	
	public abstract String toString();

	/* GETTERS */

	/**
	 * Metodo de acceso al id del articulo
	 * 
	 * @return Identificador del articulo. 
	 */
	public int getId() {
		return idArticulo;
	}
	
	/**
	 * Metodo de acceso al titulo del articulo. 
	 * 
	 * @return Nombre del producto. 
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo de acceso al numero de veces que ha sido alquilado el articulo. 
	 * 
	 * @return Numero de alquileres. 
	 **/
	
	public int getVeces() {
		return veces;
	}
	
	/**
	 * Metodo de acceso a las categorias a las que pertenece el articulo. 
	 * 
	 * @return ArrayLista de elementos Categoria. 
	 */
	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	
	/**
	 * Muestra la ArrayLista de ejemplares de un determinado articulo.
	 * 
	 * @return Ejemplares del articulo.
	 */
	public ArrayList<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	/**
	 * Metodo de acceso al ultimo id asignado. 
	 * 
	 * @return id del ultimo articulo creado. 
	 */
	public static int getLastId() {
		return lastId;
	}

	/**
	 * Metodo de acceso al numero de veces que se ha alquilado este articulo. 
	 * 
	 * @param veces Nuevo numero de veces
	 */
	public void setVeces(int veces) {
		this.veces = veces;
	}
	
	/**
	 * Metodo de acceso al precio de alquiler por uso del articulo correspondiente. 
	 * 
	 * @return Precio de alquiler. 
	 */
	public abstract double getPrecio();
	
	
	
	/* SETTERS */
	

	/**
	 * Setter del ultimo id asignado, a utilizar durante la inicializacion. 
	 * 
	 * @param lastId Nuevo "ultimo id asignado", sacado de archivo
	 */
	public static void setLastId(int lastId) {
		Articulo.lastId = lastId;
	}
	
}
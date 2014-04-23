/**
 * 
 */
package catalogo;

import java.util.ArrayList;

/**
 * Los objetos de esta clase representan las categorias a las que pueden pertenecer los articulos. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Categoria {

	private String nombre;
	private String tipoCategoria;
	private ArrayList<Integer> articulos;
	
	/* CONSTRUCTORES */
	
	/**
	 * Constructor de categoria nueva. Crea una categoria sin articulos. 
	 * 
	 * @param nombre Nombre de la categoria
	 * @param tipoCategoria String con el tipo de categoria que es: "Pelicula", "Musica" o "Serie". 
	 * @param articulos 
	 */
    public Categoria(String nombre, String tipoCategoria) {
		this.nombre = nombre;
		this.tipoCategoria = tipoCategoria;
		this.articulos = new ArrayList<Integer>();
	}
    
    /* Esta clase no tiene constructor de inicializacion porque se inicializa creando una categoria nueva y llenandola articulo a articulo. */

	/**
     * Anade un articulo al ArrayList de articulos de la categoria.
     * 
     * @param art Articulo a anadir.
     * @return true si lo ha anadido con exito, false si el articulo no es del mismo tipo que la categoria. 
     */
	public boolean anadirArticulo(Articulo art) {
		if (art == null) return false;
		//if (!(art.getClass().getSimpleName().equals(tipoCategoria))) return false;
		articulos.add(art.getId()); 
		return true;
	}
	
	/**
	 * Elimina un articulo del ArrayList de articulos de la categoria.
	 * 
	 * @return true si ha podido eliminarlo con exito, false en caso contrario.
	 */
	public boolean quitarArticulo(Articulo art) {
		if (art == null) return false;
		
		articulos.remove(art);
		return true;
	}
	
	
	
	/* GETTERS */
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "C,"+ nombre;
	}
	
	public String toStringSinC() {
		return nombre;
	}
	
	/**
	 * Metodo de acceso al tipo de la categoria. 
	 * 
	 * @return String con el tipo de categoria: "Pelicula", "Musica" o "Serie". 
	 */
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	/**
	 * Metodo encargado de mostrar todos los articulos que contenga la categoria.
	 * 
	 * @return ArrayList de articulos con dicha categoria.
	 */
	public ArrayList<Articulo> getArticulos() {
		ArrayList<Articulo> lista = new ArrayList<Articulo>();
		Catalogo cat = Catalogo.getInstance();
		for (int i = 0; i <= articulos.size() && !(articulos.isEmpty()); i++) {
			lista.set(i, cat.encuentra(articulos.get(i)));
		}
		return lista;
	}
	
	
	
}
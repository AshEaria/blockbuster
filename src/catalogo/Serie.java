/**
 *
 */
package catalogo;

/**
 * Cada objeto de esta clase contiene informacion sobre un articulo de tipo serie. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Serie extends Articulo {

    private int temporada;
    private int volumen;
    private static double precio;
    
    /* CONSTRUCTORES */

    /**
     * Constructor de un articulo de tipo serie nuevo. 
     *
     * @param titulo Titulo de la serie
     * @param temporada Numero de temporada en la serie
     * @param volumen Numero de volumen en la temporada
     */
    public Serie(String titulo, int temporada, int volumen) {
    	super(titulo);
		this.temporada = temporada;
		this.volumen = volumen;
	}
    
    /**
     * Constructor de un articulo de tipo serie de inicializacion. 
     *
     * @param id Identificador de la serie
     * @param titulo Titulo de la serie
     * @param temporada Numero de temporada en la serie
     * @param volumen Numero de volumen en la temporada
     * @param veces Veces que ha sido alquilada la serie
     */
    public Serie(int id, String titulo, int temporada, int volumen, int veces) {
    	super(id, titulo, veces);
		this.temporada = temporada;
		this.volumen = volumen;
	}
    
    @Override
 	public String toString() {
 		return "S,"+ getId()+","+getTitulo() +","+temporada+","+ volumen +","+ getVeces()+"\n";
 	}    

    
    /*GETTERS*/
    
    /**
	 * Metodo de acceso a la temporada en la serie de este articulo. 
	 * 
	 * @return Numero de temporada. 
	 */
	public int getTemporada() {
		return temporada;
	}

	/**
	 * Metodo de acceso al volumen en la temporada de este articulo. 
	 * 
	 * @return Numero de volumen. 
	 */
	public int getVolumen() {
		return volumen;
	}

	/**
	 * Metodo de acceso al precio de alquiler de series por uso. 
	 * 
	 * @return Precio de alquiler por uso. 
	 */
	public double getPrecio() {
		return precio;
	}


	
	
    /*SETTERS*/

	/**
     * Metodo encargado de modificar el precio del precio de alquiler de series por uso. 
     *
     * @param precio Nuevo precio
     */
    public static void setPrecio(double precio) {
        Serie.precio = precio;
    }
}

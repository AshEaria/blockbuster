/**
 *
 */
package catalogo;

/**
 * Cada objeto de esta clase contiene informacion sobre un articulo de tipo pelicula. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Pelicula extends Articulo {

    private int ano;
    private String director;
    private static double precio;
    
    /* CONSTRUCTORES */

    /**
     * Constructor de un articulo de tipo pelicula nuevo. 
     *
     * @param titulo Titulo de la pelicula
     * @param ano Ano de publicacion
     * @param director Director de la pelicula
     */
    public Pelicula(String titulo, int ano, String director) {
    	super(titulo);
		this.ano = ano;
		this.director = director;
	}
    
    /**
     * Constructor de un articulo de tipo pelicula de inicializacion. 
     *
     * @param id Identificador de la pelicula
     * @param titulo Titulo de la pelicula
     * @param ano Ano de publicacion
     * @param director Director de la pelicula
     * @param veces Veces que ha sido alquilada la pelicula
     */
    public Pelicula(int id, String titulo, int ano, String director, int veces) {
    	super(id, titulo, veces);
		this.ano = ano;
		this.director = director;
	}
    
    

    /*GETTERS*/
    
    /**
	 * Metodo de acceso al ano de publicacion de la pelicula. 
	 * 
	 * @return Ano de publicacion. 
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * Metodo de acceso al director de la pelicula. 
	 * 
	 * @return El director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Metodo de acceso al precio de alquiler de peliculas por uso. 
	 * 
	 * @return Precio de alquiler por uso. 
	 */
	public double getPrecio() {
		return precio;
	}

	@Override
 	public String toString() {
 		return "P,"+ getId() +","+getTitulo()+","+ ano +","+getDirector() +","+ getVeces()+"\n";
 	}    

	
	
    /*SETTERS*/

	/**
     * Metodo encargado de modificar el precio del precio de alquiler de peliculas por uso. 
     *
     * @param precio Nuevo precio
     */
    public static void setPrecio(double precio) {
        Pelicula.precio = precio;
    }
}

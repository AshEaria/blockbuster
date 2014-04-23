/**
 *
 */
package catalogo;

/**
 * Cada objeto de esta clase contiene informacion sobre un articulo de tipo musica. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Musica extends Articulo {

    private int ano;
    private String interprete;
    private static double precio;
    
    /* CONSTRUCTORES */

    /**
     * Constructor de un articulo de tipo musica nuevo. 
     *
     * @param titulo Titulo del album
     * @param ano Ano de publicacion
     * @param interprete Interprete del album
     */

    public Musica(String titulo, int ano, String interprete) {
    	super(titulo);
		this.ano = ano;
		this.interprete = interprete;
	}
    
    /**
     * Constructor de un articulo de tipo musica de inicializacion. 
     *
     * @param id Identificador del album
     * @param titulo Titulo del album
     * @param ano Ano de publicacion
     * @param interprete Interprete del album
     * @param veces Veces que ha sido alquilado el album
     */

    public Musica(int id, String titulo, int ano, String interprete, int veces) {
    	super(id, titulo, veces);
		this.ano = ano;
		this.interprete = interprete;
	}


    /* (non-Javadoc)
 	 * @see java.lang.Object#toString()
 	 */
 	@Override
 	public String toString() {
 		return "M,"+ getId() +","+getTitulo()+","+ ano +","+interprete+","+ getVeces()+"\n";
 	}    

    /* SETTERS */

	/**
	 * Metodo de acceso al ano de publicacion del album. 
	 * 
	 * @return Ano de publicacion. 
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * Metodo de acceso al interprete del album. 
	 * 
	 * @return String con el nombre del interprete. 
	 */
	public String getInterprete() {
		return interprete;
	}

	/**
	 * Metodo de acceso al precio de alquiler de musica por uso. 
	 * 
	 * @return Precio de alquiler por uso. 
	 */
	public double getPrecio() {
		return precio;
	}



	/* GETTERS*/
    
    /**
     * Metodo encargado de modificar el precio del precio de alquiler de musica por uso. 
     *
     * @param precio Nuevo precio
     */
    public static void setPrecio(double precio) {
        Musica.precio = precio;
    }
}

/**
 * 
 */
package socios;

import java.util.Calendar;

/**
 * Esta clase representa el tipo de tarifa sencilla Pelicula. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class TarifaPelicula extends Tarifa {
	private static double precio;

/* CONSTRUCTOR */
	
	/**
	 * Constructor de tarifa nueva. 
	 * 
	 * @param socio Socio asociado a la tarifa
	 * @param plus Define si la tarifa es plus (dia(s) extra de alquiler) o no
	 */
	public TarifaPelicula(Socio socio, boolean plus) {
		super(socio, plus);
	}

	/**
	 * Constructor de inicializacion. Los parametros han sido recuperados de fichero. 
	 * 
	 * @param idTarifa id de la tarifa. 
	 * @param socio Socio que ha contratado la tarifa. 
	 * @param fechaInicio Fecha de creacion de la tarifa. 
	 * @param fechaFin Fecha de finalizacion de la tarifa. 
	 * @param plus Define si la tarifa es plus (dia(s) extra de alquiler) o no
	 */
	public TarifaPelicula(int idTarifa, int socio, Calendar fechaInicio, Calendar fechaFin, boolean plus) {
		super(idTarifa, socio, fechaInicio, fechaFin, plus);
	}
	
	@Override
	public String toString() {
		return "TP,"+ getId() +","+ getSocio().getNumSocio() + "," +  getFechaInicio().get(Calendar.DAY_OF_MONTH)+"."+(getFechaInicio().get(Calendar.MONTH)+1)+"."+getFechaInicio().get(Calendar.YEAR)+ "," 
				+ getFechaFin().get(Calendar.DAY_OF_MONTH)+"."+(getFechaFin().get(Calendar.MONTH)+1)+"."+getFechaFin().get(Calendar.YEAR)+","+toPlus();
	}
	
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Comprueba si la tarifa cubre el alquiler del tipo de articulo que se le pase. 
	 * 
	 * @return true si el tipo est� incluido en la tarifa, false si no. 
	 */
	public boolean cubre(String tipo) {
		if (tipo.equals("Pelicula")) return true; 
		else if (tipo.equals("Musica")) return false; 
		else if (tipo.equals("Serie")) return false; 
		else return false;
	}
	
	
	/* GETTERS */ 
	
	/**
	 * Metodo de acceso al precio de la tarifa. 
	 * 
	 * @return Precio de contratacion de la tarifa. 
	 */
	public double getPrecio() {
		return precio;
	}

	
	
	/* SETTERS */ 
	
	/**
	 * Setter del precio de la tarifa. 
	 * 
	 * @param Nuevo precio de la tarifa. 
	 */
	public static void setPrecio(double precio) {
		TarifaPelicula.precio = precio;
	}
}

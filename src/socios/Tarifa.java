/**
 * 
 */
package socios;

import java.util.Calendar;

/**
 * Clase abstracta que representa cualquier tipo de tarifa. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public abstract class Tarifa {
	private int idTarifa;
	private int socio;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private boolean plus;
	private static int lastId = 0;
	private static double extraPlus;
	
	/* CONSTRUCTORES */
	
	/**
	 * Constructor de tarifa nueva, a utilizar exclusivamente por las subclases. 
	 * 
	 * @param socio Socio asociado a la tarifa
	 * @param plus Define si la tarifa es plus (dia(s) extra de alquiler) o no
	 */
	public Tarifa(Socio socio, boolean plus) {
		lastId++;
		this.idTarifa = lastId;
		this.socio = socio.getNumSocio(); 
		this.plus = plus;
		
		fechaInicio = Calendar.getInstance();
		fechaInicio.set(Calendar.HOUR_OF_DAY, 0);
		fechaInicio.set(Calendar.MINUTE, 0);
		fechaInicio.set(Calendar.SECOND, 0);
		fechaInicio.set(Calendar.MILLISECOND, 0);
		
		fechaFin = Calendar.getInstance();
		fechaFin.set(Calendar.HOUR_OF_DAY, 0);
		fechaFin.set(Calendar.MINUTE, 0);
		fechaFin.set(Calendar.SECOND, 0);
		fechaFin.set(Calendar.MILLISECOND, 1);
		fechaFin.setLenient(true);
		fechaFin.add(Calendar.DAY_OF_MONTH, 30);
	}
	
	/**
	 * Constructor de inicializacion, a utilizar exclusivamente por las subclases. Los parametros han sido recuperados de fichero. 
	 * 
	 * @param idTarifa id de la tarifa. 
	 * @param socio Socio que ha contratado la tarifa. 
	 * @param fechaInicio Fecha de creacion de la tarifa. 
	 * @param fechaFin Fecha de finalizacion de la tarifa. 
	 * @param plus Define si la tarifa es plus (dia(s) extra de alquiler) o no
	 */
	public Tarifa(int idTarifa, int socio, Calendar fechaInicio, Calendar fechaFin, boolean plus) {
		this.idTarifa = idTarifa;
		this.socio = socio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.plus = plus; 
	}
	
	
	
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Comprueba si la tarifa cubre el alquiler del tipo de articulo que se le pase. 
	 * 
	 * @param tipo nombre simple del tipo de articulo ("Pelicula", "Musica" o "Serie")
	 * @return true si el tipo esta incluido en la tarifa, false si no. 
	 */
	public abstract boolean cubre(String tipo);
	
	public abstract String toString();
	
	/* GETTERS */
	
	/**
	 * Metodo de acceso al id de la tarifa. 
	 * 
	 * @return Identificador de la tarifa. 
	 */
	public int getId() {
		return idTarifa;
	}

	/**
	 * Metodo de acceso al socio asociado. 
	 * 
	 * @return Socio que contrato la tarifa. 
	 */
	public Socio getSocio() {
		ListaSocios lista = ListaSocios.getInstance();
		return lista.encuentra(socio);
	}

	/**
	 * Metodo de acceso a la fecha de comienzo de la tarifa. 
	 * 
	 * @return Fecha en la que se contrato la tarifa. 
	 */
	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Metodo de acceso a la fecha de fin de la tarifa. 
	 * 
	 * @return Fecha en la que expira la tarifa. 
	 */
	public Calendar getFechaFin() {
		return fechaFin;
	}

	/**
	 * Metodo de acceso a la caracteristica que define si la tarifa es plus o no. 
	 * 
	 * @return true si la tarifa es plus, false en caso contrario. 
	 */
	public boolean isPlus() {
		return plus;
	}

	/**
	 * Metodo de acceso al coste extra de contratar una tarifa plus. 
	 * 
	 * @return El coste adicional. 
	 */
	public static double getExtraPlus() {
		return extraPlus;
	}

	/**
	 * Metodo de acceso al ultimo id asignado. 
	 * 
	 * @return El id de la ultima tarifa nueva creada. 
	 */
	public static int getLastId() {
		return lastId;
	}
	
	/**
	 * Metodo de acceso al precio de la tarifa correspondiente. 
	 * 
	 * @return El precio correspondiente al tipo de tarifa instanciado. 
	 */
	public abstract double getPrecio();
	
	
	
	/* SETTERS */

	/**
	 * Metodo de modificacion del ultimo id asignado durante inicializacion. 
	 * 
	 * @param lastId Nuevo "ultimo id asignado"
	 */
	public static void setLastId(int lastId) {
		Tarifa.lastId = lastId;
	}

	/**
	 * Metodo de modificacion del coste extra de contratar una tarifa plus. 
	 * 
	 * @param extraPlus El nuevo coste adicional
	 */
	public static void setExtraPlus(double extraPlus) {
		Tarifa.extraPlus = extraPlus;
	}
	
	public String toPlus(){
		if (plus == true) return "S";
		else return "N"; 
	}
	

}

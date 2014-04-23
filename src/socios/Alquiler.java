/**
 * 
 */
package socios;

import java.util.Calendar;
import aplicacion.Ajustes;
import catalogo.Ejemplar;
import catalogo.ListaEjemplares;

/**
 * Cada objeto de esta clase representa un alquiler en curso. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Alquiler {
	private int idAlquiler;
	private int ejemplar;
	private int socio;
	private Calendar fechaInicio; 
	private Calendar fechaFin;
	private static int lastId = 0;
	
	/* CONSTRUCTORES */
	
	/**
	 * Constructor de un nuevo alquiler. El idAlquiler generado es el id del alquiler anterior mas 1. 
	 * 
	 * @param ejem Ejemplar asociado al alquiler
	 * @param soc Socio que efectua el alquiler
	 */
	public Alquiler(Ejemplar ejem, Socio soc) {
		
		lastId++;
		idAlquiler= lastId;
		ejemplar = ejem.getId(); 
		socio = soc.getNumSocio();
		
		fechaInicio = Calendar.getInstance();
		fechaInicio.set(Calendar.HOUR_OF_DAY, 0);
		fechaInicio.set(Calendar.MINUTE, 0);
		fechaInicio.set(Calendar.SECOND, 0);
		fechaInicio.set(Calendar.MILLISECOND, 0);
		
		fechaFin = Calendar.getInstance();
		fechaFin.set(Calendar.HOUR_OF_DAY, 0);
		fechaFin.set(Calendar.MINUTE, 0);
		fechaFin.set(Calendar.SECOND, 0);
		fechaFin.set(Calendar.MILLISECOND, 0);
		fechaFin.setLenient(true);
		fechaFin.add(Calendar.MILLISECOND, -1);
		
		boolean cubierto;
		Tarifa tar = soc.getTarifa();
		if (tar == null) cubierto = false; 
		else cubierto = tar.cubre(ejem.getArticulo().getClass().getSimpleName());
		
		Ajustes aj = Ajustes.getInstance();
		
		if (cubierto && tar.isPlus()) fechaFin.add(Calendar.DAY_OF_MONTH, aj.getDiasPlus());
		else fechaFin.add(Calendar.DAY_OF_MONTH, aj.getDias());
	}
	
	/**
	 * Constructor de inicializacion. Los parametros han sido recuperados de fichero. 
	 * 
	 * @param idAlquiler id del alquiler
	 * @param ejemplar Ejemplar alquilado
	 * @param socio Numero del socio asociado al alquiler
	 * @param fechaInicio Fecha de creacion del alquiler
	 * @param fechaFin Fecha limite de devolucion
	 */
	public Alquiler(int idAlquiler, int ejemplar, int socio, Calendar fechaInicio, Calendar fechaFin) {
		this.idAlquiler = idAlquiler;
		this.ejemplar = ejemplar;
		this.socio = socio;
		this.fechaInicio = fechaInicio; 
		this.fechaFin = fechaFin;
	}
	
	
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Calcula el numero de dias de retraso del prestamo. 
	 * 
	 * @return Numero de dias de retraso, 0 si no esta retrasado. 
	 */
	public int diasRetraso() {
		Calendar hoy = Calendar.getInstance();
		hoy.set(Calendar.HOUR_OF_DAY, 0);
		hoy.set(Calendar.MINUTE, 0);
		hoy.set(Calendar.SECOND, 0);
		hoy.set(Calendar.MILLISECOND, 1);
		
		if (hoy.before(fechaFin)) return 0;
		else return (int) (hoy.getTimeInMillis() - fechaFin.getTimeInMillis())/(1000 * 60 * 60 * 24) + 1;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return idAlquiler +","+ejemplar+ "," + socio + "," + fechaInicio.get(Calendar.DAY_OF_MONTH)+"."+(fechaInicio.get(Calendar.MONTH)+1)+"."+fechaInicio.get(Calendar.YEAR)+ "," 
				+ fechaFin.get(Calendar.DAY_OF_MONTH)+"."+(fechaFin.get(Calendar.MONTH)+1)+"."+fechaFin.get(Calendar.YEAR);
	}
	
	
	/* GETTERS */

	/**
	 * Metodo de acceso al id de este alquiler. 
	 * 
	 * @return El numero de identificacion del alquiler. 
	 */
	public int getId() {
		return idAlquiler;
	}

	/**
	 * Metodo de acceso al ejemplar asociado a este alquiler. 
	 * 
	 * @return Objeto Ejemplar asociado. 
	 */
	public Ejemplar getEjemplar() {
		ListaEjemplares lista = ListaEjemplares.getInstance();
		return lista.encuentra(ejemplar);
	}

	/**
	 * Metodo de acceso al socio asignado al alquiler. 
	 * 
	 * @return Objeto Socio que contrato el alquiler. 
	 */
	public Socio getSocio() {
		ListaSocios lista = ListaSocios.getInstance();
		return lista.encuentra(socio);
	}

	/**
	 * Metodo de acceso a la fecha de inicio del prestamo. 
	 * 
	 * @return La fecha en la que se realizo el prestamo. 
	 */
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Metodo de acceso a la fecha de fin del prestamo. 
	 * 
	 * @return Fecha limite de devolucion. 
	 */
	public Calendar getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Metodo de acceso al ultimo id asignado. 
	 * 
	 * @return Id asignado al ultimo alquiler creado. 
	 */
	public static int getLastId() {
		return lastId;
	}
	
	
	
	/* SETTERS */

	/**
	 * Setter de la fecha de devolucion, creado unicamente para los tests. 
	 * 
	 * @param fechaFin Nueva fecha de devolucion. 
	 */
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Setter del ultimo id asignado, a utilizar durante la inicializacion. 
	 * 
	 * @param lastId Nuevo "ultimo id asignado", sacado de archivo
	 */
	public static void setLastId(int lastId) {
		Alquiler.lastId = lastId;
	}
	
	
		
}

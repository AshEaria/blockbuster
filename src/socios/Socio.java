/**
 * 
 */
package socios;

import java.util.ArrayList;

import aplicacion.Ajustes;

/**
 * Los objetos de esta clase representan socios, incluyendo datos personales y servicios contratados. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Socio {
	
	private int numSocio;
	private String dni;
	private String nombre;
	private String telefono;
	private String email;
	private String direccion;
	private int tarifa;
	private EstadoSocio estadoSocio;
	private ArrayList<Alquiler> alquileres;
	private static int lastId = 0;
	
	/* CONSTRUCTORES */ 
	
	/**
	 * Constructor de un nuevo socio. 
	 * 
	 * @param dni DNI del nuevo cliente 
	 * @param nombre Nombre del socio
	 * @param telefono Telefono del socio
	 * @param email Direccion de correo electronico del socio
	 * @param direccion Direccion postal del cliente
	 */
	public Socio(String dni, String nombre, String telefono, String email, String direccion) {
		lastId++;
		this.numSocio = lastId;
		this.dni = dni; 
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.estadoSocio = EstadoSocio.SIN_ALQUILERES;
		this.tarifa = -1;
		this.alquileres = new ArrayList<Alquiler>();
	}
	
	/**
	 * Constructor de inicializacion, para recuperar todos los datos de la base de datos al iniciar. 
	 * 
	 * @param numSocio Numero de socio del cliente. 
	 * @param dni DNI del nuevo cliente 
	 * @param nombre Nombre del socio
	 * @param telefono Telefono del socio
	 * @param email Direccion de correo electronico del socio
	 * @param direccion Direccion postal del cliente
	 * @param tarifa Tarifa contratada por el socio. 
	 * @param estadoSocio Estado actual del socio: SIN_ALQUILERES, CON_ALQUILERES o MOROSO. 
	 * @param alquileres Alquileres actuales del socio. 
	 */
	public Socio(int numSocio, String dni, String nombre, String telefono, String email, String direccion, int numTarifa, 
			EstadoSocio estadoSocio, ArrayList<Alquiler> alquileres) 
	{
		this.numSocio = numSocio;
		this.dni = dni; 
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.tarifa = numTarifa;
		this.estadoSocio = estadoSocio;
		this.alquileres = alquileres;
	}
	
	
	/* METODOS COMPLEJOS */
	
	/**
	 * Asigna un alquiler al socio. 
	 * 
	 * @param alq alquiler a asociar
	 * @return true si el alquiler se ha asignado correctamente, false en caso contrario. 
	 */
	public boolean asignarAlquiler(Alquiler alq) {
		
		Ajustes aj = Ajustes.getInstance();
		
		if (alquileres.size() >= aj.getMaxArticulos()) return false; 
		else {
			alquileres.add(alq);
			calculaEstado();
		}
		
		return true;	
	}
	
	/**
	 * Asigna una nueva tarifa al socio. 
	 * 
	 * @param tar tarifa a asociar
	 * @return true si la tarifa se ha asignado correctamente, false si ya existia una tarifa asociada al cliente. 
	 */
	public boolean asignarTarifa(Tarifa tar) {
		
		if (tarifa != -1) return false;
		tarifa = tar.getId();
		
		return true;
	}
	
	/**
	 * Desvincula un alquiler dado del socio y recalcula su estado. 
	 * 
	 * @param alq alquiler a desvincular
	 * @return true si el alquiler se ha desvinculado correctamente, false si no estaba asociado al socio. 
	 */
	public boolean finAlquiler(Alquiler alq) {
		
		if (estadoSocio == EstadoSocio.SIN_ALQUILERES) return false; 
		else {
			if (!(alquileres.contains(alq))) return false; 
			else {
				if (!(alquileres.remove(alq))) return false;
				
				calculaEstado();
			}
		} 
		return true;
	}
	
	/**
	 * Desvincula una tarifa plana del socio. 
	 * 
	 * @return true si el proceso se ha efectuado correctamente, false si no habia ninguna tarifa asociada al socio. 
	 */
	public boolean finTarifa() {
		
		if (tarifa == -1) return false;
		tarifa = -1;
		
		return true;
	}
	
	/**
	 * Comprueba que el socio no tenga articulos sin devolver, y elimina la tarifa plana que tenga contratada si la tiene. 
	 * 
	 * @return true si la eliminacion se ha efectuado correctamente, false si el cliente aun tiene articulos sin devolver. 
	 */
	public boolean darBaja() {

		if (estadoSocio != EstadoSocio.SIN_ALQUILERES) return false; 
		else {
			if (tarifa != -1) {
				ListaContratos lista = ListaContratos.getInstance();
				lista.terminarTarifa(lista.encuentra(tarifa));
			}
		}
		return true;
	}
	
	/**
	 * Calcula el estado del socio y se lo asigna. 
	 * 
	 */
	public void calculaEstado() {
		boolean libre = true;
		
		if (alquileres.size() == 0) estadoSocio = EstadoSocio.SIN_ALQUILERES; 
		else {
			for (Alquiler al : alquileres) {
				if (al.diasRetraso() > 0) libre = false;
			}
			if (libre == true) estadoSocio = EstadoSocio.CON_ALQUILERES;
			else estadoSocio = EstadoSocio.MOROSO;
		} 
	}
	
	
	@Override
	public String toString() {
		return numSocio +","+ dni +","+ nombre +","+ telefono +","+ email +","+ direccion +","+ tarifa +","+ estadoSocio +","+ this.getAlquileres().size()+ toAlquileres();
	}
	
	public String toAlquileres(){
		String a="";
		
		for (Alquiler s: this.getAlquileres()){
			
			a = a + "," + s.getId();
		}
		return a;
	}
	
	/* GETTERS */

	
	/**
	 * Metodo de acceso al numero de socio. 
	 * 
	 * @return El numero de socio unico de este cliente. 
	 */
	public int getNumSocio() {
		return numSocio;
	}


	/**
	 * Metodo de acceso al DNI del socio. 
	 * 
	 * @return El DNI, en forma de String. 
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Metodo de acceso al nombre del socio. 
	 * 
	 * @return String con el nombre del socio. 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo de acceso al numero de telefono del socio. 
	 * 
	 * @return String con el telefono del socio. 
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo de acceso al email del socio. 
	 * 
	 * @return String con el email. 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo de acceso al direccion del socio. 
	 * 
	 * @return Direccion postal del cliente. 
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo de acceso al estado del socio.
	 * 
	 * @return Estado actual del socio: CON_ALQUILERES, SIN_ALQUILERES o MOROSO. 
	 */
	public EstadoSocio getEstadoSocio() {
		return estadoSocio;
	}
	
	/**
	 * Metodo de acceso a la tarifa contratada por el socio. 
	 * 
	 * @return La tarifa correspondiente, null si no existe. 
	 */
	public Tarifa getTarifa() {
		ListaContratos lista = ListaContratos.getInstance();
		return lista.encuentra(tarifa);
	}

	/**
	 * Metodo de acceso a los alquileres actuales del socio. 
	 * 
	 * @return ArrayList de tipo Alquiler con los alquileres actuales, null si hay algun problema. 
	 */
	public ArrayList<Alquiler> getAlquileres() {
		return alquileres;
	}

	/**
	 * Metodo de acceso al ultimo id creado. 
	 * 
	 * @return El id del ultimo alquiler creado. 
	 */
	public static int getLastId() {
		return lastId;
	}
	
	
	
	/* SETTERS */
	
	/**
	 * Metodo de modificacion del DNI. 
	 * 
	 * @param dni Nuevo DNI. 
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Metodo de modificacion del nombre. 
	 * 
	 * @param nombre Nuevo nombre. 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo de modificacion del numero de telefono. 
	 * 
	 * @param telefono Nuevo telefono. 
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo de modificacion del email. 
	 * 
	 * @param email Nuevo email. 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo de modificacion de la direccion. 
	 * 
	 * @param direccion Nueva direccion. 
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Setter del ultimo id asignado, a utilizar durante la inicializacion. 
	 * 
	 * @param lastId Nuevo "ultimo id asignado", sacado de archivo
	 */
	public static void setLastId(int lastId) {
		Socio.lastId = lastId;
	}

	
}

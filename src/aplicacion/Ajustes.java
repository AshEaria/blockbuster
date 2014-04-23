/**
 * 
 */
package aplicacion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.StringTokenizer;

/**
 * Contiene los ajustes de la aplicacion. Solo existe una instancia. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Ajustes {
	private static Ajustes INSTANCE;
	private int dias;
	private int diasPlus;
	private int diasNivelRetraso;
	private double penalRetraso1;
	private double penalRetraso2;
	private int maxArticulos; 
	private String nombreVideoclub;
	private String contrasena;
	
	/* PATRON SINGLETON */
	
	/**
	 * Constructor de inicializacion: Lee el archivo de ajustes y lo vuelca en el objeto Ajustes creado.
	 * 
	 */
	private Ajustes() {
		try {
			this.parseAjustes("ajustes.txt");
		}
		catch(IOException ioex) {
			System.out.println(ioex);
		}
		catch(ParseException pex) {
			System.out.println(pex);
		}
		finally {
			//////////////////////////////////////////////////////////////////
		}
	}
	
	/**
	 * Devuelve la instancia unica del objeto de ajustes. 
	 * 
	 * @return Instancia de Ajustes.
	 */
	public static Ajustes getInstance() {
		if (INSTANCE == null) INSTANCE = new Ajustes();
		return INSTANCE;
	}
	
	/**
	 * Parsea el archivo de ajustes. 
	 * 
	 * @param filename Nombre del archivo. 
	 * @throws IOException
	 * @throws ParseException
	 */
	private void parseAjustes(String filename) throws IOException, ParseException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)))) {

			String p;
			
			/* Toda la informacion de ajustes esta en la primera linea */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				dias = Integer.parseInt(token.nextToken());
				diasPlus = Integer.parseInt(token.nextToken());
				diasNivelRetraso = Integer.parseInt(token.nextToken());
				penalRetraso1 = Double.parseDouble(token.nextToken());
				penalRetraso2 = Double.parseDouble(token.nextToken());
				maxArticulos = Integer.parseInt(token.nextToken()); 
				nombreVideoclub = token.nextToken();
				contrasena = token.nextToken();
			}
			
			buffer.close();
		}
	}
	
	
	
	/* GETTERS */ 

	/**
	 * M�todo de acceso al n�mero de d�as m�ximos de alquiler por uso. 
	 * 
	 * @return n�mero de d�as. 
	 */
	public int getDias() {
		return dias;
	}

	/**
	 * M�todo de acceso al n�mero de d�as m�ximos de alquiler teniendo contratada una tarifa Plus. 
	 * 
	 * @return n�mero de d�as. 
	 */
	public int getDiasPlus() {
		return diasPlus;
	}

	/**
	 * Devuelve el n�mero de d�as de retraso tras los cuales se empieza a aplicar la penalizaci�n aumentada. 
	 * 
	 * @return n�mero de d�as. 
	 */
	public int getDiasNivelRetraso() {
		return diasNivelRetraso;
	}

	/**
	 * Devuelve la penalizaci�n por d�a de retraso inicial, en euros. 
	 * 
	 * @return Importe de la penalizaci�n por d�a. 
	 */
	public double getPenalRetraso1() {
		return penalRetraso1;
	}

	/**
	 * Devuelve la penalizaci�n por d�a de retraso aumentada, en euros. 
	 * 
	 * @return Importe de la penalizaci�n por d�a.
	 */
	public double getPenalRetraso2() {
		return penalRetraso2;
	}

	/**
	 * Devuelve el n�mero de art�culos m�ximo que se puede alquilar a la vez. 
	 * 
	 * @return n�mero de art�culos. 
	 */
	public int getMaxArticulos() {
		return maxArticulos;
	}

	/**
	 * M�todo de acceso al nombre del videoclub. 
	 * 
	 * @return String con el nombre del videoclub. 
	 */
	public String getNombreVideoclub() {
		return nombreVideoclub;
	}

	/**
	 * M�todo de acceso a la contraseña del videoclub. 
	 * 
	 * @return String con la contraseña del videoclub. 
	 */
	public String getContrasena() {
		return contrasena;
	}

	
	
	/* SETTERS */
	
	/**
	 * M�todo de modificaci�n del n�mero de d�as de alquiler por uso. 
	 * 
	 * @param Nuevo n�mero de d�as. 
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}

	/**
	 * M�todo de modificaci�n del n�mero de d�as de alquiler con tarifa Plus. 
	 * 
	 * @param Nuevo n�mero de d�as. 
	 */
	public void setDiasPlus(int diasPlus) {
		this.diasPlus = diasPlus;
	}

	/**
	 * M�todo de modificaci�n del n�mero de d�as de retraso tras los que se aplica la penalizaci�n aumentada.
	 * 
	 * @param Nuevo n�mero de d�as. 
	 */
	public void setDiasNivelRetraso(int diasNivelRetraso) {
		this.diasNivelRetraso = diasNivelRetraso;
	}

	/**
	 * M�todo de modificaci�n del coste de la penalizaci�n por retraso inicial. 
	 * 
	 * @param Nuevo coste. 
	 */
	public void setPenalRetraso1(double penalRetraso1) {
		this.penalRetraso1 = penalRetraso1;
	}

	/**
	 * M�todo de modificaci�n del coste de la penalizaci�n por retraso aumentada. 
	 * 
	 * @param Nuevo coste. 
	 */
	public void setPenalRetraso2(double penalRetraso2) {
		this.penalRetraso2 = penalRetraso2;
	}

	/**
	 * M�todo de modificaci�n del n�mero m�ximo de art�culos que se pueden alquilar simult�neamente. 
	 * 
	 * @param Nuevo n�mero de art�culos. 
	 */
	public void setMaxArticulos(int maxArticulos) {
		this.maxArticulos = maxArticulos;
	}

	/**
	 * M�todo de modificaci�n del nombre del videoclub. 
	 * 
	 * @param Nuevo nombre. 
	 */
	public void setNombreVideoclub(String nombreVideoclub) {
		this.nombreVideoclub = nombreVideoclub;
	}
	
	/**
	 * M�todo de modificaci�n de la contraseña del videoclub. 
	 * 
	 * @param Nueva contraseña. 
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}

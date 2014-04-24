/**
 * 
 */
package socios;

import java.io.BufferedReader;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Este objeto, del que solo existira una instancia, lista todas las tarifas planas vigentes.
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class ListaContratos {
	private static ListaContratos INSTANCE;
	private static int numTarifas = 0;
	private ArrayList<Tarifa> contratos = new ArrayList<Tarifa>();

	/* PATRON SINGLETON */
	
	/**
	 * Constructor de inicializacion: Lee el archivo de contratos y lo vuelca en el objeto ListaContratos creado.
	 * 
	 */
	private ListaContratos() {
		try {
			this.parseListaContratos("contratos.txt");
		}
		catch(IOException ioex) {
			System.out.println(ioex);
		}
		catch(ParseException pex) {
			System.out.println(pex);
		}
		
	}

	/**
	 * Devuelve la instancia unica de la lista de contratos.
	 * 
	 * @return Instancia de ListaContratos.
	 */
	public static ListaContratos getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ListaContratos();
		return INSTANCE;
	}
	
	/**
	 * Parsea el archivo de lista de tarifas. 
	 * 
	 * @param filename Nombre del archivo. 
	 * @throws IOException
	 * @throws ParseException
	 */
	private void parseListaContratos(String filename) throws IOException,
	ParseException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)))) {
			
			String p;
			
			/* La primera linea contiene atributos estaticos de Tarifa y atributos de ListaContratos */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				numTarifas = Integer.parseInt(token.nextToken());
				Tarifa.setLastId(Integer.parseInt(token.nextToken()));
				Tarifa.setExtraPlus(Double.parseDouble(token.nextToken()));
			}
			
			/* La segunda linea contiene los precios de cada tipo de tarifa */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				TarifaPelicula.setPrecio(Double.parseDouble(token.nextToken()));
				TarifaSerie.setPrecio(Double.parseDouble(token.nextToken()));
				TarifaMusica.setPrecio(Double.parseDouble(token.nextToken()));
				TarifaPS.setPrecio(Double.parseDouble(token.nextToken()));
				TarifaPM.setPrecio(Double.parseDouble(token.nextToken()));
				TarifaSM.setPrecio(Double.parseDouble(token.nextToken()));
				TarifaPremium.setPrecio(Double.parseDouble(token.nextToken()));
			}
			

			while ((p = buffer.readLine()) != null) {

				if (!p.equals("")) {
					String[] tokens = p.split(",");
					
					Tarifa tar;
					
					DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
					
					Calendar calI = Calendar.getInstance();
					Calendar calF = Calendar.getInstance();
					
					calI.setTime(df.parse(tokens[3]));
					calI.set(Calendar.HOUR_OF_DAY, 0);
					calI.set(Calendar.MINUTE, 0);
					calI.set(Calendar.SECOND, 0);
					calI.set(Calendar.MILLISECOND, 0);

					calF.setTime(df.parse(tokens[4]));
					calF.set(Calendar.HOUR_OF_DAY, 0);
					calF.set(Calendar.MINUTE, 0);
					calF.set(Calendar.SECOND, 0);
					calF.set(Calendar.MILLISECOND, 0);
					calF.setLenient(true);
					calF.add(Calendar.MILLISECOND, -1);
					calF.add(Calendar.DAY_OF_MONTH, 1);
					
					boolean plus;
					if(tokens[5].equals("S")) plus = true;
					else plus = false;
					
					if(tokens[0].equals("TP")) tar = new TarifaPelicula(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else if(tokens[0].equals("TS")) tar = new TarifaSerie(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else if(tokens[0].equals("TM")) tar = new TarifaMusica(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else if(tokens[0].equals("TPS")) tar = new TarifaPS(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else if(tokens[0].equals("TPM")) tar = new TarifaPM(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else if(tokens[0].equals("TSM")) tar = new TarifaSM(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else if(tokens[0].equals("TPRE")) tar = new TarifaPremium(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), calI, calF, plus);
					else tar = null;
					
					contratos.add(tar);
				}
			}
			buffer.close();
		}
	}

	/**
	 * Crea una nueva tarifa, la anade a la base de datos y la asigna al socio correspondiente.
	 * 
	 * @param soc Socio que contrata la tarifa
	 * @param tipoTarifa Abreviatura de la tarifa que se contrata: TP, TS, TM, TPS, TPM, TSM o TPRE. 
	 * @param plus true si la tarifa es plus, false en caso contrario
	 * @return ID de la tarifa recien creada. 
	 */
	public int crearTarifa(Socio soc, String tipoTarifa, boolean plus) {
		
		Tarifa tar;
		
		if(tipoTarifa.equals("TP")) tar = new TarifaPelicula(soc, plus);
		else if(tipoTarifa.equals("TS")) tar = new TarifaSerie(soc, plus);
		else if(tipoTarifa.equals("TM")) tar = new TarifaMusica(soc, plus);
		else if(tipoTarifa.equals("TPS")) tar = new TarifaPS(soc, plus);
		else if(tipoTarifa.equals("TPM")) tar = new TarifaPM(soc, plus);
		else if(tipoTarifa.equals("TSM")) tar = new TarifaSM(soc, plus);
		else if(tipoTarifa.equals("TPRE")) tar = new TarifaPremium(soc, plus);
		else return -1;
		
		if (!(soc.asignarTarifa(tar))) return -1;
		
		if (!(contratos.add(tar))) return -1;

		numTarifas++;

		return tar.getId();
	}

	/**
	 * Elimina la tarifa de la base de datos y desasocia al socio de la tarifa.
	 * 
	 * @param tar Tarifa a finalizar
	 * @return true si la operacion se ha realizado correctamente, false si hay algun problema.
	 */
	public boolean terminarTarifa(Tarifa tar) {
		
		if(!(tar.getSocio().finTarifa())) return false;
		
		if(!(contratos.remove(tar))) return false;

		numTarifas--;
		return true;
	}

	/**
	 * Metodo que encuentra una tarifa a traves de su identificador.
	 * 
	 * @param id Identificador de la tarifa
	 * @return La tarifa si existe, null si no existe.
	 */
	public Tarifa encuentra(int id) {
		if (id == -1) return null;
		for (Tarifa t : contratos) {
			if (t.getId() == id)
				return t;
		}
		return null;
	}

	
	
	/* GETTERS */

	/**
	 * Metodo de acceso al numero de tarifas actual.
	 * 
	 * @return Numero de tarifas vigentes.
	 */
	public static int getNumTarifas() {
		return numTarifas;
	}

	/**
	 * Metodo de acceso al ArrayList de contratos. 
	 * 
	 * @return Lista de tarifas. 
	 */
	public ArrayList<Tarifa> getContratos() {
		return contratos;
	}
}

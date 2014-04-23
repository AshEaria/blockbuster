/**
 * 
 */
package catalogo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Este objeto, del que solo existira una instancia, lista todos los articulos y categorias existentes. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class Catalogo {
	private static Catalogo INSTANCE;
	private static int numArticulos = 0; 
	private static int numCategorias = 0;
	private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();

	/* PATRON SINGLETON */
	
	/**
	 * Constructor de inicializacion: Lee el archivo de catalogo y lo vuelca en el objeto Catalogo creado.
	 * 
	 */
	private Catalogo() {
		try {
			this.parseCatalogo("articulos.txt");
		}
		catch(IOException ioex) {
			System.out.println(ioex);
		}
		catch(ParseException pex) {
			System.out.println(pex);
		}
	
	}

	/**
	 * Devuelve la instancia unica del catalogo.
	 * 
	 * @return Instancia de Catalogo.
	 */
	public static Catalogo getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Catalogo();
		return INSTANCE;
	}
	
	/**
	 * Parsea el archivo del catalogo. 
	 * 
	 * @param filename Nombre del archivo. 
	 * @throws IOException
	 * @throws ParseException
	 */
	private void parseCatalogo(String filename) throws IOException,
	ParseException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)))) {
			String p;
			
			/* La primera linea contiene atributos estaticos de Articulo y Ejemplar, y atributos de Catalogo */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				numArticulos = Integer.parseInt(token.nextToken());
				numCategorias = Integer.parseInt(token.nextToken());
				Articulo.setLastId(Integer.parseInt(token.nextToken()));
				Ejemplar.setLastId(Integer.parseInt(token.nextToken()));
			}
			
			/* La segunda linea contiene los precios de alquiler por uso */
			p = buffer.readLine();
			if (p != null && !(p.equals(""))) {
				StringTokenizer token = new StringTokenizer(p, ",");
				Pelicula.setPrecio(Double.parseDouble(token.nextToken()));
				Serie.setPrecio(Double.parseDouble(token.nextToken()));
				Musica.setPrecio(Double.parseDouble(token.nextToken()));
			}
			
			ListaEjemplares listaEjem = ListaEjemplares.getInstance();
			
			p = buffer.readLine();

			while (p != null) {

				String[] tokens = p.split(",");
				
				Articulo art;
				int idArt = Integer.parseInt(tokens[1]);
				String tipoCat = new String( (tokens[0].equals("P")) ? "Pelicula" : (tokens[0].equals("S")) ? "Serie" : "Musica" ); 
				
				if(tokens[0].equals("P")) art = new Pelicula(idArt, tokens[2], Integer.parseInt(tokens[3]), tokens[4], Integer.parseInt(tokens[5]));
				else if(tokens[0].equals("S")) art = new Serie(idArt, tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
				else if(tokens[0].equals("M")) art = new Musica(idArt, tokens[2], Integer.parseInt(tokens[3]), tokens[4], Integer.parseInt(tokens[5]));
				else art = null;
				
				articulos.add(art);

				p = buffer.readLine();
				if (p!= null) tokens = p.split(",");
				
				
				while (tokens[0].equals("E") && p != null) {
					EstadoEjemplar estado;
					boolean formato;
					
					if (tokens[2].equals("DISPONIBLE")) estado = EstadoEjemplar.DISPONIBLE;
					else if (tokens[2].equals("ALQUILADO")) estado = EstadoEjemplar.ALQUILADO;
					else if (tokens[2].equals("RETRASADO")) estado = EstadoEjemplar.RETRASADO;
					else estado = EstadoEjemplar.NO_DISPONIBLE;
					
					if(tokens[3].equals("S")) formato = true;
					else formato = false;
					
					listaEjem.anadirEjemplar(Integer.parseInt(tokens[1]), estado, art, formato);

					p = buffer.readLine();
					if (p!= null) tokens = p.split(",");
				}
				
				while (tokens[0].equals("C") && p != null) {
					
					Categoria cat = encuentra(tokens[1], tipoCat);
					art.anadirCategoria(cat);
					
					p = buffer.readLine();
					if (p!= null) tokens = p.split(",");
				}
				
			}
			buffer.close(); 
		}
	}
	
	
	/* METODOS COMPLEJOS */

	/**
	 * Crea una nueva pelicula y la anade a la base de datos. 
	 * 
	 * @param titulo Titulo de la pelicula
	 * @param ano Ano de lanzamiento de la pelicula
	 * @param director Director de la pelicula
	 * @return Id del nuevo articulo. 
	 */
	public int crearPelicula(String titulo, int ano, String director) {
		Pelicula p = new Pelicula(titulo, ano, director);
		
		articulos.add(p);
		return p.getId();
	}
	
	/**
	 * Crea una nueva serie y la anade a la base de datos. 
	 * 
	 * @param titulo Titulo de la pelicula
	 * @param temporada Temporada en la serie
	 * @param volumen Volumen en la temporada
	 * @return Id del nuevo articulo. 
	 */
	public int crearSerie(String titulo, int temporada, int volumen) {
		Serie s = new Serie(titulo, temporada, volumen);
		
		articulos.add(s);
		return s.getId();
	}
	
	/**
	 * Crea un nuevo articulo de musica y lo anade a la base de datos. 
	 * 
	 * @param titulo Titulo del album
	 * @param ano Ano de lanzamiento
	 * @param interprete Interprete del album
	 * @return Id del nuevo articulo. 
	 */
	public int crearMusica(String titulo, int ano, String interprete) {
		Musica m = new Musica(titulo, ano, interprete);
		
		articulos.add(m);
		return m.getId();
	}

	/**
	 * Elimina un articulo de la base de datos y de todas las categorias que contuviese. 
	 * 
	 * @param art Articulo a eliminar.
	 * @return true si el articulo ha sido eliminado con exito, false en caso contrario.
	 */
	public boolean eliminarArticulo(Articulo art) {
		if (art == null)
			return false;

		articulos.remove(art);
		return true;
	}
	
	/**
	 * Crea una nueva categoria y la anade a la lista de categorias. 
	 * 
	 * @param nombre Nombre de la nueva categoria
	 * @param tipoCategoria String con el tipo de la nueva categoria: "Pelicula", "Musica" o "Serie"
	 * @return La categoria recien creada. 
	 */
	public Categoria crearCategoria(String nombre, String tipoCategoria) {
		Categoria cat = new Categoria(nombre, tipoCategoria);
		categorias.add(cat);
		return cat;
	}
	
	/**
	 * Elimina la categoria de todos los articulos que la contuviesen y la quita de la lista. 
	 * 
	 * @param cat Categoria a eliminar
	 * @return true si la operacion se ha efectuado correctamente, false si ha habido algun error. 
	 */
	public boolean eliminarCategoria(Categoria cat) {
		for (Articulo a : cat.getArticulos()) {
			a.quitarCategoria(cat);
		}
		categorias.remove(cat);
		return true;
	}

	/**
	 * Metodo que encuentra un articulo a traves de su identificador.
	 * 
	 * @param id Identificador del articulo
	 * @return El articulo si existe, null si no existe.
	 */
	public Articulo encuentra(int id) {
		if (id == -1) return null;
		for (Articulo a : articulos) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}
	
	/**
	 * Metodo que encuentra una categoria a traves de su nombre y tipo. Si no existe, la crea.
	 * 
	 * @param nombre Nombre de la categoria
	 * @return Categoria correspondiente. Si no existe, la crea. 
	 */
	public Categoria encuentra(String nombre, String tipoCategoria) {
		if (nombre == null || tipoCategoria == null) return null;
		for (Categoria c : categorias) {
			if (c.toStringSinC().equals(nombre) && c.getTipoCategoria().equals(tipoCategoria))
				return c;
		}
		return crearCategoria(nombre, tipoCategoria);
	}
	
	/**
	 * Encuentra los diez articulos mas alquilados del catalogo. 
	 * 
	 * @return Lista con los articulos. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Articulo> topTen() {
		int i=0, x=0;
		ArrayList<Articulo> todos = new ArrayList<Articulo>();
		ArrayList<Articulo> diez = new ArrayList<Articulo>();
	
		todos.addAll(Catalogo.getInstance().getArticulos());
		
		Collections.sort(todos, new Comparator() {
			
			public int compare(Object a1, Object a2) {
				return new Integer(((Articulo) a1).getVeces()).compareTo(new Integer(((Articulo) a2).getVeces()));
			}

		});
			
		if (todos.size() < 10) i=todos.size()-1;
		else i=9;
		while ( i > 0) {
			diez.add(x, todos.get(i));
			i--;
			x++;
		}
		
		return diez;
	}

	/* GETTERS */

	/**
	 * Metodo de acceso al numero total de articulos. 
	 * 
	 * @return Numero de articulos en el sistema. 
	 */
	public static int getNumArticulos() {
		return numArticulos;
	}

	/**
	 * Metodo de acceso al numero total de categorias. 
	 * 
	 * @return Numero de categorias en el sistema. 
	 */
	public static int getNumCategorias() {
		return numCategorias;
	}

	/**
	 * Metodo de acceso a la lista de articulos. 
	 * 
	 * @return ArrayList con todos los articulos del catalogo. 
	 */
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	/**
	 * Metodo de acceso a la lista de categorias de tipo Serie. 
	 * 
	 * @return ArrayList con todas las categorias de tipo Serie. 
	 */
	public ArrayList<Categoria> getCategoriasPelicula() {
		
		ArrayList<Categoria> catPelicula = new ArrayList<>();
		for (Categoria cAux: categorias){
			if (cAux.getTipoCategoria().equals("Pelicula")){
				catPelicula.add(cAux);
			}
		}
		return catPelicula;
	}
	
	/**
	 * Metodo de acceso a la lista de categorias de tipo Serie. 
	 * 
	 * @return ArrayList con todas las categorias de tipo Serie. 
	 */
	public ArrayList<Categoria> getCategoriasSerie() {
		
		ArrayList<Categoria> catSerie = new ArrayList<>();
		for (Categoria cAux: categorias){
			if (cAux.getTipoCategoria().equals("Serie")){
				catSerie.add(cAux);
			}
		}
		return catSerie;
	}
	
	/**
	 * Metodo de acceso a la lista de categorias de tipo Musica. 
	 * 
	 * @return ArrayList con todas las categorias de tipo Musica. 
	 */
	public ArrayList<Categoria> getCategoriasMusica() {
		
		ArrayList<Categoria> catMusica = new ArrayList<>();
		for (Categoria cAux: categorias){
			if (cAux.getTipoCategoria().equals("Musica")){
				catMusica.add(cAux);
			}
		}
		return catMusica;
	}
	
	/**
	 * Metodo de acceso a la lista de categorias. 
	 * 
	 * @return ArrayList con todas las categorias del catalogo. 
	 */
	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	/**
	 * Anade un articulo a la base de datos. 
	 * 
	 * @param art Articulo a anadir. 
	 * @return
	 */
	public boolean anadirArticulo(Articulo art) {
		if (art == null) return false;
		articulos.add(art);
		return true;
	}

	

}
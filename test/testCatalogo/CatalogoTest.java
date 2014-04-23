/**
 * 
 */
package testCatalogo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import catalogo.Articulo;
import catalogo.Catalogo;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * @author Jaime Monedero y Elena Lechuga
 * 
 */

public class CatalogoTest {

	private Catalogo cat;
    private Pelicula p1 = new Pelicula(1017,"The Godfather", 1972, "Francis Ford Coppola",44);
	private Serie s1 = new Serie(1018,"Friends", 7, 1994, 2);
	
	/**
	 * Test method for {@link catalogo.Catalogo#Catalogo(java.util.List)}.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Before
	public void testCatalogo() throws FileNotFoundException, IOException {
		
		 cat = Catalogo.getInstance();
		 assertNotNull(cat);
	}

	/**
	 * Test method for {@link catalogo.Catalogo#anadirArticulo(catalogo.Articulo)}.
	 */
	@Test
	public void testAnadirArticulo() {
			
		assertTrue(cat.anadirArticulo(p1));
		assertTrue(cat.anadirArticulo(s1));
		assertTrue(cat.getArticulos().contains(s1));
		assertTrue(cat.getArticulos().contains(p1));
	}

	/**
	 * Test method for {@link catalogo.Catalogo#quitarArticulo(catalogo.Articulo)}.
	 */
	@Test
	public void testQuitarArticulo() {
	
		cat.eliminarArticulo(s1);
		assertFalse(cat.getArticulos().contains(s1));
		cat.eliminarArticulo(p1);
		assertFalse(cat.getArticulos().contains(p1));
		
	}
	
	/**
	 * Test method for {@link catalogo.Catalogo#topTen()}.
	 */
	@Test
	public void testTopTen() {
	
		ArrayList<Articulo> diez = cat.topTen();
		
		for (int i = 0; i < diez.size() - 1; i++) {
			assertTrue(diez.get(i).getVeces() >= diez.get(i+1).getVeces());
		}
		
	}


}

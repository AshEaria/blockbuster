/**
 * 
 */
package testCatalogo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import catalogo.Categoria;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * @author Jaime Monedero y Elena Lechuga
 * 
 */

public class CategoriaTest {

	private Categoria cat1;
	private Categoria cat2;

	private Pelicula p1 = new Pelicula(1015,"The Godfather",1998 ,"Francis Ford Coppola",15);
	private Serie s1 = new Serie(1016,"Friends", 1994,4, 2);

	/**
	 * Test method for
	 * {@link catalogo.Categoria#Categoria(java.lang.String, java.util.List)}.
	 */
	@Before
	public void testCategoria() {

		cat1 = new Categoria("Drama", "Drama");
		cat2 = new Categoria("Comedia", "Comedia");
		
		assertNotNull(cat1);
		assertNotNull(cat2);
	}

	/**
	 * Test method for
	 * {@link catalogo.Categoria#anadirArticulo(catalogo.Articulo)}.
	 */
	@Test
	public void testAnadirArticulo() {
		
		assertTrue(cat1.anadirArticulo(p1));
		assertTrue(cat2.anadirArticulo(s1));

	}

	/**
	 * Test method for
	 * {@link catalogo.Categoria#quitarArticulo(catalogo.Articulo)}.
	 */
	@Test
	public void testQuitarArticulo() {

		assertTrue(cat1.quitarArticulo(p1));
		assertTrue(cat2.quitarArticulo(s1));

	}

	/**
	 * Test method for {@link catalogo.Categoria#getNombre()}.
	 */
	@Test
	public void testGetNombre() {
		
		assertEquals(cat1.getTipoCategoria(), "Drama");
		assertEquals(cat2.getTipoCategoria(), "Comedia");
	}


}

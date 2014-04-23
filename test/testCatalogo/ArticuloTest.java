/**
 * 
 */
package testCatalogo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import catalogo.Categoria;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * @author Jaime Monedero y Elena Lechuga
 * 
 */

public class ArticuloTest {

	private Pelicula p1;
	private Serie s1;
	private Musica m1;
	
	/**
	 * Test method for {@link catalogo.Articulo#Articulo(java.lang.String, java.util.List, java.util.List)}.
	 */
	@Before
	public void testArticulo() {
		
		p1 = new Pelicula(1001,"The Godfather", 1972,"Francis Ford Coppola", 11);
		s1 = new Serie(1002,"Friends",1994, 2,4);
		
	    m1 = new Musica (1003,"Miles Davies",1959, "Blue in Green",7);
	
		assertNotNull(p1);
		assertNotNull(s1);
		assertNotNull(m1);
	}

	/**
	 * Test method for {@link catalogo.Articulo#anadirCategoria(catalogo.Categoria)}.
	 */
	@Test
	public void testAnadirCategoria() {
		
	
		Categoria cat1 = new Categoria ("Drama", "Drama");
	    
		p1.anadirCategoria(cat1);
		Categoria cat2 = new Categoria ("Comedia", "Drama");
		s1.anadirCategoria(cat2);
		Categoria cat3 = new Categoria ("Jazz", "Drama");
		m1.anadirCategoria(cat3);

		assertTrue(p1.getCategorias().contains(cat1));
		assertTrue(s1.getCategorias().contains(cat2));
		assertTrue(m1.getCategorias().contains(cat3));
	}


}

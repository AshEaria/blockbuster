/**
 * 
 */
package testCatalogo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import catalogo.Ejemplar;
import catalogo.ListaEjemplares;
import catalogo.Pelicula;

/**
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class ListaEjemplaresTest {

	private ListaEjemplares ejemplares;
	/**
	 * Test method for {@link catalogo.ListaEjemplares#getInstance()}.
	 */
	@Before
	public void testGetInstance() {
		assertNull(ejemplares);
		ejemplares=ListaEjemplares.getInstance();
		assertNotNull(ejemplares);
	}

	/**
	 * Test method for {@link catalogo.ListaEjemplares#encuentra(int)}.
	 */
	@Test
	public void testEncuentra() {
		
		Pelicula p1 = new Pelicula(1017,"The Godfather", 1972, "Francis Ford Coppola",44);
		Ejemplar e = new Ejemplar(p1, false);
		ejemplares.getEjemplares().add(e);
		assertNotNull(ejemplares.encuentra(e.getId()));
	}

	

}

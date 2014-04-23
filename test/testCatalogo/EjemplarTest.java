/**
 * 
 */
package testCatalogo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * @author Jaime Monedero y Elena Lechuga
 * 
 */

public class EjemplarTest {

	private Ejemplar ej1, ej2, ej3, ej4;
	/**
	 * Test method for {@link catalogo.Ejemplar#Ejemplar(int, catalogo.EstadoEjemplar, catalogo.Articulo, boolean)}.
	 */
	@Before
	public void testEjemplar() {
		
		//ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		//ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

		Pelicula p1 = new Pelicula(1018,"The Godfather", 1972,
				"Francis Ford Coppola",20);
		Serie s1 = new Serie(1019,"Friends",1994,1, 2);
		
	    Musica m1 = new Musica (1031,"Miles Davies",1959, "Blue in Green",40);
		
	    assertNotNull(p1);
		assertNotNull(s1);
		assertNotNull(m1);
	    
		ej1 = new Ejemplar(1, EstadoEjemplar.DISPONIBLE,1018, true);
		ej2 = new Ejemplar(2, EstadoEjemplar.ALQUILADO,1019, false);
		ej3 = new Ejemplar(3, EstadoEjemplar.NO_DISPONIBLE,1020, true);
		ej4 = new Ejemplar(4, EstadoEjemplar.RETRASADO,1031, true);
		
		assertNotNull(ej1);
		assertNotNull(ej2);
		assertNotNull(ej3);
		
	}

	/**
	 * Test method for {@link catalogo.Ejemplar#alquilar()}.
	 */
	@Test
	public void testAlquilar() {
	
		assertTrue(ej1.alquilar());
		assertFalse(ej2.alquilar());
		assertFalse(ej3.alquilar());
		assertFalse(ej4.alquilar());
	}

	/**
	 * Test method for {@link catalogo.Ejemplar#devolver(boolean)}.
	 */
	@Test
	public void testDevolver() {
		assertFalse(ej1.devolver(true));
		assertTrue(ej2.devolver(true));
		assertFalse(ej3.devolver(false));
		assertTrue(ej4.devolver(false));
	}

	/**
	 * Test method for {@link catalogo.Ejemplar#darBaja()}.
	 */
	@Test
	public void testDarBaja() {
		
		assertTrue(ej1.darBaja()); //Disponible
		assertFalse(ej2.darBaja()); //Alquilado
		assertTrue(ej3.darBaja()); //No diponible
		assertFalse(ej4.darBaja()); //Retrasado
	}

	/**
	 * Test method for {@link catalogo.Ejemplar#reponer()}.
	 */
	@Test
	public void testReponer() {

		assertFalse(ej1.reponer()); //Disponible
		assertFalse(ej2.reponer()); //Alquilado
		assertTrue(ej3.darBaja()); //No diponible
		assertFalse(ej4.reponer()); //Retrasado

	}


}

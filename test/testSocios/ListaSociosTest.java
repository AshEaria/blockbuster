/**
 * 
 */
package testSocios;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Aplicacion;
import socios.EstadoSocio;
import socios.ListaSocios;
import socios.Socio;

/**
 * @author Elena Lechuga y Jaime Monedero
 */

public class ListaSociosTest {

	private ListaSocios socios;
	/**
	 * Test method for {@link socios.ListaSocios#getInstance()}.
	 */
	@Before
	public void testGetInstance() {
	
		Aplicacion aplicacion = Aplicacion.getInstance();
		aplicacion.inicializaListas();
		
		assertNull(socios);
		socios=ListaSocios.getInstance();
		assertNotNull(socios);
	}

	/**
	 * Test method for {@link socios.ListaSocios#crearSocio(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCrearSocio() {
		int numSoc = socios.crearSocio("94451784", "Elena", "654788422", "ele@hotmail.com", "Madrid");
		assertNotNull(socios.encuentra(numSoc));
	}

	/**
	 * Test method for {@link socios.ListaSocios#darBajaSocio()}.
	 */
	@Test
	public void testDarBajaSocio() {
		int numSoc = socios.crearSocio("07466000", "Pepa", "945118764", "pepa@gmail.es", "C/Salamanca, Madrid");
		assertTrue(socios.darBajaSocio(socios.encuentra(numSoc)));
	}

	/**
	 * Test method for {@link socios.ListaSocios#encontrar(int)}.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testEncontrar() throws FileNotFoundException, IOException {

		int numeroDesocio = socios.getNumSocios();
		socios.crearSocio("07466000", "Pepa", "945118764", "pepa@gmail.es", "C/Salamanca, Madrid");
		assertNotNull(socios.encuentra(numeroDesocio));
	}
	
	/**
	 * Test method for {@link socios.ListaSocios#getMorosos()}.
	 */
	@Test
	public void testGetMorosos() {
		
		ArrayList<Socio> morosos = socios.getMorosos();
		
		for (Socio s : morosos) {
			assertTrue(s.getEstadoSocio() == EstadoSocio.MOROSO);
		}
	}

	

}

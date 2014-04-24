/**
 * 
 */
package testAplicacion;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Aplicacion;

/**
 * @author elena
 *
 */
public class AplicacionTest {

	private Aplicacion api = Aplicacion.getInstance();
	/**
	 * Test method for {@link aplicacion.Aplicacion#main()}.
	 */
	@Test
	public void testMain() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link aplicacion.Aplicacion#inicializaListas()}.
	 */
	@Before
	public void testInicializaListas() {
		assertTrue(api.inicializaListas());
	}

	/**
	 * Test method for {@link aplicacion.Aplicacion#abreAplicacion()}.
	 */
	@Test
	public void testAbreAplicacion() {
		//fail("Not yet implemented"); PARTE GRAFICA
	}

	/**
	 * Test method for {@link aplicacion.Aplicacion#cierraAplicacion()}.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testCierraAplicacion() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		api.cierraAplicacion();
	}
	
}
/**
 * 
 */
package testAplicacion;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.Ajustes;

/**
 * Tester de la clase Ajustes. 
 *
 * @author Elena Lechuga y Jaime Monedero
 */
public class AjustesTest {

	/**
	 * Test method for {@link aplicacion.Ajustes#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		
		Ajustes aj = Ajustes.getInstance();
		
		assertNotNull(aj);
		
		/* Comprobamos que los datos se han recuperado correctamente de fichero */
		
		assertTrue(aj.getDias() == 3);
		assertTrue(aj.getDiasPlus() == 4);
		assertTrue(aj.getDiasNivelRetraso() == 10);
		assertTrue(aj.getPenalRetraso1() == 1.0);
		assertTrue(aj.getPenalRetraso2() == 5.0);
		assertTrue(aj.getMaxArticulos() == 3);
		assertTrue(aj.getNombreVideoclub().equals("Blockbuster"));
	}

}

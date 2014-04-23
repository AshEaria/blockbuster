/**
 * 
 */
package testSocios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import socios.Socio;
import socios.TarifaMusica;
import socios.TarifaPM;
import socios.TarifaPS;
import socios.TarifaPelicula;
import socios.TarifaPremium;
import socios.TarifaSM;
import socios.TarifaSerie;

/**
 * Tester de la clase abstracta Tarifa
 * 
 * @author Elena Lechuga y Jaime Monedero
 */
public class TarifaTest {

	private Socio socio = new Socio ("784479152", "Juan", "647899514", "j@hotmail.com","C/De la Rosa,Madrid");

	private TarifaMusica tm;
	private TarifaPelicula tp;
	private TarifaPM tPM;
	private TarifaPremium tPRE;
	private TarifaPS tPS;
	private TarifaSerie ts;
	private TarifaSM tSM;
	
	/**
	 * Test method for {@link socios.Tarifa#Tarifa(socios.Socio, boolean)}.
	 */
	@Before
	public void testTarifa() {
		tm = new TarifaMusica(socio, false);
	    tp = new TarifaPelicula(socio, false);
		tPM = new TarifaPM(socio, true);
		tPRE = new TarifaPremium(socio, true);
		tPS = new TarifaPS(socio, false);
		ts = new TarifaSerie(socio, true);
		tSM = new TarifaSM(socio, false);
		
		 assertNotNull(tm);
		 assertNotNull(tp);
		 assertNotNull(tPM);
		 assertNotNull(tPRE);
		 assertNotNull(tPS);
		 assertNotNull(ts);
		 assertNotNull(tSM);
	}

	/**
	 * Test method for {@link socios.Tarifa#cubre(java.lang.String)}.
	 */
	@Test
	public void testCubre() {
		
		assertTrue(tm.cubre("Musica"));
		assertFalse(tm.cubre("Pelicula"));
		assertTrue(tPM.cubre("Musica"));
		assertTrue(tPM.cubre("Pelicula"));
		assertFalse(tPM.cubre("Serie"));
		assertFalse(tSM.cubre("Pelicula"));
		assertTrue(tPRE.cubre("Musica"));
		
	}

}

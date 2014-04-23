/**
 * 
 */
package testSocios;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import socios.ListaContratos;
import socios.ListaSocios;
import socios.Socio;
import socios.Tarifa;


/**
 * @author elena
 *
 */
public class ListaContratosTest {
	private Socio soc;

	ListaContratos lista;
	/**
	 * Test method for {@link socios.ListaContratos#getInstance(java.lang.String)}.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Before
	public void testGetInstance() throws IOException, ParseException {
		
	    lista = ListaContratos.getInstance();
		assertNotNull(lista);
		
		soc = new Socio("51496551", "Hayao Miyazaki", "916574855", "hayao@miyazaki.jp", "Somewhere in Japan");
		ListaSocios listaS = ListaSocios.getInstance();
		listaS.getSocios().add(soc);
	}

	/**
	 * Test method for {@link socios.ListaContratos#crearTarifa(socios.Socio, java.lang.Class, boolean)}.
	 */
	@Test
	public void testCrearTarifa() {
		int idTar = lista.crearTarifa(soc, "TM", true);
		assertNotNull(lista.encuentra(idTar));
	}

	/**
	 * Test method for {@link socios.ListaContratos#terminarTarifa(socios.Tarifa)}.
	 */
	@Test
	public void testTerminarTarifa() {
		
		int idTar = lista.crearTarifa(soc, "TM", true);
		
		Tarifa tar = lista.encuentra(idTar);
		
		assertTrue(lista.terminarTarifa(tar));
	}

	/**
	 * Test method for {@link socios.ListaContratos#encuentra(int)}.
	 */
	@Test
	public void testEncuentra() {
		
		assertNotNull(lista.encuentra(4001));
	}


}

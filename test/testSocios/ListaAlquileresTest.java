/**
 * 
 */
package testSocios;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import socios.Alquiler;
import socios.EstadoSocio;
import socios.ListaAlquileres;
import socios.ListaSocios;
import socios.Socio;
import catalogo.Catalogo;
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;



/**
 * Tester para ListaAlquileres. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class ListaAlquileresTest {

	private ListaAlquileres lista;
	private Pelicula scottPilgrim;
	private Musica offenbach;
	private Serie killLaKill;
	private Ejemplar spe; 
	private Ejemplar ofe;
	private Ejemplar klk;
	private Socio miyazaki;
	
	/* CONSTRUCTOR */
	/**
	 * Test method for {@link socios.ListaAlquileres#getInstance()}.
	 */
	@Before
	public void testListaAlquileres() {
		
		Catalogo.getInstance();
		lista = ListaAlquileres.getInstance();
		assertNotNull(lista);
		
		scottPilgrim = new Pelicula(1011,"Scott Pilgrim vs the world", 2005, "Scott", 16);
		spe = new Ejemplar(2011, EstadoEjemplar.DISPONIBLE,1011, false);
		offenbach = new Musica(1012,"Orfeo en los infiernos",1900, "Offenbach",15);
		ofe = new Ejemplar(2012, EstadoEjemplar.RETRASADO,1012, true);
		killLaKill = new Serie(1013,"Kill la Kill", 1,1,13);
		klk = new Ejemplar(2013, EstadoEjemplar.NO_DISPONIBLE,1013, true);
		
		Catalogo cat = Catalogo.getInstance();
		cat.getArticulos().add(killLaKill);
		cat.getArticulos().add(offenbach);
		cat.getArticulos().add(scottPilgrim);
		
		ListaEjemplares listaE = ListaEjemplares.getInstance();
		listaE.getEjemplares().add(spe);
		listaE.getEjemplares().add(ofe);
		listaE.getEjemplares().add(klk);
		
		miyazaki = new Socio(0020, "51496551", "Hayao Miyazaki", "916574855", "hayao@miyazaki.jp", "Somewhere in Japan", -1, EstadoSocio.SIN_ALQUILERES, new ArrayList<Alquiler>());
		ListaSocios listaS = ListaSocios.getInstance();
		listaS.getSocios().add(miyazaki);
	}
	
	/**
	 * Test method for {@link socios.ListaAlquileres#crearAlquiler(socios.Socio, catalogo.Ejemplar)}.
	 */
	@Test
	public void testCrearAlquiler() {
		
		Alquiler alq;
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		
		assertTrue(lista.crearAlquiler(ofe, miyazaki) == -1);
		
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		assertTrue(miyazaki.getAlquileres().isEmpty());
		assertTrue(ofe.getEstadoEjem() == EstadoEjemplar.RETRASADO);
		
		assertTrue(lista.crearAlquiler(klk, miyazaki) == -1);
		
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		assertTrue(miyazaki.getAlquileres().isEmpty());
		assertTrue(klk.getEstadoEjem() == EstadoEjemplar.NO_DISPONIBLE);
		
		assertFalse(lista.crearAlquiler(spe, miyazaki) == -1);
		
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.CON_ALQUILERES);
		assertTrue(spe.getEstadoEjem() == EstadoEjemplar.ALQUILADO);
		assertNotNull(alq = miyazaki.getAlquileres().get(0));
		assertTrue(alq.getSocio().getNumSocio() == miyazaki.getNumSocio());
		assertTrue(miyazaki.getAlquileres().contains(alq));
		assertTrue(lista.getAlquileres().contains(alq));
		
	}

	/**
	 * Test method for {@link socios.ListaAlquileres#terminarAlquiler(socios.Alquiler, boolean)}.
	 */
	@Test
	public void testTerminarAlquiler() {
		
		lista.crearAlquiler(spe, miyazaki);
		
		Alquiler alq = miyazaki.getAlquileres().get(0);
		
		assertTrue(lista.terminarAlquiler(alq, false));
		
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		assertTrue(alq.getEjemplar().getEstadoEjem() == EstadoEjemplar.DISPONIBLE);
		assertTrue(miyazaki.getAlquileres().isEmpty());
		assertFalse(miyazaki.getAlquileres().contains(alq));
		assertFalse(lista.getAlquileres().contains(alq));
		
		assertFalse(lista.terminarAlquiler(alq, false));
		
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		assertTrue(miyazaki.getAlquileres().isEmpty());
		assertTrue(alq.getSocio().getNumSocio() == miyazaki.getNumSocio());
		assertFalse(miyazaki.getAlquileres().contains(alq));
		assertFalse(lista.getAlquileres().contains(alq));
		
	}

}

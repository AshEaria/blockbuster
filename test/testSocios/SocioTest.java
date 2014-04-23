/**
 * 
 */
package testSocios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import socios.Alquiler;
import socios.EstadoSocio;
import socios.ListaAlquileres;
import socios.ListaContratos;
import socios.ListaSocios;
import socios.Socio;
import socios.Tarifa;
import socios.TarifaPM;
import socios.TarifaSerie;
import aplicacion.Ajustes;
import catalogo.Catalogo;
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * Tester de la clase Socio. 
 *
 * @author Elena Lechuga y Jaime Monedero
 */
public class SocioTest {
	private Socio miyazaki;
	private Pelicula scottPilgrim;
	private Musica offenbach;
	private Serie killLaKill;
	private Ejemplar spe; 
	private Ejemplar ofe;
	private Ejemplar klk;
	private Alquiler alq1;
	private Alquiler alq2;
	private Alquiler alq3;
	private Tarifa t1;
	private Tarifa t2;

	@Before
	public void inicializa() {

		//ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		//ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		
		miyazaki = new Socio("51496551", "Hayao Miyazaki", "916574855", "hayao@miyazaki.jp", "Somewhere in Japan");
		
		ListaSocios listaS = ListaSocios.getInstance();
		listaS.getSocios().add(miyazaki);
		
		t1 = new TarifaPM(miyazaki, false);
		t2 = new TarifaSerie(miyazaki, false);
		
		ListaContratos lista = ListaContratos.getInstance();
		lista.getContratos().add(t1);
		lista.getContratos().add(t2);
		
		scottPilgrim = new Pelicula(1011,"Scott Pilgrim vs the world", 2005, "Scott", 16);
		spe = new Ejemplar(2011, EstadoEjemplar.DISPONIBLE,1011, false);
		offenbach = new Musica(1012,"Orfeo en los infiernos",1900, "Offenbach",15);
		ofe = new Ejemplar(2012, EstadoEjemplar.DISPONIBLE,1012, true);
		killLaKill = new Serie(1013,"Kill la Kill", 1,1,13);
		klk = new Ejemplar(2013, EstadoEjemplar.DISPONIBLE,1013, true);
		
		Catalogo cat = Catalogo.getInstance();
		cat.getArticulos().add(killLaKill);
		cat.getArticulos().add(offenbach);
		cat.getArticulos().add(scottPilgrim);
		
		ListaEjemplares listaE = ListaEjemplares.getInstance();
		listaE.getEjemplares().add(spe);
		listaE.getEjemplares().add(ofe);
		listaE.getEjemplares().add(klk);
		
		Ajustes aj = Ajustes.getInstance();
		
		aj.setMaxArticulos(2);
		
		alq1 = new Alquiler(spe,miyazaki);
		alq2 = new Alquiler(ofe,miyazaki);
		alq3 = new Alquiler(klk,miyazaki);
		
		ListaAlquileres listaA = ListaAlquileres.getInstance();
		listaA.getAlquileres().add(alq1);
		listaA.getAlquileres().add(alq2);
		listaA.getAlquileres().add(alq3);
		
		
	}

	/**
	 * Test method for {@link socios.Socio#asignarAlquiler(socios.Alquiler)}.
	 */
	@Test
	public void testAsignarAlquiler() {
		
		assertTrue(miyazaki.asignarAlquiler(alq1));
		assertTrue(miyazaki.getAlquileres().contains(alq1));
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.CON_ALQUILERES);
		
		assertTrue(miyazaki.asignarAlquiler(alq2));
		assertTrue(miyazaki.getAlquileres().contains(alq2));
		
		assertFalse(miyazaki.asignarAlquiler(alq3));
		assertFalse(miyazaki.getAlquileres().contains(alq3));
	}

	/**
	 * Test method for {@link socios.Socio#asignarTarifa(socios.Tarifa)}.
	 */
	@Test
	public void testAsignarTarifa() {
		
		assertTrue(miyazaki.asignarTarifa(t1));
		assertFalse(miyazaki.asignarTarifa(t2));
		
		assertTrue(miyazaki.getTarifa().getId() == t1.getId());
		assertTrue(miyazaki.getTarifa().cubre("Pelicula"));
		assertTrue(miyazaki.getTarifa().cubre("Musica"));
		assertFalse(miyazaki.getTarifa().cubre("Serie"));
	}

	/**
	 * Test method for {@link socios.Socio#finAlquiler(socios.Alquiler)}.
	 */
	@Test
	public void testFinAlquiler() {
		
		miyazaki.asignarAlquiler(alq1);
		miyazaki.asignarAlquiler(alq2);
		
		assertTrue(miyazaki.finAlquiler(alq1));
		assertTrue(miyazaki.getAlquileres().size() == 1);
		assertFalse(miyazaki.getAlquileres().contains(alq1));
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.CON_ALQUILERES);
		
		assertTrue(miyazaki.finAlquiler(alq2));
		assertTrue(miyazaki.getAlquileres().size() == 0);
		assertFalse(miyazaki.getAlquileres().contains(alq2));
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
		
		assertFalse(miyazaki.finAlquiler(alq1));
		assertFalse(miyazaki.getAlquileres().size() == 1);
		assertFalse(miyazaki.getAlquileres().contains(alq1));
		assertTrue(miyazaki.getEstadoSocio() == EstadoSocio.SIN_ALQUILERES);
	}

	/**
	 * Test method for {@link socios.Socio#finTarifa()}.
	 */
	@Test
	public void testFinTarifa() {
		miyazaki.asignarTarifa(t1);
		
		assertTrue(miyazaki.finTarifa());
		assertTrue(miyazaki.getTarifa() == null);
	}

	/**
	 * Test method for {@link socios.Socio#darBaja()}.
	 */
	@Test
	public void testDarBaja() {

		miyazaki.asignarAlquiler(alq1);
		
		assertFalse(miyazaki.darBaja());
		
		miyazaki.finAlquiler(alq1);
		
		assertTrue(miyazaki.darBaja());
		
		assertTrue(miyazaki.getTarifa() == null);
	}

}

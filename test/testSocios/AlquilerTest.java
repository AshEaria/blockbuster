/**
 * 
 */
package testSocios;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import socios.Alquiler;
import socios.ListaSocios;
import socios.Socio;
import aplicacion.Ajustes;
import catalogo.Catalogo;
import catalogo.Ejemplar;
import catalogo.EstadoEjemplar;
import catalogo.ListaEjemplares;
import catalogo.Musica;
import catalogo.Pelicula;
import catalogo.Serie;

/**
 * Tester de la clase Alquiler. 
 * 
 * @author Jaime Monedero y Elena Lechuga
 */
public class AlquilerTest {
	private Calendar today; 
	private Calendar later;
	private Calendar before;
	private Pelicula scottPilgrim;
	private Musica offenbach;
	private Serie killLaKill;
	private Ejemplar spe; 
	private Ejemplar ofe;
	private Ejemplar klk;
	private Socio miyazaki;
	
	@Before 
	public void inicializa() {
		today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.setLenient(true);
		
		later = Calendar.getInstance();
		later.set(Calendar.HOUR_OF_DAY, 0);
		later.set(Calendar.MINUTE, 0);
		later.set(Calendar.SECOND, 0);
		later.set(Calendar.MILLISECOND, 0);
		later.setLenient(true);
		later.add(Calendar.DAY_OF_MONTH, 3);
		later.add(Calendar.MILLISECOND, -1);
		
		before = Calendar.getInstance();
		before.set(Calendar.HOUR_OF_DAY, 0);
		before.set(Calendar.MINUTE, 0);
		before.set(Calendar.SECOND, 0);
		before.set(Calendar.MILLISECOND, 0);
		before.setLenient(true);
		before.add(Calendar.DAY_OF_MONTH, -1);
		
		//ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		//ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		
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
		
		miyazaki = new Socio("51496551B", "Hayao Miyazaki", "916574855", "hayao@miyazaki.jp", "Somewhere in Japan");
		
		ListaSocios listaS = ListaSocios.getInstance();
		listaS.getSocios().add(miyazaki);
		
		Ajustes aj = Ajustes.getInstance();
		
		aj.setDias(3);
		aj.setDiasPlus(5);
	}
	
	/**
	 * 
	 * Test del constructor {@link socios.Alquiler#Alquiler(catalogo.Ejemplar, socios.Socio)}.
	 */
	@Test
	public void testAlquiler() {
		
	    Alquiler a1 = new Alquiler(spe, miyazaki);
	    assertNotNull(a1);
		Alquiler a2 = new Alquiler(ofe, miyazaki);
		assertNotNull(a2);
		Alquiler a3 = new Alquiler(klk, miyazaki);
		assertNotNull(a3);
		
		assertTrue(a1.getEjemplar().getId() == spe.getId());
		assertTrue(a2.getEjemplar().getId() == ofe.getId());
		assertTrue(a3.getEjemplar().getId() == klk.getId());
		
		assertTrue(a1.getFechaInicio().equals(today));
		assertTrue(a2.getFechaInicio().equals(today));
		assertTrue(a3.getFechaInicio().equals(today));
		
		assertTrue(a1.getSocio().getNumSocio() == miyazaki.getNumSocio());
		assertTrue(a2.getSocio().getNumSocio() == miyazaki.getNumSocio());
		assertTrue(a3.getSocio().getNumSocio() == miyazaki.getNumSocio());
		
		assertTrue(a1.getFechaFin().equals(later));
		assertTrue(a2.getFechaFin().equals(later));
		assertTrue(a3.getFechaFin().equals(later));
	}

	/**
	 * Test de {@link socios.Alquiler#diasRetraso()}.
	 */
	@Test
	public void testDiasRetraso() {
		Alquiler a1 = new Alquiler(spe, miyazaki);
		
		int one = a1.diasRetraso();
		
		a1.setFechaFin(today);
		int two = a1.diasRetraso();
		
		a1.setFechaFin(before);
		int three = a1.diasRetraso();
		
		assertTrue(one == 0);
		assertTrue(two == 1);
		assertTrue(three == 2);
	}

}

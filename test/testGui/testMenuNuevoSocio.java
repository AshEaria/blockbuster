/**
 * 
 */
package testGui;

import javax.swing.JFrame;
import controladores.NuevoSocio;

/**
 * @author elena
 *
 */
public class testMenuNuevoSocio {
	
public static void main(String[] args) {
		
		JFrame ventana = new JFrame("BlockBusters - Modo Empleado");
		NuevoSocio controlador = new NuevoSocio();
		ventana.setContentPane(controlador.getVista());
		ventana.getContentPane().setVisible(true);

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(600, 400);
		ventana.setVisible(true);
	}

}

/**
 * 
 */
package testGui;

import javax.swing.JFrame;
import aplicacion.Aplicacion;

/**
 * @author elena
 *
 */
public class testMenuAplicacion {
	
public static void main(String[] args) {
		
		JFrame ventana = new JFrame("BlockBusters");
		Aplicacion controlador = new Aplicacion();
		ventana.setContentPane(controlador.getVista());
		ventana.getContentPane().setVisible(true);

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(500, 500);
		ventana.setVisible(true);
	}


}

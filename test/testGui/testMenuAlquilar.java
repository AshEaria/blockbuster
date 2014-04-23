/**
 * 
 */
package testGui;
import javax.swing.JFrame;

import controladores.Alquilar;



/**
 * @author e280219
 *
 */
public class testMenuAlquilar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame ventana = new JFrame("BlockBusters - Modo Empleado");
		Alquilar controlador = new Alquilar();
		ventana.setContentPane(controlador.getVista());
		ventana.getContentPane().setVisible(true);

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(550, 300);
		ventana.setVisible(true);
	}

}

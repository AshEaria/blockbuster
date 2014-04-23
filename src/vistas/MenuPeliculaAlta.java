/**
 * 
 */
package vistas;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import catalogo.Catalogo;
import catalogo.Categoria;

/**
 * @author elena
 * 
 */
public class MenuPeliculaAlta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton aceptar;
	private JButton atras;
	private JTextField Ttitulo;
	private JComboBox<?> Tano;
	private JComboBox<?> TNumEjem;
	private ButtonGroup formato;
	private JRadioButton DVD;
	private JRadioButton bluray;
	private JTextField Tdirector;
	private JCheckBox[] Tcategoria;
	//private JTextField NuevaCat;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPeliculaAlta() {

		/* PANTALLA PRINCIPAL */

		/* Crear componentes */
		JLabel Ltitulo = new JLabel("Dar alta pelicula");
		JLabel LTitulo = new JLabel("Titulo:");
		LTitulo.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Lano = new JLabel("Año:");
		Lano.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Linterprete = new JLabel("Director:");
		Linterprete.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Lcategoria = new JLabel("Categoría:");
		Lcategoria.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel LNumEjem = new JLabel("Nº Ejemplares:");
		LNumEjem.setBorder(BorderFactory.createEmptyBorder(1, 1, 25, 40));
		JLabel Lformato = new JLabel("Formato:");
		Lformato.setBorder(BorderFactory.createEmptyBorder(1, 30, 5, 40));

		/* Recoger componentes */

		/* Titulo */
		Ttitulo = new JTextField();
		
		/* Año */
		String[] ano = new String[81];
		int x = 0;
		for (int i = 1934; i <= 2014; i++) {
			ano[x] = String.valueOf(i);
			x++;
		}
		Tano = new JComboBox(ano);
		/* Ejemplares */
		String[] ejem = new String[200];
	    x = 0;
		for (int i = 1; i <= 200; i++) {
			ejem[x] = String.valueOf(i);
			x++;
		}
		TNumEjem = new JComboBox(ejem);
		
		bluray = new JRadioButton("bluray");
		DVD = new JRadioButton("DVD");
		
		formato = new ButtonGroup();
		formato.add(bluray);
		formato.add(DVD);
		
		/* Interprete */
		Tdirector = new JTextField();

		/* Categoria */
		ArrayList<Categoria>  ListaCategorias= Catalogo.getInstance().getCategoriasPelicula();
		String[] categorias= new String[ListaCategorias.size()];
		int iC=0;
		for (Categoria cAux: ListaCategorias){
			categorias[iC]=cAux.toStringSinC();
			iC++;
		}
		
		Tcategoria = new JCheckBox[categorias.length];
		JPanel PcategoriaF1 = new JPanel();
		JPanel PcategoriaF2 = new JPanel();

		for (int i = 0; i < categorias.length; i++) {
			if (i < categorias.length)
				Tcategoria[i] = new JCheckBox(categorias[i]);
		
			if (i < 4)
				PcategoriaF1.add(Tcategoria[i]);
			else
				PcategoriaF2.add(Tcategoria[i]);
		}


		ImageIcon imAceptar = new ImageIcon("Aceptar.png");
		aceptar = new JButton(imAceptar);
		aceptar.setBorder(null);
		ImageIcon imAtras = new ImageIcon("atras.png");
		atras = new JButton(imAtras);
		atras.setBorder(null);

		/* Modificar componentes */
		// Ltitulo.setBorder(BorderFactory.createEmptyBorder(0, 25, 45, 25));
		Ltitulo.setFont(new Font("Georgia", Font.CENTER_BASELINE, 30));

		/* Asignar nombres para Action Listener */
		aceptar.setActionCommand("modoPeliculaAlta");
		atras.setActionCommand("modoGerente");

		/* Crear paneles */

		JPanel columna1 = new JPanel();
		JPanel columna2 = new JPanel();
		JPanel columna = new JPanel();
		JPanel musica = new JPanel();
		JPanel panel = new JPanel();
		JPanel Paceptar = new JPanel();
		JPanel Patras = new JPanel();
		JPanel PTcategoria = new JPanel();
		JPanel PTITULO = new JPanel();
		JPanel Pejemplares = new JPanel();

		/* Crear paneles para cada JTextField */
		JPanel PTtitulo = new JPanel();
		JPanel PTfecha = new JPanel();
		JPanel PTinterprete = new JPanel();

		/* Modoficar paneles */
		PTtitulo.setLayout(new BoxLayout(PTtitulo, BoxLayout.LINE_AXIS));
		PTfecha.setLayout(new BoxLayout(PTfecha, BoxLayout.X_AXIS));
		PTinterprete.setLayout(new BoxLayout(PTinterprete, BoxLayout.LINE_AXIS));
		PTinterprete.setLayout(new BoxLayout(PTinterprete, BoxLayout.X_AXIS));
		PTcategoria.setLayout(new BoxLayout(PTcategoria, BoxLayout.Y_AXIS));
		PcategoriaF1.setLayout(new BoxLayout(PcategoriaF1, BoxLayout.X_AXIS));
		PcategoriaF2.setLayout(new BoxLayout(PcategoriaF2, BoxLayout.X_AXIS));

		columna1.setLayout(new BoxLayout(columna1, BoxLayout.Y_AXIS));
		columna2.setLayout(new BoxLayout(columna2, BoxLayout.Y_AXIS));
		columna.setLayout(new BoxLayout(columna, BoxLayout.X_AXIS));
		// Paceptar.setLayout(new BoxLayout(Paceptar, BoxLayout.Y_AXIS));
		Patras.setLayout(new BoxLayout(Patras, BoxLayout.Y_AXIS));
		musica.setLayout(new BoxLayout(musica, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		PTtitulo.setBorder(BorderFactory.createEmptyBorder(1, 5, 15, 0));
		PTfecha.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 1));
		PTinterprete.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 0));
		PTcategoria.setBorder(BorderFactory.createEmptyBorder(5, 5, 20, 1));
		Pejemplares.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		/* ventana */
		PTITULO.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		columna2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 58));
		Patras.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 34));
		
		/* Fondo de la aplicacion */
		// this.setBackground(new Color(0xBCF5A9));

		/* Añadir componentes sus respectivos paneles */
		PTtitulo.add(Ttitulo);
		PTfecha.add(Tano);
		PTinterprete.add(Tdirector);
		PTcategoria.add(PcategoriaF1);
		PTcategoria.add(PcategoriaF2);
		
		Pejemplares.add(TNumEjem);
		Pejemplares.add(Lformato);
		Pejemplares.add(bluray);
		Pejemplares.add(DVD);
		
		columna2.add(Pejemplares);
		columna2.add(PTtitulo);
		columna2.add(PTfecha);
		columna2.add(PTinterprete);
		columna2.add(PTcategoria);

		columna1.add(LNumEjem);
		columna1.add(LTitulo);
		columna1.add(Lano);
		columna1.add(Linterprete);
		columna1.add(Lcategoria);

		columna.add(columna1);
		columna.add(columna2);
		PTITULO.add(Ltitulo);
		musica.add(PTITULO);
		musica.add(columna);

		Paceptar.add(aceptar);
		musica.add(Paceptar);
		Patras.add(atras);
		panel.add(Patras);
		panel.add(musica);
		// panel.add(Paceptar);

		/* Anadir el panel PRINCIPAL al cardLAyout PRINCIPAL */
		this.add(panel, "PMusicaAlta");

	}


	public JComboBox<?> getCajaNumEjem() {
		return TNumEjem;
	}

	public JRadioButton getCajaDVD() {
		return DVD;
	}

	public JRadioButton getCajaBluray() {
		return bluray;
	}
	
	public JTextField getCajaTitulo() {
		return Ttitulo;
	}

	public JComboBox<?> getCajaAno() {
		return Tano;
	}

	public JTextField getCajaInterprete() {
		return Tdirector;
	}

	public JCheckBox[] getCajaCategoria() {
		return Tcategoria;
	}

	// m�todo para asignar un controlador al bot�n
	public void setControlador(ActionListener c) {

		atras.addActionListener(c);
		aceptar.addActionListener(c);
	}

}

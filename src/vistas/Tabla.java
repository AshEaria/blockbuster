/**
 * 
 */
package vistas;


import javax.swing.table.AbstractTableModel;

/**
 * @author elena
 * 
 */
public class Tabla extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] titulos;
	private Object[][] filas;

	public Tabla(Object[][] filas, String[] titulos) {
		this.titulos = titulos;
		this.filas = filas;
	}

	public int getColumnCount() {
		return titulos.length;
	}

	public int getRowCount() {
		return filas.length;
	}

	public Object getValueAt(int row, int col) {
		return filas[row][col];
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void setValueAt(Object value, int row, int col) {
		filas[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public String getColumnName(int col) {
		return titulos[col];
	}

	public Class<? extends Object> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
		
	}
	
}
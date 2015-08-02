package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

 public abstract class ModeloTabla <T> extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	// Nombres de las columnas
	private String _columnName[] ;
	
	protected ArrayList<T> datos;
	
	public ModeloTabla(String [] columnames ) {
		this._columnName = columnames;
		this.datos = new ArrayList<T>();
	}
	
	/**
	 * Establece los datos de la tabla de memoria
	 * @param data
	 */
	public void establecerTabla (ArrayList <T> lista){
		this.datos = lista;
	}
	
	/**
	 * Devuelve el tamano de la fila 
	 */
	public int getRowCount() {
		return this.datos.size();
	}

	
	/**
	 * Devuelve tamano de la columna
	 */
	public int getColumnCount(){
		return this._columnName.length;
	}
	
	/**
	 * Devuelve el nombre de la columna en funcion del numero de columna
	 * @param column
	 */
	public String getColumnName(int column) {
		if (column < this._columnName.length)
			return this._columnName[column];
		else 
			return null;
	}
	
	/**
	 * Devuelve el valor de la tabla en funcion de la fila y la columna
	 * @param rowIndex
	 * @param columnIndex
	 */
	public T getIdForRow (int rowIndex){
		return this.datos.get(rowIndex);
	}
	
	
	/**
	 * Actualizar los datos de la memoria. 
	 * Si la posicion ya existia actualizamos su valor
	 * Si la posicion no existia insertamos el par (pos,valor) ordenadamente.
	 * @param pos
	 * @param valor
	 */
	public void write(T objeto) {
		
		this.datos.add(objeto);
	}

	
	public void deleteRow (T objeto) {
		int i = 0;
		while ( i < this.datos.size() && !this.datos.get(i).equals(objeto)) 
			i++;
		this.datos.remove(i);
	}
	
	public abstract Object getValueAt(int arg0, int arg1);

	
	
}
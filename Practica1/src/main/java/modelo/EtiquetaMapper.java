package modelo;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Etiqueta;

public class EtiquetaMapper extends AbstractMapper <Etiqueta, Integer> {

	private static final String[] ETIQUETA_KEY_COLUMN_NAME = new String[] {"id"};
	private static final String[] ETIQUETA_COLUMN_NAMES = new String[] {"id" , "texto"};
	private static final String ETIQUETA_TABLE_NAME = "etiqueta";

	/**
	 * Constructor del etiquetamapper
	 * 
	 * @param db
	 */
	public EtiquetaMapper(DataSource db) {
		super(db);
	}

	@Override
	protected String[] getColumKey() {
		return ETIQUETA_KEY_COLUMN_NAME;
	}

	@Override
	protected String[] getColumnName() {
		return ETIQUETA_COLUMN_NAMES;
	}

	@Override
	protected String tableName() {
		return ETIQUETA_TABLE_NAME;
	}

	/**
	 * Devuelve un objeto con los datos extraidos de la base de datos
	 * 
	 * @param rs
	 *            puntero de lectura de la base de datos
	 * @return Objeto T
	 * @throws SQLException
	 */
	@Override
	protected Etiqueta buildObject(ResultSet rs) throws SQLException {

		int id = rs.getInt(ETIQUETA_COLUMN_NAMES[0]);
		String texto = rs.getString(ETIQUETA_COLUMN_NAMES[1]);
		

		return new Etiqueta(id,texto);
	}

	@Override
	protected Object[] getKey(Etiqueta datos) {		
		Object [] keys = new Object [1];
		keys [0] = datos.getId();
		return keys;
	}

	@Override
	protected Object[] getValues(Etiqueta datos) {
		Object[] values = new Object[ETIQUETA_COLUMN_NAMES.length];
		values[0] = datos.getId();
		values[1] = datos.getTexto();

		return values;
	}
		
	@Override
	protected String busquedaTitulo() {
		return "id";
	}

	@Override
	protected Object[] getKeys(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

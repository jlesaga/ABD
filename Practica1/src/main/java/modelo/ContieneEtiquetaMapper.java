package modelo;



import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Contiene_Etiqueta;

public class ContieneEtiquetaMapper extends AbstractMapper <Contiene_Etiqueta, Integer> {

	private static final String[] CONTIENE_KEY_COLUMN_NAME = new String[] {"id_palabra" , "id_etiqueta"};
	private static final String[] CONTIENE_COLUMN_NAMES = new String[] {"id_palabra" , "id_etiqueta"};
	private static final String CONTIENE_TABLE_NAME = "contiene_etiqueta";

	/**
	 * Constructor del contieneetiquetamapper
	 * 
	 * @param db
	 */
	public ContieneEtiquetaMapper(DataSource db) {
		super(db);
	}

	@Override
	protected String[] getColumKey() {
		return CONTIENE_KEY_COLUMN_NAME;
	}

	@Override
	protected String[] getColumnName() {
		return CONTIENE_COLUMN_NAMES;
	}

	@Override
	protected String tableName() {
		return CONTIENE_TABLE_NAME;
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
	protected Contiene_Etiqueta buildObject(ResultSet rs) throws SQLException {

		int id_palabra = rs.getInt(CONTIENE_COLUMN_NAMES[0]);
		int id_etiqueta = rs.getInt(CONTIENE_COLUMN_NAMES[1]);
		

		return new Contiene_Etiqueta(id_palabra, id_etiqueta);
	}

	@Override
	protected Object[] getKey(Contiene_Etiqueta datos) {
		Object[] keys = new Object[1];
		return keys;
	}

	@Override
	protected Object[] getValues(Contiene_Etiqueta datos) {
		Object[] values = new Object[CONTIENE_COLUMN_NAMES.length];
		values[0] = datos.getId_Palabra();
		values[1] = datos.getId_Etiqueta();

		return values;
	}
		
	@Override
	protected String busquedaTitulo() {
		return "id_palabra";
	}

	@Override
	protected Object[] getKeys(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
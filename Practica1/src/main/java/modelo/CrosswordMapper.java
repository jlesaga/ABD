package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CrosswordMapper extends AbstractMapper <Crucigrama, Integer>{

	private static final String[] CRUCIGRAMA_KEY_COLUMN_NAME =new String[] { "id"};
	private static final String[] CRUCIGRAMA_COLUMN_NAMES = new String[] {"id", "titulo", "fecha"};
	private static final String CRUCIGRAMA_TABLE_NAME = "crucigrama";
	
	/**
	 * Constructor del crosswordmapper
	 * @param db
	 */
	public CrosswordMapper(DataSource db) {
		super(db);
	}

	@Override
	protected String[] getColumKey() {
		return CRUCIGRAMA_KEY_COLUMN_NAME;
	}

	@Override
	protected String[] getColumnName() {
		return CRUCIGRAMA_COLUMN_NAMES;
	}

	@Override
	protected String tableName() {
		return CRUCIGRAMA_TABLE_NAME;
	}

	@Override
	protected String busquedaTitulo() {
		return "titulo";
	}
	/**
	 * Devuelve un objeto con los datos extraidos de la base de datos
	 * @param rs puntero de lectura de la base de datos
	 * @return Objeto T
	 * @throws SQLException
	 */
	@Override
	protected Crucigrama buildObject(ResultSet rs) throws SQLException {
		
		int id = rs.getInt(CRUCIGRAMA_COLUMN_NAMES[0]);
		String titulo = rs.getString(CRUCIGRAMA_COLUMN_NAMES[1]);
		Date fecha = rs.getDate(CRUCIGRAMA_COLUMN_NAMES[2]);
		
		return new Crucigrama (id, titulo, fecha);
	}


	@Override
	protected Object [] getKey(Crucigrama datos) {
		Object [] keys = new Object [1];
		keys [0] = datos.getId();
		return keys;
	}


	@Override
	protected Object[] getValues(Crucigrama datos) {
		Object [] values = new Object [CRUCIGRAMA_COLUMN_NAMES.length];
		values [0] = datos.getId();
		values [1] = datos.getTitulo();
		values [2] = datos.getFecha();		
		return values;
	}

	@Override
	protected Object[] getKeys(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}

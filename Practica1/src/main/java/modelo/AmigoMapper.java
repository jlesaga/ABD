package modelo;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Amigo;

public class AmigoMapper extends AbstractMapper <Amigo, String> {

	private static final String[] AMIGO_KEY_COLUMN_NAME = new String[] {"nick" , "nick_amigo"};
	private static final String[] AMIGO_COLUMN_NAMES = new String[] {"nick", "nick_amigo"};
	private static final String AMIGO_TABLE_NAME = "amigo";

	/**
	 * Constructor del amigodmapper
	 * 
	 * @param db
	 */
	public AmigoMapper(DataSource db) {
		super(db);
	}

	@Override
	protected String[] getColumKey() {
		return AMIGO_KEY_COLUMN_NAME;
	}

	@Override
	protected String[] getColumnName() {
		return AMIGO_COLUMN_NAMES;
	}

	@Override
	protected String tableName() {
		return AMIGO_TABLE_NAME;
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
	protected Amigo buildObject(ResultSet rs) throws SQLException {

		String nick = rs.getString(AMIGO_COLUMN_NAMES[0]);
		String nick_amigo = rs.getString(AMIGO_COLUMN_NAMES[1]);
		return new Amigo(nick, nick_amigo);
	}

	@Override
	protected Object[] getKey(Amigo datos) {
		Object[] keys = new Object[1];
		return keys;
	}

	@Override
	protected Object[] getValues(Amigo datos) {
		Object[] values = new Object[AMIGO_COLUMN_NAMES.length];
		values[0] = datos.getNick();
		values[1] = datos.getNick_Amigo();

		return values;
	}
		
	@Override
	protected String busquedaTitulo() {
		return "nick";
	}

	@Override
	protected Object[] getKeys(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}

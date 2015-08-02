package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Clave_crucigrama_activos;
import modelo.Crucigrama_Entrante;

public class CrucigramaEntranteMapper extends AbstractMapper <Crucigrama_Entrante, Clave_crucigrama_activos>{
	
	private static final String[] CRUCIGRAMA_KEY_COLUMN_NAME = new String[] { "nick", "nick_amigo", "id_crucigrama" };
	private static final String[] CRUCIGRAMA_COLUMN_NAMES = new String[] {"nick", "nick_amigo", "id_crucigrama"};
	private static final String CRUCIGRAMA_TABLE_NAME = "crucigrama_entrante";
	
	public CrucigramaEntranteMapper(DataSource db) {
		super(db);
	}

	@Override
	protected Crucigrama_Entrante buildObject(ResultSet rs) throws SQLException {
		String nick = rs.getString(CRUCIGRAMA_COLUMN_NAMES[0]);
		String nick_amigo = rs.getString(CRUCIGRAMA_COLUMN_NAMES[1]);
		int id_crucigrama = rs.getInt(CRUCIGRAMA_COLUMN_NAMES[2]);
		
		return new Crucigrama_Entrante ( nick, nick_amigo, id_crucigrama);
	}

	protected Object[] getValues(Crucigrama_Entrante datos) {
		Object [] values = new Object [CRUCIGRAMA_COLUMN_NAMES.length];
		values [0] = datos.getNick();
		values [1] = datos.getNick_Amigo();
		values [2] = datos.getId_crucigrama();
		return values;
	}

	@Override
	protected  Object [] getKey(Crucigrama_Entrante datos) {
		Object [] keys = new Object [1];
		
		return keys;
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
		return "nick_amigo";
	}

	@Override
	protected Object[] getKeys(Clave_crucigrama_activos id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

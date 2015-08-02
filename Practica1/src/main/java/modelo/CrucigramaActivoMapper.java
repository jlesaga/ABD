package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CrucigramaActivoMapper extends AbstractMapper <Crucigrama_Activo, Clave_crucigrama_activos>{
	
	private static final String[] CRUCIGRAMA_KEY_COLUMN_NAME = new String[] { "nick_usuario", "id_crucigrama" };
	private static final String[] CRUCIGRAMA_COLUMN_NAMES = new String[] {"nick_usuario", "id_crucigrama", "finalizado", "ayuda"};
	private static final String CRUCIGRAMA_TABLE_NAME = "crucigrama_activo";
	
	public CrucigramaActivoMapper(DataSource db) {
		super(db);
	}

	@Override
	protected Crucigrama_Activo buildObject(ResultSet rs) throws SQLException {
		String nick = rs.getString(CRUCIGRAMA_COLUMN_NAMES[0]);
		int id_crucigrama = rs.getInt(CRUCIGRAMA_COLUMN_NAMES[1]);
		boolean finalizado = rs.getBoolean(CRUCIGRAMA_COLUMN_NAMES[2]);
		boolean ayuda = rs.getBoolean(CRUCIGRAMA_COLUMN_NAMES[3]);
		return new Crucigrama_Activo ( nick, id_crucigrama, finalizado, ayuda);
	}

	protected Object[] getValues(Crucigrama_Activo datos) {
		Object [] values = new Object [CRUCIGRAMA_COLUMN_NAMES.length];
		values [0] = datos.getNick_usuario();
		values [1] = datos.getId_crucigrama();
		values [2] = datos.isFinalizado();
		values [3] = datos.isAyuda();
		return values;
	}

	@Override
	protected  Object [] getKey(Crucigrama_Activo datos) {
		Object [] keys = new Object [2];
		keys[0] = datos.getNick_usuario();
		keys[1] = datos.getId_crucigrama();
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
		return "nick_usuario";
	}

	@Override
	protected Object[] getKeys(Clave_crucigrama_activos id) {
		Object [] keys = new Object [2];
		keys[0] = id.getNick_usuario();
		keys[1] = id.getId_crucigrama();
		return keys;
	}

	
}

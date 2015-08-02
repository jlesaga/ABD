package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import modelo.Palabra_Resuelta;
import modelo.Clave_crucigrama_activos;

public class PalabraResueltaMapper extends AbstractMapper <Palabra_Resuelta, Clave_crucigrama_activos>{
	
	private static final String[] PALABRAR_KEY_COLUMN_NAME = new String[] { "nick_usuario", "id_crucigrama", "id_palabra", "fecha" };
	private static final String[] PALABRAR_COLUMN_NAMES = new String[] {"nick_usuario", "id_crucigrama", "id_palabra", "respuesta",
					"fecha", "usuario_respuesta", "correcto"};
	private static final String PALABRAR_TABLE_NAME = "palabra_resuelta";
	
	public PalabraResueltaMapper(DataSource db) {
		super(db);
	}
	
	@Override
	protected String[] getColumKey() {
		return PALABRAR_KEY_COLUMN_NAME;
	}

	@Override
	protected String[] getColumnName() {
		
		return PALABRAR_COLUMN_NAMES;
	}

	@Override
	protected String tableName() {
		return PALABRAR_TABLE_NAME;
	}
	

	@Override
	protected Palabra_Resuelta buildObject(ResultSet rs) throws SQLException {
		String nick_usuario = rs.getString(PALABRAR_COLUMN_NAMES[0]);
		int id_crucigrama = rs.getInt(PALABRAR_COLUMN_NAMES[1]);
		int id_palabra = rs.getInt(PALABRAR_COLUMN_NAMES[2]);
		String respuesta = rs.getString(PALABRAR_COLUMN_NAMES[3]);
		Timestamp fecha = rs.getTimestamp(PALABRAR_COLUMN_NAMES[4]);
		String usuario_respuesta = rs.getString(PALABRAR_COLUMN_NAMES[5]);
		boolean correcto = rs.getBoolean(PALABRAR_COLUMN_NAMES[6]);
		
		return new Palabra_Resuelta (nick_usuario, id_crucigrama, id_palabra, respuesta,
				fecha, usuario_respuesta, correcto);
	}

	protected Object[] getValues(Palabra_Resuelta datos) {
		Object [] values = new Object [PALABRAR_COLUMN_NAMES.length];
		values [0] = datos.getNick_Usuario();
		values [1] = datos.getId_Crucigrama();
		values [2] = datos.getId_Palabra();
		values [3] = datos.getRespuesta();
		values [4] = datos.getFecha();
		values [5] = datos.getUsuario_Respuesta();
		values [6] = datos.isCorrecto();
		return values;
	}

	@Override
	protected  Object [] getKey(Palabra_Resuelta datos) {
		Object [] keys = new Object [1];
		
		return keys;
	}



	@Override
	protected String busquedaTitulo() {
		return "nick_usuario";
	}

	@Override
	protected Object[] getKeys(Clave_crucigrama_activos id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

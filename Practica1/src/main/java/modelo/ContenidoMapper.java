package modelo;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import modelo.Contenido;

public class ContenidoMapper extends AbstractMapper <Contenido, Integer>{

	private static final String[] CONTENIDO_KEY_COLUMN_NAME =new String[] { "id_crucigrama","id_palabra"};
	private static final String[] CONTENIDO_COLUMN_NAMES = new String[] {"id_crucigrama","id_palabra","orientacion"
		,"posicion_x","posicion_y","puntuacion"};
	private static final String CONTENIDO_TABLE_NAME = "contenido";
	
	/**
	 * Constructor del contenidomapper
	 * @param db
	 */
	public ContenidoMapper(DataSource db) {
		super(db);
	}

	@Override
	protected String[] getColumKey() {
		return CONTENIDO_KEY_COLUMN_NAME;
	}

	@Override
	protected String[] getColumnName() {
		return CONTENIDO_COLUMN_NAMES;
	}

	@Override
	protected String tableName() {
		return CONTENIDO_TABLE_NAME;
	}


	/**
	 * Devuelve un objeto con los datos extraidos de la base de datos
	 * @param rs puntero de lectura de la base de datos
	 * @return Objeto T
	 * @throws SQLException
	 */
	@Override
	protected Contenido buildObject(ResultSet rs) throws SQLException {
		
		int id_crucigrama = rs.getInt(CONTENIDO_COLUMN_NAMES[0]);
		int id_palabra = rs.getInt(CONTENIDO_COLUMN_NAMES[1]);
		String orientacion = rs.getString(CONTENIDO_COLUMN_NAMES[2]);
		int posicion_x = rs.getInt(CONTENIDO_COLUMN_NAMES[3]);
		int posicion_y = rs.getInt(CONTENIDO_COLUMN_NAMES[4]);
		int puntuacion = rs.getInt(CONTENIDO_COLUMN_NAMES[5]);
		
		return new Contenido (id_crucigrama,id_palabra,orientacion,posicion_x,posicion_y,puntuacion);
	}


	@Override
	protected Object [] getKey(Contenido datos) {
		Object [] keys = new Object [1];
		
		return keys;
	}


	@Override
	protected Object[] getValues(Contenido datos) {
		Object [] values = new Object [CONTENIDO_COLUMN_NAMES.length];
		values [0] = datos.getId_Crucigrama();
		values [1] = datos.getId_Palabra();
		values [2] = datos.getOrientacion();
		values [3] = datos.getPosicion_x();
		values [4] = datos.getPosicion_y();
		values [5] = datos.getPuntuacion();
		
		return values;
	}

	@Override
	protected String busquedaTitulo() {
		return "id_crucigrama";
	}

	@Override
	protected Object[] getKeys(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

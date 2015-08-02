package modelo;


import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialException;



import modelo.Palabra;

public class PalabraMapper extends AbstractMapper<Palabra, Integer>{

	private static final String[] PALABRA_KEY_COLUMN_NAME =new String[] { "id"};
	private static final String[] PALABRA_COLUMN_NAMES = new String[] {"id", "caracteres", "pista", "imagen"};
	private static final String PALABRA_TABLE_NAME = "palabra";
	
	public PalabraMapper (DataSource ds){
		super (ds);
	}
	
	protected String[] getColumKey (){
		return PALABRA_KEY_COLUMN_NAME;	
	}
	
	protected String [] getColumnName (){
		return PALABRA_COLUMN_NAMES;
	}
	
	protected String tableName (){
		return PALABRA_TABLE_NAME;
	}
	
	protected Palabra buildObject(ResultSet rs) throws SQLException{
		Palabra result;
		int id = rs.getInt(PALABRA_COLUMN_NAMES[0]);
		String caracteres = rs.getString(PALABRA_COLUMN_NAMES[1]);
		String pista = rs.getString(PALABRA_COLUMN_NAMES[2]);
		
		byte [] logo = null;
		if (rs.getBlob(PALABRA_COLUMN_NAMES[3])!= null) {
			Blob imagen = rs.getBlob(PALABRA_COLUMN_NAMES[3]);
			logo = imagen.getBytes(1, (int) imagen.length());
		}
		result = new Palabra(id, caracteres, pista, logo);
		return result;		
	}


	@Override
	protected Object [] getKey(Palabra datos) {
		Object [] keys = new Object [1];
		keys [0] = datos.getId();
		return keys;
	}

	@Override
	protected Object[] getValues(Palabra datos) {
		Object [] values = new Object [PALABRA_COLUMN_NAMES.length];
		values [0] = datos.getId();
		values [1] = datos.getCaracteres();
		values [2] = datos.getPista();		
		if (values [3] != null)
			try {
				values [3] = new javax.sql.rowset.serial.SerialBlob(datos.getImagen());
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else 
			values [3] = null;
		
		return values;
	}
	
	@Override
	protected String busquedaTitulo() {
		return "titulo";
	}

	@Override
	protected Object[] getKeys(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
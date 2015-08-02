package modelo;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialException;



public class UserMapper extends AbstractMapper<User, String>{

	private static final String[] USUARIO_KEY_COLUMN_NAME =new String[] { "nick"};
	private static final String[] USUARIO_COLUMN_NAMES = new String[] {"nick", "contra", "fecha_nac", "imagen", "puntos"};
	private static final String USUARIO_TABLE_NAME = "usuario";
	
	public UserMapper (DataSource ds){
		super (ds);
	}
	
	protected String[] getColumKey (){
		return USUARIO_KEY_COLUMN_NAME;	
	}
	
	protected String [] getColumnName (){
		return USUARIO_COLUMN_NAMES;
	}
	
	protected String tableName (){
		return USUARIO_TABLE_NAME;
	}
	
	protected User buildObject(ResultSet rs) throws SQLException{
		User result;
		String nick = rs.getString(USUARIO_COLUMN_NAMES[0]);
		String contra = rs.getString(USUARIO_COLUMN_NAMES[1]);
		Date fecha = rs.getDate(USUARIO_COLUMN_NAMES[2]);
		Blob imagen = rs.getBlob(USUARIO_COLUMN_NAMES[3]);
		int puntos = rs.getInt(USUARIO_COLUMN_NAMES[4]);
		byte[] logo = null;
		if (imagen != null) 
			logo = imagen.getBytes(1, (int) imagen.length());
		result = new User(nick, contra, fecha, logo, puntos);
		return result;		
	}


	@Override
	protected Object [] getKey(User datos) {
		Object [] keys = new Object [1];
		keys [0] = datos.getNick();
		return keys;
	}

	@Override
	protected Object[] getValues(User datos) {
		Object [] values = new Object [USUARIO_COLUMN_NAMES.length];
		values [0] = datos.getNick();
		values [1] = datos.getContra();
		if (datos.getFecha_nac() == null)
			values[2] = null;
		else
			values [2] = datos.getFecha_nac();		
		if (datos.getImagen() != null)
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
		values[4] = datos.getPuntos();
		
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

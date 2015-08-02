package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;









import org.apache.commons.lang3.StringUtils;

public abstract class AbstractMapper <T, K>  {
	
	private DataSource ds;
	protected static User user;
	
	/**
	 * Devuelve un objeto con los datos extraidos de la base de datos
	 * @param rs puntero de lectura de la base de datos
	 * @return Objeto T
	 * @throws SQLException
	 */
	protected abstract T buildObject(ResultSet rs) throws SQLException;

	/**
	 * Devuelve la clave de la tabla 
	 * @param datos objeto 
	 * @return clave de la tabla
	 */
	protected abstract Object [] getKey(T datos);

	/**
	 * Devuelve los valores de los datos 
	 * @param datos objeto 
	 * @return array con los objetos de los datos
	 */
	protected abstract Object[] getValues(T datos);

	/**
	 * Devuelve el nombre de la clave de las tabla
	 * @return nombre de columnas de las tablas
	 */
	protected abstract String[] getColumKey ();
	
	/**
	 * Devuelve el nombre de las columnas de la tabla
	 * @return nombre de las columnas de la tabla
	 */
	protected abstract String [] getColumnName ();
	
	/**
	 * Devuelve el nombre de la tabla
	 * @return nombre de la tabla
	 */
	protected abstract String tableName ();
	
	/**
	 * Devuelve la columna para la realizacion de busqueda por titulo
	 * @return
	 */
	protected abstract String busquedaTitulo ();
	

	/**
	 * Establece el DataSource del Mapper
	 * @param db DataSource
	 */
	public AbstractMapper (DataSource db){
		this.ds = db;
	}
	
	
	/**
	 * Funci�n gen�rica para buscar elementos en la base de datos por una clave primaria
	 * @param id
	 * @return
	 */
	public T findById(K id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		T result = null;
		
		String [] columnNames = getColumnName();
		String [] keycolumns = getColumKey();
		String keys = keycolumns[0]+"=?";
		for (int i=1;i< keycolumns.length;i++)
			keys +=" and "+keycolumns[i]+"=?";
		
		try {
		con = this.ds.getConnection();
		String columnList = StringUtils.join(columnNames, ",");
		String sql = "SELECT " + columnList + " FROM " + tableName() + " WHERE " + keys;
		pst = con.prepareStatement(sql);
		if (id instanceof Clave_crucigrama_activos) {
			Object[] valuekey= getKeys (id);
			for (int i = 0; i < valuekey.length; i++)
				pst.setObject(i+1, valuekey[i]);
		}
		else 
			pst.setObject(1, id);
		rs = pst.executeQuery();
		if (rs.next()) 
			result = buildObject(rs);
		} catch (SQLException e){ 
			System.err.println (e.getMessage());	
			return null;
		} finally {			
			try {
				if (pst!= null)pst.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				System.err.println (e.getMessage());
			}
		}
		return result;
	}
	
	protected abstract Object[] getKeys(K id);

	/**
	 * Funci�n de insercci�n gener�ca en la base de datos
	 * @param datos
	 */
	public boolean insert (T datos){
		Connection con= null;
		PreparedStatement pst = null;
		Object [] values = getValues (datos);
		String [] columnNames = getColumnName();
		String [] marks = new String [columnNames.length];
		Arrays.fill(marks, "?");
		String marklist = StringUtils.join (marks, ",");
		String sql =  "INSERT INTO " + tableName() + " VALUES (" + marklist + ")";
		int columnas = 0;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			for (int i = 0; i < values.length; i++)
				pst.setObject(i + 1, values[i]);
			columnas = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println (e.getMessage());
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		
		if (columnas == 1)
			return true;
		else 
			return false;
	}

	
	/**
	 * Funcion de update generica en la base de datos
	 * @param datos Objeto con los datos a actualizar
	 */
	public boolean updatebyId (T datos){
		Connection con        = null;
		PreparedStatement pst = null;
		Object [] values = getValues (datos);
		int columnas = 0;
		
		String [] columnNames = getColumnName();
		String [] keycolumns = getColumKey();
		String keys = keycolumns[0]+" = ?";
		for (int i=1;i< keycolumns.length;i++)
			keys +=" and "+keycolumns[i]+" = ?";
		
		String sql = "UPDATE " + tableName() + " SET ";
		
		String columnList = StringUtils.join(columnNames, "= ?,");
		sql += columnList + " = ? WHERE " + keys;
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			
			int j = 1;
			for (int i = 0; i < values.length; i++){
				pst.setObject (j, values [i]);
				j++;
			}
			Object [] key = this.getKey(datos);
			for (int i = 0 ; i < keycolumns.length; i++){
				pst.setObject(j, key [i]);
				j++;
			}
			columnas = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		if (columnas == 1)
			return true;
		else
			return false;
	}

	/**
	 * Función de busqueda por nombre genérica en la base de datos
	 * @param name por el que se quiere buscar
	 * @return arraylist de objetos encontrodas con la palabra entre sus nombres
	 */
	public List<T> findbyname(String name, boolean exacto){
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		
		List<T> query=new ArrayList<T>();
		String [] columnNames = getColumnName();
		String columList = StringUtils.join (columnNames, ",");
		String sql = "";
		if (exacto)
			sql = "SELECT " + columList + " FROM " + tableName() + " WHERE " + busquedaTitulo()  + " like ?";
		else
			sql = "SELECT " + columList + " FROM " + tableName() + " WHERE " + busquedaTitulo()  + " like concat('%',?,'%')";
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setObject(1, name);
			rs = pst.executeQuery();
			while (rs.next()) 
				query.add(buildObject(rs));
		} catch (SQLException e) {
			System.out.println (e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return  query;		
	}

	
	/**
	 * Funci�n de insercci�n gener�ca en la base de datos
	 * @param datos
	 */
	public boolean delete (T datos){
		Connection con= null;
		PreparedStatement pst = null;
		Object [] values = getValues (datos);
	
		String [] keycolumns = getColumKey();
		String keys = keycolumns[0]+" = ?";
		for (int i=1;i< keycolumns.length;i++)
			keys +=" and "+keycolumns[i]+" = ?";
		
		String sql =  "DELETE FROM " + tableName() + " WHERE " + keys;
		int columnas = 0;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			for (int i = 0; i < values.length; i++)
				pst.setObject(i + 1, values [i]);
			columnas = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println (e.getMessage());
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		
	
		if (columnas == 1)
			return true;
		else 
			return false;
	}
	
}

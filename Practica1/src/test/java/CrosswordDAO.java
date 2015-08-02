

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import modelo.Clave_crucigrama_activos;
import modelo.CrosswordMapper;
import modelo.Crucigrama;
import modelo.CrucigramaActivoMapper;
import modelo.Crucigrama_Activo;
import modelo.User;
import modelo.UserMapper;

public class CrosswordDAO {
	
	private DataSource ds;
	private UserMapper usermapper;
	private CrosswordMapper crosswordmapper;
	private CrucigramaActivoMapper activomapper;
	

	/**
	 * Aquí se debe inicializar el pool de conexiones, mediante
	 * la creación de un DataSource, que deberá ser asignado a
	 * la variable ds.
	 * @throws PropertyVetoException 
	 */
	public CrosswordDAO() throws PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost/practica1_509");
		cpds.setUser("root");
		cpds.setPassword("");
		
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		this.ds = cpds;
		this.usermapper = new UserMapper (this.ds);
		this.crosswordmapper = new CrosswordMapper (this.ds);
		this.activomapper = new CrucigramaActivoMapper (this.ds);		
	}

	
	/**
	 * Devuelve la contraseña del usuario cuyo nick se pasa como
	 * parámetro. Devuelve null si el usuario no existe. 
	 */
	public String getPassword(String nick) {
		
		//UserMapper usermapper = new UserMapper (this.ds);
		User u = this.usermapper.findById(nick);
		if ( u != null)
			return u.getContra();
		else 
			return null;
	}
	
	/**
	 * Modifica la contraseña del usuario pasado como parámetro 
	 */
	public void modifyPassword(String nick, String newPassword) {
		
		//UserMapper usermapper = new UserMapper (this.ds);
		User u = this.usermapper.findById(nick);
		User modificado = new User (nick, newPassword, u.getFecha_nac(), u.getImagen(), u.getPuntos());
		this.usermapper.updatebyId(modificado);
		u = this.usermapper.findById(nick);
		
	}

	/**
	 * Devuelve una lista de las claves de aquellos crucigramas
	 * cuyo título contenga str.
	 * 
	 * Si escogisteis una clave numérica para la tabla de crucigramas,
	 * se debe devolver una lista de Integer. Si escogisteis el título
	 * como clave, se debe devolver una lista de String. Si, por el contrario,
	 * escogisteis una clave compuesta, debéis crear una clase para almacenar
	 * los atributos de dicha clave. 
	 */
	public List<Object> findCrosswordsByTitle(String str) {
		//CrosswordMapper crosswordmapper = new CrosswordMapper (this.ds);
		List <Crucigrama> crucigramas = this.crosswordmapper.findbyname(str, false);
		List <Object> claves = new ArrayList <Object>();
		for (int i = 0; i < crucigramas.size(); i++)
			claves.add(crucigramas.get(i).getId());
		
		return claves;
		
	}

	/**
	 * Devuelve el título del crucigrama cuya clave se pasa como
	 * parámetro.
	 */
	public String getCrosswordTitle(Object id) {
		//CrosswordMapper crosswordmapper = new CrosswordMapper (this.ds);
		Crucigrama crucigrama = null;
		if (id instanceof Clave_crucigrama_activos ){
			Clave_crucigrama_activos a = (Clave_crucigrama_activos) id;
			crucigrama = this.crosswordmapper.findById(a.getId_crucigrama());
		}
		else
			crucigrama = this.crosswordmapper.findById((Integer)id);
		
		return crucigrama.getTitulo();
	}
	
	/**
	 * Añade un crucigrama a la lista de crucigramas activos de un usuario.
	 * 
	 * El crucigrama se especifica mediante su clave
	 */
	public void addCrosswordToUser(String nick, Object crosswordId) {
		//CrucigramaActivoMapper activomapper = new CrucigramaActivoMapper (this.ds);
		this.activomapper.insert(new Crucigrama_Activo (nick, (Integer)crosswordId, false, false));
	}
	
	/**
	 * Devuelve la lista de identificadores de los crucigramas activos
	 * del usuario pasado como parámetro
	 */
	public List<Object> getCrosswordsOf(String nick) {
		//CrucigramaActivoMapper activomapper = new CrucigramaActivoMapper (this.ds);
		List<Crucigrama_Activo> crucigramas = this.activomapper.findbyname(nick, false);
		List <Object> claves = new ArrayList <Object>();
		for (int i = 0; i < crucigramas.size(); i++)
			claves.add(new Clave_crucigrama_activos (crucigramas.get(i).getNick_usuario(), crucigramas.get(i).getId_crucigrama()));	
		return claves;
	}

	/**
	 * Cierra el dataSource
	 */
	public void close() {
		 ((ComboPooledDataSource)ds).close();
	}
}

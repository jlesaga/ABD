package modelo;

import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;



public class Bd {

	private ComboPooledDataSource cpds;
	
	/**
	 * Constructor de la base de datos, crea un combopooledDataSource
	 * @throws PropertyVetoException
	 */
	public Bd() throws PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost/practica1_509");
		cpds.setUser("UsuarioP1");
		cpds.setPassword("");
		
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		this.cpds = cpds;
	}
	
	/**
	 * Devuelve el ComboPooledDataSource
	 * @return
	 */
	public ComboPooledDataSource Connection(){
		return this.cpds;
	}

}

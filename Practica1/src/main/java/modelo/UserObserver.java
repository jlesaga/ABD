package modelo;

import java.sql.Date;
import java.util.List;

public interface UserObserver {
	
	/**
	 * Actualiza la informaci�n del usuario
	 * @param nick del jugador
	 * @param edad del jugador
	 * @param puntos que lleva el jugador en el juego
	 * @param imagen avatar del jugador
	 */
	public void updateinfo (String nick, int edad, int puntos, byte [] imagen);
	
	/**
	 * Muestra un error de que el usuario no se encuentra en la base de datos o la contrase�a es incorrecta
	 */
	public void mensajeError (String msg);
	
	public void usuarioCorrecto ();

	public void msgUserAdd(String string);

	public void closeEdit();

	public void datosToEdit(String nick, String contra, Date fecha_nac);

	

	public void mostrarUsuarios(List<User> usuarios);

	
}

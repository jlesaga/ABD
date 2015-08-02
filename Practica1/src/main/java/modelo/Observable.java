package modelo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase observadora
 * @author 
 *
 * @param <T>
 */
public class Observable<T> {
	
	protected Collection<T> observers = new ArrayList<T>();

	/**
	 * Añade un nuevo observador a la lista de observadores
	 * @param observer
	 */
	public void addObserver(T observer )
	{
		if(this.observers != null)
			this.observers.add(observer);
	}

	/**
	 * Borra un observador de la lista
	 * @param observer
	 */
	public void deleteObserver(T observer)
	{
		this.observers.remove(observer);
	}

}

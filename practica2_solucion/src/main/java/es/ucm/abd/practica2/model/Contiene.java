package es.ucm.abd.practica2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Javier Lesaga López y Luis Federico Díaz Pérez
 * Clase entidad de Contiene
 */
@Entity
public class Contiene {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Crucigrama crucigrama;

	@ManyToOne
	private Definicion definicion;

	@Column(nullable = false)
	private int x;

	@Column(nullable = false)
	private int y;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Orientation orientacion;

	protected Contiene () {
		
	}
	public Contiene(Crucigrama crossword, Definicion word, int row, int column,
			Orientation orientation) {

		this.crucigrama = crossword;
		this.definicion = word;
		this.x = row;
		this.y = column;
		this.orientacion = orientation;

	}

	/**
	 * @return the crucigrama
	 */
	public Crucigrama getCrucigrama() {
		return crucigrama;
	}

	/**
	 * @param crucigrama
	 *            the crucigrama to set
	 */
	public void setCrucigrama(Crucigrama crucigrama) {
		this.crucigrama = crucigrama;
	}

	/**
	 * @return the definicion
	 */
	public Definicion getDefinicion() {
		return definicion;
	}

	/**
	 * @param definicion
	 *            the definicion to set
	 */
	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the orientacion
	 */
	public Orientation getOrientacion() {
		return orientacion;
	}

	/**
	 * @param orientacion
	 *            the orientacion to set
	 */
	public void setOrientacion(Orientation orientacion) {
		this.orientacion = orientacion;
	}

}

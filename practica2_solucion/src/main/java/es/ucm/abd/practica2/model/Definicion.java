package es.ucm.abd.practica2.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OrderColumn;

/**
 * 
 * @author Javier Lesaga López y Luis Federico Díaz Pérez
 * Clase entidad de Definicion
 */ 

@Entity
public class Definicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 200)
	private String enunciado;

	@Lob
	private byte[] imagen;	
	
	@ElementCollection
	@OrderColumn(name = "pos")
	private String[] tags;
	
	private String respuesta;

	protected Definicion () {
		this.id = 0;
		this.enunciado = "";
		this.imagen = null;
		this.tags = null;
		this.respuesta = "";
	}
	public Definicion(String sequence, String hint, String[] tags) {
		this.id = 0;
		this.respuesta = sequence;
		this.enunciado = hint;
		this.tags = tags;
		this.imagen = null;
		
	}

	public Definicion(String sequence, String hint, String[] tags, byte[] imagen) {
		this.id = 0;
		this.respuesta = sequence;
		this.enunciado = hint;
		this.tags = tags;
		this.imagen = imagen;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the enunciado
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * @param enunciado
	 *            the enunciado to set
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * @return the imagen
	 */
	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}

}

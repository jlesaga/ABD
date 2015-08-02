package es.ucm.abd.practica2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Javier Lesaga López y Luis Federico Díaz Pérez
 * Clase entidad de Crucigrama
 */

@Entity
public class Crucigrama {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 50)
	private String titulo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	
	
	@OneToMany( mappedBy = "crucigrama", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contiene> contiene;

	protected Crucigrama() {
		this.id = 0;
		this.titulo = "";
		this.fecha_creacion = null;
		this.contiene = new ArrayList<Contiene>();
	}

	public Crucigrama(String titulo, Date date) {

		this.titulo = titulo;
		this.fecha_creacion = date;
		this.contiene = new ArrayList<Contiene>();

	}

	public Crucigrama(Crucigrama crossword) {
		this.id = crossword.getId();
		this.titulo = crossword.getTitulo();
		this.fecha_creacion = crossword.fecha_creacion;
		this.contiene = crossword.getContiene();
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
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the fecha_creacion
	 */
	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	/**
	 * @param fecha_creacion
	 *            the fecha_creacion to set
	 */
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	/**
	 * @return the contiene
	 */
	public List<Contiene> getContiene() {
		return contiene;
	}

	/**
	 * @param contiene
	 *            the contiene to set
	 */
	public void setContiene(List<Contiene> contiene) {
		this.contiene = contiene;
	}
}

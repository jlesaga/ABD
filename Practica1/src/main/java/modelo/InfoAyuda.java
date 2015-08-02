package modelo;

import java.sql.Date;

public class InfoAyuda {

	private String nombre_amigo;
	private String titulo_crucigrama;
	private int id_crucigrama;
	private Date fecha;
	
	public InfoAyuda (String nombre_amigo, String titulo_crucigrama, int id_crucigrama, Date fecha) {
		this.setNick_amigo(nombre_amigo);
		this.setTitulo_crucigrama(titulo_crucigrama);
		this.setId_crucigrama(id_crucigrama);
		this.setFecha(fecha);
	}


	/**
	 * @return the nick_amigo
	 */
	public String getNick_amigo() {
		return nombre_amigo;
	}
	/**
	 * @param nick_amigo the nick_amigo to set
	 */
	public void setNick_amigo(String nick_amigo) {
		this.nombre_amigo = nick_amigo;
	}
	/**
	 * @return the titulo_crucigrama
	 */
	public String getTitulo_crucigrama() {
		return titulo_crucigrama;
	}
	/**
	 * @param titulo_crucigrama the titulo_crucigrama to set
	 */
	public void setTitulo_crucigrama(String titulo_crucigrama) {
		this.titulo_crucigrama = titulo_crucigrama;
	}
	/**
	 * @return the id_crucigrama
	 */
	public int getId_crucigrama() {
		return id_crucigrama;
	}
	/**
	 * @param id_crucigrama the id_crucigrama to set
	 */
	public void setId_crucigrama(int id_crucigrama) {
		this.id_crucigrama=id_crucigrama;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha=fecha;
	}

}

package modelo;

public class Crucigrama_Activo {
	
	private String nick_usuario;
	private int id_crucigrama;
	private boolean finalizado;
	private boolean ayuda;
	
	public Crucigrama_Activo (String nick_usuario, int id_crucigrama, boolean finalizado, boolean ayuda){
		this.setNick_usuario(nick_usuario);
		this.setId_crucigrama(id_crucigrama);
		this.setFinalizado(finalizado);
		this.setAyuda(ayuda);
	}

	/**
	 * @return the nick_usuario
	 */
	public String getNick_usuario() {
		return nick_usuario;
	}

	/**
	 * @param nick_usuario the nick_usuario to set
	 */
	public void setNick_usuario(String nick_usuario) {
		this.nick_usuario = nick_usuario;
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
		this.id_crucigrama = id_crucigrama;
	}

	/**
	 * @return the finalizado
	 */
	public boolean isFinalizado() {
		return finalizado;
	}

	/**
	 * @param finalizado the finalizado to set
	 */
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	/**
	 * @return the ayuda
	 */
	public boolean isAyuda() {
		return ayuda;
	}

	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(boolean ayuda) {
		this.ayuda = ayuda;
	}

	
}

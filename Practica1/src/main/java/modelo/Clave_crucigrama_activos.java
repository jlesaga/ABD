package modelo;

public class Clave_crucigrama_activos {

	private String nick_usuario;
	private int id_crucigrama;
	
	public Clave_crucigrama_activos (String nick_usuarios, int id_crucigrama){
		this.setNick_usuario(nick_usuarios);
		this.setId_crucigrama(id_crucigrama);
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
}

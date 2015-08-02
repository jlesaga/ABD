package modelo;

import java.sql.Timestamp;

public class Palabra_Resuelta {

	private String nick_usuario;
	private int id_crucigrama;
	private int id_palabra;
	private String respuesta;
	private Timestamp fecha;
	private String usuario_respuesta;
	private boolean correcto;
	
	public Palabra_Resuelta(String nick_usuario, int id_crucigrama, int id_palabra
			,String respuesta, Timestamp fecha, String usuario_respuesta, boolean correcto) {
		this.setNick_Usuario(nick_usuario);
		this.setId_Crucigrama(id_crucigrama);
		this.setId_Palabra(id_palabra);
		this.setRespuesta(respuesta);
		this.setFecha(fecha);
		this.setUsuario_Respuesta(usuario_respuesta);
		this.setCorrecto(correcto);
		
	}

	public String getNick_Usuario() {
		return nick_usuario;
	}

	public void setNick_Usuario(String nick_usuario) {
		this.nick_usuario = nick_usuario;
	}

	public int getId_Crucigrama() {
		return id_crucigrama;
	}

	public void setId_Crucigrama(int id_crucigrama) {
		this.id_crucigrama = id_crucigrama;
	}
	
	public int getId_Palabra() {
		return id_palabra;
	}

	public void setId_Palabra(int id_palabra) {
		this.id_palabra = id_palabra;
	}
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	public String getUsuario_Respuesta() {
		return usuario_respuesta;
	}

	public void setUsuario_Respuesta(String usuario_respuesta) {
		this.usuario_respuesta = usuario_respuesta;
	}
	
	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
	
	public String getIDCtoString (){
		return Integer.toString(this.id_crucigrama);
	}
	public String getIDPtoString (){
		return Integer.toString(this.id_palabra);
	}
	
	public String getFechatoString (){
		return null;
	}
}
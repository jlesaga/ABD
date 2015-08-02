package modelo;

public class Crucigrama_Entrante extends Observable <CrucigramaObserver> {

	private String nick;
	private String nick_amigo;
	private int id_crucigrama;

	
	public Crucigrama_Entrante (String nick, String nick_amigo, int id_crucigrama){
		this.setNick(nick);
		this.setNick_Amigo(nick_amigo);
		this.setId_crucigrama(id_crucigrama);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getNick_Amigo() {
		return nick_amigo;
	}

	public void setNick_Amigo(String nick_amigo) {
		this.nick_amigo = nick_amigo;
	}

	public int getId_crucigrama() {
		return id_crucigrama;
	}

	public void setId_crucigrama(int id_crucigrama) {
		this.id_crucigrama = id_crucigrama;
	}

	public void borrarCrucigramaentrante(InfoAyuda idForRow) {
		for (CrucigramaObserver uo: this.observers){
			uo.borrarCrucigramaentrante (idForRow);
		}			
	}

	public void msgError(String string) {
		for (CrucigramaObserver uo: this.observers){
			uo.msgError(string);
		}	
	}
	
}

package modelo;

import java.util.List;


public class Amigo extends Observable <AmigosObserver>{

	private String nick;
	private String nick_amigo;
	
	public Amigo(String nick, String nick_amigo) {
		this.setNick(nick);
		this.setNick_Amigo(nick_amigo);
	}

	public Amigo() {
		
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
	
	public void AmigosInicializar(List<String> amigo) {
		for (AmigosObserver uo: this.observers){
			uo.amigosIniciales (amigo);
		}			
	}
	
	public void mostarAmigo(String amigo) {
		for (AmigosObserver uo: this.observers){
			uo.addAmigo (amigo);
		}		
	}

	public void borrarAmigo(String amigo) {
		for (AmigosObserver uo: this.observers){
			uo.borrarAmigo (amigo);
		}			
	}

	public void msgError(String string) {
		for (AmigosObserver uo: this.observers){
			uo.msgError(string);
		}			
	}
	
	public void mostrarAmigos(List<User> usuarios) {
		for (AmigosObserver uo: this.observers){
			uo.mostrarUsuarios (usuarios);
		}	
	}
}

package modelo;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class User extends Observable <UserObserver> {
	private  String nick;
	private  String contra;
	private  Date fecha_nac;
	private  byte[] imagen;
	private  int puntos;
	
	public User (){
		
	}
	
	public User ( String nombre, String contra, Date fecha, byte [] imagen, int puntos){
		this.setNick(nombre);
		this.setContra(contra);
		this.setFecha_nac(fecha);
		this.imagen = imagen;
		this.puntos = puntos;
	}
		
	public void setUser(User usuario) {
		this.nick = usuario.getNick();
		this.contra = usuario.getContra();
		this.fecha_nac = usuario.getFecha_nac();
		this.imagen = usuario.getImagen();
		this.puntos = usuario.getPuntos ();
	}	
	
	public int getPuntos() {
		return this.puntos;
	}
	
	public void setPuntos (int puntos) {
		this.puntos = puntos;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nombre) {
		this.nick = nombre;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public int getEdad (){
		int edad = -1;
		if (this.fecha_nac != null){
			Calendar  cal = new GregorianCalendar (); 
			int year = Calendar.getInstance().get(Calendar.YEAR);
			cal.setTime(this.fecha_nac);
			edad = year - cal.get(GregorianCalendar.YEAR);
		}
		return edad;
	}	
	
	public void cargarPerfil (){
		for (UserObserver uo: this.observers){
			uo.usuarioCorrecto();
		}
	}

	public void mensajeError (String mensaje) {
		for (UserObserver uo: this.observers){
			uo.mensajeError(mensaje);
		}		
	}

	public void actualizarInfo() {
		for (UserObserver uo: this.observers){
			uo.updateinfo(this.nick, this.getEdad(), this.puntos, this.imagen);
		}
	}

	public void msgUsuarioAdd() {
		for (UserObserver uo: this.observers){
			uo.msgUserAdd("El usuario se ha añadido a la base de datos");
		}		
		
	}

	public void closeEdit() {
		for (UserObserver uo: this.observers){
			uo.closeEdit ();
		}			
	}

	public void getDatosToEdit() {
		for (UserObserver uo: this.observers){
			uo.datosToEdit (this.nick, this.contra, this.fecha_nac);
		}		
		
	}	

	

}

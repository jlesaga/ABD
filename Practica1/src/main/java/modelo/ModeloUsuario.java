package modelo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ModeloUsuario  {

	private Bd bd;
	private UserMapper usermapper;
	private AmigoMapper amigomapper;

	private User usuario;
	private Amigo amigo;

	
	public ModeloUsuario (Bd bd){
		this.bd = bd;
		this.usermapper = new UserMapper (this.bd.Connection());
		this.amigomapper = new AmigoMapper (this.bd.Connection());
		this.usuario = new User ();
		this.amigo = new Amigo ();
	}
	
	public void setUsuario (User usuario){
		this.usuario = usuario;
	}

	public void registrarUserObservers(UserObserver uo) {
		this.usuario.addObserver(uo);		
	}
	
	public void registrarAmigoObserver (AmigosObserver ao) {
		this.amigo.addObserver (ao);
	}

	public boolean checkUser(String user, String password) {		
		boolean ok = false;
		if (user != null){
			User aux = usermapper.findById(user);
			if (aux != null && aux.getContra().equals(password)){
				ok = true;
				this.usuario.setUser(aux);
				ModeloCrucigrama.setUsuario(aux);
			}
		}
		return ok;
	}

	public void usuarioCorrecto() {
		this.usuario.cargarPerfil();
		this.usuario.actualizarInfo();
	}
	
	public void usuarioIncorrecto (){
		this.usuario.mensajeError ("Usuario o contraseï¿½a incorrectos");
	}

	public void registrarUsuario(String user, String contra) {
		User usuario = usermapper.findById(user);
		if (user != null ){
			usuario = new User (user, contra, null, null, 0 );
			if (usermapper.insert(usuario))
				this.usuario.msgUsuarioAdd ();
			else
				this.usuario.mensajeError("El usuario no se ha añadido a la base de datos");
		}
		else
			this.usuario.mensajeError("El usuario ya existe en la base de datos");
	}

	public void editarUser(String nick, String contra, String dia, String mes, String year, String url) {
		Date date = null;
		if (!dia.equals("") && !mes.equals("") && !year.equals("")){
			String s=(year+"-"+mes+"-"+dia);
			date = Date.valueOf(s);
		}
		
		byte [] imagen = new byte [5000];		
		if (!url.equals("")){
			try {
				ByteArrayOutputStream buffer=null;
				InputStream is= new FileInputStream(url);
				buffer = new ByteArrayOutputStream();
				int nread;
				while((nread=is.read(imagen,0,imagen.length))!=-1)
					buffer.write(imagen,0,nread);			
				is.close();
				buffer.flush();
				imagen= buffer.toByteArray();
			} catch (IOException e) {
				
			}			
		}
		if (imagen == null)
			imagen = this.usuario.getImagen();
		User aux = new User (nick, contra, (java.sql.Date) date, imagen, this.usuario.getPuntos());
		this.usuario.setUser(aux);
		this.usermapper.updatebyId(aux);
		this.usuario.actualizarInfo();
		this.usuario.closeEdit ();
		
	}

	public void darDatos() {
		this.usuario.getDatosToEdit();
		
	}

	public void saberAmigos() {
		List <Amigo> amigos = amigomapper.findbyname(this.usuario.getNick(), true);
		List <String> amigo = new ArrayList <String>();
		for (int i = 0; i < amigos.size(); i++)
			amigo.add( amigos.get(i).getNick_Amigo()); 
		this.amigo.AmigosInicializar (amigo);		
	}

	public void datosIniciales() {
		this.usuario.actualizarInfo();
		
	}

	public void buscarUsuario(String name) {
		List <User> usuarios = 	usermapper.findbyname(name, false);
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNick().equals(usuario.getNick()))
				usuarios.remove(i);
		}
		this.amigo.mostrarAmigos (usuarios);		
	}

	public void addAmigo(String amigo) {
		boolean fallo = false;
		Amigo nuevoamigo = new Amigo (this.usuario.getNick(), amigo);
		if (amigomapper.insert(nuevoamigo)) {
			nuevoamigo = new Amigo (amigo, this.usuario.getNick());
			if (amigomapper.insert(nuevoamigo))
				this.amigo.mostarAmigo (amigo);
			else
				fallo = true;
		}
		else
			fallo = true;
		if (fallo)
			this.amigo.msgError ("No se ha podido añadir este amigo a su base de datos");
				
	}

	public void borrarAmigo(String amigo) {
		boolean fallo = false;
		Amigo borrarAmigo = new Amigo (this.usuario.getNick(), amigo);
		if (amigomapper.delete(borrarAmigo)) {
			borrarAmigo = new Amigo (amigo, this.usuario.getNick());
			if (amigomapper.delete(borrarAmigo)) 
				this.amigo.borrarAmigo (amigo);	
			else
				fallo = true;
		}
		else
			fallo = true;
		if (fallo)
			this.amigo.msgError ("No se ha podido añadir este amigo a su base de datos");
	}
	



	public void updatePuntosPropios (int puntuacion, boolean correcta, String usuario) {		
		int newPuntuacion = 0;
		int newPuntuacionamigo = 0;

		if(usuario==null){
			if (correcta){
				newPuntuacion = this.usuario.getPuntos() + puntuacion;
			}
			else{
				newPuntuacion = this.usuario.getPuntos() - 10;
			}
			this.usuario.setPuntos(newPuntuacion);
			this.usermapper.updatebyId(this.usuario);
			this.usuario.actualizarInfo();
		}
		else{
		UserMapper amigomapper = new UserMapper (this.bd.Connection());
		User amigo = amigomapper.findById(usuario);
			if (correcta){
				newPuntuacion = this.usuario.getPuntos() + puntuacion/2;
				newPuntuacionamigo = amigo.getPuntos() + puntuacion/2;
				amigo.setPuntos(newPuntuacionamigo);	
				amigomapper.updatebyId(amigo);
			}
			else
				newPuntuacion = this.usuario.getPuntos() - 10;
			
			this.usuario.setPuntos(newPuntuacion);
			this.usermapper.updatebyId(this.usuario);
			this.usuario.actualizarInfo();
		}
	}


}

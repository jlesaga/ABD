package controlador;

import modelo.AmigosObserver;
import modelo.Crucigrama;
import modelo.CrucigramaObserver;
import modelo.InfoAyuda;
import modelo.InformacionWord;
import modelo.ModeloCrucigrama;
import modelo.ModeloUsuario;
import modelo.UserObserver;

public class Controller {

	private ModeloCrucigrama modelocrucigrama;
	private ModeloUsuario modelousuario;

	/**
	 * Constructora del controlador
	 * 
	 * @param modelo
	 */
	public Controller(ModeloCrucigrama modelo, ModeloUsuario modelousuario) {
		this.modelocrucigrama = modelo;
		this.modelousuario = modelousuario;
	}

	/**
	 * Registra un observador de usuario
	 * @param uo
	 */
	public void registraruserobs(UserObserver uo) {
		this.modelousuario.registrarUserObservers(uo);
	}

	/**
	 * Registra un observador del crucigrama
	 * @param co
	 */
	public void registrarCrosswordObs(CrucigramaObserver co) {
		this.modelocrucigrama.registrarCrucigramaObserver(co);
	}

	public void registrarAmigosObs (AmigosObserver ao) {
		this.modelousuario.registrarAmigoObserver(ao);
	}
	/**
	 * Comprueba si el usuario está en la base de datos
	 * @param user nick usuario
	 * @param password contraseña del usuario
	 */
	public void checkUser(String user, String password) {
		if (this.modelousuario.checkUser(user, password))
			this.modelousuario.usuarioCorrecto();
		else
			this.modelousuario.usuarioIncorrecto();
	}

	/**
	 * Busca un usuario en la base de datos
	 * @param text nombre del crucigrama a buscar
	 */
	public void buscarCrucigrama(String text) {
		this.modelocrucigrama.buscarCrucigrama(text);
	}

	/**
	 * Registra un usuario en la base de datos
	 * @param user nick del usuario
	 * @param contra contraseña del usuario
	 */
	public void registrarUsuario(String user, String contra) {
		this.modelousuario.registrarUsuario(user, contra);
	}

	/**
	 * Edita un usuario
	 * @param nick nick del usuario
	 * @param contra contraseña del usuario
	 * @param dia de nacimiento del usuario
	 * @param mes del nacimiento del usuario
	 * @param year del nacimeitno del usuario
	 * @param url dirección donde se encuentra la imagen que se quiere actualizar
	 */
	public void editarUser(String nick, String contra, String dia, String mes,
			String year, String url) {
		this.modelousuario.editarUser(nick, contra, dia, mes, year, url);
	}

	/**
	 * Devuelve los datos del usuario
	 */
	public void decirDatos() {
		this.modelousuario.darDatos();

	}

	/**
	 * Añade un crucigrama a la lista de crucigramas activos
	 * @param c
	 */
	public void addCrucigramaActivo(Crucigrama c) {
		this.modelocrucigrama.addCrucigramaActivo(c);
	}

	/**
	 * Actualiza la tabla de crucigramas activos
	 */
	public void pintarTablaActivos() {
		this.modelocrucigrama.pintarTablaActivos();

	}

	public void saberAmigos() {
		this.modelousuario.saberAmigos();
	}

	public void datosIni() {
		this.modelousuario.datosIniciales();
		
	}


	public void buscarUsuario(String text) {
		this.modelousuario.buscarUsuario(text);
	}

	public void addAmigo(String amigo) {
		this.modelousuario.addAmigo (amigo);		
	}

	public void borrarAmigo(String amigo) {
		this.modelousuario.borrarAmigo (amigo);		
	}
	
	public void borrarCrucigramaEntrante(InfoAyuda idForRow) {
		this.modelocrucigrama.borrarCrucigramaEntrante (idForRow);		
	}

	public void saberPalabrasCrucigrama(int id) {
		this.modelocrucigrama.saberPalabrasCrucigrama (id);
		
	}

	public void anotarRespuesta(String palabra,	InformacionWord palabraSeleccionada, int id_crucigrama) {
		this.modelocrucigrama.anotarRespuesta (palabra, palabraSeleccionada, id_crucigrama);
		
	}
	
	public void anotarRespuestaamigo(String palabra, InformacionWord palabraSeleccionada, int id_crucigrama, String usuario) {
		this.modelocrucigrama.anotarRespuestaamigo (palabra, palabraSeleccionada, id_crucigrama, usuario);
		
	}

	public void updatePuntosPropios(int puntuacion,  boolean correcta, String usuario) {
		this.modelousuario.updatePuntosPropios (puntuacion, correcta, usuario);
		
	}

	public void enviarPeticion(String nick_amigo, Crucigrama crucigrama) {
		this.modelocrucigrama.enviarPeticion (nick_amigo, crucigrama);
		
	}

	public void saberPeticionesAyuda() {
		this.modelocrucigrama.saberPeticionesAyuda ();
		
	}

	public void saberPalabrasYaResueltas(Crucigrama crucigrama) {
		this.modelocrucigrama.saberPalabrasYaRespuestas (crucigrama);
		
	}
	
	public void saberPalabrasYaResueltasamigo(String usuario, Crucigrama crucigrama) {
		this.modelocrucigrama.saberPalabrasYaRespuestasamigo (usuario ,crucigrama);
		
	}

}

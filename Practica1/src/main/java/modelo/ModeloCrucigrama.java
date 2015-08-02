package modelo;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ModeloCrucigrama  {

	private Bd bd;
	private CrosswordMapper crosswordmapper;
	private CrucigramaActivoMapper activomapper;
	private ContenidoMapper contenidomapper;
	private PalabraMapper palabramapper;
	private Crucigrama crucigrama;
	private Crucigrama_Entrante entrante;
	private CrucigramaEntranteMapper entrantemapper;
	private PalabraResueltaMapper palabraResueltaMapper;
	private static User usuario;
	
	public ModeloCrucigrama (Bd bd){
		this.bd = bd;
		this.crosswordmapper = new CrosswordMapper (this.bd.Connection());
		this.activomapper = new CrucigramaActivoMapper (this.bd.Connection());
		this.contenidomapper = new ContenidoMapper (this.bd.Connection());
		this.palabramapper = new PalabraMapper (this.bd.Connection());
		this.palabraResueltaMapper = new PalabraResueltaMapper (this.bd.Connection());
		this.entrantemapper = new CrucigramaEntranteMapper (this.bd.Connection());
		this.crucigrama = new Crucigrama ();
	}

	public static void setUsuario (User user) {
		ModeloCrucigrama.usuario = user;
	}
	
	public User getUsuario() {
		return ModeloCrucigrama.usuario;
	}
	
	public void registrarCrucigramaObserver(CrucigramaObserver co) {
		this.crucigrama.addObserver(co);
	}

	public void buscarCrucigrama(String text) {		
		List<Crucigrama> c = new ArrayList <Crucigrama>();
		c = crosswordmapper.findbyname(text, false);
		this.crucigrama.actualizarLista (c);
	}


	
	public void addCrucigramaActivo(Crucigrama c) {
		Crucigrama_Activo activo = new Crucigrama_Activo (usuario.getNick(), c.getId(), false, false);
		Clave_crucigrama_activos clave = new Clave_crucigrama_activos (usuario.getNick(), c.getId());
		if (this.activomapper.findById(clave) == null){
			if (this.activomapper.insert(activo)){
				this.crucigrama.actualizarAddActivos(c);
			}
		}
	}

	public void pintarTablaActivos() {
		List <Crucigrama_Activo> activos = this.activomapper.findbyname(usuario.getNick(), true);
		List <InfoCrucigramaActivo> crucigramas = new ArrayList <InfoCrucigramaActivo>();
		for (int i = 0; i < activos.size(); i++){
			crucigramas.add(new InfoCrucigramaActivo (this.crosswordmapper.findById(activos.get(i).getId_crucigrama()), activos.get(i).isAyuda()));
		}
		this.crucigrama.actualizarListaActivos(crucigramas);
	}

	public void saberPalabrasCrucigrama(int id) {
		List <Contenido> contenido = contenidomapper.findbyname(Integer.toString(id), true);
		List <InformacionWord> informacionWord = new ArrayList <InformacionWord>();
		for (int i = 0; i < contenido.size(); i++) {
			int x = contenido.get(i).getPosicion_x();
			int y = contenido.get(i).getPosicion_y();
			Palabra palabra = palabramapper.findById(contenido.get(i).getId_Palabra());
			boolean horizontal = true;
			if (contenido.get(i).getOrientacion().equals("vertical"))
				horizontal = false;
			int puntuacion = contenido.get(i).getPuntuacion();
			Word word = new Word (x, y, palabra.getCaracteres(), horizontal);
			informacionWord.add(new InformacionWord (word, palabra.getId(), palabra.getPista(), palabra.getImagen(), puntuacion, false ));
		}
		this.crucigrama.palabrasCrucigrama (informacionWord);
	}

	public void anotarRespuesta(String palabra, InformacionWord palabraSeleccionada, int id_crucigrama) {
		boolean correcto = false;
		if (palabra.equalsIgnoreCase(palabraSeleccionada.getWord().getWord())) 
			correcto = true;
		Date fecha = new Date ();
		Timestamp date = new Timestamp (fecha.getTime());
		Palabra_Resuelta respuesta = new Palabra_Resuelta (ModeloCrucigrama.usuario.getNick(), id_crucigrama, palabraSeleccionada.getId_palabra(), 
				palabra, date, ModeloCrucigrama.usuario.getNick(), correcto);
		this.palabraResueltaMapper.insert(respuesta);
		this.crucigrama.isCorrecta (correcto, palabraSeleccionada.getId_palabra());
		
	}
	
	public void anotarRespuestaamigo(String palabra, InformacionWord palabraSeleccionada, int id_crucigrama, String usuario) {
		boolean correcto = false;
		if (palabra.equalsIgnoreCase(palabraSeleccionada.getWord().getWord())) 
			correcto = true;
		Date fecha = new Date ();
		Timestamp date = new Timestamp (fecha.getTime());
		Palabra_Resuelta respuesta = new Palabra_Resuelta (usuario, id_crucigrama, palabraSeleccionada.getId_palabra(), 
				palabra, date, ModeloCrucigrama.usuario.getNick(), correcto);
		this.palabraResueltaMapper.insert(respuesta);
		this.crucigrama.isCorrecta (correcto, palabraSeleccionada.getId_palabra());
		
	}

	public void enviarPeticion(String nick_amigo, Crucigrama crucigrama) {
		Crucigrama_Entrante entrante = new Crucigrama_Entrante (ModeloCrucigrama.usuario.getNick(), nick_amigo, crucigrama.getId());
		this.entrantemapper.insert(entrante);
		this.crucigrama.cerrarVentanaCrucigrama ();
		Crucigrama_Activo activo = this.activomapper.findById(new Clave_crucigrama_activos(ModeloCrucigrama.usuario.getNick(), crucigrama.getId()));
		activo.setAyuda(true);
		this.activomapper.updatebyId(activo);
		this.crucigrama.actualizarActivo (activo);
	}

	public void saberPeticionesAyuda() {
		List <Crucigrama_Entrante> entrantes = this.entrantemapper.findbyname(ModeloCrucigrama.usuario.getNick(), true);
		Crucigrama crucigrama = null;
		List <InfoAyuda> ayuda = new ArrayList <InfoAyuda>();
		for (int i = 0; i < entrantes.size(); i++) {
			crucigrama = this.crosswordmapper.findById(entrantes.get(i).getId_crucigrama());
			ayuda.add(new InfoAyuda (entrantes.get(i).getNick(), crucigrama.getTitulo(), crucigrama.getId(), crucigrama.getFecha()));			
		}
		this.crucigrama.listaAyudas (ayuda);
	}
	
	public void borrarCrucigramaEntrante(InfoAyuda idForRow) {
		boolean fallo = false;
		Crucigrama_Entrante borrarCrucigrama = new Crucigrama_Entrante (idForRow.getNick_amigo(), ModeloCrucigrama.usuario.getNick(), idForRow.getId_crucigrama());
		if (entrantemapper.delete(borrarCrucigrama)) {
				
				Crucigrama_Activo actualizar = new Crucigrama_Activo (idForRow.getNick_amigo(),idForRow.getId_crucigrama(),false,false);
				activomapper.updatebyId(actualizar);
			
				//this.entrante.borrarCrucigramaentrante (idForRow);	
		}
		else
			fallo = true;
		if (fallo)
			this.entrante.msgError ("No se ha borrar el crucigrama entrante de su base de datos");
	}

	public void saberPalabrasYaRespuestas(Crucigrama crucigrama) {
		List <Palabra_Resuelta> resueltas = this.palabraResueltaMapper.findbyname(ModeloCrucigrama.usuario.getNick(), true);
		for (int i = 0; i < resueltas.size(); i++) {
			if (resueltas.get(i).getId_Crucigrama() != crucigrama.getId())
				resueltas.remove(i);
		}
		ArrayList <Integer> id_palabras_resueltas = new ArrayList <Integer>();
		for (int i = 0; i < resueltas.size(); i++) {
			if (resueltas.get(i).isCorrecto())
				id_palabras_resueltas.add(resueltas.get(i).getId_Palabra());
		}
		this.crucigrama.anunciarRespuestas (id_palabras_resueltas);
	}
	
	public void saberPalabrasYaRespuestasamigo(String usuario, Crucigrama crucigrama) {
		List <Palabra_Resuelta> resueltas = this.palabraResueltaMapper.findbyname(usuario,true);
		for (int i = 0; i < resueltas.size(); i++) {
			if (resueltas.get(i).getId_Crucigrama() != crucigrama.getId())
				resueltas.remove(i);
		}
		ArrayList <Integer> id_palabras_resueltas = new ArrayList <Integer>();
		for (int i = 0; i < resueltas.size(); i++) {
			if (resueltas.get(i).isCorrecto())
				id_palabras_resueltas.add(resueltas.get(i).getId_Palabra());
		}
		this.crucigrama.anunciarRespuestas (id_palabras_resueltas);
	}

		
}

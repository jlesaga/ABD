package modelo;

public class Contenido {

	private int id_crucigrama;
	private int id_palabra;
	private String orientacion;
	private int posicion_x;
	private int posicion_y;
	private int puntuacion;
	
	public Contenido(int id_crucigrama, int id_palabra, String orientacion,
			int posicion_x, int posicion_y, int puntuacion) {
		this.setId_Crucigrama(id_crucigrama);
		this.setId_Palabra(id_palabra);
		this.setOrientacion(orientacion);
		this.setPosicion_x(posicion_x);
		this.setPosicion_y(posicion_y);
		this.setPuntuacion(puntuacion);
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
	
	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	public int getPosicion_x() {
		return posicion_x;
	}

	public void setPosicion_x(int posicion_x) {
		this.posicion_x = posicion_x;
	}

	public int getPosicion_y() {
		return posicion_y;
	}

	public void setPosicion_y(int posicion_y) {
		this.posicion_y = posicion_y;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}

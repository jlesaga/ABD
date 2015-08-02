package modelo;

public class InformacionWord {
	
	private Word word;
	private int id_palabra;
	private String pista;
	private byte [] imagen;
	private int puntuacion;
	private boolean resuelta;
	
	public InformacionWord (Word word, int id_palabra, String pista, byte [] imagen, int puntuacion, boolean resuelta) {
		this.setWord(word);
		this.setId_palabra(id_palabra);
		this.setPista(pista);
		this.setImagen(imagen);
		this.setPuntuacion(puntuacion);
		this.setResuelta(false);
	}

	/**
	 * @return the word
	 */
	public Word getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(Word word) {
		this.word = word;
	}

	/**
	 * @return the pista
	 */
	public String getPista() {
		return pista;
	}

	/**
	 * @param pista the pista to set
	 */
	public void setPista(String pista) {
		this.pista = pista;
	}

	/**
	 * @return the imagen
	 */
	public byte [] getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(byte [] imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the puntuacion
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return the id_palabra
	 */
	public int getId_palabra() {
		return id_palabra;
	}

	/**
	 * @param id_palabra the id_palabra to set
	 */
	public void setId_palabra(int id_palabra) {
		this.id_palabra = id_palabra;
	}

	/**
	 * @return the resuelta
	 */
	public boolean isResuelta() {
		return resuelta;
	}

	/**
	 * @param resuelta the resuelta to set
	 */
	public void setResuelta(boolean resuelta) {
		this.resuelta = resuelta;
	}
}

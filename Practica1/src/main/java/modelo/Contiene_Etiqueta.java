package modelo;

public class Contiene_Etiqueta {

	private  int id_palabra;
	private  int id_etiqueta;

	
	public Contiene_Etiqueta ( int id_palabra, int id_etiqueta){
		this.setId_Palabra(id_palabra);
		this.setId_Etiqueta(id_etiqueta);
	}
	
	public int getId_Palabra() {
		return id_palabra;
	}

	public void setId_Palabra(int id_palabra) {
		this.id_palabra = id_palabra;
	}
	
	public int getId_Etiqueta() {
		return id_etiqueta;
	}

	public void setId_Etiqueta(int id_etiqueta) {
		this.id_etiqueta = id_etiqueta;
	}
	
}

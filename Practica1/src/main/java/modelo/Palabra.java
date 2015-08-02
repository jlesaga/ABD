package modelo;

public class Palabra {
	
	private  int id;
	private  String caracteres;
	private  String pista;
	private  byte[] imagen;
	
	public Palabra ( int id, String caracteres, String pista, byte [] imagen){
		this.setId(id);
		this.setCaracteres(caracteres);
		this.setPista(pista);
		this.imagen = null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(String caracteres) {
		this.caracteres = caracteres;
	}

	public String getPista() {
		return pista;
	}

	public void setPista(String pista) {
		this.pista = pista;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public String getIDtoString (){
		return Integer.toString(this.id);
	}
	
	
}

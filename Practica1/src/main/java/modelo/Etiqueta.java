package modelo;

public class Etiqueta {

	private  int id;
	private  String texto;

	
	public Etiqueta( int id, String texto){
		this.setId(id);
		this.setTexto(texto);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}

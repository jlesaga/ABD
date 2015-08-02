package modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Crucigrama extends Observable <CrucigramaObserver>{

	private int id;
	private String titulo;
	private Date fecha;
	
	public Crucigrama (){
		
	}
	
	public Crucigrama(int id, String titulo, Date fecha) {
		this.id = id;
		this.setTitulo(titulo);
		this.fecha = fecha;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 *
	 * @return id crucigrama como una cadena
	 */
	public String getIDtoString (){
		return Integer.toString(this.id);
	}
	
	/**
	 * 
	 * @return cadena con la fecha en formato dia/mes/año
	 */
	public String getFechatoString (){
		return new SimpleDateFormat ("dd/MM/yyyy").format(this.fecha);
	}
	
	/**
	 * Recorre los observadores para actualizar la lista de crucigramas
	 * @param c
	 */
	public void actualizarLista(List<Crucigrama> c) {
		for (CrucigramaObserver co: this.observers){
			co.actualizarLista(c);
		}
	}
	
	/**
	 * Recorre los observadores para añadir un crucigrama a la lista de crucigramas
	 * @param c
	 */
	public void actualizarAddActivos(Crucigrama c) {
		for (CrucigramaObserver co: this.observers){
			co.actualizarAddActivos(c);
		}
	}
	
	/**
	 * Recorre los obseradores para actualizar la lista de crucigramas activos
	 * @param crucigramas
	 */
	public void actualizarListaActivos(List<InfoCrucigramaActivo> crucigramas) {
		for (CrucigramaObserver co: this.observers){
			co.actualizarListaActivos(crucigramas);
		}		
	}
	
	public void palabrasCrucigrama(List<InformacionWord> informacionWord) {
		for (CrucigramaObserver co: this.observers){
			co.palabrasCrucigrama(informacionWord);
		}
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void isCorrecta(boolean correcto, int i) {
		for (CrucigramaObserver co: this.observers){
			co.isCorrecta (correcto, i);
		}			
	}

	public void cerrarVentanaCrucigrama() {
		for (CrucigramaObserver co: this.observers){
			co.cerrarVentanaCrucigrama ();
		}			
	}

	public void listaAyudas(List<InfoAyuda> ayuda) {
		for (CrucigramaObserver co: this.observers){
			co.listaAyudas (ayuda);
		}		
		
	}

	public void actualizarActivo(Crucigrama_Activo activo) {
		for (CrucigramaObserver co: this.observers){
			co.actualizarActivo (activo);
		}			
	}

	public void anunciarRespuestas(ArrayList<Integer> id_palabras_resueltas) {
		for (CrucigramaObserver co: this.observers){
			co. anunciarRespuestas(id_palabras_resueltas);
		}			
	}

	
	
}

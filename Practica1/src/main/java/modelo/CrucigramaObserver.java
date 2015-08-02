package modelo;

import java.util.ArrayList;
import java.util.List;

public interface CrucigramaObserver {
	
	public void actualizarLista (List <Crucigrama > crucigrama);

	public void actualizarListaActivos(List<InfoCrucigramaActivo> crucigramas);

	public void actualizarAddActivos(Crucigrama c);
	
	public void palabrasCrucigrama(List<InformacionWord> informacionWord);

	public void isCorrecta(boolean correcto, int id_palabra_resuelta);

	public void cerrarVentanaCrucigrama();

	public void listaAyudas(List<InfoAyuda> ayuda);

	public void borrarCrucigramaentrante(InfoAyuda idForRow);

	public void msgError(String string);

	public void actualizarActivo(Crucigrama_Activo activo);

	public void anunciarRespuestas(ArrayList<Integer> id_palabras_resueltas);
}

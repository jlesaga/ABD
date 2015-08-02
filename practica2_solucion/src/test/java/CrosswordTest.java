import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;


public class CrosswordTest extends CrosswordTestBase <Crucigrama, Definicion>{

	/**
	 * Genera una instancia de AbstractCrosswordFacade
	 * @return la instancia
	 */
	protected AbstractCrosswordFacade<Crucigrama, Definicion> buildFacade() {
		
		return new AbstractCrossword();
	}

}
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ucm.abd.practica2.dao.AbstractCrosswordDAO;
import es.ucm.abd.practica2.dao.CrosswordDAO;
import es.ucm.abd.practica2.model.Contiene;
import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;
import es.ucm.abd.practica2.model.Orientation;

/**
 * 
 * @author Javier Lesaga López y Luis Federico Díaz Pérez
 * Clase que implementa una fachada para el acceso a las funciones
 * proporcionadas del modelo orientado a objetos.
 * @param <C> Clase de los crucigramas.
 * @param <W> Clase de las definiciones.
 *
 */
public class AbstractCrossword implements AbstractCrosswordFacade <Crucigrama, Definicion>{

	/**
	 * Crea un nuevo crucigrama.
	 * 
	 * @param title Título del crucigrama.
	 * @param date Fecha de creación del crucigrama.
	 * @return Instancia de la clase de los crucigramas con la información indicada.
	 */
    public Crucigrama newCrossword(String title, Date date) {
		return new Crucigrama (title, date);
	}
    
    /**
     * Crea una nueva definición.
     * 
     * @param sequence Respuesta correspondiente a dicha definición
     * @param hint Enunciado de la definición.
     * @param tags Lista de etiquetas asociadas a la definición.
     * @return Instancia de la clase de definiciones con la información indicada.
     */
    public Definicion newDefinition(String sequence, String hint, String[] tags) {
		return new Definicion (sequence, hint, tags);
	}
    
    /**
     * Añade una definición a un crucigrama
     * 
     * @param crossword Crucigrama
     * @param word Definición a añadir
     * @param row Fila de la definición dentro del crucigrama
     * @param column Columna de la definición dentro del crucigrama
     * @param orientation Orientación de la definición dentro del crucigrama
     */
    public void addWordToCrossword(Crucigrama crossword, Definicion word, int row, int column, Orientation orientation) {
    	Contiene contiene = new Contiene (crossword, word, row, column, orientation);
    	crossword.getContiene().add(contiene);
    }

    /**
     * Obtiene la respuesta asociada a la definición dada
     * 
     * @param word Definición.
     * @return Respuesta asociada a la definición dada.
     */
    public String getAnswerOfWord(Definicion word) {
		return word.getRespuesta();
	}
    
    /**
     * Obtiene un array con las etiquetas asociadas a una definición dada.
     * 
     * @param word Definición.
     * @return Array con las etiquetas de la definición. Si la definición
     * no contiene etiquetas, se devolverá un array con cero elementos.
     */
    public String[] getTagsOfWord(Definicion word) {
		return word.getTags();
	}
    
    /**
     * Devuelve el enunciado de una definición.
     *  
     * @param word Definición.
     * @return Enunciado de la definición pasada como parámetro.
     */
    public String getHintOfWord(Definicion word) {
		return word.getEnunciado();
	}
    
    /**
     * Devuelve el título de un crucigrama.
     * 
     * @param crossword Crucigrama,
     * @return Título del crucigrama
     */
    public String getTitleOfCrossword(Crucigrama crossword) {
		return crossword.getTitulo();
	}
    
    /**
     * Devuelve la fecha de creación de un crucigrama.
     * 
     * @param crossword Crucigrama.
     * @return Fecha de creación del crucigrama.
     */
    public Date getDateOfCrossword(Crucigrama crossword) {
		return crossword.getFecha_creacion();
	}
    
    /**
     * Devuelve la información de las palabras contenidas en un crucigrama.
     * 
     * Cada elemento de la lista devuelta por esta función es un array de
     * cuatro componentes:
     * 
     * * Respuesta de la palabra (String).
     * * Fila donde está contenida la definición dentro del crucigrama (Integer).
     * * Columna donde está contenida la definición dentro del crucigrama (Integer).
     * * Orientación de la palabra dentro del crucigrama (Orientacion.HORIZONTAL o Orientacion.VERTICAL).
     * 
     * @param crossword Crucigrama.
     * @return Lista de arrays con la información expuesta anteriormente.
     */
    public List<Object[]> getWordsOfCrossword(Crucigrama crossword) {
    	List <Contiene> contiene = crossword.getContiene();
    	List <Object []> lista = new ArrayList <Object []> (); 
    	if (contiene != null) {
	    	for (int i = 0; i < contiene.size(); i++) {
	    		Object [] datos = new Object [4];
	    		datos[0] = contiene.get(i).getDefinicion().getRespuesta();
	    		datos[1] = contiene.get(i).getX();
	    		datos[2] = contiene.get(i).getY();
	    		datos[3] = contiene.get(i).getOrientacion();
	    		lista.add(datos);
	     	}
    	}
		return lista;
	}
    

    /**
     * Devuelve un objeto DAO para realizar las operaciones de acceso a datos.
     * 
     * @return Objeto DAO.
     */
    public AbstractCrosswordDAO<Crucigrama, Definicion> createDAO() {
		return new CrosswordDAO ();
	}
    

}

package es.ucm.abd.practica2.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;

/**
 * 
 * @author Javier Lesaga López y Luis Federico Díaz Pérez
 * Clase que implementa AbstractCrosswordDAO 
 */
public class CrosswordDAO implements
		AbstractCrosswordDAO<Crucigrama, Definicion> {

	private SessionFactory session;

	public CrosswordDAO() {
		this.session = null;
	}

	/**
	 * Establece el SessionFactory que será utilizado en el resto de operaciones
	 * de acceso.
	 * 
	 * @param f
	 *            Session factory
	 */
	public void setSessionFactory(SessionFactory f) {
		this.setSession(f);
	}

	/**
	 * Añade un crucigrama a la base de datos. Devuelve el identificador del
	 * crucigrama añadido.
	 * 
	 * @param crossword
	 *            Crucigrama a añadir.
	 * @return Identificador del crucigrama añadido.
	 */
	public int insertCrossword(Crucigrama crossword) {
		Session sesion = this.session.openSession();
		Transaction tr = sesion.beginTransaction();
		sesion.save("Crucigrama", crossword);
		tr.commit();
		sesion.close();		
		return crossword.getId();
	}

	/**
	 * Añade una definición a la base de datos.
	 * 
	 * @param word
	 *            Definición a añadir.
	 */
	public void insertWord(Definicion word) {
		Session sesion = this.session.openSession();
		Transaction tr = sesion.beginTransaction();
		sesion.save ("Definicion", word);
		tr.commit();
		sesion.close();
	}

	/**
	 * Devuelve un crucigrama a partir de su identificador.
	 * 
	 * @param id
	 *            Identificador del crucigrama.
	 * @return Crucigrama.
	 */
	public Crucigrama findCrosswordById(int id) {
		Crucigrama crucigrama = null;
		Session sesion = this.session.openSession();
		
		Transaction tr = sesion.beginTransaction();
		crucigrama = (Crucigrama) sesion.get(Crucigrama.class, id);
		tr.commit();
		sesion.close();
		return crucigrama;
	}

	/**
	 * Devuelve el identificador, título, fecha de creación y número de palabras
	 * de los crucigramas cuyo título contenga la palabra pasada como parámetro.
	 * 
	 * Devuelve una lista de arrays. Cada uno de estos arrays tiene cuatro
	 * componentes:
	 * 
	 * * Identificador del crucigrama (Integer). * Título del crucigrama
	 * (String). * Fecha de creación del crucigrama (Date). * Número de palabras
	 * del crucigrama (Integer o Long).
	 * 
	 * 
	 * @param str
	 *            Cadena de búsqueda del crucigrama.
	 * @return Una lista de arrays con la información descrita anteriormente.
	 */
	public List<Object[]> getCrosswordData(String str) {
		
		List <Object[]> lista = new ArrayList<Object[]>();
		Session sesion = this.session.openSession();
		Query query = sesion.createQuery("FROM Crucigrama AS c Where c.titulo Like :title ORDER BY c.id");
		query.setString("title",  "%" + str+"%");
		
		
		@SuppressWarnings("unchecked")
		List<Crucigrama> lc = (List<Crucigrama>) query.list();
		
		for(int i=0; i < lc.size(); i++){
			
			Object[] crucigrama = new Object[4];
			crucigrama[0] = lc.get(i).getId();
			crucigrama[1] = lc.get(i).getTitulo();
			crucigrama[2] = lc.get(i).getFecha_creacion();			
			crucigrama[3] = (long) lc.get(i).getContiene().size();
			lista.add(crucigrama);
			
		}
		
		return lista;
	}

	/**
	 * Devuelve una lista con aquellas palabras que contengan TODAS las
	 * etiquetas pasadas como parámetro.
	 * 
	 * @param tags
	 *            Etiquetas a buscar.
	 * @return Lista de palabras. Cada palabra devuelta ha de contener las
	 *         etiquetas pasadas como parámetro (y posiblemente algunas más)
	 */
	@SuppressWarnings("unchecked")
	public List<Definicion> findWordsByTags(String[] tags) {
		
		List <Definicion> lista = new ArrayList<Definicion>();
		Session sesion = this.session.openSession();
		Query query = null;
		String sql = "";
		if (tags.length == 0 || tags == null) {
			sql = "FROM Definicion";
			query = sesion.createQuery(sql);
		}
		else {
			sql = "FROM Definicion AS d WHERE ? MEMBER OF d.tags";
			if (tags.length > 1) {
				for (int i = 1; i < tags.length; i++) 
					sql += " AND ? MEMBER OF d.tags";	
			}
			query = sesion.createQuery(sql);
			for (int i = 0; i < tags.length; i ++)
				query.setParameter(i, tags[i]);
		}
		lista = query.list();
		
		return lista;
	}

	/**
	 * Devuelve aquellas palabras que encajen con las restricciones pasadas como
	 * parámetro.
	 * 
	 * @param constraints
	 *            Restricciones.
	 * @return Lista de palabras.
	 */
	@SuppressWarnings("unchecked")
	public List<Definicion> getMatchingWords(CharConstraint[] constraints) {
		List <Definicion> lista = new ArrayList<Definicion>();
		Session sesion = this.session.openSession();
		Query query = sesion.createQuery("FROM Definicion");
		lista = (List <Definicion>)query.list();
		boolean borrar = false;
		for (int i = 0; i < lista.size(); i++) {
			int j = 0;
			while ( j < constraints.length && !borrar ) {
				String respuesta = lista.get(i).getRespuesta();
				if(respuesta.length()+1==constraints[j].getPosition()){
					borrar = true;
				}
				else if (respuesta.length()>=constraints[j].getPosition() &&
						respuesta.charAt(constraints[j].getPosition()-1) != constraints[j].getCharacter())
					borrar = true;
				j++;
			}
			if (borrar){
				lista.remove(i);
				i--;
			}
			borrar = false;
		}
		return lista;
	}

	/**
	 * @return the session
	 */
	public SessionFactory getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(SessionFactory session) {
		this.session = session;
	}
}

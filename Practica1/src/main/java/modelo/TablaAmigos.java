package modelo;

public class TablaAmigos extends ModeloTabla <String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TablaAmigos(String [] nombreColumnas) {
		super(nombreColumnas);
	}


	public Object getValueAt(int arg0, int arg1) {
		if (arg1 == 0)
			return super.datos.get(arg0);
		else
			return null;
	}

}

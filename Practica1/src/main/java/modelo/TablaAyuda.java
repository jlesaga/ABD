package modelo;


public class TablaAyuda extends ModeloTabla <InfoAyuda>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TablaAyuda(String [] nombreColumnas) {
		super(nombreColumnas);
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (arg1 == 0)
			return super.datos.get(arg0).getNick_amigo();
		else if (arg1 == 1)
			return super.datos.get(arg0).getId_crucigrama();
		else
			return null;
	}

}

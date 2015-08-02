package modelo;


public class TablaActivos extends ModeloTabla <Crucigrama> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TablaActivos(String [] nombreColumnas) {
		super(nombreColumnas);
	}	

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (arg1 == 0)
			return super.datos.get(arg0).getTitulo();
		else 
			return super.datos.get(arg0).getFechatoString();
	}



}

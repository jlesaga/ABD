package modelo;

public class InfoCrucigramaActivo {

	private Crucigrama crucigrama;
	private boolean ayuda;
	
	public InfoCrucigramaActivo (Crucigrama crucigrama, boolean ayuda ) {
		this.setCrucigrama(crucigrama);
		this.setAyuda(ayuda);		
	}

	/**
	 * @return the crucigrama
	 */
	public Crucigrama getCrucigrama() {
		return crucigrama;
	}

	/**
	 * @param crucigrama the crucigrama to set
	 */
	public void setCrucigrama(Crucigrama crucigrama) {
		this.crucigrama = crucigrama;
	}

	/**
	 * @return the ayuda
	 */
	public boolean isAyuda() {
		return ayuda;
	}

	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(boolean ayuda) {
		this.ayuda = ayuda;
	}
}


import java.beans.PropertyVetoException;

import controlador.Controller;
import modelo.Bd;
import modelo.ModeloCrucigrama;
import modelo.ModeloUsuario;
import vista.LoginWindow;

public class Main {

	public static void main(String[] args) {
		
		Bd bd = null;
		try {
			bd = new Bd ();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModeloCrucigrama modelocrucigrama = new ModeloCrucigrama (bd);
		ModeloUsuario modelousuario = new ModeloUsuario (bd);
		Controller controller = new Controller (modelocrucigrama, modelousuario);
		LoginWindow log = new LoginWindow(controller);
		log.configurarPanel();
	}

}

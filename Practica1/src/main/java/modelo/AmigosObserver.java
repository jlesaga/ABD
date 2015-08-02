package modelo;

import java.util.List;

public interface AmigosObserver {

	public void amigosIniciales(List<String> amigo);
	
	public void addAmigo(String amigo);

	public void borrarAmigo(String amigo);

	public void msgError(String string);

	public void mostrarUsuarios(List<User> usuarios);
}

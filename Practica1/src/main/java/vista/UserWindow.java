package vista;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.Controller;
import modelo.User;
import modelo.UserObserver;

public class UserWindow extends JPanel implements UserObserver{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel izquierdo;
	private JPanel derecho;
	private JLabel user;
	private JLabel edad;
	private JLabel puntos;
	private ImageIcon icono;
	
	private JButton avatar;

	private Controller controller;

	private EditarUserWindow editar;
	
	public UserWindow (Controller controller){
		this.controller = controller;
		this.controller.registraruserobs(this);
		
	}
	
	public JPanel construirUserWindow (){
		this.setLayout (new BorderLayout());
		this.setSize(550, 100);
		this.contruirLadoIzquierdo();
		this.construirLadoDerecho();
		this.add(this.izquierdo, BorderLayout.WEST);
		this.add(this.derecho, BorderLayout.CENTER);
		controller.datosIni ();
		return this;
	}
	private void contruirLadoIzquierdo (){
		this.izquierdo = new JPanel ();
		
		this.avatar = new JButton ();
		this.avatar.addActionListener(new buttonAvatar ());
		this.izquierdo.add(this.avatar);
	}
	
	private void construirLadoDerecho (){
		this.derecho = new JPanel ();
		this.derecho.setLayout(new BorderLayout ());
		this.user = new JLabel ("usuario");
		this.edad = new JLabel (" años");
		this.puntos = new JLabel ("xxx puntos");
		this.edad.setHorizontalAlignment(SwingConstants.CENTER);
		this.user.setHorizontalAlignment(SwingConstants.CENTER);
		this.puntos.setHorizontalAlignment(SwingConstants.CENTER);
		this.derecho.add(this.user, BorderLayout.NORTH);
		this.derecho.add(this.edad, BorderLayout.CENTER);
		this.derecho.add(this.puntos, BorderLayout.SOUTH);
		
	}
	
	public class buttonAvatar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			editar = new EditarUserWindow (controller);
			editar.construirPanel();
			
		}
		
	}

	public void updateinfo(String nick, int edad, int puntos, byte[] imagen) {
		this.user.setText(nick);
		if (edad != -1)
			this.edad.setText(Integer.toString(edad) + " años");
		else 
			this.edad.setText ("Edad no especificada");
		this.puntos.setText(Integer.toString(puntos) + " puntos");
		if (imagen != null){
			this.icono=new ImageIcon(imagen);
			Image img= this.icono.getImage();
			Image newimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			this.icono=new ImageIcon(newimg);
			this.avatar.setIcon(this.icono);
		}
	}

	public void mensajeError (String msg) {
		// TODO Auto-generated method stub
		
	}

	public void usuarioCorrecto() {
		// TODO Auto-generated method stub
		
	}

	public void msgUserAdd(String string) {
		// TODO Auto-generated method stub
		
	}

	public void closeEdit() {
		this.editar.dispose();
	}

	public void datosToEdit(String nick, String contra, Date fecha_nac) {
		// TODO Auto-generated method stub
		
	}
	
	public void amigosIniciales(List<String> amigo) {
		// TODO Auto-generated method stub
		
	}

	public void mostrarUsuarios(List<User> usuarios) {
		// TODO Auto-generated method stub
		
	}

	public void addAmigo(String amigo) {
		// TODO Auto-generated method stub
		
	}

}

package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.Controller;
import modelo.User;
import modelo.UserObserver;

public class LoginWindow extends JFrame implements UserObserver{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private PerfilWindow perfil;
	private String user = "User:";
	private String contra = "Password:";
	private JTextField userText;
	private JPasswordField passwordText;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton registerButton;
	
	public LoginWindow (Controller controller){
		super ("Bienvenido");
		this.controller = controller;
		this.registrarObservador();
		
	}
	
	public void configurarPanel (){
		
		JPanel panel = new JPanel ();
		panel.setLayout(null);
		
		this.userLabel = new JLabel(this.user);
		this.userLabel.setBounds(10, 10, 125, 25);
		panel.add(this.userLabel);

		this.userText = new JTextField(20);
		this.userText.setBounds(150, 10, 160, 25);
		panel.add(this.userText);

		this.passwordLabel = new JLabel(this.contra);
		this.passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(this.passwordLabel);

		this.passwordText = new JPasswordField(20);
		passwordText.setBounds(150, 40, 160, 25);
		panel.add(this.passwordText);

		this.loginButton = new JButton("Aceptar");
		this.loginButton.setBounds(50, 80, 80, 25);
		this.loginButton.addActionListener(new buttonAceptar());
		panel.add(this.loginButton);
		
		this.registerButton = new JButton("Nuevo Usuario");
		this.registerButton.setBounds(150, 80, 150, 25);
		this.registerButton.addActionListener(new buttonRegistrar ());
		panel.add(this.registerButton);
		
		this.add(panel);
		this.setSize(350, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.perfil = new PerfilWindow (this.controller);
		
	}
	
	private void registrarObservador() {
		
		this.controller.registraruserobs(this);
	}
	
	
	public class buttonAceptar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.checkUser (userText.getText(), ((JTextField) passwordText).getText() );
			
		}
		
	}
	
	public class buttonRegistrar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.registrarUsuario (userText.getText(), ((JTextField) passwordText).getText());
			
		}
		
	}

	public void updateinfo(String nick, int edad, int puntos, byte[] imagen) {
				
	}

	public void mensajeError (String msg) {
		JOptionPane.showMessageDialog(null, msg);		
	}

	public void usuarioCorrecto() {
		this.perfil.construirPerfil();
		this.dispose();
	}

	public void msgUserAdd(String msg) {
		JOptionPane.showMessageDialog(null, msg);	
	}

	public void closeEdit() {
		// TODO Auto-generated method stub
		
	}

	public void datosToEdit(String nick, String contra, Date fecha_nac) {
		// TODO Auto-generated method stub
		
	}

	public void amigosIniciales() {
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

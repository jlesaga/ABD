package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controller;
import modelo.User;
import modelo.UserObserver;

public class EditarUserWindow extends JFrame implements UserObserver {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JLabel nick;
	private JTextField nicktext;
	private JLabel contra;
	private JTextField textContra;
	private JLabel fecha_nac;
	private JTextField dia;
	private JTextField mes;
	private JTextField year;
	private JLabel imagen;
	private JTextField url;
	private JButton editar;
	
	public EditarUserWindow (Controller controller){
		this.controller = controller;
		this.setSize(450, 200);
	}
	
	public void construirPanel (){
		
		this.setLayout(new GridLayout (5, 1));
		this.nick = new JLabel ("Usuario:      ");
		this.nicktext = new JTextField ();
		this.nicktext.setEditable(false);
		JPanel panel_nick = new JPanel ();
		panel_nick.setLayout(new BorderLayout ());
		panel_nick.add(this.nick, BorderLayout.WEST);
		panel_nick.add(this.nicktext, BorderLayout.CENTER);
		this.add(panel_nick);
		
		this.contra = new JLabel ("Password: ");
		this.textContra = new JTextField ();
		JPanel panel_contra = new JPanel ();
		panel_contra.setLayout(new BorderLayout());
		panel_contra.add(this.contra, BorderLayout.WEST);
		panel_contra.add(this.textContra, BorderLayout.CENTER);
		this.add(panel_contra);
		
		this.fecha_nac = new JLabel ("Fecha nacimiento");
		this.dia = new JTextField();
		this.mes = new JTextField();
		this.year = new JTextField ();
		JPanel fecha_aux = new JPanel ();
		fecha_aux.setLayout(new GridLayout (1, 3));
		fecha_aux.add(this.fecha_nac);
		fecha_aux.add(this.dia);
		fecha_aux.add(this.mes);
		fecha_aux.add(this.year);
		JPanel panel_fecha = new JPanel ();
		panel_fecha.setLayout(new BorderLayout ());
		panel_fecha.add(this.fecha_nac, BorderLayout.WEST);
		panel_fecha.add(fecha_aux, BorderLayout.CENTER);
		this.add(panel_fecha);
		
		this.imagen = new JLabel ("Imagen");
		this.url = new JTextField ();
		JPanel panel_imagen = new JPanel();
		panel_imagen.setLayout(new BorderLayout ());
		panel_imagen.add(this.imagen, BorderLayout.WEST);
		panel_imagen.add(this.url, BorderLayout.CENTER);
		
		this.add(panel_imagen);
		
		this.editar = new JButton ("Editar");
		this.editar.addActionListener(new buttonEditar());
		JPanel button = new JPanel ();
		button.add(this.editar, BorderLayout.CENTER);
		this.add(button);
		
		this.setVisible(true);
		this.controller.registraruserobs(this);
		this.mostrarDatos ();
		
	}
	

	private void mostrarDatos() {
		controller.decirDatos();
	}


	public class buttonEditar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.editarUser (nicktext.getText(), textContra.getText(), dia.getText(), mes.getText(), year.getText(), url.getText());
		}
		
	}


	public void updateinfo(String nick, int edad, int puntos, byte[] imagen) {
		// TODO Auto-generated method stub
		
	}

	public void mensajeError(String msg) {
		// TODO Auto-generated method stub
		
	}

	public void usuarioCorrecto() {
		// TODO Auto-generated method stub
		
	}

	public void msgUserAdd(String string) {
		// TODO Auto-generated method stub
		
	}

	public void closeEdit() {
		// TODO Auto-generated method stub
		
	}

	public void datosToEdit(String nick, String contra, Date fecha_nac) {
		this.nicktext.setText(nick);
		this.textContra.setText(contra);
		if (fecha_nac != null){
			SimpleDateFormat sf = new SimpleDateFormat ("dd/MM/yyyy");
			String fecha = sf.format(fecha_nac);
			String [] f = fecha.split("/");
			this.dia.setText(f[0]);
			this.mes.setText(f[1]);
			this.year.setText(f[2]);
		}
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

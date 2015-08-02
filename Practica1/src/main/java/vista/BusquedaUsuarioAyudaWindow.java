package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.Controller;
import modelo.AmigosObserver;
import modelo.Crucigrama;
import modelo.TablaAmigos;
import modelo.User;

public class BusquedaUsuarioAyudaWindow extends JFrame implements AmigosObserver{
	
	
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Crucigrama crucigrama;
	private JTextField texto;
	private JButton botonBusqueda;
	private JPanel barraBusqueda;
	private TablaAmigos abstracttabla;
	private JTable table;
	private JPanel tabla;
	private JButton botonEnviar;
	private JPanel boton;

	public BusquedaUsuarioAyudaWindow (Controller controller, Crucigrama crucigrama) {
		this.controller = controller;
		this.crucigrama = crucigrama;
		this.controller.registrarAmigosObs(this);
	}
	
	public void construirVentana (){
		this.setSize(500, 500);
		
		this.texto = new JTextField ();
		this.botonBusqueda = new JButton ("Busqueda");
		
		this.barraBusqueda = new JPanel ();
		this.barraBusqueda.setLayout(new BorderLayout ());
		this.barraBusqueda.add(this.texto, BorderLayout.CENTER);
		this.barraBusqueda.add(this.botonBusqueda, BorderLayout.EAST);
		
		this.add(this.barraBusqueda, BorderLayout.NORTH);
		
		String columnas [] = new String [1];
		this.abstracttabla = new TablaAmigos (columnas);
		this.table = new JTable (this.abstracttabla);
		JScrollPane scrollpane = new JScrollPane(table);
		this.tabla = new JPanel();
		this.tabla.add(scrollpane);
		this.add(this.tabla, BorderLayout.CENTER);
		
		this.botonEnviar = new JButton ("Añadir");
		this.boton = new JPanel ();
		this.boton.add (this.botonEnviar, BorderLayout.CENTER);
		this.add(this.boton, BorderLayout.SOUTH);
		this.addListener();
		controller.saberAmigos();
		
		this.setVisible(true);
	}
	

	private void addListener() {
		this.botonEnviar.addActionListener(new buttonEnviar());
		this.botonBusqueda.addActionListener(new buttonBusqueda ());		
	}
	
	public class buttonBusqueda implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			ArrayList <String> amigos = new ArrayList <String >();
			for (int i = 0; i < abstracttabla.getRowCount(); i++) {
				String amigo = abstracttabla.getIdForRow(i);
				if (texto.getText().equals(amigo))
					amigos.add(amigo);				
			}
			abstracttabla.establecerTabla(amigos);
		}
		
	}
	
	public class buttonEnviar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()!= -1){
				controller.enviarPeticion (abstracttabla.getIdForRow(table.getSelectedRow()), crucigrama);
				setVisible(false);
			}
		}
		
	}

	public void amigosIniciales(List<String> amigo) {
		this.abstracttabla.establecerTabla((ArrayList <String>) amigo) ; 
		
	}

	public void addAmigo(String amigo) {
				
	}

	public void borrarAmigo(String amigo) {
		// TODO Auto-generated method stub
		
	}

	public void msgError(String string) {
		// TODO Auto-generated method stub
		
	}

	public void mostrarUsuarios(List<User> usuarios) {
		// TODO Auto-generated method stub
		
	}

}

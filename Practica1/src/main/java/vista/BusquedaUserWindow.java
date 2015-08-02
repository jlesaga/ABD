package vista;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.Controller;
import modelo.AmigosObserver;
import modelo.TablaAmigos;
import modelo.User;

public class BusquedaUserWindow extends JFrame implements AmigosObserver{


	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTextField texto;
	private JButton botonBusqueda;
	private JPanel barraBusqueda;
	private JPanel tabla;
	private JPanel boton;
	private JButton botonAdd;
	private TablaAmigos abstracttabla;
	private JTable table;
	
	private boolean cargada;
	
	public BusquedaUserWindow (Controller controller){
		this.controller = controller;
		this.addObserver();
		this.setCargada(false);
	}
	
	/**
	 * @return the cargada
	 */
	public boolean isCargada() {
		return cargada;
	}

	/**
	 * @param cargada the cargada to set
	 */
	public void setCargada(boolean cargada) {
		this.cargada = cargada;
	}

	private void addObserver (){
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
		
		this.botonAdd = new JButton ("Añadir");
		this.boton = new JPanel ();
		this.boton.add (this.botonAdd, BorderLayout.CENTER);
		this.add(this.boton, BorderLayout.SOUTH);
		this.addListener();
		controller.buscarUsuario("");
		
		this.setVisible(true);
		this.setCargada(true);
	}
	
	private void addListener (){
		this.botonAdd.addActionListener(new buttonAdd ());
		this.botonBusqueda.addActionListener(new buttonBusqueda ());
	}
	
	public class buttonBusqueda implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.buscarUsuario (texto.getText());	
		}
		
	}
	
	public class buttonAdd implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()!= -1){
				controller.addAmigo (abstracttabla.getIdForRow(table.getSelectedRow()));
				setVisible(false);
			}
		}
		
	}

	
	public void mostrarUsuarios(List<User> usuarios) {
		List <String> users = new ArrayList <String>();
		for (int i = 0; i < usuarios.size(); i++)
			users.add(usuarios.get(i).getNick());
		this.abstracttabla.establecerTabla((ArrayList <String>)users);
		this.abstracttabla.fireTableDataChanged();
	}

	public void addAmigo(String amigo) {
		// TODO Auto-generated method stub
		
	}

	public void amigosIniciales(List<String> amigo) {
		// TODO Auto-generated method stub
		
	}

	public void borrarAmigo(String amigo) {
		// TODO Auto-generated method stub
		
	}

	
	public void msgError(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}

}

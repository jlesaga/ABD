package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.Controller;
import modelo.AmigosObserver;
import modelo.TablaAmigos;
import modelo.User;

public class AmigosWindow extends JPanel implements AmigosObserver {

	private static final long serialVersionUID = 1L;
	
	private JPanel botones;
	private JButton add_amigo;
	private JButton eliminar;
	private Controller controller;
	private TablaAmigos tabla;
	private JTable table;
	private BusquedaUserWindow buscar;

	public AmigosWindow (Controller controller){
		
		this.controller = controller;
		String [] columnas = {"Nombre Amigo"};
		this.tabla = new TablaAmigos (columnas);
		this.table = new JTable (tabla);
		JScrollPane scrollpane = new JScrollPane(this.table);
		this.add(scrollpane, BorderLayout.NORTH);
		this.botones = this.construir_botones();
		this.add(this.botones, BorderLayout.SOUTH);
		controller.registrarAmigosObs(this);
		this.amigos_ini ();
	}
	
	private JPanel construir_botones (){
		JPanel panel = new JPanel ();
		this.add_amigo = new JButton ("Añadir amigo");
		this.eliminar = new JButton ("Eliminar");
		this.add_amigo.addActionListener(new buttonAñadir ());
		this.eliminar.addActionListener(new buttonBorrar ());
		panel.add(this.add_amigo, BorderLayout.WEST);
		panel.add (this.eliminar, BorderLayout.EAST);
		this.buscar = new BusquedaUserWindow (controller);
		return panel;
	}
	
	public class buttonAñadir implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (!buscar.isCargada())
				buscar.construirVentana();	
			else
				buscar.setVisible(true);
		}
		
	}
	
	public class buttonBorrar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()!= -1)
				controller.borrarAmigo (tabla.getIdForRow(table.getSelectedRow ()));
		}
		
	}
	public void amigos_ini () {
		controller.saberAmigos ();
	}

	public void amigosIniciales(List<String> amigo) {
		this.tabla.establecerTabla((ArrayList <String>)amigo);	
		this.tabla.fireTableDataChanged();		
	}

	public void addAmigo(String amigo) {
		this.tabla.write(amigo);
		this.tabla.fireTableDataChanged();		
	}

	public void borrarAmigo(String amigo) {
		this.tabla.deleteRow(amigo);
		this.tabla.fireTableDataChanged();
	}

	public void msgError(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}

	public void mostrarUsuarios(List<User> usuarios) {
		// TODO Auto-generated method stub
		
	}
	
}

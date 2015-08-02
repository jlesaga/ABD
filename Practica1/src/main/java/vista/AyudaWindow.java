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
import modelo.Crucigrama;
import modelo.CrucigramaObserver;
import modelo.Crucigrama_Activo;
import modelo.InfoAyuda;
import modelo.InfoCrucigramaActivo;
import modelo.InformacionWord;
import modelo.TablaAyuda;

public class AyudaWindow extends JPanel implements CrucigramaObserver{

	private static final long serialVersionUID = 1L;
	private JPanel botones;
	private JButton abrir;
	private JButton descartar;

	private Controller controller;
	private TablaAyuda tabla;

	private JTable table;

	public AyudaWindow (Controller controller){
		
		this.controller = controller;		
		String columnas [] = {"Nombre Amigo", "Titulo crucigrama"};
		this.tabla = new TablaAyuda (columnas);
		this.table = new JTable (tabla);
		JScrollPane scrollpane = new JScrollPane(this.table);
		this.add(scrollpane, BorderLayout.NORTH);
		this.botones = this.construir_botones();
		this.controller.registrarCrosswordObs(this);
		this.controller.saberPeticionesAyuda ();
		this.add(this.botones);
	}
	
	private JPanel construir_botones (){
		JPanel panel = new JPanel ();
		this.abrir = new JButton ("Abrir crucigrama");
		this.descartar = new JButton ("Descartar petición");
		this.abrir.addActionListener(new buttonAbrir ());
		this.descartar.addActionListener(new buttonBorrar ());
		panel.add(this.abrir, BorderLayout.WEST);
		panel.add (this.descartar, BorderLayout.EAST);		
		return panel;
	}
	
	public class buttonBorrar implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()!= -1)
				controller.borrarCrucigramaEntrante (tabla.getIdForRow(table.getSelectedRow ()));
			borrarCrucigramaentrante(tabla.getIdForRow(table.getSelectedRow ()));
		}
	}
	
	public class buttonAbrir implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if (table.getSelectedRow() != -1) {

				Crucigrama c;				
				c = new Crucigrama (tabla.getIdForRow(table.getSelectedRow()).getId_crucigrama()
						,tabla.getIdForRow(table.getSelectedRow()).getTitulo_crucigrama()
						,tabla.getIdForRow(table.getSelectedRow()).getFecha());
				CrosswordWindow window = new CrosswordWindow (controller, c, tabla.getIdForRow(table.getSelectedRow()).getNick_amigo(),false);
				window.construriVentana();		

			}
			
			
		}
		
	}
	

	public void borrarCrucigramaentrante(InfoAyuda idForRow) {
		this.tabla.deleteRow(idForRow);
		this.tabla.fireTableDataChanged();
		
	}

	public void actualizarLista(List<Crucigrama> crucigrama) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarAddActivos(Crucigrama c) {
		// TODO Auto-generated method stub
		
	}

	public void palabrasCrucigrama(List<InformacionWord> informacionWord) {
		// TODO Auto-generated method stub
		
	}

	public void cerrarVentanaCrucigrama() {
		// TODO Auto-generated method stub
		
	}

	public void listaAyudas(List<InfoAyuda> ayuda) {
		this.tabla.establecerTabla((ArrayList<InfoAyuda>) ayuda);
		this.tabla.fireTableDataChanged();		
	}
	
	public void msgError(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}

	public void actualizarListaActivos(List<InfoCrucigramaActivo> crucigramas) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarActivo(Crucigrama_Activo activo) {
		// TODO Auto-generated method stub
		
	}

	public void anunciarRespuestas(ArrayList<Integer> id_palabras_resueltas) {
		// TODO Auto-generated method stub
		
	}

	public void isCorrecta(boolean correcto, int id_palabra_resuelta) {
		// TODO Auto-generated method stub
		
	}


}

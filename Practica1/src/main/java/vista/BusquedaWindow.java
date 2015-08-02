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
import modelo.Crucigrama;
import modelo.CrucigramaObserver;
import modelo.Crucigrama_Activo;
import modelo.InfoAyuda;
import modelo.InfoCrucigramaActivo;
import modelo.InformacionWord;
import modelo.TablaActivos;

public class BusquedaWindow extends JFrame implements CrucigramaObserver{


	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTextField texto;
	private JButton botonBusqueda;
	private JPanel barraBusqueda;
	private JPanel tabla;
	private JPanel boton;
	private JButton botonAdd;
	private TablaActivos abstracttabla;
	private JTable table;
	
	private boolean cargada;
	
	public BusquedaWindow (Controller controller){
		this.controller = controller;
		this.addObserver();
		this.setCargada(false);
	}
	
	private void addObserver (){
		this.controller.registrarCrosswordObs(this);
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
		this.abstracttabla = new TablaActivos (columnas);
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
		controller.buscarCrucigrama("");
		this.setVisible(true);
		this.setCargada(true);
	}
	
	private void addListener (){
		this.botonAdd.addActionListener(new buttonAdd ());
		this.botonBusqueda.addActionListener(new buttonBusqueda ());
	}
	
	public class buttonBusqueda implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.buscarCrucigrama (texto.getText());	
		}
		
	}
	
	public class buttonAdd implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()!= -1){
				Crucigrama c = abstracttabla.getIdForRow(table.getSelectedRow());
				controller.addCrucigramaActivo (c);
				setVisible (false);
			}
		}
		
	}

	public void actualizarLista(List<Crucigrama> crucigramas) {
		this.abstracttabla.establecerTabla( (ArrayList <Crucigrama>)crucigramas);
		this.abstracttabla.fireTableDataChanged();
	}

	public void actualizarAddActivos(Crucigrama c) {
		// TODO Auto-generated method stub
		
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

	public void palabrasCrucigrama(List<InformacionWord> informacionWord) {
		// TODO Auto-generated method stub
		
	}

	public void isCorrecta(boolean correcto) {
		// TODO Auto-generated method stub
		
	}

	public void cerrarVentanaCrucigrama() {
		// TODO Auto-generated method stub
		
	}

	public void listaAyudas(List<InfoAyuda> ayuda) {
		// TODO Auto-generated method stub
		
	}


	public void borrarCrucigramaentrante(InfoAyuda idForRow) {
		// TODO Auto-generated method stub
		
	}

	public void msgError(String string) {
		// TODO Auto-generated method stub
		
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

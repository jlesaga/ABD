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
import modelo.TablaActivos;

public class CrucigramasWindow extends JPanel implements CrucigramaObserver {


	private static final long serialVersionUID = 1L;
	private JPanel botones;
	private JPanel tabla;
	private JButton abrir;
	private JButton busqueda;
	private Controller controller;
	private BusquedaWindow buscar;
	private TablaActivos tablaActivos;
	private JTable table;
	private List<InfoCrucigramaActivo> infoActivos;

	public CrucigramasWindow (Controller controller){
		this.controller = controller;
		String columnas [] = {"Titulo", "Fecha"};
		this.tablaActivos = new TablaActivos (columnas);
		this.table = new JTable (this.tablaActivos);
		JScrollPane scrollpane = new JScrollPane(table);
		this.tabla = new JPanel();
		this.tabla.add(scrollpane);
		this.add(this.tabla, BorderLayout.CENTER);
		
		this.botones = this.construir_botones();
		this.add(this.botones, BorderLayout.SOUTH);
		this.controller.registrarCrosswordObs(this);
		this.controller.pintarTablaActivos ();
		this.buscar = new BusquedaWindow (controller);
	}
	
	private JPanel construir_botones (){
		JPanel panel = new JPanel ();
		this.abrir = new JButton ("Abrir Crucigrama");
		this.busqueda = new JButton ("Busqueda de crucigrama");
		this.addListener();
		panel.add(this.abrir, BorderLayout.WEST);
		panel.add (this.busqueda, BorderLayout.EAST);		
		return panel;
	}
	
	private void addListener (){
		this.abrir.addActionListener(new buttonAbrir ());
		this.busqueda.addActionListener(new buttonBusqueda ());
	}

	public void actualizarLista(List<Crucigrama> crucigrama) {
		
	}
	
	public class buttonBusqueda implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (!buscar.isCargada())
				buscar.construirVentana();		
			else 
				buscar.setVisible(true);
			
		}
		
	}
	
	public class buttonAbrir implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() != -1) {
				int i = 0;
				Crucigrama c = tablaActivos.getIdForRow(table.getSelectedRow());
				while (i < infoActivos.size() && c != infoActivos.get(i).getCrucigrama())
					i++;
				if (i == infoActivos.size() || !infoActivos.get(i).isAyuda()) {
					CrosswordWindow window = new CrosswordWindow (controller, c,null, true);
					window.construriVentana();		
				}
				else
					msgError ("No se puede abrir el crucigrama pues has pedido ayuda");
			}
		}
		
	}

	public void actualizarListaActivos(List<InfoCrucigramaActivo> crucigramas) {
		ArrayList <Crucigrama> activos = new ArrayList <Crucigrama>();
		for (int i = 0; i < crucigramas.size(); i++)
			activos.add(crucigramas.get(i).getCrucigrama());
		this.tablaActivos.establecerTabla(activos);
		this.tablaActivos.fireTableDataChanged();
		this.infoActivos = crucigramas;
		
	}
	
	public void actualizarAddActivos(Crucigrama c) {
		this.tablaActivos.write(c);	
		this.tablaActivos.fireTableDataChanged();
	}


	public void palabrasCrucigrama(List<InformacionWord> informacionWord) {
		
	}

	public void isCorrecta(boolean correcto, String nick_respuesta) {
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

	public void msgError(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		
	}

	public void actualizarActivo(Crucigrama_Activo activo) {
		int i = 0;
		while ( i < infoActivos.size() && activo.getId_crucigrama() != infoActivos.get(i).getCrucigrama().getId()) 
			i++;
		infoActivos.get(i).setAyuda(true);
		
	}

	public void anunciarRespuestas(ArrayList<Integer> id_palabras_resueltas) {
		// TODO Auto-generated method stub
		
	}

	public void isCorrecta(boolean correcto, int id_palabra_resuelta) {
		// TODO Auto-generated method stub
		
	}

}

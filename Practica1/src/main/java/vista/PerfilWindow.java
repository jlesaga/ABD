package vista;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controlador.Controller;
import modelo.Crucigrama;

public class PerfilWindow extends JFrame{

	
	private static final long serialVersionUID = 1L;

	private UserWindow info;	
	private CrucigramasWindow crucigramawindow;
	private AmigosWindow amigoswindow;
	private AyudaWindow ayudawindow;
	private JTabbedPane tabbed;
	

	private Controller controller;
	
	public PerfilWindow (Controller controller){
		this.controller = controller;		
	}	
	
	public void construirPerfil (){
		this.info = new UserWindow (controller);
		this.setSize(500, 620);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(this.info.construirUserWindow(), BorderLayout.NORTH);
		this.construir_opciones();
		this.add(this.tabbed, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void construir_opciones (){
		this.tabbed = new JTabbedPane ();
		
		this.crucigramawindow = new CrucigramasWindow(controller);
		this.tabbed.addTab("Crucigramas", this.crucigramawindow);
		
		this.amigoswindow = new AmigosWindow (this.controller);
		this.tabbed.addTab("Amigos", this.amigoswindow);
		
		this.ayudawindow = new AyudaWindow (this.controller);
		this.tabbed.addTab("Petiones de ayuda", this.ayudawindow );
	
	}
	

	public void actualizarLista(List<Crucigrama> crucigrama) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarListaActivos(List<Crucigrama> crucigrama) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarAddActivos(Crucigrama c) {
		// TODO Auto-generated method stub
		
	}

}

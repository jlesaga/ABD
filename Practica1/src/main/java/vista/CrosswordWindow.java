package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.Controller;
import es.ucm.abd.crossword.CrosswordPanel;
import es.ucm.abd.crossword.CrosswordPanelEventListener;
import modelo.Crucigrama;
import modelo.CrucigramaObserver;
import modelo.Crucigrama_Activo;
import modelo.InfoAyuda;
import modelo.InfoCrucigramaActivo;
import modelo.InformacionWord;
import modelo.Word;


public class CrosswordWindow extends JFrame implements CrucigramaObserver{


	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Crucigrama crucigrama;
	
	private CrosswordPanel <Word> panel_crossword;	
	
	private Collection <Word> palabrasCrucigrama;
	private JScrollPane jScrollPane;
	private List<InformacionWord> informacionPalabras;
	
	private JPanel panel_inferior;
	private ImageIcon icono;
	private JButton buttonicono;
	private JTextArea textArea;
	private JScrollPane scrollArea;
	private JLabel num_letras;
	private JTextField insertar_palabra;
	private JButton buttonAceptar;
	private JButton buttonEnviar;
	private InformacionWord palabraSeleccionada;
	private String usuario;
	private boolean propietario;
	protected Word word_seleccionada;
	
	
	public CrosswordWindow (Controller controller, Crucigrama crucigrama, String usuario, boolean propietario ) {
		this.controller = controller;
		this.crucigrama = crucigrama;
		this.controller.registrarCrosswordObs(this);
		this.usuario = usuario;
		this.propietario =propietario;
	}
	
	public void construriVentana () {
		this.setSize(500, 550);
		this.jScrollPane = new JScrollPane();
		this.add(this.jScrollPane, BorderLayout.NORTH);		
		this.saberPalabras();
		this.panel_crossword = new CrosswordPanel <Word> (this.jScrollPane, this.palabrasCrucigrama);
		this.panel_crossword.setMinimumSize(new Dimension (500, 200));
		this.panel_crossword.setMaximumSize(new Dimension (500,200));
		this.jScrollPane.setViewportView(this.panel_crossword);
		
		this.icono = new ImageIcon ();
		this.buttonicono = new JButton ();
		this.buttonicono.setIcon(icono);
		this.textArea = new JTextArea (5, 30);
		this.textArea.setEditable(false);
		this.scrollArea = new JScrollPane (this.textArea);
		JPanel imagen_texto = new JPanel ();
		imagen_texto.add(this.buttonicono, BorderLayout.WEST);
		imagen_texto.add(this.scrollArea, BorderLayout.CENTER);
		
		this.num_letras = new JLabel ("x letras:");
		this.insertar_palabra = new JTextField (15);
		this.buttonAceptar = new JButton ("Aceptar");
		this.buttonEnviar = new JButton ("Enviar a un amigo");
		JPanel texto_botones = new JPanel ();
		texto_botones.add(this.num_letras);
		texto_botones.add(this.insertar_palabra);
		texto_botones.add(this.buttonAceptar);
		texto_botones.add(this.buttonEnviar);
		this.panel_inferior = new JPanel ();
		this.panel_inferior.add(imagen_texto, BorderLayout.NORTH);
		this.panel_inferior.add(texto_botones, BorderLayout.SOUTH);
		this.add(this.panel_inferior);
		this.createEventListener();
		this.setVisible(true);
		if(propietario)
		this.controller.saberPalabrasYaResueltas (this.crucigrama);	
		else if (!propietario)
		this.controller.saberPalabrasYaResueltasamigo(this.usuario, this.crucigrama);
		this.mostrarYaResueltas ();
	}


	private void mostrarYaResueltas() {
		for (int i = 0; i < this.informacionPalabras.size(); i++) {
			if (this.informacionPalabras.get(i).isResuelta())
				this.panel_crossword.showWord(this.informacionPalabras.get(i).getWord());
		}		
	}

	private void createEventListener () {
			
		this.panel_crossword.addEventListener(new CrosswordPanelEventListener<Word>() {
            public void wordSelected(CrosswordPanel<Word> source, Word newWord) {
                if (newWord != null) {
                	word_seleccionada = newWord;
                    cambiar_ventana ( palabra_seleccionada (newWord));
                } else {
                	source.setSelectedWord(null);
                }
            }
            
            public void deselected(CrosswordPanel<Word> source) {
                source.setSelectedWord(null);
            }

			public void cellSelected(CrosswordPanel<Word> source, Point newCell) {
				source.setSelectedCell(newCell);
				
			}
        });
		
		this.buttonAceptar.addActionListener(new buttonAcept ());
		this.buttonEnviar.addActionListener(new buttonSend ());
	}
	
	
	
	public class buttonAcept implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (!insertar_palabra.getText().equals("")) 
				if(propietario)
					controller.anotarRespuesta (insertar_palabra.getText(), palabraSeleccionada, crucigrama.getId());
				else 
					controller.anotarRespuestaamigo (insertar_palabra.getText(), palabraSeleccionada, crucigrama.getId(), usuario);
			else
				mostrar_mensaje ("Se debe introducir una respuesta");
		}
		
	}
	
	public class buttonSend implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(propietario){
				BusquedaUsuarioAyudaWindow ayuda = new BusquedaUsuarioAyudaWindow (controller, crucigrama);
				ayuda.construirVentana();
			}
		}
		
	}
	
	protected void cambiar_ventana(InformacionWord info) {
		byte [] imagen = info.getImagen();
		if (imagen != null){
			this.icono=new ImageIcon(imagen);
			Image img= this.icono.getImage();
			Image newimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			this.icono=new ImageIcon(newimg);
			this.buttonicono.setIcon(this.icono);
		}
		this.num_letras.setText(info.getWord().getWord().length() + " letras: ");
		if (!info.getPista().equals("") && info.getPista() != null )
			this.textArea.setText(info.getPista());
		else
			this.textArea.setText("");
		
	}

	public void mostrar_mensaje(String string) {
		JOptionPane.showMessageDialog(null, string);		
	}

	protected InformacionWord palabra_seleccionada (Word newWord) {
		int i = 0;
		while (i < this.informacionPalabras.size() && !this.informacionPalabras.get(i).getWord().equals(newWord))
			i++;
		this.palabraSeleccionada = this.informacionPalabras.get(i);
		return this.palabraSeleccionada;
	}
	
	private void saberPalabras(){
		this.controller.saberPalabrasCrucigrama (this.crucigrama.getId());
	}

	public void actualizarLista(List<Crucigrama> crucigrama) {
		// TODO Auto-generated method stub
		
	}


	public void actualizarAddActivos(Crucigrama c) {
		// TODO Auto-generated method stub
		
	}

	public void palabrasCrucigrama(List<InformacionWord> informacionWord) {
		this.palabrasCrucigrama = new LinkedList<Word>();
		for (int i = 0; i < informacionWord.size(); i++) 
			this.palabrasCrucigrama.add(informacionWord.get(i).getWord());
		this.informacionPalabras = informacionWord;
		
	}

	public void isCorrecta(boolean correcto, int id_palabra_resuelta) {
		if (correcto) {
			panel_crossword.showWord(this.word_seleccionada);
			controller.updatePuntosPropios (this.palabraSeleccionada.getPuntuacion(), true, usuario);
			int i = 0;
			while (i < this.informacionPalabras.size() && id_palabra_resuelta != this.informacionPalabras.get(i).getId_palabra())
				i++;
			this.informacionPalabras.get(i).setResuelta(true);
			this.panel_crossword.showWord(this.informacionPalabras.get(i).getWord());
		}
		else {
			mostrar_mensaje("Respuesta incorrecta");
			controller.updatePuntosPropios (this.palabraSeleccionada.getPuntuacion(), false, usuario);
		}
		
	}

	public void cerrarVentanaCrucigrama() {
		this.setVisible(false);
		this.dispose();		
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
		for (int i = 0; i < id_palabras_resueltas.size(); i++) {
			for (int j = 0; j < this.informacionPalabras.size(); j++) {
				if (id_palabras_resueltas.get(i) == this.informacionPalabras.get(j).getId_palabra())
					this.informacionPalabras.get(j).setResuelta(true);
			}
		}
	}

	
}

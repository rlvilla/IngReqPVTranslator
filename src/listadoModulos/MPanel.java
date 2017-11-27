package listadoModulos;

import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MPanel extends Panel {
	private JTextArea listaM = new JTextArea(20,20);
	private JScrollPane OrigScroll = new JScrollPane(listaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public MPanel(){
		this.setLayout(new BoxLayout(this, 2));
		this.add(listaM);
		
		//JPanel Buttons = new 
	}
	public void setController(MController ctr) {
		
	}
}

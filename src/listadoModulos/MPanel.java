package listadoModulos;

import java.awt.Panel;

import javax.swing.*;


public class MPanel extends Panel {
	private JTextArea listaM = new JTextArea(20,20);
	private JScrollPane listaMScroll = new JScrollPane(listaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public MPanel(){
		this.setLayout(new BoxLayout(this, 2));
		listaM.add(listaMScroll);
		this.add(listaM);
		JPanel Buttons = new JPanel();

	}
	public void setController(MController ctr) {
		
	}
}

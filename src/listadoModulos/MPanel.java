package listadoModulos;

import java.awt.*;

import javax.swing.*;


public class MPanel extends Panel {
	private JTextArea listaM = new JTextArea(20,20);
	private JScrollPane listaMScroll = new JScrollPane(listaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton bCargarDatos = new JButton("Cargar Datos");
	private JButton bVerMedidas = new JButton("Ver Medidas");
	private JButton bEliminarModulo = new JButton("Eliminar Modulo");
	private JButton bAnadirModulo = new JButton("Añadir Módulo");

	public MPanel(){
		this.setLayout(new BoxLayout(this, 1));
		JPanel pList = new JPanel();
		listaM.setEditable(false);
		pList.setLayout(new BoxLayout(pList, 1));
		pList.add(listaMScroll, BorderLayout.NORTH);
		pList.add(listaM, BorderLayout.SOUTH);

		JPanel pButtons = new JPanel();
		pButtons.setLayout(new GridLayout(2, 2));
		pButtons.add(bCargarDatos);
		pButtons.add(bVerMedidas);
		pButtons.add(bAnadirModulo);
		pButtons.add(bEliminarModulo);

		this.add(pList);
		this.add(pButtons);
	}
	public void setController(MController ctr) {
		String as = listaM.getSelectedText();
	}
}

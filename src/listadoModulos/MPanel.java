package listadoModulos;

import java.awt.Panel;


public class MPanel extends Panel {
	public void setController(MController ctr) {
		sizeField.addActionListener(ctr);
		sizeField.setActionCommand("ENTER");
	}
}

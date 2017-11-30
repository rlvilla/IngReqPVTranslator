package listadoModulos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MController implements ActionListener {
	private MPanel panel;
	//

	public MController(MPanel p) {
		panel = p;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(MPanel.CARGARMODULO)) {
			//TODO
		}
		if(e.getActionCommand().equals(MPanel.CARGARMEDIDAS)){
		    //TODO
        }
        if(e.getActionCommand().equals(MPanel.ELIMINARMODULO)){
            //TODO
        }
        if(e.getActionCommand().equals(MPanel.VERMEDIDAS)){
            //TODO
        }
        if(e.getActionCommand().equals(MPanel.MOSTRARMODULOS)){
            panel.muestraModulos(ListadoModulo.leerListaModulo());
        }
	}
}

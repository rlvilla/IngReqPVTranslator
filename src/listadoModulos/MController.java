package listadoModulos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MController implements ActionListener {
	private MPanel panel;
	//

	public MController(MPanel p) {
		panel = p;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(MPanel.CARGARMODULO)) {
            JFileChooser jfile = new JFileChooser();
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION){
                File file = jfile.getSelectedFile();
            }
        }

		if(e.getActionCommand().equals(MPanel.CARGARMEDIDAS)){
            JFileChooser jfile = new JFileChooser();
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION){
                File file = jfile.getSelectedFile();
            }
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

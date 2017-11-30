package listadoModulos;

import Modelo.BD;
import Modelo.FReader;
import listadoCampana.CPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class MController implements ActionListener {
	private MPanel panel;
	private FReader freader;
	//

	public MController(MPanel p) {
		panel = p;
		freader = new FReader();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(MPanel.CARGARMODULO)) {
            JFileChooser jfile = new JFileChooser();
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION){
                File file = jfile.getSelectedFile();
                try {
                    String[] modulo = freader.addModuloFichero(file);
                    ListadoModulo.anadirModulo(modulo[0], modulo[1], modulo[3], modulo[5], modulo[7]);
                    panel.muestraModulos(ListadoModulo.leerListaModulo());
                } catch(FileNotFoundException error){
                    error.printStackTrace();
                }
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
            String se = panel.getSelect();
            ListadoModulo.eliminarModulo(se);
            panel.muestraModulos(ListadoModulo.leerListaModulo());
        }
        if(e.getActionCommand().equals(MPanel.VERCAMPANAS)){
            String se = panel.getSelect();
            //ListadoModulo.miBD = BD.getInstance();
            final JFrame window = new JFrame("Listado Campana");
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    CPanel.createGUI(window);
                }
            });
        }
        if(e.getActionCommand().equals(MPanel.MOSTRARMODULOS)){
            panel.muestraModulos(ListadoModulo.leerListaModulo());
        }
	}
}

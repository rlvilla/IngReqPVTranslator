package listadoModulos;

import Modelo.FReader;
import listadoCampana.CPanel;
import listadoCampana.ListadoCampana;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class MController implements ActionListener {
	private MPanel panel;
	private FReader freader;

	public MController(MPanel p) {
		panel = p;
		freader = new FReader();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(MPanel.CARGARMODULO)) {
            JFileChooser jfile = new JFileChooser();
            jfile.setFileFilter(new FileNameExtensionFilter("DAT Files","dat"));
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION){
                File file = jfile.getSelectedFile();
                try {
                    String[] modulo = freader.leerFicheroModulo(file);
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
                try {
                    String[] campanaMedidas = freader.leerCampanaCanalesMedida(file);
                    ListadoCampana.anadirCampana(campanaMedidas[1],campanaMedidas[0],campanaMedidas[2],campanaMedidas[2]);
                    Map<String, String[]> puntosCurva = freader.leerPuntosCurva(file);
                    panel.muestraModulos(ListadoModulo.leerListaModulo());
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                }
            }
        }
        if(e.getActionCommand().equals(MPanel.ELIMINARMODULO)){
            ListadoModulo.eliminarModulo(panel.getSelect());
            panel.muestraModulos(ListadoModulo.leerListaModulo());
        }
        if(e.getActionCommand().equals(MPanel.VERCAMPANAS)){
            final JFrame window = new JFrame("Listado Campana");
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    CPanel.createGUI(window, panel.getSelect());
                }
            });
        }
        if(e.getActionCommand().equals(MPanel.MOSTRARMODULOS)){
            panel.muestraModulos(ListadoModulo.leerListaModulo());
        }
	}
}

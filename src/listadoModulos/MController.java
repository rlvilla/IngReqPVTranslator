package listadoModulos;

import Modelo.FReader;
import listadoCampana.CPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

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
		    if(panel.getSelect() !=null) {
                ListadoModulo.eliminarModulo(panel.getSelect());
                panel.muestraModulos(ListadoModulo.leerListaModulo());
            }else{
                JOptionPane.showMessageDialog(panel, "No has seleccionado ningún módulo", "Error: 507" , JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(MPanel.VERCAMPANAS)){
		    if(panel.getSelect() != null){
                final JFrame window = new JFrame("Listado Campana");
                SwingUtilities.invokeLater(new Runnable(){
                    public void run() {
                        CPanel.createGUI(window, panel.getSelect());
                    }
                });
            }else{
		        JOptionPane.showMessageDialog(panel, "No has seleccionado ningún módulo", "Error: 507" , JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getActionCommand().equals(MPanel.MOSTRARMODULOS)){
            panel.muestraModulos(ListadoModulo.leerListaModulo());
        }
	}
}

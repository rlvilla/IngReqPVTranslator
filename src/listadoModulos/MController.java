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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    String date = parseDate(campanaMedidas[2]);
                    if(ListadoModulo.miBD.selectEscalar("SELECT NOMBRE FROM CAMPANA WHERE NOMBRE = '"+campanaMedidas[1]+"';") == null){
                        ListadoCampana.anadirCampana(campanaMedidas[1],campanaMedidas[0],date,date);
                    }else{
                        if(new SimpleDateFormat("yyyy-MM-dd").parse(ListadoModulo.miBD.selectEscalar("SELECT DIAINI FROM CAMPANA WHERE NOMBRE = '"+campanaMedidas[1]+"';")).after
                                                                                                                                (new SimpleDateFormat("yyyy-MM-dd").parse(date))){
                            ListadoModulo.miBD.update("UPDATE CAMPANA SET DIAINI = '"+date+"' WHERE NOMBRE = '"+campanaMedidas[1]+"';");

                        }else if(new SimpleDateFormat("yyyy-MM-dd").parse(ListadoModulo.miBD.selectEscalar("SELECT DIAFIN FROM CAMPANA WHERE NOMBRE = '"+campanaMedidas[1]+"';")).before
                                                                                                                                        (new SimpleDateFormat("yyyy-MM-dd").parse(date))){
                            ListadoModulo.miBD.update("UPDATE CAMPANA SET DIAFIN = '"+date+"' WHERE NOMBRE = '"+campanaMedidas[1]+"';");
                        }
                    }
                    Map<String, String[]> puntosCurva = freader.leerPuntosCurva(file);
                    panel.muestraModulos(ListadoModulo.leerListaModulo());
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
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

    private String parseDate(String date) {
	    StringBuilder result = new StringBuilder();
	    String[] sepDate = date.split("/");
        result.append(sepDate[2]).append("-").append(sepDate[1]).append("-").append(sepDate[0]);
        return result.toString();
    }
}

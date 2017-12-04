package listadoModulos;

import Modelo.FReader;
import Modelo.Medida;
import listadoCampana.CPanel;
import listadoCampana.ListadoCampana;
import listadoMedidas.ListadoMedida;

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
            jfile.setFileFilter(new FileNameExtensionFilter("Archivos DAT","dat"));
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = jfile.getSelectedFile();
                try {
                    String[] modulo = freader.leerFicheroModulo(file);
                    ListadoModulo.anadirModulo(modulo[0], modulo[1], modulo[3], modulo[5], modulo[7]);
                    panel.muestraModulos(ListadoModulo.leerListaModulo());
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals(MPanel.CARGARMEDIDAS)) {
            JFileChooser jfile = new JFileChooser();
            jfile.setFileFilter(new FileNameExtensionFilter("Archivos XLS","xls"));
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = jfile.getSelectedFile();
                try {
                    String[] campanaMedidas = freader.leerCampanaCanalesMedida(file);
                    String date = parseDate(campanaMedidas[2]);
                    //Introduccion de la campaña en la BBDD si no existe
                    if (ListadoModulo.miBD.selectEscalar("SELECT NOMBRE FROM CAMPANA WHERE NOMBRE = '" + campanaMedidas[1] + "';") == null) {
                        ListadoCampana.anadirCampana(campanaMedidas[1], campanaMedidas[0], date, date);
                    } else {//en el caso de existir, se comparan las fechas de inicio y de fin con la fecha de la medida y se cambian si es necesario
                        if (new SimpleDateFormat("yyyy-MM-dd").parse(ListadoModulo.miBD.selectEscalar("SELECT DIAINI FROM CAMPANA WHERE NOMBRE = '" + campanaMedidas[1] + "';")).after
                                (new SimpleDateFormat("yyyy-MM-dd").parse(date))) {
                            ListadoModulo.miBD.update("UPDATE CAMPANA SET DIAINI = '" + date + "' WHERE NOMBRE = '" + campanaMedidas[1] + "';");

                        } else if (new SimpleDateFormat("yyyy-MM-dd").parse(ListadoModulo.miBD.selectEscalar("SELECT DIAFIN FROM CAMPANA WHERE NOMBRE = '" + campanaMedidas[1] + "';")).before
                                (new SimpleDateFormat("yyyy-MM-dd").parse(date))) {
                            ListadoModulo.miBD.update("UPDATE CAMPANA SET DIAFIN = '" + date + "' WHERE NOMBRE = '" + campanaMedidas[1] + "';");
                        }
                    }
                    //Introducción de la medida en la base de datos
                    ListadoMedida.anadirMedida(campanaMedidas, date);
                    Map<Integer, String[]> puntosCurva = freader.leerPuntosCurva(file);
                    for (int i = 0; i < puntosCurva.size(); i++) {
                        ListadoMedida.anadirPunto(i, puntosCurva.get(i), Medida.IDsetter(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(campanaMedidas[2] + " " + campanaMedidas[3])));
                    }
                    panel.muestraModulos(ListadoModulo.leerListaModulo());
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }

        }
        if (e.getActionCommand().equals(MPanel.ELIMINARMODULO)) {
            if (panel.getSelect() != null) {
                ListadoModulo.eliminarModulo(panel.getSelect());
                panel.muestraModulos(ListadoModulo.leerListaModulo());
            } else {
                JOptionPane.showMessageDialog(panel, "No has seleccionado ningún módulo", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand().equals(MPanel.VERCAMPANAS)) {
            if (panel.getSelect() != null) {
                final JFrame window = new JFrame("Listado Campana");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        CPanel.createGUI(window, panel.getSelect());
                    }
                });
            } else {
                JOptionPane.showMessageDialog(panel, "No has seleccionado ningún módulo", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getActionCommand().equals(MPanel.MOSTRARMODULOS)) {
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

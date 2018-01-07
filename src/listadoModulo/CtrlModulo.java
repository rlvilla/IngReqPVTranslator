package listadoModulo;

import Modelo.FReader;
import Modelo.Medida;
import Modelo.PVBD;
import listadoCampana.ViewCampana;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class CtrlModulo implements ActionListener {
    private ViewModulo panel;
    private FReader freader;

    public CtrlModulo(ViewModulo p) {
        panel = p;
        freader = new FReader();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ViewModulo.CARGARMODULO)) {
            JFileChooser jfile = new JFileChooser();
            jfile.setFileFilter(new FileNameExtensionFilter("Archivos DAT", "dat"));
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = jfile.getSelectedFile();
                try {
                    String[] modulo = freader.leerFicheroModulo(file);
                    PVBD.anadirModulo(modulo[0], modulo[1], modulo[3], modulo[5], modulo[7], modulo[9]);
                    panel.muestraModulos(PVBD.leerListaModulo());
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals(ViewModulo.CARGARMEDIDAS)) {
            JFileChooser jfile = new JFileChooser();
            jfile.setMultiSelectionEnabled(true);
            jfile.setFileFilter(new FileNameExtensionFilter("Archivos XLS", "xls"));
            int ret = jfile.showOpenDialog(panel);

            if (ret == JFileChooser.APPROVE_OPTION) {
                //File file = jfile.getSelectedFile();
                File[] files = jfile.getSelectedFiles();
                try {
                    for (File file : files) {
                        String[] campanaMedidas = freader.leerCampanaCanalesMedida(file);
                        String date = parseDate(campanaMedidas[2]);
                        //Introduccion de la campaña en la BBDD si no existe
                        if (PVBD.miBD.selectEscalar("SELECT NOMBRE FROM CAMPANA WHERE NOMBRE = '" + campanaMedidas[1] + "';") == null) {
                            PVBD.anadirCampana(campanaMedidas[1], campanaMedidas[0], date, date);
                        } else {//en el caso de existir, se comparan las fechas de inicio y de fin con la fecha de la medida y se cambian si es necesario
                            if (new SimpleDateFormat("yyyy-MM-dd").parse(PVBD.miBD.selectEscalar("SELECT DIAINI FROM CAMPANA WHERE NOMBRE = '" + campanaMedidas[1] + "';")).after
                                    (new SimpleDateFormat("yyyy-MM-dd").parse(date))) {
                                PVBD.miBD.update("UPDATE CAMPANA SET DIAINI = '" + date + "' WHERE NOMBRE = '" + campanaMedidas[1] + "';");

                            } else if (new SimpleDateFormat("yyyy-MM-dd").parse(PVBD.miBD.selectEscalar("SELECT DIAFIN FROM CAMPANA WHERE NOMBRE = '" + campanaMedidas[1] + "';")).before
                                    (new SimpleDateFormat("yyyy-MM-dd").parse(date))) {
                                PVBD.miBD.update("UPDATE CAMPANA SET DIAFIN = '" + date + "' WHERE NOMBRE = '" + campanaMedidas[1] + "';");
                            }
                        }
                        //Introducción de la medida en la base de datos
                        PVBD.anadirMedida(campanaMedidas, date);
                        int idMedida = Medida.IDsetter(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(campanaMedidas[2] + " " + campanaMedidas[3]), campanaMedidas[1]);
                        Map<String, String[]> puntosCurva = freader.leerPuntosCurva(file);
                        for (int i = 0; i < puntosCurva.size(); i++) {
                            String[] valores = puntosCurva.get(Integer.toString(i));
                            for (int j = 0; j < 3; j++) {
                                valores[j] = valores[j].replace(',', '.');
                            }
                            PVBD.anadirPunto(Integer.toString(i), valores, Integer.toString(idMedida));
                        }
                    }
                    panel.muestraModulos(PVBD.leerListaModulo());
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals(ViewModulo.ELIMINARMODULO)) {
            if (panel.getSelect() != null) {
                PVBD.eliminarModulo(panel.getSelect());
                panel.muestraModulos(PVBD.leerListaModulo());
            } else {
                JOptionPane.showMessageDialog(panel, "No has seleccionado ningún módulo", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand().equals(ViewModulo.VERCAMPANAS)) {
            if (panel.getSelect() != null) {
                final JFrame window = new JFrame("Listado Campana");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ViewCampana.createGUI(window, panel.getSelect());
                    }
                });
            } else {
                JOptionPane.showMessageDialog(panel, "No has seleccionado ningún módulo", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getActionCommand().equals(ViewModulo.MOSTRARMODULOS)) {
            panel.muestraModulos(PVBD.leerListaModulo());
        }
    }

    private String parseDate(String date) {
        StringBuilder result = new StringBuilder();
        String[] sepDate = date.split("/");
        result.append(sepDate[2]).append("-").append(sepDate[1]).append("-").append(sepDate[0]);
        return result.toString();
    }
}

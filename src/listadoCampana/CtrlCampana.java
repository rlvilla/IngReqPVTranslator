package listadoCampana;

import Modelo.PVBD;
import listadoMedida.ViewMedida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlCampana implements ActionListener{

    private ViewCampana cpanel;

    public CtrlCampana(ViewCampana p) {
        cpanel = p;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(ViewCampana.ELIMINARCAMPANA)){
            if(cpanel.getSelectC() != null) {
                PVBD.eliminarCampana(cpanel.getSelectC());
                cpanel.muestraCampanas(PVBD.leerListaCampana(cpanel.getMod()));
            }else{
                JOptionPane.showMessageDialog(cpanel, "No has seleccionado ninguna campaña", "Error: 507" , JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(ViewCampana.VERMEDIDAS)) {
            if (cpanel.getSelectC() != null) {
                final JFrame window = new JFrame("Listado Medidas");
                SwingUtilities.invokeLater(new Runnable(){
                    public void run() {
                        ViewMedida.createGUI(window, cpanel.getSelectC());
                    }
                });
            } else {
                JOptionPane.showMessageDialog(cpanel, "No has seleccionado ninguna campaña", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(ViewCampana.MOSTRARCAMPANA)){

            cpanel.muestraCampanas(PVBD.leerListaCampana(cpanel.getMod()));
        }
    }
}

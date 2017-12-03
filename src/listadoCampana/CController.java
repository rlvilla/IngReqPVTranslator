package listadoCampana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CController implements ActionListener{

    private CPanel cpanel;

    public CController(CPanel p) {
        cpanel = p;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(CPanel.ELIMINARCAMPANA)){
            if(cpanel.getSelectC() != null) {
                ListadoCampana.eliminarCampana(cpanel.getSelectC());
                cpanel.muestraCampanas(ListadoCampana.leerListaCampana(cpanel.getMod()));
            }else{
                JOptionPane.showMessageDialog(cpanel, "No has seleccionado ninguna campaña", "Error: 507" , JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(CPanel.VERMEDIDAS)) {
            if (cpanel.getSelectC() != null) {

            } else {
                JOptionPane.showMessageDialog(cpanel, "No has seleccionado ninguna campaña", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(CPanel.MOSTRARCAMPANA)){

            cpanel.muestraCampanas(ListadoCampana.leerListaCampana(cpanel.getMod()));
        }
    }
}

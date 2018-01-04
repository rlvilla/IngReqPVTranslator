package listadoMedida;

import Modelo.PVBD;
import verRepresentacion.ViewRepresentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMedida implements ActionListener{
    private ViewMedida mpanel;

    public CtrlMedida(ViewMedida p) {
        mpanel = p;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(ViewMedida.ELIMINARMEDIDA)){
            if(mpanel.getSelectC() != null) {
                PVBD.eliminarMedida(mpanel.getSelectID());
                mpanel.muestraMedidas(PVBD.leerListaMedida(mpanel.getMod()));
            }else{
                JOptionPane.showMessageDialog(mpanel, "No has seleccionado ninguna medida", "Error: 507" , JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(ViewMedida.VERCURVA)) {
            if (mpanel.getSelectC() != null) {
                final JFrame window = new JFrame("Representacion");
                SwingUtilities.invokeLater(new Runnable(){
                    public void run() {
                        ViewRepresentacion.createGUI(window, mpanel.getSelectC());
                    }
                });
            } else {
                JOptionPane.showMessageDialog(mpanel, "No has seleccionado ninguna medida", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(ViewMedida.MOSTRARMEDIDA)){

            mpanel.muestraMedidas(PVBD.leerListaMedida(mpanel.getMod()));
        }
    }
}

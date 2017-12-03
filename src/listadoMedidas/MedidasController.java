package listadoMedidas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedidasController implements ActionListener{
    private MedidasPanel mpanel;

    public MedidasController(MedidasPanel p) {
        mpanel = p;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(MedidasPanel.ELIMINARMEDIDA)){
            if(mpanel.getSelectC() != null) {
                ListadoMedida.eliminarMedida(mpanel.getSelectID());
                mpanel.muestraMedidas(ListadoMedida.leerListaMedida(mpanel.getMod()));
            }else{
                JOptionPane.showMessageDialog(mpanel, "No has seleccionado ninguna medida", "Error: 507" , JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(MedidasPanel.VERCURVA)) {
            if (mpanel.getSelectC() != null) {

            } else {
                JOptionPane.showMessageDialog(mpanel, "No has seleccionado ninguna medida", "Error: 507", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals(MedidasPanel.MOSTRARMEDIDA)){

            mpanel.muestraMedidas(ListadoMedida.leerListaMedida(mpanel.getMod()));
        }
    }
}

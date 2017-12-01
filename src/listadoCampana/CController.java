package listadoCampana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CController implements ActionListener{

    private CPanel panel;

    public CController(CPanel p) {
        panel = p;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(CPanel.ELIMINARCAMPANA)){
            ListadoCampana.eliminarCampana(panel.getSelect());
            panel.muestraCampanas(ListadoCampana.leerListaCampana(panel.getMod()));
        }
        if(e.getActionCommand().equals(CPanel.VERMEDIDAS)){
            //TODO
        }
        if(e.getActionCommand().equals(CPanel.MOSTRARCAMPANA)){
            panel.muestraCampanas(ListadoCampana.leerListaCampana(panel.getMod()));
        }
    }
}

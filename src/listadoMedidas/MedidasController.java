package listadoMedidas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedidasController implements ActionListener{
    private MedidasPanel panel;

    public MedidasController(MedidasPanel p){
        panel = p;
    }
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("ENTER")){

        }
    }
}

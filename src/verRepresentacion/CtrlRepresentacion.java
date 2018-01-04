package verRepresentacion;

import Modelo.Medida;
import Modelo.PVBD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CtrlRepresentacion implements ActionListener {
    private ViewRepresentacion panel;
    private Medida medida;

    public CtrlRepresentacion(ViewRepresentacion panel, int medida) {
        this.panel=panel;
        List<String[]> datos = PVBD.miBD.select("SELECT * FROM MEDIDA WHERE id = "+medida+";");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        // TODO Auto-generated method stub

    }

}

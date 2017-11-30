import Modelo.BD;
import listadoModulos.ListadoModulo;
import listadoModulos.MPanel;

import javax.swing.*;

public class Main {
   // static BD miBD;
    public static void main(String[] args) {
        ListadoModulo.miBD = BD.getInstance();
//        ListadoModulo.miBD.insert("INSERT INTO CAMPANA (NOMBRE, MODULO, DIAINI, DIAFIN) values ('cam', 'I-53 946431', '1997-12-12', '1998-12-12');");
//        ListadoModulo.miBD.insert("INSERT INTO CAMPANA (NOMBRE, MODULO, DIAINI, DIAFIN) values ('cam2', 'I-53 946431', '1993-12-12', '1995-12-12');");
        final JFrame window = new JFrame("Listado Módulos");
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                MPanel.createGUI(window);
            }
        });

    }
}

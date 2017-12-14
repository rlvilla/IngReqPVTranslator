import Modelo.BD;
import Modelo.PVBD;
import listadoModulo.ViewModulo;

import javax.swing.*;

public class Main {
   // static BD miBD;
    public static void main(String[] args) {
        PVBD.miBD = BD.getInstance();
//        ListadoModulo.miBD.insert("INSERT INTO CAMPANA (NOMBRE, MODULO, DIAINI, DIAFIN) values ('cam', 'Hola jeje', '1997-12-12', '1998-12-12');");
//        ListadoModulo.miBD.insert("INSERT INTO CAMPANA (NOMBRE, MODULO, DIAINI, DIAFIN) values ('cam2', 'Hola jeje', '1993-12-12', '1995-12-12');");
        final JFrame window = new JFrame("Listado Módulos");
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                ViewModulo.createGUI(window);
            }
        });
    }
}

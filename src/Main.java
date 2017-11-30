import Modelo.BD;
import listadoModulos.ListadoModulo;
import listadoModulos.MPanel;

import javax.swing.*;

public class Main {
   // static BD miBD;
    public static void main(String[] args) {
        ListadoModulo.miBD = BD.getInstance();
        final JFrame window = new JFrame("Listado Módulos");
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                MPanel.createGUI(window);
            }
        });
    }
}

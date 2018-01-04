package verRepresentacion;

import Modelo.Medida;
import Modelo.PVBD;
import Modelo.Punto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CtrlRepresentacion implements ActionListener {
    private ViewRepresentacion panel;
    private Medida medida;

    public CtrlRepresentacion(ViewRepresentacion panel, int medidaID) throws ParseException{
        this.panel = panel;
        List<String[]> puntos = PVBD.miBD.select("SELECT * FROM PUNTO WHERE idm = " + medidaID + ";");
        List<String[]> datos = PVBD.miBD.select("SELECT * FROM MEDIDA WHERE idm = " + medidaID + ";");
        Punto[] punticos = new Punto[puntos.size()];
        for (String[] medida : datos) {
            for (String[] punto:puntos) {
                punticos[Integer.parseInt(punto[1])] = new Punto(Integer.parseInt(punto[1]),Integer.parseInt(punto[2]),Integer.parseInt(punto[3]), Integer.parseInt(punto[4]));
            }
            this.medida = new Medida(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(medida[2] + " " + medida[3]), medida[4], punticos);
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.equals(ViewRepresentacion.MOSTRARCURVA)) {
//            panel.mostrarCurva(medida.getPuntos());
    }
        if (event.equals(ViewRepresentacion.CORREGIR)) {

        }
        if (event.equals(ViewRepresentacion.MOSTRARIV)) {
//            panel.mostrarIV();
        }
        if (event.equals(ViewRepresentacion.MOSTRARPV)) {
//            panel.mostrarPV();
        }
    }

}

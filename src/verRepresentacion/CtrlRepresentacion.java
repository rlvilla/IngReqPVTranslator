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

    public CtrlRepresentacion(ViewRepresentacion panel, int medidaID) throws ParseException {
        this.panel = panel;
        List<String[]> puntos = PVBD.miBD.select("SELECT * FROM PUNTO WHERE idm = " + medidaID + ";");
        String fecha = PVBD.miBD.selectEscalar("SELECT fecha FROM MEDIDA WHERE idm = " + medidaID + ";");
        String hora = PVBD.miBD.selectEscalar("SELECT hora FROM MEDIDA WHERE idm = " + medidaID + ";");
        String correcion = PVBD.miBD.selectEscalar("SELECT Correccion FROM MEDIDA WHERE idm = " + medidaID + ";");
        Punto[] punticos = new Punto[puntos.size()];
        for (String[] punto : puntos) {
            punticos[Integer.parseInt(punto[1])] = new Punto(Integer.parseInt(punto[1]), Float.parseFloat(punto[2]), Float.parseFloat(punto[3]), Float.parseFloat(punto[4]));
        }
        this.medida = new Medida(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha + " " + hora), correcion, punticos);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.equals(ViewRepresentacion.MOSTRARCURVA)) {
            for (Punto punto : medida.getPuntos()) {
                System.out.println(punto.toString());
            }
            System.out.println("hola que tal buenisimas noches");
            panel.mostrarCurva(medida.getPuntos());
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

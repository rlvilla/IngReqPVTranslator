package verRepresentacion;

import Modelo.PVBD;
import Modelo.Punto;
import verCorregida.ViewCorregida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CtrlRepresentacion implements ActionListener {
    private ViewRepresentacion panel;
    private int idm;

    public CtrlRepresentacion(ViewRepresentacion panel, int medidaID) {
        this.panel = panel;
        idm = medidaID;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ViewRepresentacion.MOSTRARCURVA)) {
            panel.muestraPuntos(PVBD.leerListaPuntos(Integer.toString(idm)));
        }
        if (event.getActionCommand().equals(ViewRepresentacion.CORREGIR)) {
            String method = panel.getSelectC();
            if (method.equals("IEC-60891_1")) {
                float isc1 = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT Isc FROM MEDIDA WHERE idm = " + idm + ";"));
                float g1 = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT Piranometro FROM MEDIDA WHERE idm = " + idm + ";"));
                float t1 = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT TempAmbiente FROM MEDIDA WHERE idm = " + idm + ";"));
                //TODO pop-up para introducir g2 y t2
                float g2 = 1000;
                float t2 = 25;
                int idc = (method + "_" + g2 + "_" + t2).hashCode();
                float alpha = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT alpha FROM MEDIDA INNER JOIN CAMPANA" +
                        " C ON MEDIDA.Campana = C.NOMBRE INNER JOIN MODULO M ON C.MODULO = M.Nombre WHERE idm = " + idm + ";"));
                float beta = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT beta FROM MEDIDA INNER JOIN CAMPANA" +
                        " C ON MEDIDA.Campana = C.NOMBRE INNER JOIN MODULO M ON C.MODULO = M.Nombre WHERE idm = " + idm + ";"));
                float kappa = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT kappa FROM MEDIDA INNER JOIN CAMPANA" +
                        " C ON MEDIDA.Campana = C.NOMBRE INNER JOIN MODULO M ON C.MODULO = M.Nombre WHERE idm = " + idm + ";"));
                float rs = Float.parseFloat(PVBD.miBD.selectEscalar("SELECT Rs FROM MEDIDA INNER JOIN CAMPANA" +
                        " C ON MEDIDA.Campana = C.NOMBRE INNER JOIN MODULO M ON C.MODULO = M.Nombre WHERE idm = " + idm + ";"));
                List<Punto> puntos = PVBD.crearListaPunto(Integer.toString(idm));
                List<Punto> nuevosPuntos = new ArrayList<>();
                for (Punto puntico : puntos) {
                    int orden = puntico.getOrder();
                    float newI = puntico.getCorriente() + isc1 * (g2 / g1 - 1) + alpha * (t2 - t1);
                    float newV = puntico.getTension() - rs * (newI - puntico.getCorriente()) - kappa * newI * (t2 - t1) + beta * (t2 - t1);
                    nuevosPuntos.add(new Punto(orden, newV, newI, newV * newI));
                }
                float pMax = getPMax(nuevosPuntos);
                float ipMax = nuevosPuntos.get(getPMaxIndex(nuevosPuntos, pMax)).getCorriente();
                float vpMax = nuevosPuntos.get(getPMaxIndex(nuevosPuntos, pMax)).getTension();

                final JFrame window = new JFrame("Correccion");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ViewCorregida.createGUI(window, panel.getID());
                    }
                });
            }
        }
        if (event.getActionCommand().equals(ViewRepresentacion.MOSTRARIV)) {
            panel.mostrarIV();
        }
        if (event.getActionCommand().equals(ViewRepresentacion.MOSTRARPV)) {
            panel.mostrarPV();
        }
    }

    private int getPMaxIndex(List<Punto> nuevosPuntos, float pMax) {
        int index = 0;
        for (Punto p : nuevosPuntos) {
            if (p.getPotencia() == pMax) {
                index = nuevosPuntos.indexOf(p);
            }
        }
        return index;
    }

    private float getPMax(List<Punto> nuevosPuntos) {
        float pMax = Float.MIN_VALUE;
        for (Punto p : nuevosPuntos) {
            if (p.getPotencia() > pMax) {
                pMax = p.getPotencia();
            }
        }
        return pMax;
    }

}

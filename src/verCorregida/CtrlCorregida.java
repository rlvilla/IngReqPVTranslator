package verCorregida;

import Modelo.PVBD;
import listadoMedida.ViewMedida;
import verRepresentacion.ViewRepresentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class CtrlCorregida implements ActionListener {

    private ViewCorregida panel;
    private int idm, idc;


    public CtrlCorregida(ViewCorregida panel, int idm, int idc) {
        this.panel = panel;
        this.idm = idm;
        this.idc = idc;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ViewCorregida.MOSTRARCORREGIDAS)) {
            panel.muestraPuntos(PVBD.leerListaPuntosCorregidos(Integer.toString(idm), Integer.toString(idc)));
        }
    }
}

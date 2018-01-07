package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Medida {
    private String campana;
    private Date fecha;
    private int id;
    private String corr;
    private Map<String, Float> canales;
    Punto[] puntos;

    public Medida(String campana, Date fecha, String corr, Punto[] puntos) {
        this.campana = campana;
        this.id = IDsetter(fecha, this.campana);
        this.fecha = fecha;
        if (corr.equals("ninguna")) {
            this.corr = null;
        } else {
            this.corr = corr;
        }
        this.puntos = puntos;
    }

    public String getFecha() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    public String getHora() {
        return new SimpleDateFormat("HH:mm:ss").format(fecha);
    }

    public Punto[] getPuntos() {
        return puntos;
    }

    public String getID() {
        return String.valueOf(id);
    }

    public static int IDsetter(Date fec, String campana) {
        return (campana + (new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(fec).toString())).hashCode();
    }

    public String getCorr() {
        return corr;
    }
}

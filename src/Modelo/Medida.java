package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;

public class Medida {
    private Date fecha;
    private int id;
    private String corr;
    private Dictionary<String, Float> canales;
    Punto[] puntos;

    public Medida(Date fecha, String corr, Punto[] puntos) {
        this.id = IDsetter(fecha);
        this.fecha = fecha;
        if(corr.equals("ninguna")){
            this.corr=null;
        }else{
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

    public static int IDsetter(Date fec) {
        return new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(fec).hashCode();
    }

    public String getCorr() {
        return corr;
    }
}

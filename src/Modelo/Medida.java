package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;

public class Medida {
    private Date fecha;
    private int id;
    private Dictionary<String,Float> canales;
    Punto[] puntos;

    public Medida(Date fecha, Punto[] puntos){
        this.id = IDsetter(fecha);
        this.fecha = fecha;
        this.puntos = puntos;
    }

    public String getFecha (){
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(fecha);
    }

    public String getID(){
        return String.valueOf(id);
    }

    public int IDsetter(Date fec){
        return new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(fec).hashCode();
    }
}

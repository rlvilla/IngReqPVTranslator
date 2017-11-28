package Modelo;

import java.util.Dictionary;

public class Medida {
    private String fecha;
    private Dictionary<String,Float> canales;
    Punto[] puntos;

    public Medida(String fecha, Punto[] puntos){
        this.fecha = fecha;
        this.puntos = puntos;
    }
}

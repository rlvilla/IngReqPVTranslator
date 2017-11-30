package Modelo;

import java.util.Date;

public class Campana {
    private String name;
    private Date ini;
    private Date fin;

    public Campana(String name,Date ini,Date fin) {
        this.name = name;
        this.ini = ini;
        this.fin = fin;
    }

    public String getName(){
        return name;
    }

    public String getDates(){
        return "Inicio: " + ini.toString() + "  Fin: " + fin.toString();
    }

}

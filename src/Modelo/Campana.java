package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public String getInicio(){
        return new SimpleDateFormat("dd/MM/yyyy").format(ini);
    }

    public String getFin(){
        return new SimpleDateFormat("dd/MM/yyyy").format(fin);
    }

}

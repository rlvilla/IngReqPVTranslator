package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Modulo {
    private String name;
    private float alpha, beta, gamma, kappa, rs;

    private List<Medida> medidas;

    public Modulo(String name, float alpha, float beta, float gamma, float kappa, float rs){
        this.name = name;
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.kappa = kappa;
        this.rs = rs;
        medidas = new ArrayList<>();
    }

    public String toString(){
        return("Name: " +name+ "\nAlpha: "+alpha+"\nBeta: "+beta+"\nGamma: "+gamma+"\nKappa: "+kappa+"\n");
    }

    public String getName(){
        return name;
    }

}

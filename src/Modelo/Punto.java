package Modelo;

public class Punto {
    private int order;
    private float tension, corriente;

    public Punto(int n, float V, float I){
        order = n;
        tension = V;
        corriente = I;
    }
}

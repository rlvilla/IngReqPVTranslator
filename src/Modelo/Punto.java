package Modelo;

public class Punto {
    private int order;
    private float tension, corriente, potencia;

    public Punto(int n, float V, float I, float W){
        order = n;
        tension = V;
        corriente = I;
        potencia = W;
    }

    public float getTension(){
        return tension;
    }
    public float getCorriente(){
        return corriente;
    }
    public float getPotencia(){
        return potencia;
    }

    public int getOrder() {return order;}

    public String toString(){
        return(order+":\n"+"V: "+tension+"\nI: "+ corriente+"\nP: "+potencia);
    }
}

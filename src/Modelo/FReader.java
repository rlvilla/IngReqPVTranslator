package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FReader {

    private String nalpha, nbeta, ngamma, nkappa, name;
    private String alpha, beta, gamma, kappa;
    private  int aux=0;
    public void addModuloFichero(File fichero) {
        try {
            Scanner sc = new Scanner(fichero);
            String[] as = new String[9];
            addModulo(as, sc);
            sc.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private String [] addModulo(String[] as, Scanner sc) {
        int cont = 1;

        while ((sc.hasNextLine() && (cont < 23))) {
           as[aux]= Modulo(sc.nextLine(), cont);
            cont++;
        }
        return as;
    }

    private String Modulo(String linea, int contador){
        if (contador==1){
            aux++;
            return name=linea;
        }else if(contador==15){
            aux++;
            return alpha=(linea);
        }else if(contador==16){
            aux++;
            return nalpha="mA/ºC";
        }else if(contador==17){
            aux++;
            return beta=(linea);
        }else if(contador==18){
            aux++;
            return nbeta="mV/ºC";
        }else if(contador==19){
            aux++;
            return gamma=(linea);
        }else if(contador==20){
            aux++;
            return ngamma="mW/ºC";
        }else if(contador==21){
            aux++;
            return kappa=(linea);
        }else if(contador==22){
            aux++;
            return nkappa="mOhm/ºC";
        }
        return "";
    }

}

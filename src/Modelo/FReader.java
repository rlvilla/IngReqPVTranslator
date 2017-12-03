package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class FReader {

    private void print(String[] s) {//Inutil, comprobaciones y poco mas
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i] + "\n");
        }
    }

    private File parseFile(File file) {
        try {
            Path p = Paths.get(file.toString());
            ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(p));
            CharBuffer cb = Charset.forName("windows-1252").decode(bb);
            bb = Charset.forName("UTF-8").encode(cb);
            Files.write(Paths.get("medidas.xls"), bb.array());
        } catch (IOException error) {
            error.printStackTrace();
        }
        return new File("medidas.xls");
    }

    /*
        funciones lectura archivo medidas
        Separados en:
        leerCampanaCanales, noCanal y leerLineaCampanaCanales para extraer los datos de campaña y Medida
        leerPuntosCurva y leerLineaPuntos para extraer los datos a introducir en la curva
    */

    public String[] leerCampanaCanalesMedida(File fichero) throws FileNotFoundException {
        fichero = parseFile(fichero);
        Scanner sc = new Scanner(fichero);
        String[] sCanales = new String[18];
        int index = 0;
        int cnt = 1;
        while (sc.hasNextLine() && cnt <= 32) {
            if (cnt > 13 && !noCanal(cnt)) {//lineas 14,17,20,23,26,29,32 -> Canales de la medida
                if (cnt == 29) {
                    String linea29 = leerLineaCampanaCanales(sc.nextLine());
                    if (linea29.equals("ºC")) {
                        sCanales[index] = "null";
                    } else {
                        sCanales[index] = linea29;
                    }
                } else {
                    sCanales[index] = leerLineaCampanaCanales(sc.nextLine());
                }
                index++;
            } else if (cnt != 5 && cnt != 13 && cnt < 13) {//lineas 1-12 ignorando la 5(vacia) y la 13(informacion no util)
                if (cnt == 1) {
                    sCanales[index] = sc.nextLine().split(":")[1].split("\\s+", 2)[1];
                } else {
                    sCanales[index] = leerLineaCampanaCanales(sc.nextLine());
                }
                index++;
            } else {//Resto de lineas, las ignora y salta a la siguiente
                sc.nextLine();
            }
            cnt++;
        }
        for (int i = 0; i < sCanales.length; i++) {
            sCanales[i] = sCanales[i].replaceAll(",", ".");
        }
        sc.close();
        try {
            Files.delete(Paths.get("medidas.xls"));
        } catch (IOException roberto) {
            roberto.printStackTrace();
        }
        return sCanales;
    }

    private boolean noCanal(int cnt) {
        return cnt != 14 && cnt != 17 && cnt != 20 && cnt != 23 && cnt != 26 && cnt != 29 && cnt != 32;
    }

    private String leerLineaCampanaCanales(String linea) {
        String[] aux = linea.split(":", 2);
        return aux[1].split("\\s+")[1];//aux1 contiene el valor y las unidades, por lo tanto volvemos a separar
    }

    public Map<Integer, String[]> leerPuntosCurva(File fichero) throws FileNotFoundException {
        fichero = parseFile(fichero);
        Scanner sc = new Scanner(fichero);
        Map<Integer, String[]> puntos = new HashMap<>();
        int n = 0; //numero de puntos
        int cnt = 1;
        while (cnt < 36) {//nos saltamos las primeras 35 lineas ya que nos son irrelevantes para los puntos
            cnt++;
            sc.nextLine();
        }
        n = Integer.parseInt(sc.nextLine().split(":")[1].split("\\s+")[1]);
        sc.nextLine();//salto linea 37
        for (int i = 0; i < n; i++) {
            String[] valores = leerLineaPuntos(sc.nextLine());
            puntos.put(i, valores);
        }
        sc.close();
        try {
            Files.delete(Paths.get("medidas.xls"));
        } catch (IOException roberto) {
            roberto.printStackTrace();
        }
        return puntos;
    }

    private String[] leerLineaPuntos(String linea) {
        String aux[] = linea.split("\\s+");
        for (int i = 0; i < aux.length; i++) {
            aux[i] = aux[i].replaceAll(",", ".");
        }
        String result[] = {aux[1], aux[2], aux[3]};
        return result;
    }


    /*
        Funciones para cargar un archivo de modulo
    */
    public String[] leerFicheroModulo(File fichero) throws FileNotFoundException {
        int cont = 1;
        int aux = 0;
        Scanner sc = new Scanner(fichero);
        String[] datosModulo = new String[9];

        while ((sc.hasNextLine() && (cont < 23))) {
            String linea = sc.nextLine();
            if (cont == 1) {
                datosModulo[aux] = linea;
                aux++;
            } else if (cont == 15) {
                datosModulo[aux] = linea;
                aux++;
            } else if (cont == 16) {
                datosModulo[aux] = "mA/ºC";
                aux++;
            } else if (cont == 17) {
                datosModulo[aux] = linea;
                aux++;
            } else if (cont == 18) {
                datosModulo[aux] = "mV/ºC";
                aux++;
            } else if (cont == 19) {
                datosModulo[aux] = linea;
                aux++;
            } else if (cont == 20) {
                datosModulo[aux] = "mW/ºC";
                aux++;
            } else if (cont == 21) {
                datosModulo[aux] = linea;
                aux++;
            } else if (cont == 22) {
                datosModulo[aux] = "mOhm/ºC";
                aux++;
            }
            cont++;
        }
        sc.close();
        return (datosModulo);
    }
}
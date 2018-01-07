package Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PVBD {
    public static BD miBD;

    private static List<Modulo> crearListaModulo() {
        List<Modulo> listaMod = new ArrayList<>();
        for (Object[] mod : miBD.select("SELECT * FROM MODULO")) {
            listaMod.add(new Modulo((String) mod[0], Float.parseFloat((String) mod[1]), Float.parseFloat((String) mod[2]),
                    Float.parseFloat((String) mod[3]), Float.parseFloat((String) mod[4]), Float.parseFloat((String)mod[5])));
        }
        return listaMod;
    }

    public static String[] leerListaModulo() {
        List<Modulo> listaMod = crearListaModulo();
        String[] listaModulo = new String[listaMod.size()];
        for (Modulo mod : listaMod) {
            listaModulo[listaMod.indexOf(mod)] = mod.getName();
        }
        return listaModulo;
    }

    //Inutil, solo para comprobar los modulos por consola
    private static void printConsole(List<Modulo> lista) {
        for (Modulo elem : lista) {
            System.out.println(elem.toString());
        }
    }

    public static void anadirModulo(String name, String alpha, String beta, String gamma, String kappa, String Rs) {
        miBD.insert("INSERT INTO MODULO (Nombre, alpha, beta, gamma, kappa, Rs) values ('" + name + "', " + alpha +
                ", " + beta + ", " + gamma + ", " + kappa + ","+Rs+");");
    }

    public static void eliminarModulo(String name) {
        miBD.delete("DELETE FROM MODULO WHERE Nombre = '" + name + "';");
    }


    public static List<Campana> crearListaCampana(String modulo) throws ParseException {
        List<Campana> list = new ArrayList<Campana>();
        for (String[] st : PVBD.miBD.select("SELECT * FROM CAMPANA WHERE MODULO = '" + modulo + "';")) {
            list.add(new Campana(st[0], new SimpleDateFormat("yyyy-MM-dd").parse(st[2]),
                    new SimpleDateFormat("yyyy-MM-dd").parse(st[3])));
        }
        return list;
    }

    public static String[][] leerListaCampana(String modulo) {
        List<Campana> listaCam = null;
        try {
            listaCam = crearListaCampana(modulo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[][] listaCampana = new String[listaCam.size()][3];
        for (Campana cam : listaCam) {
            listaCampana[listaCam.indexOf(cam)] = new String[]{cam.getName(), cam.getInicio(), cam.getFin()};
        }
        return listaCampana;
    }

    public static void anadirCampana(String nombre, String modulo, String diaIni, String diaFin) {
        if (!modulo.equals(PVBD.miBD.selectEscalar("Select Nombre FROM MODULO WHERE Nombre = '" + modulo + "'"))) {
            PVBD.anadirModulo(modulo, "0", "0", "0", "0", "0");
        }
        PVBD.miBD.insert("INSERT INTO CAMPANA(NOMBRE, MODULO, DIAINI, DIAFIN) VALUES ('" + nombre + "', '" + modulo +
                "', '" + diaIni + "', '" + diaFin + "')");
    }

    public static void eliminarCampana(String campana) {
        PVBD.miBD.delete("DELETE FROM CAMPANA WHERE NOMBRE = '" + campana + "';");
    }

    public static List<Medida> crearListaMedida(String campana) throws ParseException {
        List<Medida> list = new ArrayList<>();
        for (String[] st : PVBD.miBD.select("SELECT * FROM MEDIDA WHERE CAMPANA = '" + campana + "';")) {
            list.add(new Medida(st[0],new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(st[2] + " " + st[3]), st[4], null));
        }
        return list;
    }

    public static String[][] leerListaMedida(String campana) {
        List<Medida> listaMed = null;
        try {
            listaMed = crearListaMedida(campana);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[][] listaMedida = new String[listaMed.size()][1];
        for (Medida med : listaMed) {
            listaMedida[listaMed.indexOf(med)] = new String[]{med.getFecha(), med.getHora(), med.getCorr()};
        }
        return listaMedida;
    }

    public static void anadirMedida(String[] campanaMedidas, String date) throws ParseException {
        PVBD.miBD.insert("INSERT INTO MEDIDA (Campana, idm, fecha, hora, Correccion, Isc, Voc, PMax, IPmax, VPmax, FF, VViento, DirViento, HumedadRel, TempAmbiente, Piranometro, RTD, CelulaIso) VALUES ('" +
                campanaMedidas[1] + "', " + Medida.IDsetter(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(campanaMedidas[2] + " " + campanaMedidas[3]),campanaMedidas[1]) +
                ", '" + date + "', '" + campanaMedidas[3] + "', '" + campanaMedidas[4] + "', " + campanaMedidas[5] + ", " + campanaMedidas[6] + ", " + campanaMedidas[7] + ", " + campanaMedidas[8] + ", " + campanaMedidas[9] +
                ", " + campanaMedidas[10] + ", " + campanaMedidas[11] + ", " + campanaMedidas[12] + ", " + campanaMedidas[13] + ", " + campanaMedidas[14] + ", " + campanaMedidas[15] + ", " + campanaMedidas[16] + ", " + campanaMedidas[17] + ");");
    }

    public static void eliminarMedida(int id) {
        PVBD.miBD.delete("DELETE FROM MEDIDA WHERE idm = " + id + ";");
    }

    public static void anadirPunto(String order, String[] values, String idm) {
        miBD.insert("INSERT INTO PUNTO (idm, orden, Tension, Corriente, Potencia) values (" + idm + "," + order + "," + values[0] + "," + values[1] + "," + values[2]+");");
    }

    public static List<Punto> crearListaPunto(String medida) {
        List<Punto> list = new ArrayList<>();
        for (String[] st : PVBD.miBD.select("SELECT * FROM PUNTO WHERE idm = '" + medida + "';")) {
            list.add(new Punto(Integer.parseInt(st[1]), Float.parseFloat(st[2]),Float.parseFloat(st[3]),Float.parseFloat(st[4])));
        }
        return list;
    }
    
    public static String[][] leerListaPuntos(String medida){
        List<Punto> listaPun = null;
        listaPun = crearListaPunto(medida);

        String[][] listaPunto = new String[listaPun.size()][1];
        for (Punto pun : listaPun) {
            listaPunto[listaPun.indexOf(pun)] = new String[]{Float.toString(pun.getTension()),
                    Float.toString(pun.getCorriente()), Float.toString(pun.getPotencia())};

        }
        return listaPunto;
    }
}

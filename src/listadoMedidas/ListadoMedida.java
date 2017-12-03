package listadoMedidas;

import Modelo.Medida;
import listadoModulos.ListadoModulo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListadoMedida {
    public static List<Medida> crearListaMedida (String campana) throws ParseException{
        List <Medida> list = new ArrayList<>();
        for (String [] st: ListadoModulo.miBD.select("SELECT * FROM MEDIDAS WHERE CAMPANA = '" + campana + "';")) {
            list.add(new Medida(new SimpleDateFormat("yyyy-MM-dd").parse(st[2]),null));
        }
        return list;
    }

    public static String[][] leerListaMedida(String campana){
        List<Medida> listaMed = null;
        try {
            listaMed = crearListaMedida(campana);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[][] listaMedida = new String[listaMed.size()][1];
        for (Medida med: listaMed) {
            listaMedida[listaMed.indexOf(med)] = new String[]{med.getFecha()};
        }
        return listaMedida;
    }

    public static void eliminarMedida(String medidas){
        ListadoModulo.miBD.delete("DELETE FROM MEDIDA WHERE NOMBRE = '" + medidas + "';");
    }
}

package listadoCampana;

import Modelo.Campana;
import listadoModulos.ListadoModulo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListadoCampana {
    public static List<Campana> crearListaCampana (String modulo) throws ParseException{
        List <Campana> list = new ArrayList<Campana>();
        for (String [] st: ListadoModulo.miBD.select("SELECT * FROM CAMPANA WHERE MODULO = '" + modulo + "';")) {
            list.add(new Campana(st[0], new SimpleDateFormat("yyyy-MM-dd").parse(st[2]),
                    new SimpleDateFormat("yyyy-MM-dd").parse(st[3])));
        }
        return list;
    }

    public static String[][] leerListaCampana(String modulo){
        List<Campana> listaCam = null;
        try {
            listaCam = crearListaCampana(modulo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[][] listaCampana = new String[listaCam.size()][3];
        for (Campana cam: listaCam) {
            listaCampana[listaCam.indexOf(cam)] = new String[]{cam.getName(), cam.getInicio(), cam.getFin()};
        }
        return listaCampana;
    }

    public static void eliminarCampana(String campana){
        ListadoModulo.miBD.delete("DELETE FROM CAMPANA WHERE NOMBRE = '" + campana + "';");
    }
}

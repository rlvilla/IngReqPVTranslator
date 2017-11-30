package listadoCampana;

import Modelo.Campana;
import listadoModulos.ListadoModulo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListadoCampana {
    public static List<Campana> crearListaCampana (String modulo) throws ParseException{
        ListadoModulo.miBD.insert("INSERT INTO CAMPANA (NOMBRE, MODULO, DIAINI, DIAFIN) values ('cam', 'I-53 946431', '1997-12-12', '1998-12-12');");
        ListadoModulo.miBD.insert("INSERT INTO CAMPANA (NOMBRE, MODULO, DIAINI, DIAFIN) values ('cam2', 'I-53 946431', '1993-12-12', '1995-12-12');");

        List <Campana> list = new ArrayList<Campana>();
        for (String [] st: ListadoModulo.miBD.select("SELECT * FROM CAMPANA WHERE MODULO = '" + modulo + "';")) {
            list.add(new Campana(st[0], new SimpleDateFormat("yyyy-MM-dd").parse(st[2]),
                    new SimpleDateFormat("yyyy-MM-dd").parse(st[3])));
        }
        return list;
    }

    public static String[] leerListaCampana(String modulo){
        List<Campana> listaCam = null;
        try {
            listaCam = crearListaCampana(modulo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] listaCampana = new String[listaCam.size()];
        for (Campana cam: listaCam) {
            listaCampana[listaCam.indexOf(cam)] = cam.getName() + " " + cam.getDates();
        }
        return listaCampana;
    }
}

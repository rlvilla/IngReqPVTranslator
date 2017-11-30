package listadoCampana;

import Modelo.Campana;
import listadoModulos.ListadoModulo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListadoCampana {
    public List<Campana> getCampanas (String modulo) throws ParseException{
        List <Campana> list = new ArrayList<Campana>();
        for (String [] st: ListadoModulo.miBD.select("SELECT * FROM CAMPANA WHERE MODULO = '" + modulo + "';")) {
            list.add(new Campana(st[0], new SimpleDateFormat("yyyy-MM-dd").parse(st[2]),
                    new SimpleDateFormat("yyyy-MM-dd").parse(st[3])));
        }
        return list;
    }
}

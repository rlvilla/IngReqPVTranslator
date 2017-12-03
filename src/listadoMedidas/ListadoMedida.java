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

    public static void anadirMedida(String[] campanaMedidas, String date) throws ParseException {
        ListadoModulo.miBD.insert("INSERT INTO MEDIDA (Campana, idm, fecha, hora, Correccion, Isc, Voc, PMax, IPmax, VPmax, FF, VViento, DirViento, HumedadRel, TempAmbiente, Piranometro, RTD, CelulaIso) VALUES ('"+
        campanaMedidas[1]+"', "+Medida.IDsetter(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(campanaMedidas[2] + " " + campanaMedidas[3]))+
                ", '"+date+"', '"+campanaMedidas[3]+"', '"+campanaMedidas[4]+"', "+campanaMedidas[5]+", "+campanaMedidas[6]+", "+campanaMedidas[7]+", "+campanaMedidas[8]+", "+campanaMedidas[9]+
                ", "+campanaMedidas[10]+", "+campanaMedidas[11]+", "+campanaMedidas[12]+", "+campanaMedidas[13]+", "+campanaMedidas[14]+", "+campanaMedidas[15]+", "+campanaMedidas[16]+", "+campanaMedidas[17]+");");
    }

    public static void eliminarMedida(String medidas){
        ListadoModulo.miBD.delete("DELETE FROM MEDIDA WHERE NOMBRE = '" + medidas + "';");
    }
}

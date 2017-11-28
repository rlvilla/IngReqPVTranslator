package listadoModulos;

import javax.swing.*;
import Modelo.*;
import listadoMedidas.ListadoMedidas;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class ListadoModulo {
	static BD miBD;

	public static void main(String[] args) {
		miBD = BD.getInstance();
		final JFrame window = new JFrame("Listado Módulos");
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				MPanel.createGUI(window);
			}
		});
	}

	private static List<Modulo> crearListaModulo(){
		List<Modulo> listaMod = new ArrayList<>();
		for (Object[] mod:miBD.select("SELECT * FROM MODULO")) {
			listaMod.add(new Modulo((String)mod[0],Float.parseFloat((String)mod[1]), Float.parseFloat((String)mod[2]),
					Float.parseFloat((String)mod[3]),Float.parseFloat((String)mod[4])));
		}
		return listaMod;
	}

	public static String[] leerListaModulo(){
		List<Modulo> listaMod = crearListaModulo();
		String[] listaModulo = new String[listaMod.size()];
		for(Modulo mod:listaMod){
			listaModulo[listaMod.indexOf(mod)] = mod.getName();
		}
		return listaModulo;
	}

	//Inutil, solo para comprobar los modulos por consola
	private static void printConsole(List<Modulo> lista){
		for (Modulo elem : lista){
			System.out.println(elem.toString());
		}
	}

	private static void anadirModulo(String name, String alpha, String beta, String gamma, String kappa){
	    miBD.insert("INSERT INTO MODULO (Nombre, alpha, beta, gamma, kappa) values ('"+name+"', "+alpha+
                                                                            ", "+beta+", "+gamma+", "+kappa+");");
    }
}

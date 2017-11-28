package listadoModulos;

import javax.swing.*;
import Modelo.*;

import java.util.ArrayList;
import java.util.List;


public class ListadoModulo {
	public static void createGUI(JFrame window) {
		MPanel panel = new MPanel();
		MController ctr = new MController(panel);
		panel.setController(ctr);
		window.setContentPane(panel);
		window.setVisible(true);
		window.pack();
		window.setSize(500, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		BD miBD = BD.getInstance();
		miBD.insert("INSERT INTO MODULO(Nombre, alpha, beta, gamma, kappa) values (hola, 1, 2, 3, 4);");
		List<Modulo> listaMod = new ArrayList<>();
		for (Object[] mod:miBD.select("SELECT * from MODULO")) {
			listaMod.add(new Modulo((String)mod[0]));
		}
		printConsole(listaMod);
		final JFrame window = new JFrame("Listado Módulos");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI(window);
			}
		});
	}

	private static void printConsole(List<Modulo> lista){
		for (Modulo elem : lista){
			System.out.println(elem.toString());
		}
	}
}

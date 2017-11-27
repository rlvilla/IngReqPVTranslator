package listadoModulos;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


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
		final JFrame window = new JFrame("Lists application");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI(window);
			}
		});
	}
}

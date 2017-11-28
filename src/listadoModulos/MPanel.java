package listadoModulos;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class MPanel extends Panel {
	//Componentes
    private JList listaM = new JList();
	private JScrollPane listaMScroll = new JScrollPane(listaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton bCargarMedidas = new JButton("Cargar Medidas");
	private JButton bVerMedidas = new JButton("Ver Medidas");//TBC ver campañas
	private JButton bEliminarModulo = new JButton("Eliminar Modulo");
	private JButton bAnadirModulo = new JButton("Añadir Módulo");

	//Constantes de comando
    static final String CARGARMEDIDAS = "Cargar medidas";
    static final String VERMEDIDAS = "Ver medidas"; //TBC ver campañas
    static final String ANADIRMODULO = "Añadir modulo";
    static final String ELIMINARMODULO = "Eliminar modulo";
    static final String MOSTRARMODULOS = "Mostrar modulos";

    //Constructor
    public MPanel(){
		this.setLayout(new BoxLayout(this, 1));
		JPanel pList = new JPanel();
		pList.setLayout(new BoxLayout(pList, 1));
		//pList.add(listaMScroll, BorderLayout.NORTH);
		pList.add(listaM, BorderLayout.SOUTH);

		JPanel pButtons = new JPanel();
		pButtons.setLayout(new GridLayout(2, 2));

		//Acciones de los botones
		bCargarMedidas.setActionCommand(CARGARMEDIDAS);
		bVerMedidas.setActionCommand(VERMEDIDAS);
		bAnadirModulo.setActionCommand(ANADIRMODULO);
		bEliminarModulo.setActionCommand(ELIMINARMODULO);

		pButtons.add(bCargarMedidas);
		pButtons.add(bVerMedidas);//TBC ver campañas
		pButtons.add(bAnadirModulo);
		pButtons.add(bEliminarModulo);

		this.add(pList);
		this.add(pButtons);
	}

	//Funciones
    public void muestraModulos(String[] listaMod){
        listaM.setListData(listaMod);
    }

    public void cargarDatos(){
        //TODO
    }

    public void anadirModulo(){
        //TODO
    }

    public void eliminarModulo(){
        //TODO
    }

    public void verMedidas(){
        //TODO
    }

	public void setController(MController ctr) {
		bCargarMedidas.addActionListener(ctr);
		bVerMedidas.addActionListener(ctr);
		bAnadirModulo.addActionListener(ctr);
		bEliminarModulo.addActionListener(ctr);

	}

    public static void createGUI(JFrame window) {
        MPanel panel = new MPanel();
        MController ctr = new MController(panel);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARMODULOS));
        window.setVisible(true);
        window.pack();
        window.setSize(500,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

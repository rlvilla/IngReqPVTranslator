package listadoModulos;

import Modelo.Modulo;

import java.awt.*;

import javax.swing.*;


public class MPanel extends Panel {
	//Componentes
    private JTextArea listaM = new JTextArea(20,20);
	private JScrollPane listaMScroll = new JScrollPane(listaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton bCargarDatos = new JButton("Cargar Datos");
	private JButton bVerMedidas = new JButton("Ver Medidas");//TBC ver campañas
	private JButton bEliminarModulo = new JButton("Eliminar Modulo");
	private JButton bAnadirModulo = new JButton("Añadir Módulo");

	//Constantes de comando
    static final String CARGARDATOS = "Cargar datos";
    static final String VERMEDIDAS = "Ver medidas"; //TBC ver campañas
    static final String ANADIRMODULO = "Añadir modulo";
    static final String ELIMINARMODULO = "Eliminar modulo";
    static final String MOSTRARMODULOS = "Mostrar modulos";

    //Constructor
    public MPanel(){
		this.setLayout(new BoxLayout(this, 1));
		JPanel pList = new JPanel();
		listaM.setEditable(false);
		pList.setLayout(new BoxLayout(pList, 1));
		pList.add(listaMScroll, BorderLayout.NORTH);
		pList.add(listaM, BorderLayout.SOUTH);

		JPanel pButtons = new JPanel();
		pButtons.setLayout(new GridLayout(2, 2));

		//Acciones de los botones
		bCargarDatos.setActionCommand(CARGARDATOS);
		bVerMedidas.setActionCommand(VERMEDIDAS);
		bAnadirModulo.setActionCommand(ANADIRMODULO);
		bEliminarModulo.setActionCommand(ELIMINARMODULO);

		pButtons.add(bCargarDatos);
		pButtons.add(bVerMedidas);//TBC ver campañas
		pButtons.add(bAnadirModulo);
		pButtons.add(bEliminarModulo);

		this.add(pList);
		this.add(pButtons);
	}

	//Funciones
    public void muestraModulos(){
        //TODO
        listaM.append("Aquí van los modulos, zunormah\n");
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
		String as = listaM.getSelectedText();
	}
}

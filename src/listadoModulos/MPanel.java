package listadoModulos;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MPanel extends Panel {
	//Componentes
    private JList listaM = new JList();
	private JScrollPane listaMScroll = new JScrollPane(listaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton bCargarModulo = new JButton("Cargar Modulo");
	private JButton bVerCampanas = new JButton("Ver Campañas");//TBC ver campañas
	private JButton bEliminarModulo = new JButton("Eliminar Modulo");
	private JButton bCargarMedidas = new JButton("Cargar Medidas");

	//Constantes de comando
    static final String CARGARMODULO = "Cargar modulo";
    static final String VERCAMPANAS = "Ver campanas"; //TBC ver campañas
    static final String CARGARMEDIDAS = "Cargar medidas";
    static final String ELIMINARMODULO = "Eliminar modulo";
    static final String MOSTRARMODULOS = "Mostrar modulos";

    //Constructor
    public MPanel(){
		this.setLayout(new BorderLayout());
        listaM.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    bVerCampanas.doClick();
                }
            }
        });
		JPanel pList = new JPanel();
		pList.setLayout(new BoxLayout(pList, 1));
        listaM.setPreferredSize(new Dimension (200,200));
        listaM.setFont(new Font("Arial",Font.BOLD,20));
		pList.add(listaMScroll);

		JPanel pButtons = new JPanel();
		pButtons.setLayout(new GridLayout(1, 5));

		//Acciones de los botones
		bCargarModulo.setActionCommand(CARGARMODULO);
		bVerCampanas.setActionCommand(VERCAMPANAS);
		bCargarMedidas.setActionCommand(CARGARMEDIDAS);
		bEliminarModulo.setActionCommand(ELIMINARMODULO);

        pButtons.add(bCargarModulo);
        pButtons.add(bCargarMedidas);
        pButtons.add(new JLabel());
        pButtons.add(bVerCampanas);//TBC ver campañas
		pButtons.add(bEliminarModulo);

		pButtons.setMinimumSize(new Dimension(500,100));
		pButtons.setMaximumSize(new Dimension(500,100));//Ajusta el tamaño máximo de los botones a las dimensiones

		this.add(pList, BorderLayout.CENTER);
		this.add(pButtons, BorderLayout.SOUTH);
	}

	//Funciones
    public void muestraModulos(String[] listaMod){
        listaM.setListData(listaMod);
    }

    public void cargarMedidas(){
        //TODO
    }

    public String getSelect(){
        return (String)listaM.getSelectedValue();
    }

	public void setController(MController ctr) {
		bCargarModulo.addActionListener(ctr);
		bVerCampanas.addActionListener(ctr);
		bCargarMedidas.addActionListener(ctr);
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
        window.setSize(800,400);
        //window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

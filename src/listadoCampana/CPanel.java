package listadoCampana;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CPanel extends Panel {
    private JList listaC = new JList();
    private JScrollPane listaCScroll = new JScrollPane(listaC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton bVerMedidas = new JButton("Ver Medidas");//TBC ver campañas
    private JButton bEliminarCampana = new JButton("Eliminar Campaña");

    //Constantes de comando
    static final String VERMEDIDAS = "Ver medidas"; //TBC ver campañas
    static final String ELIMINARCAMPANA = "Eliminar campana";
    static final String MOSTRARCAMPANA = "Mostrar campana";

    //Constructor
    public CPanel(){
        this.setLayout(new BorderLayout());
        JPanel pList = new JPanel();
        pList.setLayout(new BoxLayout(pList, 1));
        listaC.setPreferredSize(new Dimension (200,200));
        listaC.setFont(new Font("Arial",Font.BOLD,20));
        pList.add(listaCScroll);

        JPanel pButtons = new JPanel();
        pButtons.setLayout(new GridLayout(1, 2));

        //Acciones de los botones
        bVerMedidas.setActionCommand(VERMEDIDAS);
        bEliminarCampana.setActionCommand(ELIMINARCAMPANA);

      
        pButtons.add(bVerMedidas);//TBC ver campañas
        pButtons.add(bEliminarCampana);

        pButtons.setMinimumSize(new Dimension(500,100));
        pButtons.setMaximumSize(new Dimension(500,100));//Ajusta el tamaño máximo de los botones a las dimensiones

        this.add(pList, BorderLayout.CENTER);
        this.add(pButtons, BorderLayout.SOUTH);
    }

    //Funciones
    public void muestraCampanas(String[] listaCod){
        listaC.setListData(listaCod);
    }

    public void eliminarCampana(String el){

    }

    public void verMedidas(){
        //TODO
    }

    public String getSelect(){
        return (String)listaC.getSelectedValue();
    }

    public void setController(CController ctr) {
        bVerMedidas.addActionListener(ctr);
        bEliminarCampana.addActionListener(ctr);
    }

    public static void createGUI(JFrame window) {
        CPanel panel = new CPanel();
        CController ctr = new CController(panel);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARCAMPANA));
        window.setVisible(true);
        window.pack();
        window.setSize(800,400);
        //window.setResizable(false);
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

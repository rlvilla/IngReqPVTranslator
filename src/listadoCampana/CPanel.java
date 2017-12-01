package listadoCampana;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CPanel extends Panel {
    private JLabel jName = new JLabel();
    //private JList listaC = new JList();
    private DefaultTableModel model = new DefaultTableModel(null, new String[]{"Nombre", "Fecha Inicio", "Fecha Fin"});
    private JTable tablaC = new JTable(model);
    private JScrollPane listaCScroll = new JScrollPane(tablaC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton bVerMedidas = new JButton("Ver Medidas");//TBC ver campañas
    private JButton bEliminarCampana = new JButton("Eliminar Campaña");

    //Constantes de comando
    static final String VERMEDIDAS = "Ver medidas"; //TBC ver campañas
    static final String ELIMINARCAMPANA = "Eliminar campana";
    static final String MOSTRARCAMPANA = "Mostrar campana";

    //Constructor
    public CPanel(String name){
        this.setLayout(new BorderLayout());
        jName.setText(name);
        JPanel ptitulo = new JPanel(new BorderLayout());
        ptitulo.add(jName, BorderLayout.WEST);
        JPanel pList = new JPanel();
        pList.setLayout(new BoxLayout(pList, 1));
        tablaC.setPreferredSize(new Dimension (200,285));
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

        this.add(ptitulo, BorderLayout.NORTH);
        this.add(pList, BorderLayout.CENTER);
        this.add(pButtons, BorderLayout.SOUTH);
    }

    //Funciones
    public void muestraCampanas(String[][] listaCod){
        model.setRowCount(0);
        for (String[] line: listaCod) {
            model.addRow(line);
        }
    }

    public void verMedidas(){
        //TODOwefwe
    }

    public String getSelectC(){
        if (tablaC.getSelectedRow() >=0)
            return (String)tablaC.getValueAt(tablaC.getSelectedRow(),0);
        else
            return null;
    }

    public void setController(CController ctr) {
        bVerMedidas.addActionListener(ctr);
        bEliminarCampana.addActionListener(ctr);
    }

    public String getMod (){
        return jName.getText();
    }

    public static void createGUI(JFrame window, String name) {
        CPanel panel = new CPanel(name);
        CController ctr = new CController(panel);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARCAMPANA));
        window.setVisible(true);
        window.pack();
        window.setSize(800,400);
    }
}

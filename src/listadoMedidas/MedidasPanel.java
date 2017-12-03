package listadoMedidas;

import Modelo.Medida;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MedidasPanel extends Panel{
    private JLabel jName = new JLabel();
    //private JList tablaM = new JList();
    private DefaultTableModel model = new DefaultTableModel(null, new String[]{"Fecha", "Hora", "Corrección"}){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable tablaM = new JTable(model);
    private JScrollPane tablaMScroll = new JScrollPane(tablaM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton bVerCurva = new JButton("Ver curva");//TBC ver campañas
    private JButton bEliminarCampana = new JButton("Eliminar Medida");

    //Constantes de comando
    static final String VERCURVA = "Ver curva"; //TBC ver campañas
    static final String ELIMINARMEDIDA = "Eliminar medida";
    static final String MOSTRARMEDIDA = "Mostrar medida";
    
    public MedidasPanel(String name){
        this.setLayout(new BorderLayout());
        tablaM.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    bVerCurva.doClick();
                }
            }
        });
        jName.setText(name);
        JPanel ptitulo = new JPanel(new BorderLayout());
        ptitulo.add(jName, BorderLayout.WEST);
        JPanel pList = new JPanel();
        pList.setLayout(new BoxLayout(pList, 1));
        tablaM.setPreferredSize(new Dimension (200,285));
        pList.add(tablaMScroll);

        JPanel pButtons = new JPanel();
        pButtons.setLayout(new GridLayout(1, 2));

        //Acciones de los botones
        bVerCurva.setActionCommand(VERCURVA);
        bEliminarCampana.setActionCommand(ELIMINARMEDIDA);


        pButtons.add(bVerCurva);//TBC ver campañas
        pButtons.add(bEliminarCampana);

        pButtons.setMinimumSize(new Dimension(500,100));
        pButtons.setMaximumSize(new Dimension(500,100));//Ajusta el tamaño máximo de los botones a las dimensiones

        this.add(ptitulo, BorderLayout.NORTH);
        this.add(pList, BorderLayout.CENTER);
        this.add(pButtons, BorderLayout.SOUTH);
    }

    //Funciones
    public void muestraMedidas(String[][] tablaMod) {
        model.setRowCount(0);
        for (String[] line : tablaMod) {
            model.addRow(line);
        }
    }

    public String getSelectC(){
        if (tablaM.getSelectedRow() >=0)
            return (String)tablaM.getValueAt(tablaM.getSelectedRow(),0);
        else
            return null;
    }

    public int getSelectID() {
        if (tablaM.getSelectedRow() >=0) {
            try {
                return Medida.IDsetter(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .parse(tablaM.getValueAt(tablaM.getSelectedRow()
                                , 0) + " " + tablaM.getValueAt(tablaM.getSelectedRow(), 1)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return -1;
        }
        else
            return -1;
    }

    public void setController(MedidasController ctr) {
        bVerCurva.addActionListener(ctr);
        bEliminarCampana.addActionListener(ctr);
    }

    public String getMod (){
        return jName.getText();
    }

    public static void createGUI(JFrame window, String name) {
        MedidasPanel panel = new MedidasPanel(name);
        MedidasController ctr = new MedidasController(panel);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARMEDIDA));
        window.setVisible(true);
        window.pack();
        window.setSize(800,400);
    }
}

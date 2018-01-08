package verRepresentacion;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ViewRepresentacion extends Panel {

    private int id;

    // Desplegable
    String[] opcionesCombo = {"IEC-60891_1", "IEC-60891_2", "IEC-60891_3", "ASTM E1036"};
    JComboBox comboList = new JComboBox(opcionesCombo);
    // RadioButton de los IV/PV
    JRadioButton IV = new JRadioButton("IV", true);
    JRadioButton PV = new JRadioButton("PV", false);
    ButtonGroup group = new ButtonGroup();

    // Modelo para que no se pueda deditar ninguna de las tablas
    String[] izquierdanombres = {"V", "I", "P"};
    String[] inferiornombres = {"Correccion", "Isc", "Voc", "PMax", "IPMax", "VPMax"};

    JFreeChart xyLine;

    private DefaultTableModel model1 = new DefaultTableModel(null, izquierdanombres){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable tablaIzquierda = new JTable(model1);
    
    private String[][] tablaModel;
    private String[][] tablaCorregidas;

    private JScrollPane tablaIzquierdaScroll = new JScrollPane(tablaIzquierda, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private DefaultTableModel model2 = new DefaultTableModel(null, inferiornombres){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable tablaInferior = new JTable(model2);
    private JScrollPane tablaInferiorScroll = new JScrollPane(tablaInferior, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    //Constantes de funciones
    static final String MOSTRARIV = "MOSTRAR CURVA IV";
    static final String MOSTRARPV = "MOSTRAR CURVA PV";
    static final String MOSTRARCURVA = "Mostrar curva";
    static final String CORREGIR = "Corregir curva";

    // Boton corregir
    private JButton bCorregir = new JButton("Corregir");
    private JTextArea datosArea = new JTextArea();
    private JScrollPane datosAreaScroll = new JScrollPane(datosArea, JScrollPane. VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    public ViewRepresentacion(int id) {

        this.id = id;

        this.setLayout(new BorderLayout());
        group.add(IV);
        group.add(PV);

        // Botones derecha
        JPanel panelDerecha = new JPanel();
        IV.setActionCommand(MOSTRARIV);
        PV.setActionCommand(MOSTRARPV);
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.PAGE_AXIS));
        comboList.setPreferredSize(new Dimension (200,20));
        comboList.setMaximumSize(new Dimension (200,20));
        comboList.setMinimumSize(new Dimension (200,20));
        panelDerecha.add(comboList);
        panelDerecha.add(bCorregir);
        bCorregir.setActionCommand(CORREGIR);
        panelDerecha.add(IV);
        panelDerecha.add(PV);
        datosArea.setEditable(false);

        panelDerecha.add(datosAreaScroll);
        //panelDerecha.setMaximumSize(new Dimension(260, 400));
        //panelDerecha.setMinimumSize(new Dimension(260, 400));
        panelDerecha.setPreferredSize(new Dimension(260, 100));

        // TablaIzquierda
        JPanel panelListaIzq = new JPanel();
        panelListaIzq.setLayout(new BoxLayout(panelListaIzq, 1));
        tablaIzquierdaScroll.setPreferredSize(new Dimension(260, 400));
        //tablaIzquierda.setFont(new Font("Arial", Font.BOLD, 20));
        panelListaIzq.setPreferredSize(new Dimension(260, 400));
        panelListaIzq.add(tablaIzquierdaScroll);

        // Grafica (CENTRO)

        XYSeries IV = new XYSeries("Representar");
        IV.add(1, 2);
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(IV);
        xyLine = ChartFactory.createXYLineChart("Grafica IV", "I", "V", datos, PlotOrientation.VERTICAL,
                true, true, false);
        //xyLine.
        ChartPanel panelGrafica = new ChartPanel(xyLine);
        panelGrafica.setLayout(new BoxLayout(panelGrafica, 1));
        panelGrafica.setMaximumSize(new Dimension(300, 300));
        panelGrafica.setMinimumSize(new Dimension(300, 300));

        // TablaInferior
        JPanel panelListaInf = new JPanel();
        panelListaInf.setLayout(new BoxLayout(panelListaInf, 1));
        tablaInferiorScroll.setPreferredSize(new Dimension(500, 200));
        panelListaInf.add(tablaInferiorScroll);

        this.add(panelListaIzq, BorderLayout.WEST);
        this.add(panelGrafica, BorderLayout.CENTER);
        this.add(panelDerecha, BorderLayout.EAST);
        this.add(panelListaInf, BorderLayout.SOUTH);

    }

    public static void createGUI(JFrame window, int id) throws ParseException{
        ViewRepresentacion panel = new ViewRepresentacion(id);
        CtrlRepresentacion ctr = new CtrlRepresentacion(panel, id);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARCURVA));
        window.setVisible(true);
        window.pack();
        window.setSize(1000, 600);
    }

    private void setController(CtrlRepresentacion ctr) {
        bCorregir.addActionListener(ctr);
        IV.addActionListener(ctr);
        PV.addActionListener(ctr);
    }
    public void muestradatos(String []  s){
        StringBuilder sb = new StringBuilder("Isc: ");
        sb.append(s[0].toString() + " \nVoc: ");
        sb.append(s[1].toString() + " \nPmax: ");
        sb.append(s[2].toString() + " \nIPmax: ");
        sb.append(s[3].toString() + " \nVPmax: ");
        sb.append(s[4].toString() + " \nTemperatura Ambiente: " );
        sb.append(s[5].toString() + "ºC \nIrradiancia: " );
        sb.append(s[6].toString() );
        datosArea.setText(sb.toString());
    }

    public void muestraPuntos(String[][] tablaPuntos, String[][] tablaMedidasCorr) {
        model1.setRowCount(0);
        model2.setRowCount(0);
        tablaModel = tablaPuntos;
        tablaCorregidas = tablaMedidasCorr;
        XYSeries IV = new XYSeries("Representar");
        for (String[] line : tablaModel) {
            model1.addRow(line);
            IV.add(Double.parseDouble(line[1]),Double.parseDouble(line[0]));
        }
        for (String[] line : tablaCorregidas) {
            model2.addRow(line);
        }
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(IV);
        xyLine.getXYPlot().setDataset(datos);

    }

    public void mostrarIV(){
        XYSeries IV = new XYSeries("Representar");
        for (String[] line : tablaModel) {
            IV.add(Double.parseDouble(line[1]),Double.parseDouble(line[0]));
        }
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(IV);
        xyLine.getXYPlot().setDataset(datos);
    }

    public void mostrarPV(){
        XYSeries PV = new XYSeries("Representar");
        for (String[] line : tablaModel) {
            PV.add(Double.parseDouble(line[2]),Double.parseDouble(line[0]));
        }
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(PV);
        xyLine.getXYPlot().setDataset(datos);
    }

    public int getID(){
        return id;
    }

    public String getSelectC(){
        return comboList.getSelectedItem().toString();
    }

    public String[][] getModel (){
        return tablaModel;
    }

}


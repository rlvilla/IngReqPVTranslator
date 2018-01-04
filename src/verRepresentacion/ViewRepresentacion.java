package verRepresentacion;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import Modelo.Punto;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ViewRepresentacion extends Panel {
    // Desplegable
    String[] opcionesCombo = {"medida1", "medida2", "medida3"};
    JComboBox comboList = new JComboBox(opcionesCombo);
    // RadioButton de los IV/PV
    JRadioButton IV = new JRadioButton("IV", true);
    JRadioButton PV = new JRadioButton("PV", false);
    ButtonGroup group = new ButtonGroup();

    // Modelo para que no se pueda deditar ninguna de las tablas

    // DATOS SIN IMPORTANCIA
    String[] izquierdanombres = {"V", "I", "P"};
    String[] inferiornombres = {"Nombre Medida", "V", "I", "P", "Correccion"};
    Object[][] datosIzquierda = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
    Object[][] datosInferior = {{"Medida1", 1, 1, 1, "Si"}, {"Medida2", 2, 2, 2, "Si"},
            {"Medida3", 3, 3, 3, "Si"}};

    // DECLARACION DE LAS TABLAS
//    private JList listaIzq = new JList();
//    private JScrollPane listaIzqScroll = new JScrollPane(listaIzq, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    private JList listaInf = new JList();
//    private JScrollPane listaInfScroll = new JScrollPane(listaInf, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
    private JTable tablaIzquierda = new JTable(datosIzquierda, izquierdanombres);
    private JScrollPane tablaIzquierdaScroll = new JScrollPane(tablaIzquierda, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JTable tablaInferior = new JTable(datosInferior, inferiornombres);
    private JScrollPane tablaInferiorScroll = new JScrollPane(tablaInferior, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    //Constantes de funciones
    static final String MOSTRARIV = "MOSTRAR CURVA IV";
    static final String MOSTRARPV = "MOSTRAR CURVA PV";
    static final String MOSTRARCURVA = "Mostrar medidas";
    static final String CORREGIR = "Corregir curva";

    // Boton corregir
    private JButton bCorregir = new JButton("Corregir");

    public ViewRepresentacion(String name) {
//        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        group.add(IV);
        group.add(PV);

        // Botones derecha
        JPanel panelDerecha = new JPanel();
        IV.setActionCommand(MOSTRARIV);
        PV.setActionCommand(MOSTRARPV);
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.PAGE_AXIS));
        panelDerecha.add(bCorregir);
        bCorregir.setActionCommand(CORREGIR);
        panelDerecha.add(comboList);
        panelDerecha.add(IV);
        panelDerecha.add(PV);
        //panelDerecha.setMaximumSize(new Dimension(500, 100));
        //panelDerecha.setMinimumSize(new Dimension(500, 100));

        // TablaIzquierda
        JPanel panelListaIzq = new JPanel();
        panelListaIzq.setLayout(new BoxLayout(panelListaIzq, 1));
        //tablaIzquierda.setPreferredSize(new Dimension(260, 400));
        //tablaIzquierda.setFont(new Font("Arial", Font.BOLD, 20));
        panelListaIzq.add(tablaIzquierdaScroll);

        // Grafica (CENTRO)
        XYSeries IV = new XYSeries("Representar");
        IV.add(1, 2);
        IV.add(3, 4);
        IV.add(1, 1);
        IV.add(4, 4);
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(IV);
        JFreeChart xyLine = ChartFactory.createXYLineChart("Grafica IV", "I", "V", datos, PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panelGrafica = new ChartPanel(xyLine);
        panelGrafica.setLayout(new BoxLayout(panelGrafica, 1));
        panelGrafica.setMaximumSize(new Dimension(300, 300));
        panelGrafica.setMinimumSize(new Dimension(300, 300));

        // TablaInferior
        JPanel panelListaInf = new JPanel();
        panelListaInf.setLayout(new BoxLayout(panelListaInf, 1));
        //tablaInferior.setPreferredSize(new Dimension(260, 200));
        //tablaInferior.setFont(new Font("Arial", Font.BOLD, 20));
        panelListaInf.add(tablaInferiorScroll);

        this.add(panelGrafica, BorderLayout.CENTER);
        this.add(panelDerecha, BorderLayout.EAST);
        this.add(panelListaIzq, BorderLayout.WEST);
        this.add(panelListaInf, BorderLayout.SOUTH);

    }

    public static void createGUI(JFrame window, String name, int id) throws ParseException{
        ViewRepresentacion panel = new ViewRepresentacion(name);
        CtrlRepresentacion ctr = new CtrlRepresentacion(panel, id);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARCURVA));
        window.setVisible(true);
        window.pack();
        window.setSize(1000, 600);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setController(CtrlRepresentacion ctr) {
        bCorregir.addActionListener(ctr);
    }

    public void mostrarCurva(Punto[] puntos){

    }

    public void mostrarIV(){

    }

    public void mostrarPV(){

    }

}


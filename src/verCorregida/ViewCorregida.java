package verCorregida;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ViewCorregida extends Panel {

    //Componentes
    JFrame ventana = new JFrame("Medida Corregida");

    // RadioButton de los IV/PV
    JRadioButton IV = new JRadioButton("I-V", false);
    JRadioButton PV = new JRadioButton("P-V", false);
    ButtonGroup group = new ButtonGroup();
    // DATOS SIN IMPORTANCIA
    String[] izquierdanombres = { "V", "I", "P" };
    Object[][] datosIzquierda = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 } };


    // DECLARACION DE LAS TABLAS
    private JList listaIzq = new JList();
    private JScrollPane listaIzqScroll = new JScrollPane(listaIzq, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    static final String MOSTRARIV = "MOSTRAR CURVA IV";
    static final String MOSTRARPV ="MOSTRAR CURVA PV";
    static final String MOSTRARCORREGIDAS = "MOSTRAR CURVAS CORREGIDAS";

    public ViewCorregida() {
        this.setLayout(new BorderLayout());
        group.add(IV);
        group.add(PV);

        // Botones derecha
        JPanel panelDerecha = new JPanel();
        IV.setActionCommand(MOSTRARIV);
        PV.setActionCommand(MOSTRARPV);
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.PAGE_AXIS));
        panelDerecha.add(IV);
        panelDerecha.add(PV);
        panelDerecha.setMaximumSize(new Dimension (500,100));
        panelDerecha.setMinimumSize(new Dimension (500,100));

        // TablaIzquierda
        JPanel panelTablaIzquierda = new JPanel();
        panelTablaIzquierda.setLayout(new BoxLayout(panelTablaIzquierda,1));
        listaIzq.setPreferredSize(new Dimension (260,400));
        listaIzq.setFont(new Font("Arial", Font.BOLD,20));
        panelTablaIzquierda.add(listaIzqScroll);


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
        panelGrafica.setMaximumSize(new Dimension(300,100));
        panelGrafica.setMinimumSize(new Dimension(300,100));

        // TablaInferior


        this.add(panelGrafica, BorderLayout.CENTER);
        this.add(panelDerecha, BorderLayout.EAST);
        this.add(panelTablaIzquierda, BorderLayout.WEST);



    }

    public static void createGUI(JFrame window) {
        ViewCorregida panel = new ViewCorregida();
        CtrlCorregida ctr = new CtrlCorregida(panel);
        panel.setController(ctr);
        window.setContentPane(panel);
        ctr.actionPerformed(new ActionEvent(panel, 1, MOSTRARCORREGIDAS));
        window.setVisible(true);
        window.pack();
        window.setSize(800,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setController(CtrlCorregida ctr) {
        // TODO Auto-generated method stub
        IV.addActionListener(ctr);
        PV.addActionListener(ctr);

    }

}

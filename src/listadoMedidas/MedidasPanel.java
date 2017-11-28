package listadoMedidas;

import java.awt.*;
import javax.swing.*;

public class MedidasPanel extends Panel{
    private JTextArea ListaMedidas = new JTextArea(20, 20);
    private JScrollPane ListaMedScroll = new JScrollPane(ListaMedidas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton ListMod = new JButton("Listado Modulos");
    private JButton AbrirCurv = new JButton("Abrir Curva");

    public MedidasPanel(){
        this.setLayout(new BoxLayout(this,1));
        JPanel Lista = new JPanel();
        ListaMedidas.setEditable(false);
        Lista.setLayout(new BoxLayout(Lista,1));
        Lista.add(ListaMedScroll, BorderLayout.NORTH);
        Lista.add(ListaMedidas, BorderLayout.SOUTH);

        JPanel Botones = new JPanel();
        Botones.setLayout(new GridLayout(1,2));
        Botones.add(ListMod);
        Botones.add(AbrirCurv);

        this.add(Lista);
        this.add(Botones);
    }
    public void setController(MedidasController ctr){
        String s = ListaMedidas.getSelectedText();
    }
}

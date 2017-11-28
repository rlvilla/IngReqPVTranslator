package listadoMedidas;

import java.awt.Panel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MedidasPanel extends Panel{
    private JTextArea ListaMedidas = new JTextArea(20, 20);
    private JScrollPane OrigniScroll = new JScrollPane(ListaMedidas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    public MedidasPanel(){
        this.setLayout(new BoxLayout(this,2));
        this.add(ListaMedidas);

        //JPanel Buttons = new
    }
    public void setController(MedidasController ctr){

    }
}

package chat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lista_Grupos extends JPanel {
    
    JLabel prueba;
    
    public Lista_Grupos(){
        inicio();   
    };
    
     private void inicio(){
        this.setLayout(null);
        
        prueba = new JLabel("Prueba");
        prueba.setText("Si sirve 4 xd");
        prueba.setBounds(50, 50, 100, 100);
        this.add(prueba);
     }
}

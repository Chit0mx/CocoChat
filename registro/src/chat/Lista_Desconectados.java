package chat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lista_Desconectados extends JPanel {
    
    JLabel prueba;
    
    public Lista_Desconectados(){
        inicio();   
    };
    
     private void inicio(){
        this.setLayout(null);
        
        prueba = new JLabel("Prueba");
        prueba.setText("Si sirve 2 xd");
        prueba.setBounds(50, 50, 100, 100);
        this.add(prueba);
     }
}

package chat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lista_Amigos extends JPanel {
    
    JLabel prueba;
    
    public Lista_Amigos(){
        inicio();   
    };
    
     private void inicio(){
         
        this.setLayout(null);
        
        prueba = new JLabel("Prueba");
        prueba.setText("Si sirve 3 xd");
        prueba.setBounds(50, 50, 100, 100);
        this.add(prueba);
     }
}


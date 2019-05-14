package chat;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alan Franco
 */
public class Lista_Conectados extends JPanel {
    
    JLabel prueba;
    
    public Lista_Conectados(){
        inicio();   
    };
    
     private void inicio(){
        this.setLayout(null);
        
        prueba = new JLabel("Prueba");
        prueba.setText("Si sirve 1 xd");
        prueba.setBounds(50, 50, 100, 100);
        this.add(prueba);
        
        
     }
}


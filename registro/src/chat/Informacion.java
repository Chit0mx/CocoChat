package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Informacion extends JPanel {
    
    JLabel ip;
    JTextField ip_usuario;
    JButton salir;
    
    public Informacion(){
        inicio();   
    };

     private void inicio(){
        this.setLayout(null);
        /*
        ip = new JLabel("IP");
        ip.setText("Tu IP:");
        ip.setBounds(40, 50, 100, 20);
        this.add(ip);
        
        ip_usuario = new JTextField();
        ip_usuario.setText("x.x.x.x");
        ip_usuario.setBounds(80, 50, 100, 20);
        this.add(ip_usuario);
        */
        salir = new JButton();
        salir.setText("Cerrar aplicación");
        salir.setBounds(80, 80, 150, 40);
        this.add(salir);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                System.exit(0);
            }
        });
     }
}
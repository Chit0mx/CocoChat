package chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana_Chat extends JFrame {
    
    private JTextField msg;
    private JButton enviar;
    private JTextArea recibido;
    
    JLabel prueba;
    
    // Instancia de la logica del chat
    chatLogic chat = new chatLogic();
    // Holder del mensaje escrito
    String mensaje;
    
    void actuaizarChat(){
        String TodosMSG="";
        for(mensaje iterador:chat.getMensajes()){
            TodosMSG+=iterador.getNombre()+": " + iterador.getMensaje()+ "\n" ;
        }
       recibido.setText(TodosMSG);
    }
    public Ventana_Chat(){
        inicio();   
    };
    
     private void inicio(){
        setTitle("Chat");
        setSize(750, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); 
        setLayout(null);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); 
        //campo de texto de msg
        msg=new JTextField();
        //setBounds(x,y,ancho,alto)
        this.msg.setBounds(50, 600, 500, 50);
        this.add(msg);
        //boton Enviar msg
        enviar = new JButton("Enviar");
        enviar.setBounds(600, 600, 100, 50);
        this.add(enviar);
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar mensaje escrito en una variable
                   mensaje = msg.getText();
                   chat.addMensaje(new mensaje(1,"Chit0mx", mensaje));
                   msg.setText("");
                   actuaizarChat();
            }
        }
        );
        recibido=new JTextArea();
        //setBounds(x,y,ancho,alto)
        this.recibido.setBounds(50, 50, 650, 500);
        this.add(recibido);
     }
}
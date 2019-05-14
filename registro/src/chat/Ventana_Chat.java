package chat;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana_Chat extends JPanel {
    
    private JTextField msg;
    private JButton enviar;
    private JTextArea recivido;
    
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
       recivido.setText(TodosMSG);
    }
    public Ventana_Chat(){
        inicio();   
    };
    
     private void inicio(){
         
        this.setLayout(null);
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
                   chat.addMensaje(new mensaje(1,"El Chitomcs", mensaje));
                   msg.setText("");
                   actuaizarChat();
            }
        }
        );
        recivido=new JTextArea();
        JScrollPane scroll =new JScrollPane(recivido, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //setBounds(x,y,ancho,alto)
        this.recivido.setBounds(50, 50, 650, 500);
        //recivido.setEnabled(false);
        this.add(recivido);
     }
}
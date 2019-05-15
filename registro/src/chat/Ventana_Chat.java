package chat;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana_Chat extends JFrame {
    
    private JTextField textInput;
    private JButton enviar;
    private JTextArea cajaMensajes;
    
    // Instancia de la logica del chat
    chatLogic chat = new chatLogic();
    
    void actuaizarChat(){
        String TodosMSG="";
        for(mensaje iterador:chat.getMensajes()){
            TodosMSG+=iterador.getNombre()+": " + iterador.getMensaje()+ "\n" ;
        }
       cajaMensajes.setText(TodosMSG);
    }
    public Ventana_Chat(){
        inicio();   
    };
    
     private void inicio(){
        createFrame();
     }
    
    private void createFrame() {
        //Build JFrame
        setLayout(null);
        setSize(750,750);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        Container contentPane = getContentPane();
        // Crear el scroll del text area antes que el text area
        JScrollPane scrollPane2 = new JScrollPane(textArea()/*Crear textarea*/);
        scrollPane2.setBounds(25, 25, 690, 660);
        
        contentPane.add(scrollPane2); // Añadir textarea al canvas
        contentPane.add(buttonAddText());
        contentPane.add(textInput());

        //Set Frame Visible
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    private JTextArea textArea(){
        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setLineWrap(true); // Text return to line, so no horizontal scrollbar
        output.setForeground(Color.BLACK);
        output.setBackground(Color.WHITE);
        cajaMensajes = output;

        return cajaMensajes;
    }
    
    private JTextField textInput() {
        JTextField input = new JTextField();
        input.setBounds(25, 690, 590, 27);
        textInput = input;
        
        return textInput;
    }
    
    private JButton buttonAddText() {
        JButton testbutton = new JButton("Enviar");
        testbutton.setBounds(615, 690, 100, 25);

        testbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar mensaje escrito en una variable
                   String mensaje = textInput.getText();
                   chat.addMensaje(new mensaje(1,"Chit0mx", mensaje));
                   textInput.setText("");
                   actuaizarChat();
            }
        });

        return testbutton;
    }
}
package chat;

import cliente.ChatClient;
import cliente.MessageListener;
import cliente.UserStatusListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import usuario.Usuario;

public class Ventana_Chat extends JFrame {
    
    private JTextField textInput;
    private JButton enviar;
    private JTextArea cajaMensajes;
    private ChatClient client;
    
    public JTextField getTextInput() {
        return textInput;
    }

    public ChatClient getClient() {
        return client;
    }
    private String activeChat;

    public String getActiveChat() {
        return activeChat;
    }

    public void setActiveChat(String activeChat) {
        this.activeChat = activeChat;
    }
    
    // Instancia de la logica del chat
    chatLogic chat = new chatLogic();
    private final Usuario usuario;
    
    void actuaizarChat(){
        String TodosMSG="";
        for(mensaje iterador:chat.getMensajes()){
            TodosMSG+=iterador.getNombre()+": " + iterador.getMensaje()+ "\n" ;
        }
       cajaMensajes.setText(TodosMSG);
    }
    public Ventana_Chat(Usuario usuario){
        this.usuario = usuario;
        inicio();
        crearCliente();
    };
    
     private void inicio(){
        createFrame();
     }
    
    private void createFrame() {
        // Crear el cliente del server
        
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
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    client.logout();
                } catch (IOException ex) {
                    Logger.getLogger(Ventana_Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
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
                   System.out.println("El mensajes es para " + activeChat);
                   String mensaje = textInput.getText();
                   client.msg(activeChat, mensaje);
                   CrearHistorial CH = new CrearHistorial(usuario, mensaje, activeChat);
                   CH.subirMensaje();
                   mostrarMensaje(usuario.getIdUsuario(),usuario.getNombre(), mensaje);
            }
        });

        return testbutton;
    }
    
    private void mostrarMensaje (int id, String nombre, String mensaje) {
        chat.addMensaje(new mensaje(id, nombre, mensaje));
        textInput.setText("");
        actuaizarChat();
    }

    private void crearCliente() {
        client = new ChatClient("localhost", 8818);
        client.addOutput (chat); // Esto lo puse pero aun no se si se va a usar xd
        // Esto es lo que se va a ejecutar cuando en el cliente socket le que se contecto o desconecto alguien
        client.addUserStatusListener(new UserStatusListener() {
            @Override
            public void online(String login) {
                mostrarMensaje(0, "System" , login + " is now online");
                System.out.println("ONLINE: " + login);
            }

            @Override
            public void offline(String login) {
                System.out.println("OFFLINE: " + login);
            }
        });
        // Esto es lo que se va a ejecutar cuando en el cliente socket le llegue un mensaje
        client.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(String fromLogin, String msgBody) {
                mostrarMensaje(0, fromLogin, msgBody);
                System.out.println("You got a message from " + fromLogin + " ====> " + msgBody);
            }
        });

        if(!client.connect()) {
            System.err.println("Connection failed!");
        } else {
            System.out.print("Connection OK!\n");
            client.login(usuario.getNombre(), "sin_password");
        }
    }
}
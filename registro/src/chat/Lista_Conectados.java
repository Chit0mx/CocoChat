package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import usuario.Usuario;

/**
 *
 * @author Alan Franco XD
 */
public class Lista_Conectados extends JPanel {
    int y = 0;
    private final Ventana_Chat vChat;
    private final Usuario usuario;
    
    public Lista_Conectados(Usuario usuario, Ventana_Chat vChat){
        this.usuario = usuario;
        this.vChat = vChat;
        inicio();   
    };
    
    
    private void inicio(){
        this.setLayout(null); 
        ArrayList<String> conectados = this.vChat.getClient().getConectados();
        System.out.println("Los conectados desde la lista son: " + conectados);
        for (String conectado : conectados) {
                JPopupMenu menu;
                JButton boton;
                JLabel grupo = new JLabel("Grupo");
                
                grupo.setText(conectado);
                grupo.setBounds(10, y += 20, 50, 20);
                this.add(grupo);
                
                boton = new JButton("Opciones");
                boton.setBounds(60, y, 100, 20);
                
                menu = new JPopupMenu();
                menu.add(new MenuDesplegable("Enviar mensaje", (new Usuario(conectado, 0)), this.usuario , vChat));
                menu.add(new MenuDesplegable("Agregar amigo", (new Usuario(conectado, 0)), this.usuario, vChat));
                menu.add(new MenuDesplegable("Agregar a grupo", (new Usuario(conectado, 0)), this.usuario, vChat));
                menu.add(new MenuDesplegable("Cambiar apodo", (new Usuario(conectado, 0)), this.usuario, vChat));
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menu.show(boton, 0, 0);
                    }
                });
                this.add(boton);
        }
    }

}


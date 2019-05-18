package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import usuario.Usuario;

public class Lista_Amigos extends JPanel {
    private final Usuario usr;
    JLabel prueba;
    JButton boton;
    private ArrayList<Usuario> amigos;
    Ventana_Chat vChat;
    private final int y = 0;

    Lista_Amigos(Usuario usuarioActual, Ventana_Chat vChat) {
        this.usr = usuarioActual;
        this.vChat = vChat;
        inicio();  
    }
    
    
     private void inicio(){
        this.setLayout(null);
        this.amigos = this.usr.getAmigos();
        int y = 0;
        for(int i = 0; i < amigos.size(); i++){
            JPopupMenu menu;
            prueba = new JLabel("Prueba");
            Usuario amigo = amigos.get(i);
            System.out.println("Amigo: " + amigo.getNombre());
            prueba.setText(amigo.getNombre());
            prueba.setBounds(10, y += 20, 50, 20);
            boton = new JButton("Opciones");
            boton.setBounds(60, y, 100, 20);
            menu = new JPopupMenu();
            menu.add(new MenuDesplegable("Enviar mensaje", amigo, this.usr, vChat));
            menu.add(new MenuDesplegable("Agregar amigo", amigo, this.usr, vChat));
            menu.add(new MenuDesplegable("Agregar a grupo", amigo, this.usr, vChat));
            menu.add(new MenuDesplegable("Cambiar apodo", amigo, this.usr, vChat));
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.show(boton, 0, 0);
                }
            });
            this.add(prueba);
            this.add(boton);
        }
        
     }
}


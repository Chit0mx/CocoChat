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
    JPopupMenu menu;
    private ArrayList<Usuario> amigos;
    private final int y = 0;
    public Lista_Amigos(Usuario usuario){
        this.usr = usuario;
        inicio();   
    };
    
    
     private void inicio(){
        this.setLayout(null);
        this.amigos = this.usr.getAmigos();
        int y = 0;
        for(int i = 0; i < amigos.size(); i++){
            prueba = new JLabel("Prueba");
            Usuario amigo = amigos.get(i);
            prueba.setText(amigo.getNombre());
            prueba.setBounds(10, y += 20, 50, 20);
            boton = new JButton("Opciones");
            boton.setBounds(60, y, 100, 20);
            menu = new JPopupMenu();
            menu.add(new MenuDesplegable("Enviar mensaje"));
            menu.add(new MenuDesplegable("Agregar amigo"));
            menu.add(new MenuDesplegable("Agregar a grupo"));
            menu.add(new MenuDesplegable("Cambiar apodo"));
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


package chat;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import usuario.Usuario;

public class Lista_Amigos extends JPanel {
    private final Usuario usr;
    JLabel prueba;
    JButton boton;
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
            this.add(prueba);
            this.add(boton);
        }
        
     }
}


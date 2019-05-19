package chat;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import usuario.Usuario;

public class listas extends JFrame{
    
    private final Usuario usuarioActual;
    Lista_Desconectados desconectados;
    Lista_Amigos amigos;
    Lista_Grupos grupos;
    Lista_Conectados conectados;
    Informacion info = new Informacion();
    Ventana_Chat vChat;
    
    //Componente para poder crear las pestañas
    JTabbedPane tabbedPane = new JTabbedPane();
    
    
   /* public listas(Usuario usuario){
        this.usuarioActual = usuario;
        amigos = new Lista_Amigos(usuarioActual);
        conectados = new Lista_Conectados(usuarioActual);
        inicio();
    };*/

    listas(Usuario usuarioActual, Ventana_Chat vChat) {
        this.vChat = vChat;
        this.usuarioActual = usuarioActual;
        amigos = new Lista_Amigos(usuarioActual, vChat);
        desconectados = new Lista_Desconectados(usuarioActual, vChat);
        conectados = new Lista_Conectados(usuarioActual);
        grupos = new Lista_Grupos(usuarioActual, vChat);
        inicio();
    }
    
    private void inicio(){
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,600);
        this.setTitle("CocoChat");
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        setResizable(false);
   
        //Añadir los paneles
        tabbedPane.add(conectados,"Conectados");
        tabbedPane.add(desconectados,"Desconectados");
        tabbedPane.add(amigos,"Amigos");
        tabbedPane.add(grupos,"Grupos");
        tabbedPane.add(info,"Informacion");
        add(tabbedPane);
    }
}
package chat;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JTabbedPane;
import usuario.Usuario;

public class listas extends JFrame{
    
    private final Usuario usuarioActual;
    Lista_Desconectados desconectados = new Lista_Desconectados();
    Lista_Amigos amigos;
    Lista_Grupos grupos = new Lista_Grupos();
    Lista_Conectados conectados;
    Informacion info = new Informacion();
    
    //Componente para poder crear las pestañas
    JTabbedPane tabbedPane = new JTabbedPane();
    
    
    public listas(Usuario usuario){
        this.usuarioActual = usuario;
        amigos = new Lista_Amigos(usuarioActual);
        conectados = new Lista_Conectados(usuarioActual);
        inicio();
    };
    
    private void inicio(){
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
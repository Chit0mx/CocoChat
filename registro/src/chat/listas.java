package chat;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JTabbedPane;

public class listas extends JFrame{
    
    Lista_Conectados conectados = new Lista_Conectados();
    Lista_Desconectados desconectados = new Lista_Desconectados();
    Lista_Amigos amigos = new Lista_Amigos();
    Lista_Grupos grupos = new Lista_Grupos();
    Informacion info = new Informacion();
    
    //Componente para poder crear las pestañas
    JTabbedPane tabbedPane = new JTabbedPane();
    
    public listas(){
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
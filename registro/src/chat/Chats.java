package chat;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class Chats extends JFrame{
    
    Lista_Conectados conectados = new Lista_Conectados();
    Lista_Desconectados desconectados = new Lista_Desconectados();
    Lista_Amigos amigos = new Lista_Amigos();
    Lista_Grupos grupos = new Lista_Grupos();
    Ventana_Chat chat = new Ventana_Chat();
    Informacion info = new Informacion();
    
    //Componente para poder crear las pestañas
    JTabbedPane tabbedPane = new JTabbedPane();
    
    //Componente para separar la ventana en 2 partes
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tabbedPane),new JScrollPane(chat));
    
    public Chats(){
        inicio();
    };
    
    private void inicio(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280,720);
        this.setTitle("CocoChat");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
         setResizable(false);
       
        //Añadir los paneles
        tabbedPane.add(conectados,"Conectados");
        tabbedPane.add(desconectados,"Desconectados");
        tabbedPane.add(amigos,"Amigos");
        tabbedPane.add(grupos,"Grupos");
        tabbedPane.add(info,"Informacion");
        add(splitPane); 
    }
    
}
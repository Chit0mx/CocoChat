package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import usuario.Usuario;

public class Lista_Desconectados extends JPanel {
    
    JLabel grupo;
    JButton boton;
    JLabel prueba;
    private Usuario usr;
    private Ventana_Chat vChat;
    
    /*public Lista_Desconectados(){
        inicio();   
    };*/
    
    Lista_Desconectados(Usuario usuarioActual, Ventana_Chat vChat) {
        this.usr = usuarioActual;
        this.vChat = vChat;
        inicio();  
    }
    
     private void inicio(){
        this.setLayout(null);
        //conexion
        conectar cc=new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = "SELECT * FROM usuario";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int y = 0;
            
            while (rs.next()) {
                JPopupMenu menu;
                String nombre = rs.getString("nombre");
                int idUsuario = rs.getInt("idUsuario");
                grupo = new JLabel("Grupo");
                grupo.setText(nombre);
                grupo.setBounds(10, y += 20, 50, 20);
                this.add(grupo);
                
                boton = new JButton("Opciones");
                boton.setBounds(60, y, 100, 20);
                
                menu = new JPopupMenu();
                menu.add(new MenuDesplegable("Enviar mensaje", (new Usuario(nombre, idUsuario)), this.usr , vChat));
                menu.add(new MenuDesplegable("Agregar amigo", (new Usuario(nombre, idUsuario)), this.usr, vChat));
                menu.add(new MenuDesplegable("Agregar a grupo", (new Usuario(nombre, idUsuario)), this.usr, vChat));
                menu.add(new MenuDesplegable("Cambiar apodo", (new Usuario(nombre, idUsuario)), this.usr, vChat));
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menu.show(boton, 0, 0);
                    }
                });
                this.add(boton);
            }
            st.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
     }
}

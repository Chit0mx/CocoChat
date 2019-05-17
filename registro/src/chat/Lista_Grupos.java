package chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lista_Grupos extends JPanel {
    

    JLabel grupo;
    JButton boton;

    
    public Lista_Grupos(){
        inicio();   
    };
    
     private void inicio(){
        this.setLayout(null);
        
        //conexion

        conectar cc = new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = "SELECT * FROM grupos";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int y = 0;
            while (rs.next())
            {
                String nombre = rs.getString("nombre");
                grupo = new JLabel("Grupo");
                grupo.setText(nombre);
                grupo.setBounds(10, y += 20, 50, 20);
                boton = new JButton("Opciones");
                boton.setBounds(60, y, 100, 20);
                this.add(grupo);
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

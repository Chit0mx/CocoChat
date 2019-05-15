package chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lista_Grupos extends JPanel {
    
    JLabel prueba;
    JLabel grupo;
    
    public Lista_Grupos(){
        inicio();   
    };
    
     private void inicio(){
        this.setLayout(null);
        
        //conexion
        conectar cc=new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = "SELECT * FROM grupos";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int y = 50;
            while (rs.next())
            {
                String nombre = rs.getString("nombre");
                grupo = new JLabel("Grupo");
                grupo.setText(nombre);
                grupo.setBounds(50, y += 10, 100, 100);
                this.add(grupo);
            }
            st.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        prueba = new JLabel("Prueba");
        prueba.setText("Si sirve 4 xd");
        prueba.setBounds(50, 50, 100, 100);
        this.add(prueba);
     }
}

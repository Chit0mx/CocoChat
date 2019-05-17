package chat;

import java.sql.*;
import javax.swing.*;

public class conectar {
    Connection conect = null;
    public Connection conexion()
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conect = DriverManager.getConnection("jdbc:mysql://localhost/cocochat","root","");
                //System.out.println("¡Estas conectado!");
                //JOptionPane.showMessageDialog(null,"Conexion a la base de datos establecida");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error de conexion");
                JOptionPane.showMessageDialog(null,"Error de conexion "+e);
                System.exit(0);
            }
            return conect;
        }     
}
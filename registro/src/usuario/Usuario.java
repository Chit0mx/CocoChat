/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import chat.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author esaue
 */
public class Usuario {
    private final String nombre;
    private final int idUsuario;
    private ArrayList<Usuario> amigos;

    public Usuario(String nombre, int idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        amigos = new ArrayList();
        traerAmigos();
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }
    
    public void traerAmigos(){
        //conexion
        conectar cc=new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = "SELECT * FROM amigos INNER JOIN usuario on amigos.idAmigo = usuario.idUsuario WHERE amigos.idUsuario =" + idUsuario;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int y = 0;
            while (rs.next())
            {
                int id = rs.getInt("idAmigo");
                String nAmigo = rs.getString("nombre");
                Usuario u = new Usuario(nAmigo, id);
                amigos.add(u);
            }
            st.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            System.out.println("No se pudo traer a los amigos con exito");
        }
    }
}

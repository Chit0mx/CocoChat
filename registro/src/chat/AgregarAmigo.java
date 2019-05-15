/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Efraín
 */
public class AgregarAmigo {
    //Solicitar amistad
    private boolean SolicitudAceptada;
    private String Apodo;
    public AgregarAmigo() {
        this.SolicitudAceptada = false;
    }
    
    public void setApodo(String a) {
        this.Apodo = a;
    }
    
    public void aceptarSolicitud() {
        this.SolicitudAceptada = true;
    }
    
    public void hacerAmistad(/*Usuario usuario1, Usuario usuario2*/) {
        //conexion
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        //Eniviar a la base de datos la relacion de amistad
        int idU = 1;
        int idA = 2;
        String apodo = "amiguis";
        String sql="INSERT INTO amigos (idUsuario, idAmigo, apodo) VALUES (?, ?, ?)";
         try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idU);
            pst.setInt(2,idA);
            pst.setString(3,apodo);
            pst.executeUpdate();
            System.out.println("Se creo amistad");
   
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
    }
    
}



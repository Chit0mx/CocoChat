package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuario.Usuario;

public class CrearHistorial {
    private final Usuario usuario;
    private final String mensaje;
    private final String amigo;
    private int idAmistad1, idAmistad2;
    
    public CrearHistorial(Usuario usr, String msj, String amg) {
        this.usuario = usr;
        this.mensaje = msj;
        this.amigo = amg;
    }
    
    public int traerIdAmigo(String amg) {
         //conexion
        int idAmigo;
         
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = " SELECT * FROM usuario WHERE nombre ='"+amg+"'";
        
      
 
        try {
            Statement st;
            ResultSet rs ;
            st = cn.createStatement();
            rs = st.executeQuery(query);
            if(rs.next()){
                idAmigo = rs.getInt("idUsuario");
                return idAmigo;
            }
           
        } catch (SQLException ex) {
            System.out.println("No sirvio traer idamigo");
            Logger.getLogger(CrearHistorial.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    public int traerIdGrupo(String amg) {
         //conexion
        int idGrupo;
         
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = " SELECT * FROM grupo WHERE nombre ='"+amg+"'";
        
       
 
        try {
            Statement st;
            ResultSet rs ;
            st = cn.createStatement();
            rs = st.executeQuery(query);
            if(rs.next()){
                idGrupo = rs.getInt("idGrupo");
                return idGrupo;
            }
        } catch (SQLException ex) {
            System.out.println("No sirvio traerIdgrupo");
            Logger.getLogger(CrearHistorial.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    public void traerAmistad(int idAmigo) {
         //conexion
         
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = "SELECT * FROM usuario INNER JOIN amigos ON usuario.idUsuario = amigos.idUsuario WHERE usuario.idUsuario ='"+this.usuario.getIdUsuario()+"'and idAmigo ='"+idAmigo+"'";
 
        try {
            Statement st;
            ResultSet rs ;
            st = cn.createStatement();
            rs = st.executeQuery(query);
            if(rs.next()){
                this.idAmistad1 = rs.getInt("idAmistad");
                //return idAmistad1;
            }
        } catch (SQLException ex) {
            System.out.println("No sirvio traerAmistad");
            Logger.getLogger(CrearHistorial.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //ID AMISTAD
        String query2 = "SELECT * FROM usuario INNER JOIN amigos ON usuario.idUsuario = amigos.idUsuario WHERE usuario.idUsuario ='"+idAmigo+"'and idAmigo ='"+this.usuario.getIdUsuario()+"'";
 
        try {
            Statement st2;
            ResultSet rs2;
            st2 = cn.createStatement();
            rs2 = st2.executeQuery(query2);
            if(rs2.next()){
                this.idAmistad2 = rs2.getInt("idAmistad");
                //return idAmistad2;
            }
        } catch (SQLException ex) {
            System.out.println("No sirvio traerAmistad");
            Logger.getLogger(CrearHistorial.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //return 0;
    }
    
     public void subirMensaje() {
         //conexion
        
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        
        String  msg = this.usuario.getNombre() + " a " + this.amigo + " : " + this.mensaje;
        int id;
        String query;
       
        if (this.amigo.charAt(0) == '#') {
            String Grupo  = this.amigo.substring(1, this.amigo.length());   
            id = traerIdGrupo(Grupo);
            query = "INSERT INTO mensajesgrupos ( idGrupo, mensaje, timestamp) VALUES ( ?,?,? )";
        } else {
            int idAmigo = traerIdAmigo(this.amigo);
            traerAmistad(idAmigo);
            query = "INSERT INTO mensajesamigos ( idAmistad, mensaje, timestamp) VALUES ( ?,?,? )";
        }
        
        PreparedStatement pst;
 
         try {
             System.out.println("idAmistad1: " + idAmistad1 + " idAmistad2: " + idAmistad2);
            pst = cn.prepareStatement(query);
            pst.setInt(1,idAmistad1);
            pst.setString(2,msg);
            //el timestamp siempre darle un valor, sino truena
            pst.setLong(3,1);
            pst.executeUpdate();
            // PARA AGREGAR EL HISTORIAL DEL SEGUNDO AMIGO
            pst = cn.prepareStatement(query);
            pst.setInt(1,idAmistad2);
            pst.setString(2,msg);
            //el timestamp siempre darle un valor, sino truena
            pst.setLong(3,1);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No sirvio subirMensaje");
            Logger.getLogger(registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

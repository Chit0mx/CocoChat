package chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class historial {
    
    private final int idUsuario;
    ArrayList<msgAmistad> listMsg= new ArrayList();
    ArrayList<msgGrupo> listMsgG= new ArrayList();
    
    public historial (int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public boolean loadHistorial(){
    try {
            conectar cc=new conectar();
            Connection cn = cc.conexion();
            
            String sql = "SELECT * FROM usuario INNER JOIN amigos ON usuario.idUsuario = amigos.idUsuario WHERE usuario.idUsuario ='"+idUsuario+"'";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            int idAmistad = rs.getInt("idAmistad");
            
            String sql1 ="SELECT * FROM mensajesamigos WHERE idAmistad ='"+idAmistad+"'";
            st = cn.createStatement();
            rs = st.executeQuery(sql1);
            while(rs.next()){
                
                msgAmistad msgA =new msgAmistad(idAmistad,rs.getString("mensaje"),rs.getLong("timestamp") ,rs.getInt("idMensaje"));
                listMsg.add(msgA);
                }
            }  
                
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Mensajes guardados" + listMsg);
        return false;
    }  
      public boolean loadHistorialG(){
    try {
            conectar cc=new conectar();
            Connection cn = cc.conexion();
            
            String sql = "SELECT * FROM usuariosgrupo INNER JOIN grupos ON usuariosgrupo.idGrupo = grupos.idGrupo WHERE usuariosgrupo.idUsuario ='"+idUsuario+"'";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            int idGrupo = rs.getInt("idGrupo");
            
            String sql1 ="SELECT * FROM mensajesgrupos WHERE idGrupo ='"+idGrupo+"'";
            st = cn.createStatement();
            rs = st.executeQuery(sql1);
            while(rs.next()){
                
                msgGrupo msgG =new msgGrupo(idGrupo,rs.getString("mensaje"),rs.getLong("timestamp") ,rs.getInt("idMensaje"));
                listMsgG.add(msgG);
                }
            }  
                
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Mensajes de grupo guardados" + listMsgG);
        return false;
    }
    
    public class msgAmistad{

        public msgAmistad(int idAmistad, String mensaje, long timestamp, int idMensaje) {
            this.idAmistad = idAmistad;
            this.mensaje = mensaje;
            this.timestamp = timestamp;
            this.idMensaje = idMensaje;
        }
        
        int idAmistad;
        String mensaje;
        long timestamp;
        int idMensaje;
        
        public String toString () {
            return "Mensajes : " + mensaje;
        }
        
        public int getIdMensaje() {
            return idMensaje;
        }

        public void setIdMensaje(int idMensaje) {
            this.idMensaje = idMensaje;
        }

        public int getIdAmistad() {
            return idAmistad;
        }

        public void setIdAmistad(int idAmistad) {
            this.idAmistad = idAmistad;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
       
        
    }
    public class msgGrupo{

        public msgGrupo(int idGrupo, String mensaje, long timestamp, int idMensaje) {
            this.idGrupo = idGrupo;
            this.mensaje = mensaje;
            this.timestamp = timestamp;
            this.idMensaje = idMensaje;
        }
        
        int idGrupo;
        String mensaje;
        long timestamp;
        int idMensaje;
        
        public String toString () {
            return "Mensajes grupo : " + mensaje;
        }
        
        public int getIdMensaje() {
            return idMensaje;
        }

        public void setIdMensaje(int idMensaje) {
            this.idMensaje = idMensaje;
        }

        public int getIdGrupo() {
            return idGrupo;
        }

        public void setIdGrupo(int idGrupo) {
            this.idGrupo = idGrupo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
       
        
    }
}


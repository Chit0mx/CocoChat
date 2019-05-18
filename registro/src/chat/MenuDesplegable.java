/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import usuario.Usuario;

/**
 *
 * @author Alan Franco
 */
public class MenuDesplegable extends AbstractAction{
    private String textoOpcion;
    Usuario destino;
    conectar cc=new conectar();
    Connection cn = cc.conexion();
    Ventana_Chat vChat;
    private Usuario usuarioActual;

	public MenuDesplegable(String textoOpcion) {
		this.textoOpcion = textoOpcion;
		this.putValue(Action.NAME, textoOpcion);
	}

    public MenuDesplegable(String textoOpcion, Usuario destino, Usuario usuarioActual, Ventana_Chat vChat) {
        this.textoOpcion = textoOpcion;
        this.destino = destino;
	this.putValue(Action.NAME, textoOpcion);
        this.vChat = vChat;
        this.usuarioActual = usuarioActual;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
            if (textoOpcion.equalsIgnoreCase("Enviar mensaje")) {
                System.out.println("Chat activo: " + destino.getNombre());
                vChat.setActiveChat(destino.getNombre());
                //vChat.getClient().msg(destino, vChat.getTextInput().getText());
            } else if (textoOpcion.equalsIgnoreCase("Agregar amigo")) {
                System.out.println("Agregar a " + destino.getIdUsuario() + " " + destino.getNombre());
                try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO amigos (idUsuario, idAmigo, apodo) VALUES (?,?,?);");
                    pst.setInt(1,usuarioActual.getIdUsuario());
                    pst.setInt(2,destino.getIdUsuario());
                    pst.setString(3,destino.getNombre());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
                    }   
                } catch (SQLException ex) {
                    Logger.getLogger(registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (textoOpcion.equalsIgnoreCase("Unirse al grupo")) {
                //System.out.println("Unido al grupo: " + destino.getNombre());
                boolean res = vChat.getClient().joinTopic(destino.getNombre());
            } else if (textoOpcion.equalsIgnoreCase("Enviar mensaje al grupo")) {
                System.out.println("Chat de grupo activo: #" + destino.getNombre());
                vChat.setActiveChat("#" + destino.getNombre());
            }
		System.out.println("Pulsado " + textoOpcion);
	}
}

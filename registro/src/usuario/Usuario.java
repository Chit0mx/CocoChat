/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

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
}

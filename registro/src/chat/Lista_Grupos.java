package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import usuario.Usuario;

public class Lista_Grupos extends JPanel {
    

    JLabel grupo;
    JButton boton;
    JButton añadirGrupo;
    JTextField nombreGrupo;
    conectar cc=new conectar();
    Connection cn = cc.conexion();
    private final Usuario usuarioActual;
    private final Ventana_Chat vChat;

    
    public Lista_Grupos(Usuario usuarioActual, Ventana_Chat vChat) {
        this.usuarioActual = usuarioActual;
        this.vChat = vChat;
        inicio();  
    }
    
     private void inicio(){
        this.setLayout(null);
        
        nombreGrupo = new JTextField();
        nombreGrupo.setBounds(160, 500, 100, 20);
        this.add(nombreGrupo);
        
        añadirGrupo = new JButton("Crear grupo");
        añadirGrupo.setBounds(20, 500, 130, 20);
        añadirGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear grupo
                try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO grupos (nombre) VALUES (?);");
                    pst.setString(1, nombreGrupo.getText());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
                    }   
                } catch (SQLException ex) {
                    Logger.getLogger(registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.add(añadirGrupo);
        
        //conexion

        conectar cc = new conectar();
        Connection cn = cc.conexion();
        
        //SQLs
        String query = "SELECT * FROM grupos";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int y = 0;
            while (rs.next()){
                JPopupMenu menu;
                String nombre = rs.getString("nombre");
                grupo = new JLabel("Grupo");
                grupo.setText(nombre);
                grupo.setBounds(10, y += 20, 50, 20);
                boton = new JButton("Opciones");
                boton.setBounds(60, y, 100, 20);
                
                menu = new JPopupMenu();
                menu.add(new MenuDesplegable("Enviar mensaje al grupo", (new Usuario(nombre, 0)), this.usuarioActual , vChat));
                menu.add(new MenuDesplegable("Eliminar Grupo", (new Usuario(nombre, 0)), this.usuarioActual , vChat));
                
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menu.show(boton, 0, 0);
                    }
                });
                
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

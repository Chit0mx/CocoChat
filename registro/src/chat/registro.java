package chat;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class registro extends JFrame{
    private JButton conectar;
    private JButton registrar;
    private JTextField Nombre;
    private JTextField Username;
    private JPasswordField contra;
    private JLabel Nom;
    private JLabel Usr;
    private JLabel Cont;
    
    public registro() {
        inicio();
        limpiar();
    }
    private JFrame getFrame(){
        return this;
    }
    private void limpiar(){
        Nombre.setText("");
        Username.setText("");
        contra.setText("");
    }
    private void meterdatos(){
        //conexion
        conectar cc=new conectar();
        Connection cn =cc.conexion();
        //SQLs
        String sql;
        String username = Username.getText();
        String password = String.valueOf(contra.getPassword());
        String nombre = Nombre.getText();
        sql="INSERT INTO usuario(username,password,nombre) VALUES (?,?,?)";
        //MD5 
        // UPDATE usuario SET password =MD5(password);
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,nombre);
            int n=pst.executeUpdate();
               if(n>0){
                 JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
                    }   
                } catch (SQLException ex) {
                    Logger.getLogger(registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    public boolean nulo(){
       boolean verif = true;
       int cont =0;
       
       if(Nombre.getText().equals("")){
           JOptionPane.showMessageDialog(null,"Falta Nombre"); 
           cont++;
       }
       if(Username.getText().equals("")){
           JOptionPane.showMessageDialog(null,"Falta Usuario");
           cont++;
       }
       if(contra.getText().equals("")){
           JOptionPane.showMessageDialog(null,"Falta Contraseña"); 
           cont++;
       }
       if(cont==3||cont==2||cont==1){
           verif = false;
       }
      return verif;
    }
    private void cambioV(){
         login log = new login(){;
            //Con esto cuando llamemos a dispose de vNueva abrimos la principal
            @Override
            public void dispose(){
                //Hacemos visible la principal
                getFrame().setVisible(true);
                //Cerramos vNueva
                super.dispose();
            }
                };
        //Cerramos la principal
        dispose();
    }

    private void inicio() {
        
        //ventana principal
        setTitle("Registro");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); 
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        //Nombre 
        Nom = new JLabel();
        Nom.setText("NOMBRE");
        Nom.setBounds(100,50,100,80);
        this.add(Nom);
       
        
        Nombre=new JTextField();
        //setBounds(x,y,ancho,alto)
        this.Nombre.setBounds(100, 100, 300, 50);
        this.add(Nombre);
        Nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                Nombre.transferFocus();
            }
        });
        
        //Username
        Usr = new JLabel();
        Usr.setText("Username");
        Usr.setBounds(100,150,100,80);
        this.add(Usr);
       
        
        Username=new JTextField();
        //setBounds(x,y,ancho,alto)
        this.Username.setBounds(100, 200, 300, 50);
        this.add(Username);
        Username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                Username.transferFocus();
            }
        });
        
        //Contraseña
        Cont = new JLabel();
        Cont.setText("Contraseña");
        Cont.setBounds(100,250,100,80);
        this.add(Cont);
       
        
        contra=new JPasswordField();
        //setBounds(x,y,ancho,alto)
        this.contra.setBounds(100, 300, 300, 50);
        this.add(contra);
        contra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                contra.transferFocus();
            }
        });
        
        //boton Registrar
        conectar = new JButton("Registrarse");
        //setBounds(x,y,ancho,alto)
        conectar.setBounds(185, 400, 120, 50);
        this.add(conectar);
        conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nulo()==false){
                    JOptionPane.showMessageDialog(null,"Rellene los campos en blanco");  
                }else{
                    meterdatos();
                    cambioV();
                }
            }
        });
    }
}
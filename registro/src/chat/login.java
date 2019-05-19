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
import usuario.Usuario;

public class login extends JFrame {

    private JButton conectar;
    private JTextField Username;
    private JPasswordField contra;
    private JLabel Usr;
    private JLabel Cont;
    private Usuario usuarioActual;
    int intentos=0;
    
    conectar cc=new conectar();
    Connection cn = cc.conexion();
    registro reg = new registro();

    public login() {
        inicio();
        //Username.setText("H3lltronik");
        //contra.setText("123");
        //acceder();
    }

    private JFrame getFrame() {
        return this;
    }

    public void acceder() {

        int resultado = 0;

        try {
            String usuario = Username.getText();
            String pass = String.valueOf(contra.getPassword());

            String sql = "SELECT * FROM usuario WHERE username='" + usuario + "'and password='" + pass + "'";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                resultado=1;
                if(resultado==1){
                    // Crear la instancia de usuario con los datos del login
                    int idUsuario = rs.getInt("idUsuario"); // Obtener la idUsuario desde el query de arriba
                    historial hMsg = new historial(idUsuario);
                    hMsg.loadHistorial();
                    hMsg.loadHistorialG();
                    this.usuarioActual = new Usuario(usuario, idUsuario);
                    Ventana_Chat vChat = new Ventana_Chat(this.usuarioActual);
                    vChat.setVisible(true);
                    
                    listas ini = new listas(this.usuarioActual, vChat);
                    ini.setVisible(true);
                    JOptionPane.showMessageDialog(null,"Inicio de sesión exitoso");
                    this.dispose();
                }
            } else {
                resultado = 0;
                if (intentos == 2 && resultado == 0) {
                    JOptionPane.showMessageDialog(null, "Intenta creando una cuenta nueva");
                    this.dispose();
                    reg.setVisible(true);
                } else if (intentos != 2 && resultado == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontro registro del usuario o las credenciales son incorrectas");
                    intentos++;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicio() {
        //ventana principal
        setTitle("Inicio de sesión");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Username
        Usr = new JLabel();
        Usr.setText("Username");
        Usr.setBounds(100, 50, 100, 80);
        this.add(Usr);

        Username = new JTextField();
        //setBounds(x,y,ancho,alto)
        this.Username.setBounds(100, 100, 300, 50);
        this.add(Username);
        Username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Username.getText();
            }
        });

        //Contraseña
        Cont = new JLabel();
        Cont.setText("Contraseña");
        Cont.setBounds(100, 150, 100, 80);
        this.add(Cont);

        contra = new JPasswordField();
        //setBounds(x,y,ancho,alto)
        this.contra.setBounds(100, 200, 300, 50);
        this.add(contra);
        contra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contra.getPassword();
            }
        });

        //boton Registrar
        conectar = new JButton("Registrarse");
        //setBounds(x,y,ancho,alto)
        conectar.setBounds(100, 300, 120, 50);
        this.add(conectar);
        conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ventana de registro
                registro reg = new registro() {
                    ;
            //Con esto cuando llamemos a dispose de vNueva abrimos la principal
            @Override
                    public void dispose() {
                        //Hacemos visible la principal
                        getFrame().setVisible(true);
                        //Cerramos vNueva
                        super.dispose();
                    }
                };
                //Hacemos visible registro
                reg.setVisible(true);
                //Cerramos la principal
                dispose();
            }
        });

        //boton Iniciar sesión 
        conectar = new JButton("Iniciar sesión");
        conectar.setBounds(280, 300, 120, 50);
        this.add(conectar);
        conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceder();
            }
        }
        );
    }
    
    public Usuario getUsuarioActual(){
        return this.usuarioActual;
    }
}

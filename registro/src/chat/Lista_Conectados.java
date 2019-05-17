package chat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import usuario.Usuario;

/**
 *
 * @author Alan Franco
 */
public class Lista_Conectados extends JPanel {
    JLabel usuario;
    int y = 0;
    
    public Lista_Conectados(Usuario usuario){
        inicio();   
    };
    
    
    private void inicio(){
        this.setLayout(null); 
        usuario = new JLabel("Usuario");
        usuario.setText("usuario");
        usuario.setBounds(50, y += 10, 100, 100);
    this.add(usuario);
    }

}


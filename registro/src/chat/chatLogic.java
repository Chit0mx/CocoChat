package chat;

import java.util.ArrayList;

public class chatLogic {

    public ArrayList<mensaje> mensajes;

    public ArrayList<mensaje> getMensajes() {
        return mensajes;
    }
    
    public void addMensaje (mensaje mensaje) {
        this.mensajes.add(mensaje);
        System.out.println("Se guardo "+mensaje.getMensaje());
    }
    public chatLogic() {
        mensajes = new ArrayList();
    }
}


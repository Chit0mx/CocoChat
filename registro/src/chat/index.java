package chat;

public class index {

    public static void main(String[] args) {
        
        registro reg=new registro();
        reg.setVisible(false);
        
        login log=new login();
        log.setVisible(true);
        
        listas list = new listas();
        list.setVisible(false);
        
        Ventana_Chat chat = new Ventana_Chat();
        chat.setVisible(false);
        
    }
}

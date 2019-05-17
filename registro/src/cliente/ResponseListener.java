package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseListener extends Thread implements Runnable {
    private Socket socket;
    DataInputStream dout;

    public ResponseListener(Socket socket) {
        this.socket = socket;
        try {
            dout = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ResponseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void run() {
        System.out.println("Que pedo");
        System.out.println("Que pedo 2");
        System.out.println("En el reader\nSocket cerrado: " + socket.isClosed());
        while (true) {
            try {
                if (dout.readLine() != null) {
                    System.out.println("Server response: " + dout.readLine());
                }
            } catch (IOException ex) {
                Logger.getLogger(ResponseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

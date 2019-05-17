package cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ResponseListener extends Thread implements Runnable {
    private Socket socket;


    public ResponseListener(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        Scanner sc;
        try {
            sc = new Scanner(socket.getInputStream());
            System.out.println("En el reader\nSocket cerrado: " + socket.isClosed() + " Scann.hasNextLine: " + sc.hasNextLine());
            while (!socket.isClosed() && sc.hasNextLine()) {
                System.out.println("Server response: " + sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

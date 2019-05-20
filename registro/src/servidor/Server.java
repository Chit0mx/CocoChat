package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Server extends Thread{
    int port;
    // Arreglo de "clientes"
    ArrayList<ServerWorker> workerList = new ArrayList<>();
    // Getter para que un cliente tenga acceso a los demas para operaciones
    public ArrayList<ServerWorker> getWorkerList() {
        return workerList;
    }

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // Crear el socket del servidor
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado...");
            while(true) {
                // Se crea un socket cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Socket cliente conectado... ");
                // Se crea nuestra clase "cliente"
                ServerWorker worker = new ServerWorker(this, clientSocket);
                // Añadir el cliente a la lista de clientes
                workerList.add(worker);
                // Empezar la ejecucion del hilo
                worker.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void removeWorker(ServerWorker aThis) {
        workerList.remove(aThis);
    }
}

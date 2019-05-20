package servidor;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Esta clase extiende de Thread, es un hilo, esto es lo que permite las conexiones simultaneas
class ServerWorker extends Thread {

    private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;
    // Porque un HashSet en lugar de un list o arraylist? buena pregunta xd
    private HashSet<String> topicSet = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public ServerWorker(Server server, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.server = server;
    }
    // Metodo de la clase Thread que se ejecuta al llamar a start()
    @Override
    public void run () {
        try {
            // Ejecutar el metodo para el manejo de interacciones
            handleClientSocket(clientSocket);
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Metodo para el manejo de la interaccion servidor-cliente?
    private void handleClientSocket (Socket clientSocket) throws IOException, InterruptedException {
        // Obtenemos el input del cliente
        InputStream inputStream = clientSocket.getInputStream();
        // Obtenemos el output del cliente
        this.outputStream = clientSocket.getOutputStream();
        // Para leer linea por linea del input del cliente
        DataInputStream dout = new DataInputStream(clientSocket.getInputStream());
        // Para guardar lo que el cliente escriba
        String linea;
        while ( true ) {
            // Leer lo que se escriba
            linea = dout.readLine();
            // Cuando es null es que se cerro la conexion con el socket cliente
            if (linea == null) {
                break;
            }
            System.out.println("Linea recibida: " + linea);
            // Dividir la linea por espacios
            String[] tokens = linea.split(" ");
            if (tokens.length > 0 && tokens != null) {
                // La primera palabra es el comando
                String cmd = tokens[0];
                // Else if para los comandos
                if ("quit".equalsIgnoreCase(cmd)) {
                    handleLogout ();
                    break;
                } else if ("msg".equalsIgnoreCase(cmd)) {
                    handleMessage(tokens);
                } else if ("join".equalsIgnoreCase(cmd)) {
                    handleJoin(tokens);
                } else if ("leave".equalsIgnoreCase(cmd)) {
                    handleLeave(tokens);
                } else if ("login".equalsIgnoreCase(cmd)) {
                    handleLogin (outputStream, tokens);
                }
            }
        }

        clientSocket.close();
    }
    // Esto se ejecuta cada vez que se logea alguien
    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        // Solo se esperan 3 tokens i.e *login esau 123* son 3 palabras
        System.out.println("En el login");
        if (tokens.length == 3) {
            System.out.println("Ok");
            String login = tokens[1];
            String password = tokens[2];
            System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2]);
            /*login.equals("efrain") && password.equals("123") || login.equals("esau") && password.equals("123") || login.equals("andres") && password.equals("123")*/
            if (true) {
                System.out.println("Login ok???");
                String msg = "Ok login" + "\n";
                // Enviar el resultado de la operacion al cliente
                outputStream.write(msg.getBytes());
                getTodosActivos();
                this.login = login;
                System.out.println("User logged: " + login);

                String onlineMsg = "online " + login + "\n";
                // Se obtienen todos los clientes del servidor
                List<ServerWorker> workerList = server.getWorkerList();
                // Y se iteran para mandar el mensaje de online a todos
                for (ServerWorker aux : workerList) {
                    // Para no enviar el mensaje de login a si mismo
                    if (!login.equals(aux.getLogin())) {
                        aux.send(onlineMsg);
                    }
                }
            } else {
                String msg = "Error login" + "\n";
                outputStream.write(msg.getBytes());
                System.err.println("Login error: " + login + " " + password);
            }
        }
    }

    private void getTodosActivos() {

    }

    private void send(String onlineMsg) throws IOException {
        outputStream.write(onlineMsg.getBytes());
    }
    // Se manda el status de logout a todos los demas
    private void handleLogout() throws IOException {
        server.removeWorker(this);
        String offlineMsg = "offline " + login + "\n";
        // Se obtienen todos los clientes del servidor
        List<ServerWorker> workerList = server.getWorkerList();
        // Y se iteran para mandar el mensaje de online a todos
        for (ServerWorker aux : workerList) {
            // Para no enviar el mensaje de login a si mismo
            if (!login.equals(aux.getLogin())) {
                aux.send(offlineMsg);
            }
        }
        // Se cierra el socket cliente
        clientSocket.close();
    }

    private void handleMessage(String[] tokens) throws IOException {
        String destino = tokens[1];
        String body = "";
        // Usamos el mismo metodo de mandar mensajes para "mandar mensajes xd" a los topicos
        boolean isTopic = destino.charAt(0) == '#';
        // Ya que el mensaje puede tener espacios se detecta como tokens, hay que juntarlos
        for(int i = 2; i < tokens.length; i++) {
            body += tokens[i] + " ";
        }
        // Iterar todos los clientes y encontrar al que se le quiere enviar le mensaje
        List<ServerWorker> workerList = server.getWorkerList();
        // Y se iteran para encontrar al usuario que se le enviara el mensaje
        for (ServerWorker aux : workerList) {
            if (isTopic) {
                System.out.println("Mensaje al topico " + destino);
                // Mensajes para miembros del topico
                if (aux.isMemberOfTopic(destino)) {
                    String outMsg = "msg " + login + " " + destino + ": " + body + "\n";
                    aux.send(outMsg);
                }
            } else {
                // Si es el usuario que se quiere
                if (destino.equalsIgnoreCase(aux.getLogin())) {
                    String outMsg = "msg " + login + " " + body + "\n";
                    aux.send(outMsg);
                }
            }
        }
    }
    // Checar si es miembro del topico
    private boolean isMemberOfTopic (String topic) {
        return topicSet.contains(topic);
    }

    // Contorlar cuando se quiera unir a una "discucion" i.e join #jotadas
    private void handleJoin(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            String topic = tokens[1];
            System.out.println(login + " se ha unido al topico " + topic);
            send("Te uniste al topico " + topic);
            outputStream.write(("Te uniste al topico " + topic).getBytes());
            topicSet.add(topic);
        }
    }

    private void handleLeave(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            String topic = tokens[1];
            System.out.println(login + " se salio del topico " + topic);
            outputStream.write(("Has abandonado el topico " + topic).getBytes());
            topicSet.remove(topic);
        }
    }
}

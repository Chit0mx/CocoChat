package cliente;

import chat.chatLogic;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient {
    private final String serverName;
    private final int serverPort;
    private String loginData = "", passwordData = "";
    private Socket socket;
    private OutputStream serverOut;
    private InputStream serverIn;
    private BufferedReader bufferedIn;
    private ArrayList<UserStatusListener> userStatusListeners = new ArrayList<>();
    private ArrayList<MessageListener> messageListeners = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private chatLogic chat;

    public ChatClient(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    /*public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 8818);

        client.addUserStatusListener(new UserStatusListener() {
            @Override
            public void online(String login) {
                System.out.println("ONLINE: " + login);
            }

            @Override
            public void offline(String login) {
                System.out.println("OFFLINE: " + login);
            }
        });

        client.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(String fromLogin, String msgBody) {
                System.out.println("You got a message from " + fromLogin + " ====> " + msgBody);
            }
        });

        if(!client.connect()) {
            System.err.println("Connection failed!");
        } else {
            System.out.print("Connection OK!\n");
            client.obtenerDatos();
        }

        //client.logoff ();
    }*/

    public void obtenerDatos () {
        System.out.print("Connection OK!\nIngrese sus datos\nLogin: ");
        loginData = scanner.nextLine();
        System.out.print("Password: ");
        passwordData = scanner.nextLine();

        if (login(loginData, passwordData)) {
            System.out.println("Login OK");
            //client.msg("esau", "Joto");
        } else {
            System.out.println("Error login");
        }
    }

    public void msg(String sendTo, String msgBody) {
        String cmd = "msg " + sendTo + " " + msgBody + "\n";
        try {
            serverOut.write(cmd.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logoff() {
        String cmd = "quit\n";
        try {
            serverOut.write(cmd.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            System.out.println("Client port is " + socket.getLocalPort());
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean joinTopic (String topic) {
        String cmd = "join #" + topic + "\n";
        System.out.println("Ward 0");
        try {
            System.out.println("Ward 1");
            //System.out.println("Command: " + cmd);
            serverOut.write(cmd.getBytes());
            System.out.println("Ward 2");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(serverIn));
            System.out.println("Ward 3");
            //String response = reader.readLine();
            System.out.println("Ward 4");
            //System.out.println("Response line: " + response);
            return true;
            /*if ( ("Te uniste al topico #" + topic).equalsIgnoreCase(response)) {
                System.out.println("Te has unido correctamente al grupo: " + topic);
                return true;
            } else {
                return false;
            }*/
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean login(String login, String password) {
        String cmd = "login " + login + " " + password + "\n";
        try {
            //System.out.println("Command: " + cmd);
            serverOut.write(cmd.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(serverIn));
            String response = reader.readLine();
            //System.out.println("Response line: " + response);

            if ("ok login".equalsIgnoreCase(response)) {
                startMessageReader ();
                startClientReader ();
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // Para leer los mensajes del cliente
    private void startClientReader() {
        Thread t = new Thread() {
            String linea = "";
            @Override
            public void run() {
                while (true) {
                    linea = scanner.nextLine();
                    System.out.println("Escribiste: " + linea);

                    String[] tokens = linea.split(" ");
                    if (tokens.length > 0 && tokens != null) {
                        // La primera palabra es el comando
                        String cmd = tokens[0];
                        System.out.println("El comando: " + cmd);
                        if ("msg".equalsIgnoreCase(cmd)) {
                            System.out.println("Mensaje");
                            String body = "";
                            // Ya que el mensaje puede tener espacios se detecta como tokens, hay que juntarlos
                            for(int i = 2; i < tokens.length; i++) {
                                body += tokens[i] + " ";
                            }
                            msg(tokens[1], body);
                        } else if ("quit".equalsIgnoreCase(cmd)) {
                            System.out.println("Salir...");
                            logoff ();
                        }
                    }
                }
            }
        };
        t.start();
    }
    //Para leer los mensajes del server
    private void startMessageReader() {
        Thread t = new Thread() {
            @Override
            public void run() {
                readMessageLoop ();
            }
        };
        t.start();
    }

    private void readMessageLoop() {
        try {
            String linea;
            while( true ) {
                // Leer lo que se escriba
                linea = bufferedIn.readLine();
                System.out.println("Entre xd " + linea);
                // Cuando es null es que se cerro la conexion con el socket cliente
                if (linea == null) {
                    break;
                }
                String[] tokens = linea.split(" ");
                if (tokens.length > 0 && tokens != null) {
                    // La primera palabra es el comando
                    String cmd = tokens[0];
                    System.out.println("Comando recibido: " + cmd);
                    if ("online".equalsIgnoreCase(cmd)) {
                        handleOnline(tokens);
                    } else if ("offline".equalsIgnoreCase(cmd)) {
                        handleOffline(tokens);
                    } else if ("msg".equalsIgnoreCase(cmd)) {
                        System.out.println("Mensaje?");
                        String body = "";
                        // Ya que el mensaje puede tener espacios se detecta como tokens, hay que juntarlos
                        for(int i = 2; i < tokens.length; i++) {
                            body += tokens[i] + " ";
                        }
                        handleMessage(tokens[1], body);
                    } else if ("conectados".equalsIgnoreCase(cmd)) {
                        ArrayList<String> nombreConectados = new ArrayList();
                        for(int i = 2; i < tokens.length; i++) {
                            nombreConectados.add(tokens[i]);
                        }
                        
                        System.out.println("Los conectados son: " + nombreConectados);
                    }
                }
            }
        } catch (Exception exeption) {
            exeption.printStackTrace();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void logout () throws IOException {
        logoff();
    }

    private void handleMessage(String login, String body) {
        for (MessageListener listener : messageListeners) {
            listener.onMessage(login, body);
        }
    }

    private void handleOffline(String[] tokens) {
        String login = tokens[1];
        for (UserStatusListener listener : userStatusListeners) {
            listener.offline(login);
        }
    }

    private void handleOnline(String[] tokens) {
        String login = tokens[1];
        for (UserStatusListener listener : userStatusListeners) {
            listener.online(login);
        }
    }

    public void addUserStatusListener (UserStatusListener listener) {
        userStatusListeners.add(listener);
    }

    public void removeUserStatusListener (UserStatusListener listener) {
        userStatusListeners.remove(listener);
    }

    public void addMessageListener (MessageListener listener) {
        messageListeners.add(listener);
    }

    public void removeMessageListener (MessageListener listener) {
        messageListeners.remove(listener);
    }

    public void addOutput(chatLogic chat) {
        this.chat = chat;
    }

}

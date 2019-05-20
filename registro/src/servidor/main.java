package servidor;

public class main {
    public static void main(String[] args) {
        /* Casi todos los try catch que se vean se añadieron con ctrl + intro xd */
        int port = 8818;
        // Custom clase socket servidor
        Server server = new Server(port);
        server.start();
    }
}

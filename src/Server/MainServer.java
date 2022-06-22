package Server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        Servidor server = new Servidor("server");

        server.startServer();
    }
}

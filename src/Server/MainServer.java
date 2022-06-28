package Server;


import Engine.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    static final int PORT = 2508;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        Stand stand1 = new Stand();
        boolean stand1First = true;
        Servidor threadServer1  = new Servidor(socket, StandNumber.Stand1, stand1);

        Stand stand2 = new Stand();
        boolean stand2First = true;
        Servidor threadServer2  = new Servidor(socket, StandNumber.Stand2, stand2);

        Stand stand3 = new Stand();
        boolean stand3First = true;
        Servidor threadServer3  = new Servidor(socket, StandNumber.Stand3, stand3);

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        try{
            Writer.Write("Servidor Iniciado");
            while (true){
                try {
                    Writer.Write("Oyendo");
                    socket = serverSocket.accept();
                    Writer.Write("Conectados");
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
                // new thread for a client
                if (stand1First){
                    stand1First = false;
                    threadServer1 = new Servidor(socket, StandNumber.Stand1, stand1);
                    threadServer1.start();
                } else{
                    if(threadServer1.isAlive()){
                        if (stand2First){
                            stand2First = false;
                            threadServer2 = new Servidor(socket, StandNumber.Stand2, stand2);
                            threadServer2.start();
                        } else{
                            if(threadServer2.isAlive()){
                                if (stand3First){
                                    stand3First = false;
                                    threadServer3 = new Servidor(socket, StandNumber.Stand3, stand3);
                                    threadServer3.start();
                                } else{
                                    stand3 = threadServer3.getStand();
                                    threadServer3 = new Servidor(socket, StandNumber.Stand3, stand3);
                                }
                            }else{
                                stand2 = threadServer2.getStand();
                                threadServer2 = new Servidor(socket, StandNumber.Stand2, stand2);
                            }
                        }
                    }else{
                        stand1 = threadServer1.getStand();
                        threadServer1 = new Servidor(socket, StandNumber.Stand1, stand1);
                    }
                }
            }
        }
        catch (Exception e){
            Writer.Write(e.getMessage());
        }
    }
}

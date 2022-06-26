package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Engine.*;

public class MainServer {
    public static void main(String[] args) {
        try{
            Servidor threadServer1 = new Servidor(StandNumber.Stand1);
            Servidor threadServer2 = new Servidor(StandNumber.Stand2);
            Servidor threadServer3 = new Servidor(StandNumber.Stand3);
            threadServer1.start();
            threadServer2.start();
            threadServer3.start();
            Writer.Write("Servidor Iniciado");
            while (true){ }
        }
        catch (Exception e){
            Writer.Write(e.getMessage());
        }
    }
}

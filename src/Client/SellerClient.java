package Client;

import Engine.Accion;
import Engine.Message;
import Engine.StandNumber;
import Engine.Writer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SellerClient{
    protected ServerSocket sellerServerSocket;
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;

    public static void main(String[] args) {
        try {
            SellerClient client = new SellerClient();
            System.out.println("Vendedor viendo si abastece");
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void run() {
        
        try {
            Writer.Write("Creando Conexion");
            int sellerServerSocket = 2511;
            this.sellerServerSocket = new ServerSocket(sellerServerSocket);
            while (true){
                Writer.Write("Oyendo");
                this.socket = this.sellerServerSocket.accept();
                Writer.Write("Conectados");
                inputStream = new ObjectInputStream(this.socket.getInputStream());
                outputStream = new ObjectOutputStream(this.socket.getOutputStream());
                String message = inputStream.readUTF();
                Writer.Write("Mensaje Recibido" + message);
                socket.close();
                if (Message.ReadAccion(message) == Accion.pedir.toString()){
                    refillStand(StandNumber.Stand1);
                    refillStand(StandNumber.Stand2);
                    refillStand(StandNumber.Stand3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refillStand(StandNumber standNumber){
        try {
            String HOST = "localhost";
            int serverPORT1 = 2508;
            int serverPORT2 = 2509;
            int serverPORT3 = 2510;
            Socket socket1 = new Socket();
            switch (standNumber){
                case Stand1:
                    socket1 = new Socket(HOST, serverPORT1);
                    break;
                case Stand2:
                    socket1 = new Socket(HOST, serverPORT2);
                    break;
                case Stand3:
                    socket1 = new Socket(HOST, serverPORT3);
                    break;
            }
            inputStream = new ObjectInputStream(socket1.getInputStream());
            outputStream = new ObjectOutputStream(socket1.getOutputStream());
            outputStream.writeUTF(Message.Send(Accion.abastecer.toString()));
            outputStream.close();
            socket.close();
        }catch (Exception ignore){
        }
    }
}

package Client;

import Engine.Accion;
import Engine.Message;
import Engine.StandNumber;
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
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void run() {
        
        try {
            while (true){
                int sellerServerSocket = 2511;
                this.sellerServerSocket = new ServerSocket(sellerServerSocket);
                this.socket = this.sellerServerSocket.accept();
                inputStream = new ObjectInputStream(this.socket.getInputStream());
                String message = inputStream.readUTF();
                socket.close();
                if (Message.ReadAccion(message) == Accion.pedir){
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
            switch (standNumber){
                case Stand1:
                    this.socket = new Socket(HOST, serverPORT1);
                    break;
                case Stand2:
                    this.socket = new Socket(HOST, serverPORT2);
                    break;
                case Stand3:
                    this.socket = new Socket(HOST, serverPORT3);
                    break;
            }
            inputStream = new ObjectInputStream(this.socket.getInputStream());
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            outputStream.writeUTF(Message.Send(Accion.abastecer));
            outputStream.close();
            socket.close();
        }catch (Exception ignore){
        }
    }
}

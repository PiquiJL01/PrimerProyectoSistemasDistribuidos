package Client;

import Engine.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// import java.util.concurrent.TimeUnit;

public abstract class SmokerClient {
    private final String HOST = "localhost";
    // private final String HOST;
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;
    private final Map<Item, Boolean> Ingredients;
    private final Item infiniteItem;
    private LogWriter log;
    private int requestNumber;
    private String messageToSave = "";

    public SmokerClient(Item item) throws UnknownHostException {
        // this.HOST = InetAddress.getLocalHost().getHostName();
        Ingredients = new HashMap<>();
        infiniteItem = item;
        for (Item i:
             Item.Items) {
            if(i == infiniteItem){
                Ingredients.put(i, true);
            }else{
                Ingredients.put(i, false);
            }
        }
        System.out.println("Fumador [" + infiniteItem + "] tiene " + Ingredients);
        requestNumber = 0;
        log = new LogWriter();
    }

    // public static void main(String[] args){
    //     try{
    //         SmokerClient client = new SmokerClient();
    //         client.run();
    //     }
    //     catch (Exception e){
    //         Writer.Write("Error mientras corre");
    //     }
    // }

    public void run() throws Exception {
        // int requestNumber = 0;
        
        while(true){

            // Thread.sleep(2000);
            TimeUnit.SECONDS.sleep(2);
            try {

                // messageToSave += "fumador[" + this.infiniteItem + "]" + " - accion[buscando] - stand: Stand1";
                Writer.Write("Buscando en el stand 1 - intento: " + requestNumber);
                this.SearchIngredient(StandNumber.Stand1);
                // messageToSave += " - intento: [" + this.requestNumber+"]";
                // Writer.Write(messageToSave);
                // log.saveMessageOnFile("fumador[" + this.infiniteItem + "]" + " - accion[buscando] - intento: [" + this.requestNumber+"] - stand: Stand1");
            } catch (Exception e){
                try{
                    // Thread.sleep(2000);
                    TimeUnit.SECONDS.sleep(2);
                    
                    // messageToSave = "fumador[" + infiniteItem + "]" + " - accion[buscando]";
                    Writer.Write("Buscando en el stand 2 - intento: " + requestNumber);
                    this.SearchIngredient(StandNumber.Stand2);
                    // messageToSave += "- intento: [" + requestNumber+"] - stand: Stand2";
                    Writer.Write(messageToSave);
                    // log.saveMessageOnFile("fumador[" + this.infiniteItem + "]" + " - accion[buscando] - intento: [" + this.requestNumber+"] - stand: Stand2");
                }catch (Exception e2){
                    try{
                        // Thread.sleep(2000);
                        TimeUnit.SECONDS.sleep(2);
                        Writer.Write("Buscando en el stand 3  - intento: " + requestNumber);
                        // String messageToSave = "fumador[" + this.infiniteItem + "]" + " - accion[buscando] - intento: [" + this.requestNumber+"] - stand: Stand3";
                        
                        // messageToSave = "fumador[" + this.infiniteItem + "]" + " - accion[buscando] - stand: Stand3";
                        this.SearchIngredient(StandNumber.Stand3);
                        // messageToSave += "- intento: [" + this.requestNumber+"]";
                        // Writer.Write(messageToSave);
                        // log.saveMessageOnFile("fumador[" + this.infiniteItem + "]" + " - accion[buscando] - intento: [" + this.requestNumber+"] - stand: Stand3");

                    }catch (Exception e3){
                        TimeUnit.SECONDS.sleep(2);
                        Writer.Write("No se consiguio en los stands");
                    }
                }
            }
            this.Smoke();
        }
    }

    public void SearchIngredient(StandNumber standNumber) throws Exception{
        // Writer.Write("Buscando ingrediente");
        // Thread.sleep(2000);
        for (Item i: Item.Items) {
            if(!this.Ingredients.get(i)){
                Writer.Write("Buscando [" + i.toString() + "] en el " + standNumber);
                Thread.sleep(500);
                try{
                    setConnection(standNumber);
                    // Writer.Write("Enviando Mensaje");
                    // Thread.sleep(500);
                    outputStream.writeUTF(Message.Buscar + i.toString());
                    // Writer.Write("Mensaje Enviado");
                    // Thread.sleep(500);
                    outputStream.flush();
                    // Writer.Write("Leyendo Respuesta");
                    // Thread.sleep(500);
                    boolean inMessage = inputStream.readBoolean();
                    // Writer.Write("Mensaje recibido");
                    // Thread.sleep(2000);
                    outputStream.close();
                    socket.close();
                    if (inMessage){
                        Writer.Write("Ingrediente Conseguido");
                        Thread.sleep(500);
                        this.Ingredients.replace(i, true);
                    }else {
                        Writer.Write("Ingrediente no Conseguido");
                        Thread.sleep(500);
                        this.requestNumber += 1;
                        if (this.requestNumber == 2){
                            this.requestNewIngredients();
                            this.requestNumber = 0;
                        }

                        throw new Exception();
                    }
                }catch (IOException e){
                    Writer.Write("Error Buscando Ingrediente");
                }
            }
        }
    }

    private void setConnection(StandNumber standNumber) throws IOException {
        // Writer.Write("Creando Conexion");
        int serverPORT1 = 2508;
        int serverPORT2 = 2509;
        int serverPORT3 = 2510;
        switch (standNumber){
            case Stand1:
                socket = new Socket(HOST, serverPORT1);
                break;
            case Stand2:
                socket = new Socket(HOST, serverPORT2);
                break;
            case Stand3:
                socket = new Socket(HOST, serverPORT3);
                break;
        }
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        // Writer.Write("Conexion creada");
    }

    private void requestNewIngredients() throws IOException {
        Writer.Write("Solicitando Refrescar");
        int sellerPORT = 2511;
        socket = new Socket(HOST, sellerPORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        outputStream.writeUTF(Message.Pedir);
        outputStream.flush();
        outputStream.close();
        socket.close();
    }

    public void Smoke() throws InterruptedException, IOException {
        // String messageToSend = "";
        Writer.Write("Intentando Fumar");
        Thread.sleep(500);
        boolean canSmoke = true;
        for (Item i:
             Item.Items) {
            if(!Ingredients.get(i)){
                canSmoke = false;
            }
        }
        System.out.println("Fumador [" + infiniteItem + "] tiene " + Ingredients);
        if(canSmoke){
            for (Item i:
                 Item.Items) {
               if(i != infiniteItem){
                   Ingredients.replace(i, false);
               }
            }
            // messageToSend = "fumador[" + this.infiniteItem + "]" + " - accion[fumando] - intento: [" + this.requestNumber+"] ";
            Writer.Write("Fumando");
            // log.saveMessageOnFile(messageToSend);
            // TimeUnit.SECONDS.sleep(5);
            Thread.sleep(5000);
            Writer.Write("Termina de Fumar");

        }
    }
}

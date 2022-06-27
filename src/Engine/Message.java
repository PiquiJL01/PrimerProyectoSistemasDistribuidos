package Engine;

public class Message {
    public static final String Buscar = "Buscar";
    public static final String Pedir = "Pedir";
    public static final String Recibir= "Recibir";
    public static final String Abastecer = "Abastecer";
    public static final String Error = "Error";
    public static final String Papel = "Papel";
    public static final String Tabaco = "Tabaco";
    public static final String Fosforo = "Fosforo";

    public static String Send(String accion){
        return accion;
    }

    public static String Send(String accion, String item){
        String message = Send(accion.toString());
        message += item.toString();
        return message;
    }

    public static String SendError(){
        return Error;
    }

    public static String ReadAccion(String message) throws Exception {
        if(message.contains(Buscar)){
            return Buscar;
        }
        if(message.contains(Pedir)){
            return Pedir;
        }
        if (message.contains(Recibir)) {
            return Pedir;
        }
        if (message.contains(Abastecer)){
            return Abastecer;
        }
        if (message.contains(Error)){
            return Error;
        }
        throw new Exception();
    }

    public static String ReadItem(String message) throws Exception{
        if (message.contains(Papel)){
            return Papel;
        }
        if (message.contains(Tabaco)){
            return Tabaco;
        }
        if (message.contains(Fosforo)){
            return Fosforo;
        }
        if (message.contains(Error)){
            return Error;
        }
        throw new Exception();
    }
}

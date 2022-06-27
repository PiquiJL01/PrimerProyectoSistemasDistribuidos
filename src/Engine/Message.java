package Engine;

public class Message {
    private static final String Buscar = "Buscar";
    private static final String Pedir = "Pedir";
    private static final String Recibir= "Recibir";
    private static final String Abastecer = "Abastecer";
    private static final String Error = "Error";
    private static final String Papel = "Papel";
    private static final String Tabaco = "Tabaco";
    private static final String Fosforo = "Fosforo";

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
            return Accion.buscar.toString();
        }
        if(message.contains(Pedir)){
            return Accion.pedir.toString();
        }
        if (message.contains(Recibir)) {
            return Accion.pedir.toString();
        }
        if (message.contains(Abastecer)){
            return Accion.abastecer.toString();
        }
        if (message.contains(Error)){
            return "";
        }
        throw new Exception();
    }

    public static String ReadItem(String message) throws Exception{
        if (message.contains(Papel)){
            return Item.papel.toString();
        }
        if (message.contains(Tabaco)){
            return Item.tabaco.toString();
        }
        if (message.contains(Fosforo)){
            return Item.fosforo.toString();
        }
        if (message.contains(Error)){
            return "";
        }
        throw new Exception();
    }
}

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

    public static String Send(Accion accion){
        String message = "";
        switch (accion){
            case buscar:
                message += Buscar;
            case pedir:
                message += Pedir;
            case recibir:
                message += Recibir;
            case abastecer:
                message += Abastecer;
            default:
                message += Error;
        }

        return message;
    }

    public static String Send(Accion accion, Item item){
        String message = Send(accion);
        switch (item){
            case papel:
                message += Papel;
            case tabaco:
                message += Tabaco;
            case fosforo:
                message += Fosforo;
        }
        return message;
    }

    public static String SendError(){
        return Error;
    }

    public static Accion ReadAccion(String message) throws Exception {
        if(message.contains(Buscar)){
            return Accion.buscar;
        }
        if(message.contains(Pedir)){
            return Accion.pedir;
        }
        if (message.contains(Recibir)) {
            return Accion.pedir;
        }
        if (message.contains(Abastecer)){
            return Accion.abastecer;
        }
        if (message.contains(Error)){
            return null;
        }
        throw new Exception();
    }

    public static Item ReadItem(String message) throws Exception{
        if (message.contains(Papel)){
            return Item.papel;
        }
        if (message.contains(Tabaco)){
            return Item.tabaco;
        }
        if (message.contains(Fosforo)){
            return Item.fosforo;
        }
        if (message.contains(Error)){
            return null;
        }
        throw new Exception();
    }
}

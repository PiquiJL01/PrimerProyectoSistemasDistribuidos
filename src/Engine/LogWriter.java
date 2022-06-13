package Engine;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {
	public static void mensajeALog(int standNumber, Accion accion, Item item) {
		String message = TimeStamp() + CreateLogEntry(standNumber, accion, item);
		try {
    		File archivo = new File("log.txt");
    		archivo.createNewFile();
    		try{
    			FileWriter fw = new FileWriter("log.txt", true);
    			BufferedWriter bw = new BufferedWriter(fw);
    			PrintWriter out = new PrintWriter(bw);
    			out.println(message);
    			out.close();
    			System.out.println(message);
    		} catch (IOException e) {
    			    //exception handling left as an exercise for the reader
    		}
    	} catch (IOException e) {
    		System.out.println("Ocurrio un error");
    		System.out.println(TimeStamp() + ": " + message);
    		e.printStackTrace();
    	}   
	}
	
	private static String CreateLogEntry(int standNumber, Accion accion, Item item) {
		String message = "";
		switch(accion) {
			case pedir:
				message += "Se pidio al ";
				break;
			case recibe:
				message += "Se recibe del ";
				break;
			case entrega:
				message += "Se entrega del ";
				break;
			case solicitar:
				message += "Se solicita abastecer al ";
				break;
			case abastecer:
				message += "Se abastece al ";
				break;
		}
		switch(standNumber) {
			case 1:
				message += "stand 1";
				break;
			case 2:
				message += "stand 2";
				break;
			case 3:
				message += "stand 3";
				break;
		}
		switch(item) {
			case papel:
				message += " papel de cigarro";
				break;
			case tabaco:
				message += " tabaco";
				break;
			case vacio:
				break;
		}
		return message;
	}
	
	private static String TimeStamp() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}

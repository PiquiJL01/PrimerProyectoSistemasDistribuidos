package Engine;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
	public void saveMessageOnFile(String message) throws IOException {
        String route = "/home/rubendgomes/Documents/GitHub/PrimerProyectoSistemasDistribuidos/src/Engine/log.txt";
        File file = new File(route);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); 
        writer.newLine(); 
        writer.write("["+ dtf.format(now) +"] - " + message); 
        writer.close();          
    }
}

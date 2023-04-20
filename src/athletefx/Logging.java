
package athletefx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * @author Mesathus
 */
public class Logging {
    // fields for generating log files
    private static LocalDate currDate = LocalDate.now(ZoneId.of("UTC"));
    private static LocalDateTime currDateTime;
    private final static Path BASEDIR = Paths.get(System.getProperty("user.dir"));
    private static Path logDir;
    private static PrintWriter writer;
    
    // ensure the path to the log file exists and prepare the class fields
    public static void Init() throws IOException{
        try{
            logDir = Paths.get(BASEDIR.toString(),"Logs");
            if(!Files.exists(logDir)) Files.createDirectory(logDir);
            logDir = Paths.get(BASEDIR.toString(),"Logs",currDate.toString() + ".txt");  // create a path to baseApplicationDirectory\logs\today.txt
            if(!Files.exists(logDir)) Files.createFile(logDir);
            writer = new PrintWriter(new BufferedWriter(new FileWriter(logDir.toString(), true)));
        }
        catch(IOException e){     //print a system message if the log file is unavailable
            System.out.println("Error log unable to be created.");
            System.out.println(e.getMessage());
        }
    }
    
    // method to generate a log entry
    public static boolean StampLog(Exception ex){
        try{            
                currDate = LocalDate.now(ZoneId.of("UTC"));  // set the date for the filename
                Init();
                currDateTime = LocalDateTime.now(ZoneId.of("UTC"));  // set the time to stamp the log with            
                writer.write(currDateTime.toString() + " UTC " + " The following error occured: " + ex.getMessage() + System.lineSeparator());
                return true;            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            writer.flush();
        }
    }
    
}

package logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *  Logger Class
 */
public class log 
{
	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private static void setupLogger()
	{
		LogManager.getLogManager().reset();
		log.setLevel(Level.ALL);
		
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.SEVERE);
		log.addHandler(ch);
		
		try
		{
			FileHandler fh = new FileHandler("doc/Inventory.log" , true);
			fh.setLevel(Level.ALL);
			log.addHandler(fh);
		} catch (java.io.IOException e){
			log.log(Level.SEVERE, "Log file not working", e);
		}
	}
	
	public static void main(String[] args) throws java.io.IOException 
	{
		setupLogger();
	}
}

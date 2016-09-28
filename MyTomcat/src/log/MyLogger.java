package log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class MyLogger {
	public MyLogger(){
		initMyLog();
	}
	
	public void initMyLog(){
		
		Logger log;
		try {
			log = Logger.getLogger("ServerLog");
			log.setLevel(Level.INFO);

			ConsoleHandler consoleHandler =new ConsoleHandler();
			consoleHandler.setLevel(Level.ALL);
			log.addHandler(consoleHandler);
			FileHandler fileHandler = new FileHandler("F:/java/work_1/MyFrame_1.0/log/ServerLog.log",true);
			fileHandler.setLevel(Level.INFO);
			fileHandler.setFormatter(new MyLogHander()); 
			log.addHandler(fileHandler);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//设置日志文件输出格式
class MyLogHander extends Formatter {
    @Override
    public String format(LogRecord record) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    	Date date = new Date();
    	String dateFormat = sdf.format(date);
        return record.getLevel() + ": - -[" +
        dateFormat + "]  " + record.getMessage() +"\n";
    }
    
}

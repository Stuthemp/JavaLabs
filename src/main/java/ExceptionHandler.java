import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class ExceptionHandler {
    private static ArrayList <Exception> ErrList;
    private static Logger log;
    private boolean debug=false;
    FileInputStream ins;

    public ExceptionHandler(String filename) throws IOException  {
        ins = new FileInputStream(filename);
        ErrList=new ArrayList <Exception>();
        LogManager.getLogManager().readConfiguration(ins);
        log=Logger.getLogger(ExceptionHandler.class.getName());

    }

    public int addErr(Exception e) {
        ErrList.add(e);
        return ErrList.size();
    }

    public int addErrWithLog(Exception e) {

        ErrList.add(e);
        log.log(Level.FINE, e.getMessage());
        return ErrList.size();
    }

    public int getErrCount() {
        return ErrList.size();
    }


    //Пробрасываем ошибку добавив ее в список
    public Exception makeErr(Exception e) {
        addErr(e);
        return new Exception(e);
    }

    public void addtologs(String message) {
        if(debug)
            log.log(Level.FINE,message);
    }

    public void closeFile() {
        try {
            ins.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class ExceptionHandler {
    private static List<Exception> ErrList = new ArrayList<>();
    private static Logger log;
    //private boolean debug=false;
    FileInputStream ins;

    public ExceptionHandler(String filename) throws IOException  {
        ins = new FileInputStream(filename);
        ErrList=new ArrayList <>();
        LogManager.getLogManager().readConfiguration(ins);
        //log=Logger.getLogger(ExceptionHandler.class.getName());

    }

    public static int addErr(Exception e, String message) {
        ErrList.add(e);
        if(Model.logging) {
            Model.Log.log(Level.FINE, e.getMessage());
            logWriter(message);
        }
        return ErrList.size();
    }


    public static int getErrCount() {
        return ErrList.size();
    }


    //Пробрасываем ошибку добавив ее в список
//    public Exception makeErr(Exception e) {
//        addErr(e,"");
//        return new Exception(e);
//    }

//    public void addtologs(String message) {
//        if(Model.logging) {
//            log.log(Level.FINE, message);
//            logWriter(message);
//        }
//    }


    public static void timingLogWriter(String message){
        if(!Model.logging) return;
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("timeLogs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write(message);
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Model.Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public static void logWriter(String message){
        if(!Model.logging) return;
        Model.Log.log(Level.WARNING, message);
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write(message);
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Model.Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public static void logStart(String login){
        if(!Model.logging) return;
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write("Программа запущена пользователем " + login  + " в " + LocalTime.now());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Model.Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public static void logEnd(String login){
        if(!Model.logging) return;
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write("Программа, запущенная пользователем " + login + " завершена в " + LocalTime.now());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Model.Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public boolean isDebug() {
        return Model.logging;
    }


}

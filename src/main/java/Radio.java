import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Radio {

    private static final Logger Log =
            Logger.getLogger(Main.class.getName());

    public Radio(String currentStation, boolean isOn){
        try {
            this.currentStation = currentStation;
        }
        catch (Exception e){
            String errorMessage = "Неправильно введено название станции. Время ошибки: " + LocalTime.now();
            Controller.logWriter(errorMessage);
            Log.log(Level.WARNING, errorMessage);
        }
        try {
            this.isOn = isOn;
        }
        catch (Exception e){
            String errorMessage = "Неправильно задана информации о работе радио. Время ошибки: " + LocalTime.now();
            Controller.logWriter(errorMessage);
            Log.log(Level.WARNING, errorMessage);
        }

    }
    public Radio(){
        currentStation = "DriveFM";
        isOn = false;
    }
    public String currentStation;
    public boolean isOn;
    public void playTunes(){
        if(isOn) System.out.println("The station " + currentStation +" plays great music.");
        else System.out.println("You need to turn on the radio.");
    }

    public String fileWriter(){
        StringBuilder SB = new StringBuilder("");
        SB.append(this.currentStation).append(",");
        SB.append(this.isOn);
        return SB.toString();
    }

//    public void logWriter(String message){
//        try(
//                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
//        ){
//            Log.log(Level.INFO, message);
//            writer.write(System.lineSeparator());
//            writer.write(message);
//            writer.flush();
//            writer.close();
//        }
//        catch(IOException e){
//            Log.log(Level.SEVERE, "Ошибка вывода!", e);
//        }
//
//    }
}

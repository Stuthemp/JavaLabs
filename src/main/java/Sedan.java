
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sedan extends Car {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    public Sedan(String mark, int maxSpeed, Radio radio, int currentSpeed) {
        super(mark, maxSpeed, radio, currentSpeed);
    }

    private static final Logger Log =
            Logger.getLogger(Main.class.getName());

    @Override
    public void move(int currentSpeed) {
        System.out.println("Sedan moves with speed " + currentSpeed + "km/h.");
    }



    @Override
    public String fileWriter(){
        StringBuilder SB = new StringBuilder("");
        SB.append(this.mark).append(",");
        SB.append(this.maxSpeed).append(",");
        SB.append(this.radio.fileWriter()).append(",");
        SB.append(this.currentSpeed);
        return SB.toString() + System.lineSeparator();
    }

    @Override
    public void toFile(String Path, String isLogNeeded){
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(Path,true), StandardCharsets.UTF_8)
        ){

            boolean doWeWriteLogs = isLogNeeded.equals("true");
            String message = "Произвдена запись в файл " + Path;
            if(doWeWriteLogs) logWriter(message);
            writer.write(this.fileWriter());
            //writer.write(System.lineSeparator());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }
    }

    @Override
    public void dpsRelatedParams(){
        System.out.printf("Марка: %s\nТекущая скорость: %d\n",mark,currentSpeed);
        System.out.println("-------------------------------------");
    }
}

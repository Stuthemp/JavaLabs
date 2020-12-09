
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Один из двух основных классов - лнгковой автомобиль
 */
public class Sedan extends Car {

    public Sedan(String mark, int maxSpeed, Radio radio, int currentSpeed) {
        super(mark, maxSpeed, radio, currentSpeed);
    }

    private static final Logger Log =
            Logger.getLogger(Main.class.getName());

    @Override
    public void move(int currentSpeed) {
        System.out.println("Sedan moves with speed " + currentSpeed + "km/h.");
    }

    /**
     * Компоновка информации об объекте в строку
     * @return - строка с информацией об объекте
     */
    @Override
    public String fileWriter(){
        StringBuilder SB = new StringBuilder("");
        SB.append(this.mark).append(",");
        SB.append(this.maxSpeed).append(",");
        SB.append(this.radio.fileWriter()).append(",");
        SB.append(this.currentSpeed);
        return SB.toString() + System.lineSeparator();
    }

    /**
     * TODO попробовать перетащить в контролер
     * @param Path - путь для файла записи
     */
//    @Override
//    public void toFile(String Path){
//        try(
//                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(Path,true), StandardCharsets.UTF_8)
//        ){
//
//            String message = "Произвдена запись в файл " + Path + " в " + LocalTime.now();
//            if(!Model.logging) ExceptionHandler.logWriter(message);
//            writer.write(this.fileWriter());
//            //writer.write(System.lineSeparator());
//            writer.flush();
//            writer.close();
//        }
//        catch(IOException e){
//            Log.log(Level.SEVERE, "Ошибка вывода!", e);
//        }
//    }

    /**
     * Выводит параметры, нужные для демонстрации DPS
     */
    @Override
    public void dpsRelatedParams(){
        System.out.printf("Марка: %s\nТекущая скорость: %d\n",mark,currentSpeed);
        System.out.println("-------------------------------------");
    }
}

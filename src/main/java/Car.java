import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Car {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    Properties prop = new Properties();
    protected String mark;
    protected int maxSpeed;
    protected Radio radio;
    protected int currentSpeed;
    private static final Logger Log =
            Logger.getLogger(Main.class.getName());

    public Car(String mark, int maxSpeed, Radio radio, int currentSpeed) {
        this.mark = mark;
        this.maxSpeed = maxSpeed;
        this.radio = radio;
        this.currentSpeed = currentSpeed;
    }

    public void move(int currentSpeed){
    }

    public String fileWriter(){return "";}

    public void toFile(String Path, String isLogNeeded){}

    public void logWriter(String message){
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            Log.log(Level.INFO, message);
            writer.write(System.lineSeparator());
            writer.write(message);
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public  static ArrayList<Car> fromFile(String Path){
        Log.log(Level.INFO, "Произведено чтение из файла {0} в {1}", new Object[] {Path, LocalTime.now()});
        ArrayList<Car> cars = new ArrayList<>();
        try {
            File file = new File(Path);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String [] fields = line.split(",");
                if(fields.length == 5)
                    cars.add(new Sedan(fields[0].toString(),Integer.parseInt(fields[1]),new Radio(fields[2].toString(),
                            Boolean.parseBoolean(fields[3])),Integer.parseInt(fields[4])));
                else
                    cars.add(new Truck(fields[0].toString(),Integer.parseInt(fields[1]),new Radio(fields[2].toString(),
                            Boolean.parseBoolean(fields[3])),Integer.parseInt(fields[4]),
                            Integer.parseInt(fields[5]),Double.parseDouble(fields[6])));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void dpsRelatedParams(){};

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Radio getRadio() {
        return radio;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}

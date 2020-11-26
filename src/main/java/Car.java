import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
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
        try {
            this.mark = mark;
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена марка. Время ошибки: " + LocalTime.now();
            Controller.logWriter(errorMessage);
            Log.log(Level.WARNING, errorMessage);
        }
        try {
            this.maxSpeed = maxSpeed;
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена Максимальная скорость. Время ошибки: " + LocalTime.now();
            Controller.logWriter(errorMessage);
            Log.log(Level.WARNING, errorMessage);
        }
        try {
            this.radio = radio;
        }
        catch (Exception e){
            String errorMessage = "Неправильно заданы парметры радио. Время ошибки: " + LocalTime.now();
            Controller.logWriter(errorMessage);
            Log.log(Level.WARNING, errorMessage);
        }
        try {
            this.currentSpeed = currentSpeed;
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена текущая скорость. Время ошибки: " + LocalTime.now();
            Controller.logWriter(errorMessage);
            Log.log(Level.WARNING, errorMessage);
        }



    }

    public void move(int currentSpeed){
    }

    public String fileWriter(){return "";}

    public void toFile(String Path, String isLogNeeded){}

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

    public void setRadioStation(String radio) {
        this.radio.currentStation = radio;
    }

    public void setRadioState(boolean bool){
        this.radio.isOn = bool;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}

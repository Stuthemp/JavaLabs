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

/**
 * Абстрактный класс для Sedan и Truck
 * По сути облегчает работу с файлами и коллекциями, позволяя ссылаться на него
 */

//check1
public abstract class Car {

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

    /**
     * Компоновка информации об объекте в строку
     * @return - строка с информацией об объекте
     */
    public String fileWriter(){return "";}

    /**
     * TODO попробовать перетащить в контролер
     * @param Path - путь для файла записи
     */
    //public void toFile(String Path){}

    /**
     * Выводит параметры, нужные для демонстрации DPS
     */
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

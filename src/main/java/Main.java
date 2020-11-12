
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    //public static Properties prop = new Properties();
    public static FileInputStream fileInputStream;
    public static final Logger Log = Logger.getLogger(Main.class.getName());
    static String login="";
    static String userGroup = "";
    static boolean logging;



    public static void main(String[] args) {
        //final Logger Log = Logger.getLogger(Main.class.getName());

//        try {
//            //обращаемся к файлу и получаем данные
//            fileInputStream = new FileInputStream(Model.PATH_TO_PROPERTIES);
//            Model.prop.load(fileInputStream);
//
//            login = Model.prop.getProperty("login");
//            userGroup = Model.prop.getProperty("userGroup");
//            Model.prop.getProperty("isLogNeeded");
//
//            //печатаем полученные данные в консоль
//
//        } catch (IOException e) {
//            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
//            e.printStackTrace();
//        }

        Model.startApp();


//		volvo.toFile("file.txt");
//		kamaz.toFile("file.txt");

//		ArrayList<Car> cars = Car.fromFile("file.txt");
//
//		System.out.println(cars.get(3).fileWriter());
        Controller.logStart(Model.prop.getProperty("login"));
        if(Model.userGroup.equals("root"))
        {
            View.enterPassword();
            View.rootMenu();
        }
        else View.menu();

    }

    /*
    public static void rootMenu(){
//        Properties prop = new Properties();
//        FileInputStream fileInputStream;
        Scanner scanner = new Scanner(System.in);
//        String password;
//        System.out.println("Введите пароль для получения прав root.");
//        password = scanner.nextLine();
//        if(!password.equals(prop.getProperty("password"))){
//            String errorMessage = "Неудачная попытка получения прав root. Время ошибки: " + LocalTime.now();
//            Car.logWriter(errorMessage);
//            rootMenu();
//        }
        int choice;
        System.out.println("Добро пожаловать," + login + "\nВыберите действие");
        System.out.println("1: Демонстрация работы класса Sedan");
        System.out.println("2: Демонстрация работы класса Truck");
        System.out.println("3: Демонстрация работы класса DPS");
        System.out.println("4: Завершить программу.");
        System.out.println("5: Переключить режим отладки.");
        System.out.println("6: Запустить автотесты.");
        System.out.println("7: Продемонстрировать содержание файла.");
        System.out.println("8: Удалить элемент из файла.");
        System.out.println("9: Изменить элемент в файле.");
        choice = scanner.nextInt();
        switch (choice){
            case 1: sedanCreation();break;
            case 2: truckCreation();break;
            case 3: dpsDemonstration();break;
            case 4: logEnd(login);break;
            case 5: switchDebugging();break;
            case 6:
                System.out.println("Автотесты активно идут...");
                sleep();
                rootMenu();
                break;

            case 7: showDb();
                    rootMenu();
                    break;
            case 8: deleteFromDb();
                    rootMenu();
                    break;
            case 9: changeDb();
                    rootMenu();
                    break;
            default: {
                String errorMessage = "Неправильно выбран пункт меню. Время ошибки: " + LocalTime.now();
                Car.logWriter(errorMessage);
                rootMenu();
            }
        }
    }

    public static void menu(){
//        Properties prop = new Properties();
//        FileInputStream fileInputStream;


        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Добро пожаловать," + login + "\nВыберите действие");
        System.out.println("1: Демонстрация работы класса Sedan");
        System.out.println("2: Демонстрация работы класса Truck");
        System.out.println("3: Демонстрация работы класса DPS");
        System.out.println("4: Завершить программу.");
        System.out.println("5: Продемонстрировать содержание файла.");
        System.out.println("6: Удалить элемент из файла.");
        System.out.println("7: Изменить элемент в файле.");
        choice = scanner.nextInt();
        switch (choice){
            case 1: sedanCreation();break;
            case 2: truckCreation();break;
            case 3: dpsDemonstration();break;
            case 4: logEnd(login);break;
            case 5: showDb();
                rootMenu();
                break;
            case 6: deleteFromDb();
                rootMenu();
                break;
            case 7: changeDb();
                rootMenu();
                break;
            default: {
                String errorMessage = "Неправильно выбран пункт меню. Время ошибки: " + LocalTime.now();
                Car.logWriter(errorMessage);
                menu();
            }
        }
    }

    public static void sedanCreation(){
        Scanner scanner = new Scanner(System.in);
        String mark;
        int maxSpeed = 0;
        String currentStation;
        boolean isOn;
        int currentSpeed;
        System.out.println("Введите марку машины: ");
        mark = scanner.nextLine();
        System.out.println("Введите название радиостанции: ");
        currentStation = scanner.nextLine();
        System.out.println("Введите максимальную скорость машины: ");
        try {
            maxSpeed = scanner.nextInt();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена максимальная скорость. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }
        System.out.println("Включено ли радио?: ");
        try {
            isOn = scanner.nextBoolean();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена информация о состоянии радио. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }

        System.out.println("Введите текущую скорость скорость машины: ");
        try {
            currentSpeed = scanner.nextInt();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена текущая скорость. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }

        Sedan sedan = new Sedan(mark,maxSpeed,new Radio(currentStation,isOn),currentSpeed);
        sedanDemonstration(sedan);
    }

    public static void sedanDemonstration(Sedan sedan){
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Выберите метод класса Sedan: ");
        System.out.println("1: Движение.");
        System.out.println("2: Узнать марку.");
        System.out.println("3: Узнать максимальную скорость.");
        System.out.println("4: Узнать текущую радиостанцию.");
        System.out.println("5: Узнать включено ли радио.");
        System.out.println("6: Узнать текущую скорость.");
        System.out.println("7: Записать данные об объекте в файл.");
        System.out.println("8: Вернуться в главное меню.");

        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                sedan.move(sedan.currentSpeed);
                sleep();
                sedanDemonstration(sedan);
            }
            case 2 -> {
                System.out.println(sedan.getMark());
                sleep();
                sedanDemonstration(sedan);
            }
            case 3 -> {
                System.out.println(sedan.getMaxSpeed());
                sleep();
                sedanDemonstration(sedan);
            }
            case 4 -> {
                System.out.println(sedan.radio.currentStation);
                sleep();
                sedanDemonstration(sedan);
            }
            case 5 -> {
                System.out.println(sedan.radio.isOn);
                sleep();
                sedanDemonstration(sedan);
            }
            case 6 -> {
                System.out.println(sedan.getCurrentSpeed());
                sleep();
                sedanDemonstration(sedan);
            }
            case 7 -> {
                {
                    String path;
                    System.out.println("Введите название файла: ");
                    path = scanner.next();
                    sedan.toFile(path,prop.getProperty("isLogNeeded"));
                    sleep();
                }
                sedanDemonstration(sedan);
            }
            case 8 -> {
                if(userGroup.equals("root")) rootMenu();
                else menu();
            }
            default -> sedanDemonstration(sedan);
        }
    }

    public static void truckCreation(){
        Scanner scanner = new Scanner(System.in);
        String mark;
        int maxSpeed;
        String currentStation;
        boolean isOn;
        int currentSpeed;
        int weight;
        double carcaseHeight;
        System.out.println("Введите марку машины: ");
        mark = scanner.nextLine();
        System.out.println("Введите название радиостанции: ");
        currentStation = scanner.nextLine();
        System.out.println("Введите максимальную скорость машины: ");
        try {
            maxSpeed = scanner.nextInt();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена максимальная скорость. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }
        System.out.println("Включено ли радио?: ");
        try {
            isOn = scanner.nextBoolean();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена информация о состоянии радио. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }

        System.out.println("Введите текущую скорость скорость машины: ");
        try {
            currentSpeed = scanner.nextInt();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена текущая скорость. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }
        System.out.println("Введите вес машины: ");
        try {
            weight = scanner.nextInt();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введен вес грузовика. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }
        System.out.println("Введите высоту кузова");
        try {
            carcaseHeight = scanner.nextDouble();
        }
        catch (Exception e){
            String errorMessage = "Неправильно введена высота кузова грузовика. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            return;
        }
        Truck truck = new Truck(mark,maxSpeed,new Radio(currentStation,isOn),currentSpeed,weight,carcaseHeight);
        truckDemonstration(truck);
    }

    public static void truckDemonstration(Truck truck){
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Выберите метод класса Sedan: ");
        System.out.println("1: Движение.");
        System.out.println("2: Узнать марку.");
        System.out.println("3: Узнать максимальную скорость.");
        System.out.println("4: Узнать текущую радиостанцию.");
        System.out.println("5: Узнать включено ли радио.");
        System.out.println("6: Узнать текущую скорость.");
        System.out.println("7: Узнать вес машины.");
        System.out.println("8: Узнать высоту кузова.");
        System.out.println("9: Записать данные об объекте в файл.");
        System.out.println("10: Вернуться в главное меню.");

        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                truck.move(truck.currentSpeed);
                sleep();
                truckDemonstration(truck);
            }
            case 2 -> {
                System.out.println(truck.getMark());
                sleep();
                truckDemonstration(truck);
            }
            case 3 -> {
                System.out.println(truck.getMaxSpeed());
                sleep();
                truckDemonstration(truck);
            }
            case 4 -> {
                System.out.println(truck.radio.currentStation);
                sleep();
                truckDemonstration(truck);
            }
            case 5 -> {
                System.out.println(truck.radio.isOn);
                sleep();
                truckDemonstration(truck);
            }
            case 6 -> {
                System.out.println(truck.getCurrentSpeed());
                sleep();
                truckDemonstration(truck);
            }
            case 7 -> {
                System.out.println(truck.getWeight());
                sleep();
                truckDemonstration(truck);
            }
            case 8 -> {
                System.out.println(truck.getCarcaseHeight());
                sleep();
                truckDemonstration(truck);
            }
            case 9 -> {
                {
                    String path;
                    System.out.println("Введите название файла: ");
                    path = scanner.next();
                    truck.toFile(path,prop.getProperty("isLogNeeded"));
                    sleep();
                }
                truckDemonstration(truck);
            }
            case 10 -> {
                if(userGroup.equals("root")) rootMenu();
                else menu();
            }
            default -> truckDemonstration(truck);
        }
    }

    public static void dpsDemonstration(){
        ArrayList<Car> cars = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;
        cars.add(new Sedan("Volvo",200,new Radio("DriveFM",true),120));
        cars.add(new Sedan("Lada",200,new Radio("DriveFM",true),80));
        cars.add(new Truck("Kamaz",200,new Radio("DriveFM",true),60,1200,1.5));
        cars.add(new Truck("Tesla",200,new Radio("DriveFM",true),70,900,1.3));
        cars.add(new Truck("Very high truck",200,new Radio("DriveFM",true),70,900,2.6));
        System.out.println("Выберите  для проверки одну из машин со следующими значащами параметрами, или нажмите 0" +
                " для выхода в главное меню ");
        for (Car car: cars) {
            System.out.println((cars.indexOf(car) + 1) + ":");
            car.dpsRelatedParams();
        }
        choice = scanner.nextInt();
        switch (choice){
            case 0 -> menu();
            case 1 -> {
                DPS.pass(cars.get(0));
                sleep();
                dpsDemonstration();
            }
            case 2 -> {
                DPS.pass(cars.get(1));
                sleep();
                dpsDemonstration();
            }
            case 3 -> {
                DPS.pass(cars.get(2));
                sleep();
                dpsDemonstration();
            }
            case 4 -> {
                DPS.pass(cars.get(3));
                sleep();
                dpsDemonstration();
            }
            case 5 -> {
                DPS.pass(cars.get(4));
                sleep();
                dpsDemonstration();
            }
        }

    }

    public static void sleep(){
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void logStart(String login){
        if(!logging) return;
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write("Программа запущена пользователем " + login  + " в " + LocalTime.now());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public static void logEnd(String login){
        if(!logging) return;
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write("Программа, запущенная пользователем " + login + " завершена в " + LocalTime.now());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }

    }

    public static void enterPassword(){
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.println("Введите пароль для получения прав root.");
        password = scanner.nextLine();
        if(!password.equals(prop.getProperty("password"))){
            String errorMessage = "Неудачная попытка получения прав root. Время ошибки: " + LocalTime.now();
            Car.logWriter(errorMessage);
            enterPassword();
        }
    }

    public static void switchDebugging(){
        //prop.setProperty("isLogNeeded","true");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(PATH_TO_PROPERTIES);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(PATH_TO_PROPERTIES);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();

        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(logging) {
            Main.logging = false;
            props.setProperty("isLogNeeded", "false");
            props.setProperty("login", "Semyon");
            props.setProperty("password", "javaLabs");
            props.setProperty("userGroup", "root");
            System.out.println("Запись логов отключена");
        }
        else {
            Main.logging = true;
            props.setProperty("isLogNeeded", "true");
            props.setProperty("login", "Semyon");
            props.setProperty("password", "javaLabs");
            props.setProperty("userGroup", "root");
        }
        try {
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Car.logWriter("Запись логов включена");
        rootMenu();
    }

    public static void deleteFromDb(){
        Scanner scanner = new Scanner(System.in);
        String path;
        int index;
        System.out.println("Введите название файла");
        path = scanner.nextLine();
        System.out.println("Введите индекс элемента который необходимо удалить.");
        index = scanner.nextInt();
        Car.deleteObject(path,index-1);
        Car.logWriter("Из файла " + path + " удален элемент. " + " Время: " + LocalTime.now());
    }

    public static void showDb(){
        Scanner scanner = new Scanner(System.in);
        String path;
        System.out.println("Введите название файла");
        path = scanner.nextLine();
        ArrayList<Car> cars = Car.fromFile(path);
        for (Car car: cars){
            System.out.println(car.fileWriter());
        }
        sleep();
    }

    public static void changeDb(){
        Scanner scanner = new Scanner(System.in);
        String path;
        System.out.println("Введите название файла");
        path = scanner.nextLine();
        ArrayList<Car> cars = Car.fromFile(path);
        int index;
        System.out.println("Введите индекс измняемого элемента");
        index= scanner.nextInt();
        ArrayList<Car> carsNew = Car.changeObject(cars,index);
        String logs;
        if(logging){
            logs = "true";
        }
        else logs="false";
        File file = new File(path);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
            for (Car obj: carsNew){
                obj.toFile(path,logs);
            }
            Car.logWriter("Внесены изменения в файл " + path + " Время: " + LocalTime.now());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
*/


}

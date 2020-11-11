
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;


public class Main {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    public static Properties prop = new Properties();
    public static FileInputStream fileInputStream;




    public static void main(String[] args) {
        final Logger Log = Logger.getLogger(Main.class.getName());



//		volvo.toFile("file.txt");
//		kamaz.toFile("file.txt");

//		ArrayList<Car> cars = Car.fromFile("file.txt");
//
//		System.out.println(cars.get(3).fileWriter());

        menu();

    }

    public static void menu(){
//        Properties prop = new Properties();
//        FileInputStream fileInputStream;
        String login="";
        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            login = prop.getProperty("login");

            //печатаем полученные данные в консоль

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Добро пожаловать," + login + "\nВыберите действие");
        System.out.println("1: Демонстрация работы класса Sedan");
        System.out.println("2: Демонстрация работы класса Truck");
        System.out.println("3: Демонстрация работы класса DPS");
        choice = scanner.nextInt();
        switch (choice){
            case 1: sedanCreation();break;
            case 2: truckCreation();break;
            case 3: dpsDemonstration();break;
        }
    }

    public static void sedanCreation(){
        Scanner scanner = new Scanner(System.in);
        String mark;
        int maxSpeed;
        String currentStation;
        boolean isOn;
        int currentSpeed;
        System.out.println("Введите марку машины: ");
        mark = scanner.nextLine();
        System.out.println("Введите название радиостанции: ");
        currentStation = scanner.nextLine();
        System.out.println("Введите максимальную скорость машины: ");
        maxSpeed = scanner.nextInt();
        System.out.println("Включено ли радио?: ");
        isOn = scanner.nextBoolean();
        System.out.println("Введите текущую скорость скорость машины: ");
        currentSpeed = scanner.nextInt();
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
            case 8 -> menu();
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
        maxSpeed = scanner.nextInt();
        System.out.println("Включено ли радио?: ");
        isOn = scanner.nextBoolean();
        System.out.println("Введите текущую скорость скорость машины: ");
        currentSpeed = scanner.nextInt();
        System.out.println("Введите вес машины: ");
        weight = scanner.nextInt();
        System.out.println("Введите высоту кузова");
        carcaseHeight = scanner.nextDouble();
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
            case 10 -> menu();
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
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


}

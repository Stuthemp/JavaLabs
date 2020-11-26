import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private static final Logger Log = Logger.getLogger(Main.class.getName());

    public  static ArrayList<Car> fromFile(String Path){
        //Log.log(Level.INFO, "Произведено чтение из файла {0} в {1}", new Object[] {Path, LocalTime.now()});
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
                //System.out.println(line);
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

    public  static ArrayList<Car> deleteObject(String Path, int index){
        ArrayList<Car> cars = new ArrayList<>();
        try {
            File file = new File(Path);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            //System.out.println("Перед while");
            while (line != null) {
                //System.out.println(line);
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
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        cars.remove(index);

        for (Car car: cars) {
            if(car instanceof Sedan)
                ((Sedan)car).toFile(Path,Boolean.toString(Main.logging));
            else ((Truck)car).toFile(Path,Boolean.toString(Main.logging));
        }
        return cars;
    }

    public  static ArrayList<Car> changeObject(ArrayList<Car> cars, int index){
        Car car = cars.get(index);
        if(car instanceof Sedan){
            Scanner scanner = new Scanner(System.in);
            int choice;
            System.out.println("Выберите поле которое необходимо изменить: ");
            System.out.println("1: Текущую скорость.");
            System.out.println("2: Марку.");
            System.out.println("3: Максимальную скорость.");
            System.out.println("4: Текущую радиостанцию.");
            System.out.println("5: Состояние радио.");

            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите новое значение");
                    int speed;
                    speed = scanner.nextInt();
                    car.setCurrentSpeed(speed);
                }
                case 2 -> {
                    System.out.println("Введите новое значение");
                    String mark;
                    mark = scanner.next();
                    car.setMark(mark);
                }
                case 3 -> {
                    System.out.println("Введите новое значение");
                    int speed;
                    speed = scanner.nextInt();
                    car.setMaxSpeed(speed);
                }
                case 4 -> {
                    System.out.println("Введите новое значение");
                    String station;
                    station = scanner.nextLine();
                    car.setRadioStation(station);
                }
                case 5 -> {
                    System.out.println("Введите новое значение");
                    boolean bool;
                    bool = scanner.nextBoolean();
                    car.setRadioState(bool);
                }
                default -> changeObject(cars,index);
            }
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int choice;
            System.out.println("Выберите поле которое необходимо изменить: ");
            System.out.println("1: Текущую скорость.");
            System.out.println("2: Марку.");
            System.out.println("3: Максимальную скорость.");
            System.out.println("4: Текущую радиостанцию.");
            System.out.println("5: Состояние радио.");
            System.out.println("6: Вес машины.");
            System.out.println("7: Высоту кузова.");

            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите новое значение");
                    int speed;
                    speed = scanner.nextInt();
                    car.setCurrentSpeed(speed);
                }
                case 2 -> {
                    System.out.println("Введите новое значение");
                    String mark;
                    mark = scanner.nextLine();
                    car.setMark(mark);
                }
                case 3 -> {
                    System.out.println("Введите новое значение");
                    int speed;
                    speed = scanner.nextInt();
                    car.setMaxSpeed(speed);
                }
                case 4 -> {
                    System.out.println("Введите новое значение");
                    String station;
                    station = scanner.nextLine();
                    car.setRadioStation(station);
                }
                case 5 -> {
                    System.out.println("Введите новое значение");
                    boolean bool;
                    bool = scanner.nextBoolean();
                    car.setRadioState(bool);
                }
                case 6 -> {
                    System.out.println("Введите новое значение");
                    int weight;
                    weight = scanner.nextInt();
                    ((Truck)car).setWeight(weight);
                }
                case 7 -> {
                    System.out.println("Введите новое значение");
                    double height;
                    height = scanner.nextDouble();
                    ((Truck)car).setCarcaseHeight(height);
                }
                default -> changeObject(cars,index);
            }
        }
        cars.remove(index);
        cars.add(index,car);

        return cars;
    }

    public static void logWriter(String message){
        if(!Model.logging) return;
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("logs.txt",true), StandardCharsets.UTF_8)
        ){
            writer.write(System.lineSeparator());
            writer.write(message);
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Log.log(Level.SEVERE, "Ошибка вывода!", e);
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

    public static ArrayList<Sedan> arrayListCreation(int size){
        ArrayList<Sedan> sedanArrayList = new ArrayList<>();
        String logSeparator = "--------------------------------------------------------------------------";
        logWriter(logSeparator);
        String[] marks = {"Volvo", "Nissan", "Lada", "BMW", "Subaru", "Toyota"};
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {

            long internalStart = System.currentTimeMillis();

            int mark = (int)(Math.random()*6);
            int maxSpeed = (int)((Math.random()*((300-100)+1))+100);
            int currentSpeed = (int)(Math.random()*maxSpeed);
            sedanArrayList.add(new Sedan(marks[mark],maxSpeed,new Radio(),currentSpeed));

            long internalEnd = System.currentTimeMillis();

            String msg = "add LinkedList, ID=" + i + ", " + (internalEnd - internalStart);
            logWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size;
        logWriter(count);
        String medianTime = "addArrayListMedianTime=" + (double)timeTaken/size;
        logWriter(medianTime);
        String totalTime = "addArrayListTotalTime=" + timeTaken;
        logWriter(totalTime);
        return sedanArrayList;
    }

    public static ArrayList<Sedan> arrayListClean(ArrayList<Sedan> sedans){
        String logSeparator = "--------------------------------------------------------------------------";
        logWriter(logSeparator);
        int size = sedans.size();
        long start = System.currentTimeMillis();
        for (int i = 0; i < sedans.size()/10; i++) {

            long internalStart = System.currentTimeMillis();

            int index = (int)(Math.random()*sedans.size());
           // System.out.println(index);

            sedans.remove(index);

            long internalEnd = System.currentTimeMillis();

            String msg = "remove ArrayList, ID=" + index + ", " + (internalEnd - internalStart);
            logWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size/10;
        logWriter(count);
        String medianTime = "removeArrayListMedianTime=" + (double)timeTaken/size/10;
        logWriter(medianTime);
        String totalTime = "removeArrayListTotalTime=" + timeTaken;
        logWriter(totalTime);
        return sedans;
    }

    public static LinkedList<Sedan> linkedListCreation(int size){
        LinkedList<Sedan> sedanArrayList = new LinkedList<>();
        String logSeparator = "--------------------------------------------------------------------------";
        logWriter(logSeparator);
        String[] marks = {"Volvo", "Nissan", "Lada", "BMW", "Subaru", "Toyota"};
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {

            long internalStart = System.currentTimeMillis();

            int mark = (int)(Math.random()*6);
            int maxSpeed = (int)((Math.random()*((300-100)+1))+100);
            int currentSpeed = (int)(Math.random()*maxSpeed);
            sedanArrayList.add(new Sedan(marks[mark],maxSpeed,new Radio(),currentSpeed));

            long internalEnd = System.currentTimeMillis();

            String msg = "add LinkedList, ID=" + i + ", " + (internalEnd - internalStart);
            logWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size;
        logWriter(count);
        String medianTime = "addLinkedListMedianTime=" + (double)timeTaken/size;
        logWriter(medianTime);
        String totalTime = "addLinkedListTotalTime=" + timeTaken;
        logWriter(totalTime);
        return sedanArrayList;
    }

    public static LinkedList<Sedan> linkedListClean(LinkedList<Sedan> sedans){
        String logSeparator = "--------------------------------------------------------------------------";
        logWriter(logSeparator);
        int size = sedans.size();
        long start = System.currentTimeMillis();
        for (int i = 0; i < sedans.size()/10; i++) {

            long internalStart = System.currentTimeMillis();

            int index = (int)(Math.random()*sedans.size());
            // System.out.println(index);

            sedans.remove(index);

            long internalEnd = System.currentTimeMillis();

            String msg = "remove ArrayList, ID=" + index + ", " + (internalEnd - internalStart);
            logWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size/10;
        logWriter(count);
        String medianTime = "removeLinkedListMedianTime=" + (double)timeTaken/size/10;
        logWriter(medianTime);
        String totalTime = "removeLinkedListTotalTime=" + timeTaken;
        logWriter(totalTime);
        return sedans;
    }
}

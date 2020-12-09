import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.spec.ECField;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Выполняет часть логики по записи в файлы
 */

//check1
    //check2
public class Controller {


    public static void toFile(String Path,String text){
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(Path,true), StandardCharsets.UTF_8)
        ){
            ExceptionHandler.logWriter(text);
            String message = "Произвдена запись в файл " + Path + " в " + LocalTime.now();
            ExceptionHandler.logWriter(message);
            writer.write(text);
            //writer.write(System.lineSeparator());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            ExceptionHandler.addErr(e,"Ошибка записи в файл");
        }
    }

    /**
     * Читаем данные из файла с БД
     * @param Path - файл откуда мы читаем данные
     * @return - возрващаем прочитанный массив
     */
    public  static ArrayList<Car> fromFile(String Path){
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
            ExceptionHandler.logWriter("Произведено чтение из фала " + Path + " в " + LocalTime.now());
        } catch (FileNotFoundException e) {
            ExceptionHandler.addErr(e,"файл не найден");
        } catch (IOException e) {
            ExceptionHandler.addErr(e,"Проблемы с операцией ввода-вывода");
        }
        return cars;
    }

    /**
     * Удаляем объект их файла базы данных
     * @param Path - путь до файла БД откуда удаляем объект
     * @param index - индекс объекта который будем удалять
     * @return - возвращаем измененную коллекцию
     */
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
            ExceptionHandler.logWriter("Удален объект из файла "+ Path + " в " + LocalTime.now());
        } catch (FileNotFoundException e) {
            ExceptionHandler.addErr(e,"Файл не анйден");
        } catch (IOException e) {
            ExceptionHandler.addErr(e,e.getMessage());
        }


        cars.remove(index);

        for (Car car: cars) {
            Controller.toFile(Path,car.fileWriter());
        }
        return cars;
    }

    /**
     * Меняем объект в переданной коллекции
     * @param cars - коллекция объектов, в которой будем проводить изменения
     * @param index - индекс изменяемого элемента
     * @return -  возвращаем измененный массив
     */
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

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                String errorMessage = "Неверный выбор пункта меню. Время ошибки: " + LocalTime.now();
                ExceptionHandler.addErr(e,errorMessage);
                System.out.println("Введена не цифра.");
                changeObject(cars,index);
                return cars;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите новое значение");
                    int speed;
                    try {
                        speed = scanner.nextInt();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена скорость. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена максимальная скорость.");
                        changeObject(cars,index);
                        return cars;
                    }
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
                    try {
                        speed = scanner.nextInt();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена скорость. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена максимальная скорость.");
                        changeObject(cars,index);
                        return cars;
                    }
                    car.setMaxSpeed(speed);
                }
                case 4 -> {
                    System.out.println("Введите новое значение");
                    String station;
                    station = scanner.next();
                    car.setRadioStation(station);
                }
                case 5 -> {
                    System.out.println("Введите новое значение");
                    boolean bool;
                    try {
                        bool = scanner.nextBoolean();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена информация о состоянии радио. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена информация о состоянии радио");
                        changeObject(cars,index);
                        return cars;
                    }
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
                    try {
                        speed = scanner.nextInt();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена скорость. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена максимальная скорость.");
                        changeObject(cars,index);
                        return cars;
                    }
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
                    try {
                        speed = scanner.nextInt();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена скорость. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена максимальная скорость.");
                        changeObject(cars,index);
                        return cars;
                    }
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
                    try {
                        bool = scanner.nextBoolean();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена информация о состоянии радио. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена информация о состоянии радио");
                        changeObject(cars,index);
                        return cars;
                    }
                    car.setRadioState(bool);
                }
                case 6 -> {
                    System.out.println("Введите новое значение");
                    int weight;
                    try {
                        weight = scanner.nextInt();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введен вес грузовика. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введен вес грузовика.");
                        changeObject(cars,index);
                        return cars;
                    }
                    ((Truck)car).setWeight(weight);
                }
                case 7 -> {
                    System.out.println("Введите новое значение");
                    double height;
                    try {
                        height = scanner.nextDouble();
                    } catch (Exception e) {
                        String errorMessage = "Неправильно введена высота кузова грузовика. Время ошибки: " + LocalTime.now();
                        ExceptionHandler.addErr(e,errorMessage);
                        System.out.println("Неправильно введена высота кузова грузовика.");
                        changeObject(cars,index);
                        return cars;
                    }
                    ((Truck)car).setCarcaseHeight(height);
                }
                default -> changeObject(cars,index);
            }
        }
        cars.remove(index);
        cars.add(index,car);

        return cars;
    }

}

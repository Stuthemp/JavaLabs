import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;

/**
 * Обеспчивает взаимодействие пользователя с программой
 */

//check1
    //check2
@SuppressWarnings("serial")
public class View extends JFrame {


    public View(String name, boolean hora) {
        super(name);
        if(hora)//true = add
            Model.y= SedanArray.YsAdd;
        else
            Model.y= SedanArray.YsRem;
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComponent(), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize(600, 650);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /**
     * Меню  для пользователя с root правами, включает в себя дополнительный функционал: включение/отключение логов,
     * запуск автотестов. Для доступа к этому меню неоюходимо верно ввести пароль
     */

    public static void rootMenu() {


        Scanner scanner = new Scanner(System.in);

        int choice;
        System.out.println("Добро пожаловать," + Model.login + "\nВыберите действие");
        System.out.println("1: Демонстрация работы класса Sedan");
        System.out.println("2: Демонстрация работы класса Truck");
        System.out.println("3: Демонстрация работы класса DPS");
        System.out.println("4: Завершить программу.");
        System.out.println("5: Переключить режим отладки.");
        System.out.println("6: Запустить автотесты.");
        System.out.println("7: Продемонстрировать содержание файла.");
        System.out.println("8: Удалить элемент из файла.");
        System.out.println("9: Изменить элемент в файле.");
        System.out.println("10: Логи ArrayList.");
        System.out.println("11: Логи LinkedList.");
        System.out.println("12: Нарисовать графики.");

        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            String errorMessage = "Неверный выбор пункта меню. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Введена не цифра.");
            rootMenu();
            return;
        }
        switch (choice) {
            case 1:
                sedanCreation();
                break;
            case 2:
                truckCreation();
                break;
            case 3:
                dpsDemonstration();
                break;
            case 4:
                ExceptionHandler.logEnd(Model.login);
                break;
            case 5:
                switchDebugging();
                break;
            case 6:
                System.out.println("Автотесты активно идут...");
                sleep();
                rootMenu();
                break;

            case 7:
                showDb();
                rootMenu();
                break;
            case 8:
                deleteFromDb();
                rootMenu();
                break;
            case 9:
                changeDb();
                rootMenu();
                break;
            case 10:
                arrayListDemonstration();
                rootMenu();
                break;
            case 11:
                linkedListDemonstration();
                rootMenu();
                break;
            case 12:
                draw();
                rootMenu();
                break;
            default: {
                String errorMessage = "Неправильно выбран пункт меню. Время ошибки: " + LocalTime.now();
                System.out.println("Введено неверное значение, попробуйте снова");
                ExceptionHandler.logWriter(errorMessage);
                rootMenu();
            }
        }
    }

    /**
     * Обычное меню, предоставляющие классический функционал
     */
    public static void menu() {

        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Добро пожаловать," + Model.login + "\nВыберите действие");
        System.out.println("1: Демонстрация работы класса Sedan");
        System.out.println("2: Демонстрация работы класса Truck");
        System.out.println("3: Демонстрация работы класса DPS");
        System.out.println("4: Завершить программу.");
        System.out.println("5: Продемонстрировать содержание файла.");
        System.out.println("6: Удалить элемент из файла.");
        System.out.println("7: Изменить элемент в файле.");
        System.out.println("8: Вызов методов для ArrayList.");
        System.out.println("9: Вызов методов для LinkedList.");
        System.out.println("10: Нарисовать графики.");
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            String errorMessage = "Неверный выбор пункта меню. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Введена не цифра.");
            menu();
            return;
        }
        switch (choice) {
            case 1:
                sedanCreation();
                break;
            case 2:
                truckCreation();
                break;
            case 3:
                dpsDemonstration();
                break;
            case 4:
                ExceptionHandler.logEnd(Model.login);
                break;
            case 5:
                showDb();
                rootMenu();
                break;
            case 6:
                deleteFromDb();
                rootMenu();
                break;
            case 7:
                changeDb();
                rootMenu();
                break;
            case 8:
                arrayListDemonstration();
                menu();
                break;
            case 9:
                linkedListDemonstration();
                menu();
                break;
            case 10:
                draw();
                menu();
                break;
            default: {
                String errorMessage = "Неправильно выбран пункт меню. Время ошибки: " + LocalTime.now();
                System.out.println("Введено неверное значение, попробуйте снова");
                ExceptionHandler.logWriter(errorMessage);
                menu();
            }
        }
    }

    /**
     * Создание объекта класса Sedan, данные для создания вводит пользователь.
     * После создания объекта, вызывается метод для его демонстрации
     */
    public static void sedanCreation() {
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
        try {
            maxSpeed = scanner.nextInt();
        } catch (Exception e) {
            String errorMessage = "Неправильно введена максимальная скорость. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена максимальная скорость.");
            sedanCreation();
            return;
        }
        System.out.println("Включено ли радио?: ");
        try {
            isOn = scanner.nextBoolean();
        } catch (Exception e) {
            String errorMessage = "Неправильно введена информация о состоянии радио. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена информация о состоянии радио");
            sedanCreation();
            return;
        }

        System.out.println("Введите текущую скорость скорость машины: ");
        try {
            currentSpeed = scanner.nextInt();
        } catch (Exception e) {
            String errorMessage = "Неправильно введена текущая скорость. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена текущая скорость.");
            sedanCreation();
            return;
        }

        Sedan sedan = new Sedan(mark, maxSpeed, new Radio(currentStation, isOn), currentSpeed);
        sedanDemonstration(sedan);
    }

    /**
     * Демонстрация функционала класса Sedan
     * @param sedan - объект, на котором будут вызываться функции демонстрации
     */
    public static void sedanDemonstration(Sedan sedan) {
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
                    Controller.toFile(path,sedan.fileWriter());
                    sleep();
                }
                sedanDemonstration(sedan);
            }
            case 8 -> {
                if (Model.userGroup.equals("root")) rootMenu();
                else menu();
            }
            default -> {
                System.out.println("Неправильно выбран пункт меню");
                sedanDemonstration(sedan);
            }
        }
    }

    /**
     * Создание объекта класса Truck, данные для создания вводит пользователь.
     * После создания объекта, вызывается метод для его демонстрации
     */
    public static void truckCreation() {
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
        } catch (Exception e) {
            String errorMessage = "Неправильно введена максимальная скорость. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена максимальная скорость.");
            truckCreation();
            return;
        }
        System.out.println("Включено ли радио?: ");
        try {
            isOn = scanner.nextBoolean();
        } catch (Exception e) {
            String errorMessage = "Неправильно введена информация о состоянии радио. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена информация о состоянии радио.");
            truckCreation();
            return;
        }

        System.out.println("Введите текущую скорость скорость машины: ");
        try {
            currentSpeed = scanner.nextInt();
        } catch (Exception e) {
            String errorMessage = "Неправильно введена текущая скорость. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена текущая скорость.");
            truckCreation();
            return;
        }
        System.out.println("Введите вес машины: ");
        try {
            weight = scanner.nextInt();
        } catch (Exception e) {
            String errorMessage = "Неправильно введен вес грузовика. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введен вес грузовика.");
            truckCreation();
            return;
        }
        System.out.println("Введите высоту кузова");
        try {
            carcaseHeight = scanner.nextDouble();
        } catch (Exception e) {
            String errorMessage = "Неправильно введена высота кузова грузовика. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введена высота кузова грузовика.");
            truckCreation();
            return;
        }
        Truck truck = new Truck(mark, maxSpeed, new Radio(currentStation, isOn), currentSpeed, weight, carcaseHeight);
        truckDemonstration(truck);
    }

    /**
     * Демонстрация функционала класса Truck
     * @param truck- объект, на котором будут вызываться функции демонстрации
     */
    public static void truckDemonstration(Truck truck) {
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
                    Controller.toFile(path,truck.fileWriter());
                    sleep();
                }
                truckDemonstration(truck);
            }
            case 10 -> {
                if (Model.userGroup.equals("root")) rootMenu();
                else menu();
            }
            default -> {
                System.out.println("Неправльно выбран пункт меню.");
                truckDemonstration(truck);
            }
        }
    }

    /**
     * Демонстрация работы класса DPS.
     * Интересного мало, объекты создаются автоматически, параметры выбираются специально для того,
     * чтобы проверка давала разный результат
     */
    public static void dpsDemonstration() {
        ArrayList<Car> cars = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;
        cars.add(new Sedan("Volvo", 200, new Radio("DriveFM", true), 120));
        cars.add(new Sedan("Lada", 200, new Radio("DriveFM", true), 80));
        cars.add(new Truck("Kamaz", 200, new Radio("DriveFM", true), 60, 1200, 1.5));
        cars.add(new Truck("Tesla", 200, new Radio("DriveFM", true), 70, 900, 1.3));
        cars.add(new Truck("Very high truck", 200, new Radio("DriveFM", true), 70, 900, 2.6));
        System.out.println("Выберите  для проверки одну из машин со следующими значащами параметрами, или нажмите 0" +
                " для выхода в главное меню ");
        for (Car car : cars) {
            System.out.println((cars.indexOf(car) + 1) + ":");
            car.dpsRelatedParams();
        }
        choice = scanner.nextInt();
        switch (choice) {
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
            default -> {
                System.out.println("Неправильно выбран пункт меню.");
                dpsDemonstration();
            }
        }

    }

    /**
     * Создан по причине того, что я забыл что Thread.sleep() существует.
     * Нужен для того чтобы вывод в консоль было удобнее читать
     */
    public static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Проверка правильности пароля, если устанволен root пользователь
     * при неправильном вводе будет бесконечно предлагать ввести пароль
     */
    public static void enterPassword() {
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.println("Введите пароль для получения прав root.");
        password = scanner.nextLine();
        if (!password.equals(Model.prop.getProperty("password"))) {
            String errorMessage = "Неудачная попытка получения прав root. Время ошибки: " + LocalTime.now();
            ExceptionHandler.logWriter(errorMessage);
            enterPassword();
        }
    }

    /**
     * Включает отладку, если она выключена, и наоборот.
     */
    public static void switchDebugging() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Model.PATH_TO_PROPERTIES);
        } catch (FileNotFoundException e) {
            ExceptionHandler.addErr(e,"Файл не найден");
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(Model.PATH_TO_PROPERTIES);
        } catch (FileNotFoundException e) {
            ExceptionHandler.addErr(e,"Файл не найден");
        }
        Properties props = new Properties();

        try {
            props.load(in);
        } catch (IOException e) {
            ExceptionHandler.addErr(e,"Ошибка ввода-вывода");
        }
        try {
            in.close();
        } catch (IOException e) {
            ExceptionHandler.addErr(e,"Ошибка ввода-вывода");
        }
        if (Model.logging) {
            Model.logging = false;
            props.setProperty("isLogNeeded", "false");
            props.setProperty("login", "Semyon");
            props.setProperty("password", "javaLabs");
            props.setProperty("userGroup", "root");
            System.out.println("Запись логов отключена");
        } else {
            Model.logging = true;
            props.setProperty("isLogNeeded", "true");
            props.setProperty("login", "Semyon");
            props.setProperty("password", "javaLabs");
            props.setProperty("userGroup", "root");
        }
        try {
            props.store(out, null);
        } catch (IOException e) {
            ExceptionHandler.addErr(e,"Ошибка ввода-вывода");
        }
        try {
            out.close();
        } catch (IOException e) {
            ExceptionHandler.addErr(e,"Ошибка ввода-вывода");
        }
        ExceptionHandler.logWriter("Запись логов включена");
        rootMenu();
    }

    /**
     * Вызывает диалог с пользователем,для измененя объекта в базе данных, самум логику изменения выполняет
     * Controller.deleteObject();
     */
    public static void deleteFromDb() {
        Scanner scanner = new Scanner(System.in);
        String path;
        int index;
        System.out.println("Введите название файла");
        try {
            path = scanner.nextLine();
        } catch (Exception e) {
            String errorMessage = "Неправильно введено название файла. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введено название файла.");
            deleteFromDb();
            return;
        }

        System.out.println("Введите индекс элемента который необходимо удалить.");
        index = scanner.nextInt();

        try {
            Controller.deleteObject(path, index - 1);
        } catch (Exception e) {
            String errorMessage = "Неправильно введен индекс. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введен индекс.");
            deleteFromDb();
            return;
        }
        ExceptionHandler.logWriter("Из файла " + path + " удален элемент. " + " Время: " + LocalTime.now());
    }

    /**
     * Демонстрирует объекты из быза данных
     */
    public static void showDb() {
        Scanner scanner = new Scanner(System.in);
        String path;
        System.out.println("Введите название файла");
        try {
            path = scanner.nextLine();
        } catch (Exception e) {
            String errorMessage = "Неправильно введено название файла. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введено название файла.");
            showDb();
            return;
        }
        ArrayList<Car> cars = Controller.fromFile(path);
        for (Car car : cars) {
            System.out.println(car.fileWriter());
        }
        sleep();
    }

    /**
     * Вызывает диалог с пользователем,для измененя объекта в базе данных, самум логику изменения выполняет
     * Controller.changeObject();
     */
    public static void changeDb() {
        Scanner scanner = new Scanner(System.in);
        String path;
        System.out.println("Введите название файла");
        try {
            path = scanner.nextLine();
        } catch (Exception e) {
            String errorMessage = "Неправильно введено название файла. Время ошибки: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Неправильно введено название файла.");
            changeDb();
            return;
        }
        ArrayList<Car> cars = Controller.fromFile(path);
        int index;
        System.out.println("Введите индекс измняемого элемента");
        index = scanner.nextInt();
        ArrayList<Car> carsNew = Controller.changeObject(cars, index - 1);
        File file = new File(path);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
            for (Car obj : carsNew) {
                Controller.toFile(path,obj.fileWriter());
            }
            ExceptionHandler.logWriter("Внесены изменения в файл " + path + " в " + LocalTime.now());
        } catch (FileNotFoundException e) {
            String errorMessage = "Ошибка при внесении данных в файл: " + LocalTime.now();
            ExceptionHandler.addErr(e,errorMessage);
            System.out.println("Ошибка при внесении данных в файл.");
            changeDb();
            return;
        }

    }

    /**
     * Запуск методов для измерения времени работы ArrayList
     */
    public static void arrayListDemonstration() {
        ArrayList<Sedan> ARRAY_LIST_SIZE_10 = ListsCount.arrayListCreation(10);
        ArrayList<Sedan> ARRAY_LIST_SIZE_100 = ListsCount.arrayListCreation(100);
        ArrayList<Sedan> ARRAY_LIST_SIZE_1000 = ListsCount.arrayListCreation(1000);
        ArrayList<Sedan> ARRAY_LIST_SIZE_10000 = ListsCount.arrayListCreation(10000);
        ArrayList<Sedan> ARRAY_LIST_SIZE_100000 = ListsCount.arrayListCreation(100000);
        ListsCount.arrayListClean(ARRAY_LIST_SIZE_10);
        ListsCount.arrayListClean(ARRAY_LIST_SIZE_100);
        ListsCount.arrayListClean(ARRAY_LIST_SIZE_1000);
        ListsCount.arrayListClean(ARRAY_LIST_SIZE_10000);
        ListsCount.arrayListClean(ARRAY_LIST_SIZE_100000);
    }

    /**
     * Запуск методов для измерения времени работы LinkedList
     */
    public static void linkedListDemonstration() {
        LinkedList<Sedan> LINKED_LIST_SIZE_10 = ListsCount.linkedListCreation(10);
        LinkedList<Sedan> LINKED_LIST_SIZE_100 = ListsCount.linkedListCreation(100);
        LinkedList<Sedan> LINKED_LIST_SIZE_1000 = ListsCount.linkedListCreation(1000);
        LinkedList<Sedan> LINKED_LIST_SIZE_10000 = ListsCount.linkedListCreation(10000);
        LinkedList<Sedan> LINKED_LIST_SIZE_100000 = ListsCount.linkedListCreation(100000);
        ListsCount.linkedListClean(LINKED_LIST_SIZE_10);
        ListsCount.linkedListClean(LINKED_LIST_SIZE_100);
        ListsCount.linkedListClean(LINKED_LIST_SIZE_1000);
        ListsCount.linkedListClean(LINKED_LIST_SIZE_10000);
        ListsCount.linkedListClean(LINKED_LIST_SIZE_100000);
    }

    /**
     *
     */
    public static void draw(){
        SedanArray С1 = new SedanArray(10);
        SedanArray С2 = new SedanArray(100);
        SedanArray С3 = new SedanArray(1000);
        SedanArray С4 = new SedanArray(10000);
        SedanArray С5 = new SedanArray(100000);
        SedanArray.toFile();
        SedanArray.fromFile();
        Scanner S = new Scanner(System.in);
            System.out.println("Построить график добавления (1) или удаления (2) элементов (введи 0 для выхода)?");

            int var = S.nextInt();
            switch(var)
            {
                case 0:
                    Model.Log.log(Level.INFO, "Завершение программы в {0}",LocalTime.now());
                    S.close();
                    return;
                case 1:
                    new View("График добавления элементов",true).setVisible(true);
                    break;
                case 2:
                    new View("График удаления элементов", false).setVisible(true);
                    break;
            }
    }
}


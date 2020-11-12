import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Model {


    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    public static Properties prop = new Properties();
    public static FileInputStream fileInputStream;
    public static final Logger Log = Logger.getLogger(Main.class.getName());
    static String login="";
    static String userGroup = "";
    static boolean logging;

    public static void startApp() {
        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream(Model.PATH_TO_PROPERTIES);
            Model.prop.load(fileInputStream);

            login = Model.prop.getProperty("login");
            userGroup = Model.prop.getProperty("userGroup");
            Model.prop.getProperty("isLogNeeded");

            //печатаем полученные данные в консоль

        } catch (
                IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
    }


}


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

        Model.startApp();

        Controller.logStart(Model.prop.getProperty("login"));
        if(Model.userGroup.equals("root"))
        {
            View.enterPassword();
            View.rootMenu();
        }
        else View.menu();

    }
}

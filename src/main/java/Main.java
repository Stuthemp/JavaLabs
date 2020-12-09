
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

/**
 * Класс запуска программы
 */

//check1
public class Main {

    public static void main(String[] args) {

        Model.startApp();

        ExceptionHandler.logStart(Model.prop.getProperty("login"));
        if(Model.userGroup.equals("root"))
        {
            View.enterPassword();
            View.rootMenu();
        }
        else View.menu();

    }
}

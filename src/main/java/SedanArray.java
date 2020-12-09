import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SedanArray {
    private void handle(){
        FileHandler handler1=null;
        FileHandler handler2=null;
        try {
            handler1 = new FileHandler("ArrayList"+Quantity+".log",true);
            handler2 = new FileHandler("LinkedList"+Quantity+".log",true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        handler1.setFormatter(new SimpleFormatter());
        handler2.setFormatter(new SimpleFormatter());
    }
    private final ArrayList<Sedan> ArrayListSedans;
    private final LinkedList<Sedan> LinkedListSedans;
    private final int Quantity;
    private static int yCount = 0;
    public static long[][] YsAdd = new long[2][5];
    public static long[][] YsRem = new long[2][5];
    public SedanArray(int Quantity){
        ArrayListSedans = new ArrayList<Sedan>(Quantity);
        LinkedListSedans = new LinkedList<Sedan>();
        this.Quantity=Quantity;
        this.handle();
        add();
        remove();
        yCount++;
    }
    private void add(){
        long time=0, allTime2=0, allTime1=0;
        for(int i = 0; i<Quantity; i++){
            Sedan New = RandomSedan();

            time = System.nanoTime();
            ArrayListSedans.add(New);
            time = System.nanoTime() - time;

            allTime1+=time;

            time = System.nanoTime();
            LinkedListSedans.add(New);
            time = System.nanoTime() - time;
            allTime2+=time;

        }

        YsAdd[0][yCount]=allTime1/Quantity;
        YsAdd[1][yCount]=allTime2/Quantity;

    }
    private void remove(){
        long time=0, allTime2=0, allTime1=0;
        int Perc = Quantity/10, j=(int) (Math.random()*Quantity);
        boolean[] isDeleted = new boolean[Quantity];
        for(int i = 0; i<Perc; i++){

            while(isDeleted[j])
                j = (int) (Math.random()*ArrayListSedans.size());

            isDeleted[j] = true;

            time = System.nanoTime();
            ArrayListSedans.remove(j);
            time = System.nanoTime() - time;
            allTime1+=time;

            time = System.nanoTime();
            LinkedListSedans.remove(j);
            time = System.nanoTime() - time;
            allTime2+=time;
        }

        YsRem[0][yCount]=allTime1/Quantity;

        YsRem[1][yCount]=allTime2/Quantity;
    }
    private Sedan RandomSedan(){
        String[] marks = {"Volvo", "Nissan", "Lada", "BMW", "Subaru", "Toyota"};

            int mark = (int)(Math.random()*6);
            int maxSpeed = (int)((Math.random()*((300-100)+1))+100);
            int currentSpeed = (int)(Math.random()*maxSpeed);
            return new Sedan(marks[mark],maxSpeed,new Radio(),currentSpeed);
    }
    public void resize(){
        int newSize = Quantity*3/2+1;
        ArrayListSedans.ensureCapacity(newSize);
        newSize-=Quantity;
        long time=0,allTime1=0;
        for(int i=0; i<newSize; i++){
            Sedan New = RandomSedan();

            time = System.nanoTime();
            ArrayListSedans.add(New);
            time = System.nanoTime() - time;
            allTime1+=time;
        }

    }
    public static void toFile(){
        try {
            Files.delete(Paths.get("Add.txt"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try(FileWriter writer = new FileWriter("Add.txt", true))
        {
            for(int i = 0; i<5; i++)
                writer.write(YsAdd[0][i]+" "+YsAdd[1][i]+System.lineSeparator());
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        try {
            Files.delete(Paths.get("Remove.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter("Remove.txt", true))
        {

            for(int i = 0; i<5; i++)
                writer.write(YsRem[0][i]+" "+YsRem[1][i]+System.lineSeparator());
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void fromFile(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("Add.txt"), StandardCharsets.UTF_8);
            int i = 0;
            for(String l : lines){
                String[] s = l.split(" ");
                YsAdd[0][i] = Long.parseLong(s[0]);
                YsAdd[1][i] = Long.parseLong(s[1]);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get("Remove.txt"), StandardCharsets.UTF_8);
            int i = 0;
            for(String l : lines){
                String[] s = l.split(" ");
                YsRem[0][i] = Long.parseLong(s[0]);
                YsRem[1][i] = Long.parseLong(s[1]);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

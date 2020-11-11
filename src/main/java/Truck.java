
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Truck extends Car {
    private int weight;
    private double carcaseHeight;
    private static final Logger Log =
            Logger.getLogger(Main.class.getName());

    public Truck(String mark, int maxSpeed, Radio radio, int currentSpeed, int weight, double carcaseHeight) {
        super(mark, maxSpeed, radio, currentSpeed);
        this.weight = weight;
        this.carcaseHeight = carcaseHeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getCarcaseHeight() {
        return carcaseHeight;
    }

    public void setCarcaseHeight(double carcaseHeight) {
        this.carcaseHeight = carcaseHeight;
    }

    @Override
    public void move(int currentSpeed) {
        System.out.println("Truck moves with the speed " + currentSpeed + " km/h.");
    }

    @Override
    public String fileWriter(){
        StringBuilder SB = new StringBuilder("");
        SB.append(this.mark).append(",");
        SB.append(this.maxSpeed).append(",");
        SB.append(this.radio.fileWriter()).append(",");
        SB.append(this.currentSpeed).append(",");
        SB.append(this.weight).append(",");
        SB.append(this.carcaseHeight);
        return SB.toString() + System.lineSeparator();
    }

    @Override
    public void toFile(String Path, String isLogNeeded){
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(Path,true), StandardCharsets.UTF_8)
        ){


            boolean doWeWriteLogs = isLogNeeded.equals("true");
            String message = "Произвдена запись в файл " + Path;
            if(doWeWriteLogs) logWriter(message);
            writer.write(this.fileWriter());
            //writer.write(System.lineSeparator());
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            Log.log(Level.SEVERE, "Ошибка вывода!", e);
        }
    }

    @Override
    public void dpsRelatedParams(){
        System.out.printf("Марка: %s\nТекущая скорость: %d\nВес: %d\nВысота кузова: %f\n",mark,currentSpeed,weight,carcaseHeight);
        System.out.println("-------------------------------------");
    }

}

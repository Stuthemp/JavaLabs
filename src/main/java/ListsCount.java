import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executor;

/**
 * все связанное с листами
 */

//check1
public class ListsCount {

    /**
     * Создаем ArrayList, заполненный указанным числом случайных элементов
     * @param size - число элемнтов,которые будут помещены в коллекцию
     * @return - созданная коллекция
     */
    public static ArrayList<Sedan> arrayListCreation(int size){
        ExceptionHandler.timingLogWriter("ArrayList");
        ArrayList<Sedan> sedanArrayList = new ArrayList<>();
        String[] marks = {"Volvo", "Nissan", "Lada", "BMW", "Subaru", "Toyota"};
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {

            long internalStart = System.currentTimeMillis();

            int mark = (int)(Math.random()*6);
            int maxSpeed = (int)((Math.random()*((300-100)+1))+100);
            int currentSpeed = (int)(Math.random()*maxSpeed);
            sedanArrayList.add(new Sedan(marks[mark],maxSpeed,new Radio(),currentSpeed));

            long internalEnd = System.currentTimeMillis();

            String msg = "add,ID=" + i + "," + (internalEnd - internalStart);
            ExceptionHandler.timingLogWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size;
        ExceptionHandler.timingLogWriter(count);
        String medianTime = "addArrayListMedianTime=" + (double)timeTaken/size;
        ExceptionHandler.timingLogWriter(medianTime);
        String totalTime = "addArrayListTotalTime=" + timeTaken;
        ExceptionHandler.timingLogWriter(totalTime);
        return sedanArrayList;
    }

    /**
     * В переданной коллекции удаляются случайные элементы
     * @param sedans - коллекция, в которую будут вноситься изменения
     * @return - измененная коллекция
     */
    public static ArrayList<Sedan> arrayListClean(ArrayList<Sedan> sedans){
        int size = sedans.size();
        long start = System.currentTimeMillis();
        for (int i = 0; i < sedans.size()/10; i++) {

            long internalStart = System.currentTimeMillis();

            int index = (int)(Math.random()*sedans.size());
            // System.out.println(index);

            sedans.remove(index);

            long internalEnd = System.currentTimeMillis();

            String msg = "remove,ID=" + index + "," + (internalEnd - internalStart);
            ExceptionHandler.timingLogWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size/10;
        ExceptionHandler.timingLogWriter(count);
        String medianTime = "removeArrayListMedianTime=" + (double)timeTaken/size/10;
        ExceptionHandler.timingLogWriter(medianTime);
        String totalTime = "removeArrayListTotalTime=" + timeTaken;
        ExceptionHandler.timingLogWriter(totalTime);
        return sedans;
    }

    /**
     * Создаем LinkedList, заполненный указанным числом случайных элементов
     * @param size - число элемнтов,которые будут помещены в коллекцию
     * @return - созданная коллекция
     */
    public static LinkedList<Sedan> linkedListCreation(int size){
        ExceptionHandler.timingLogWriter("LinkedList");
        LinkedList<Sedan> sedanArrayList = new LinkedList<>();
        String[] marks = {"Volvo", "Nissan", "Lada", "BMW", "Subaru", "Toyota"};
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {

            long internalStart = System.currentTimeMillis();

            int mark = (int)(Math.random()*6);
            int maxSpeed = (int)((Math.random()*((300-100)+1))+100);
            int currentSpeed = (int)(Math.random()*maxSpeed);
            sedanArrayList.add(new Sedan(marks[mark],maxSpeed,new Radio(),currentSpeed));

            long internalEnd = System.currentTimeMillis();

            String msg = "add,ID=" + i + "," + (internalEnd - internalStart);
            ExceptionHandler.timingLogWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size;
        ExceptionHandler.timingLogWriter(count);
        String medianTime = "addLinkedListMedianTime=" + (double)timeTaken/size;
        ExceptionHandler.timingLogWriter(medianTime);
        String totalTime = "addLinkedListTotalTime=" + timeTaken;
        ExceptionHandler.timingLogWriter(totalTime);
        return sedanArrayList;
    }

    /**
     * В переданной коллекции удаляются случайные элементы
     * @param sedans - коллекция, в которую будут вноситься изменения
     * @return - измененная коллекция
     */
    public static LinkedList<Sedan> linkedListClean(LinkedList<Sedan> sedans){
        int size = sedans.size();
        long start = System.currentTimeMillis();
        for (int i = 0; i < sedans.size()/10; i++) {

            long internalStart = System.currentTimeMillis();

            int index = (int)(Math.random()*sedans.size());
            // System.out.println(index);

            sedans.remove(index);

            long internalEnd = System.currentTimeMillis();

            String msg = "remove,ID=" + index + "," + (internalEnd - internalStart);
            ExceptionHandler.timingLogWriter(msg);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        String count = "Count=" + size/10;
        ExceptionHandler.timingLogWriter(count);
        String medianTime = "removeLinkedListMedianTime=" + (double)timeTaken/size/10;
        ExceptionHandler.timingLogWriter(medianTime);
        String totalTime = "removeLinkedListTotalTime=" + timeTaken;
        ExceptionHandler.timingLogWriter(totalTime);
        return sedans;
    }

}

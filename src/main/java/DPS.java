
public class DPS {
    static final int  speedLimit = 90;
    static final int maxWeight = 1000;
    static final double maxHeight = 2;
    public static void pass(Car car){
        if (car.currentSpeed > speedLimit) {System.out.println("Превышена максимально допустимая скорость."); return;}
        if(car instanceof Truck){
            if (((Truck) car).getWeight() > maxWeight) {System.out.println("Превышен максимальный допустимый вес.");return;}
            if (((Truck) car).getCarcaseHeight() > maxHeight) {System.out.println("Превышена масксимально допустимая высота кузова."); return;}
        }
        System.out.println("Проезд разрешен.");

    }
}
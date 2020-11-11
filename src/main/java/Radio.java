
public class Radio {
    public Radio(String currentStation, boolean isOn){
        this.currentStation = currentStation;
        this.isOn = isOn;
    }
    public Radio(){
        currentStation = "DriveFM";
        isOn = false;
    }
    public String currentStation;
    public boolean isOn;
    public void playTunes(){
        if(isOn) System.out.println("The station " + currentStation +" plays great music.");
        else System.out.println("You need to turn on the radio.");
    }

    public String fileWriter(){
        StringBuilder SB = new StringBuilder("");
        SB.append(this.currentStation).append(",");
        SB.append(this.isOn);
        return SB.toString();
    }
}

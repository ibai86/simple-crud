import java.util.ArrayList;

public final class ExampleImmutableClass {

    private final String month;
    private final ArrayList<Double> averageTemp;

    public ExampleImmutableClass(String month, ArrayList<Double> averageTemp) {
        this.month = month;
        ArrayList<Double> safeCopy = new ArrayList<Double>();
        for (Double temp : averageTemp)
            safeCopy.add(temp);
        this.averageTemp = safeCopy;
    }

    public String getMonth() {
        return month;
    }

    public ArrayList<Double> getAverageTemp() {
        ArrayList<Double> safeCopy = new ArrayList<Double>();
        for (Double temp : averageTemp)
            safeCopy.add(temp);
        return safeCopy;
    }
}

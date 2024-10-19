import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public final class UtilityClass {

    private UtilityClass() {
    }

    public static void printTestText() {
        System.out.println("Hey, this is a Java utility class!");
    }

    public static void main(String[] args) throws Exception {
        UtilityClass.printTestText();

        TestReflection testRefl = new TestReflection();
        System.out.println("Original value: " + testRefl.getName());
        Class testClass = testRefl.getClass();
        Field field = testClass.getDeclaredField("name");
        field.setAccessible(true);
        field.set(testRefl, "Ivan");
        System.out.println("Changed value: " + testRefl.getName());

        ArrayList<Double> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(i + 2.3);

        }
        ExampleImmutableClass exampleImmutableClass = new ExampleImmutableClass("May", temp);
        System.out.println(exampleImmutableClass.getAverageTemp().toString());
    }

}

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Port port = new Port(new String[]{"1,3-5", "2-6", "3-4", "7-10"});

        port.stringIndexesToInteger();
        System.out.println(Arrays.toString(port.getIntegerIndexes()));

        port.sortedGroupsOfIntegerFromIndexes();
    }
}

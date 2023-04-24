import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Тесты")
class PortTest {

    private ArrayList<Port> ports;

    @BeforeEach
    void init(){
        ports = new ArrayList<>(Arrays.asList(new Port(new String[]{"1,3-5", "2", "3-4"}),
                new Port(new String[]{"1,3-5", "2-3", "3-4", "6-8"})));
    }

    @Test
    @DisplayName("Провекра преобразования строки в массив чисел")
    void testStringIndexesToInteger() {
        int[][] actual = new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8},
        };

        for (int i = 0; i < ports.size(); i++){
            ports.get(i).stringIndexesToInteger();
            assertArrayEquals(Arrays.stream(ports.get(i).getIntegerIndexes()).toArray(), Arrays.stream(actual[i]).toArray());
        }
    }

    @Test
    @DisplayName("Провекра формирования групп")
    void testSortedGroupsOfIntegerFromIndexes() {
        ArrayList<ArrayList<int[]>> actual = new ArrayList<>();
        actual.add(new ArrayList<>(Arrays.asList(new int[]{1, 2, 3}, new int[]{1, 2, 4}, new int[]{3, 2, 3}, new int[]{3, 2, 4}, new int[]{4, 2, 3}, new int[]{4, 2, 4}, new int[]{5, 2, 3}, new int[]{5, 2, 4})));
        actual.add(new ArrayList<>(Arrays.asList(new int[]{1, 2, 3, 6}, new int[]{1, 2, 3, 7}, new int[]{1, 2, 3, 8}, new int[]{1, 2, 4, 6}, new int[]{1, 2, 4, 7}, new int[]{1, 2, 4, 8}, new int[]{1, 3, 3, 6}, new int[]{1, 3, 3, 7}, new int[]{1, 3, 3, 8}, new int[]{1, 3, 4, 6}, new int[]{1, 3, 4, 7}, new int[]{1, 3, 4, 8}, new int[]{3, 2, 3, 6}, new int[]{3, 2, 3, 7}, new int[]{3, 2, 3, 8}, new int[]{3, 2, 4, 6}, new int[]{3, 2, 4, 7}, new int[]{3, 2, 4, 8}, new int[]{3, 3, 3, 6}, new int[]{3, 3, 3, 7}, new int[]{3, 3, 3, 8}, new int[]{3, 3, 4, 6}, new int[]{3, 3, 4, 7}, new int[]{3, 3, 4, 8}, new int[]{4, 2, 3, 6}, new int[]{4, 2, 3, 7}, new int[]{4, 2, 3, 8}, new int[]{4, 2, 4, 6}, new int[]{4, 2, 4, 7}, new int[]{4, 2, 4, 8}, new int[]{4, 3, 3, 6}, new int[]{4, 3, 3, 7}, new int[]{4, 3, 3, 8}, new int[]{4, 3, 4, 6}, new int[]{4, 3, 4, 7}, new int[]{4, 3, 4, 8}, new int[]{5, 2, 3, 6}, new int[]{5, 2, 3, 7}, new int[]{5, 2, 3, 8}, new int[]{5, 2, 4, 6}, new int[]{5, 2, 4, 7}, new int[]{5, 2, 4, 8}, new int[]{5, 3, 3, 6}, new int[]{5, 3, 3, 7}, new int[]{5, 3, 3, 8}, new int[]{5, 3, 4, 6}, new int[]{5, 3, 4, 7}, new int[]{5, 3, 4, 8})));

        for (int i = 0; i < ports.size(); i++){
            ports.get(i).sortedGroupsOfIntegerFromIndexes();
            assertArrayEquals(Arrays.stream(ports.get(i).getGroupsOfInteger().toArray()).toArray(), Arrays.stream(actual.get(i).toArray()).toArray());
        }
    }
}

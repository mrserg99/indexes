import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Port {
    private String[] indexes;
    private int[] integerIndexes;

    private ArrayList<int[]> groupsOfInteger;

    Port(String[] indexes){
        this.indexes = indexes;
    }

    public void stringIndexesToInteger(){
        integerIndexes = stringIndexesToInteger(indexes);
    }

    public void sortedGroupsOfIntegerFromIndexes(){
        int[][] groups = getGroups();

        ArrayList<int[]> result = new ArrayList<>();

        // Состовляем все возможные комбинации
        ArrayList<Integer> makeGroupsToOneArray = getAllGroups(groups, 0, new ArrayList<>());

        // Формирую группы
        result = makeGroups(groups.length, makeGroupsToOneArray);

        this.groupsOfInteger = result;
    }

    private int[][] getGroups() {
        int[][] groups = new int[indexes.length][];
        TreeSet<Integer> sortedIndexes = new TreeSet<>();
        int i = 0;

        for (String interval: indexes){
            splitIndexesAndAddToSet(sortedIndexes, interval);
            groups[i] = getIntsArrayFromSet(sortedIndexes);
            i++;
            sortedIndexes.clear();
        }

        return groups;
    }

    private ArrayList<Integer> getAllGroups(int[][] groups, int depth, ArrayList<Integer> savePath) {
        ArrayList<Integer> result = new ArrayList<>();

        if (depth == groups.length - 1) {
            for (int k = 0; k < groups[depth].length; k++) {
                result.addAll(savePath);
                result.add(groups[depth][k]);
            }
            return result;
        }

        // Рекурсивно формирую группы в одну строку
        for (int k = 0; k < groups[depth].length; k++) {
            if (depth != groups.length - 1) {
                savePath.add(groups[depth][k]);
                result.addAll(getAllGroups(groups, ++depth, savePath));
                savePath.remove(savePath.size() - 1);
                depth--;
            }
        }
        return result;
    }

    private ArrayList<int[]> makeGroups(int groupsLength, ArrayList<Integer> groupsForThisFirst) {
        ArrayList<int[]> result = new ArrayList<>();
        int[] group = new int[groupsLength];
        int p = 0;

        for (int l = 0; l < groupsForThisFirst.size(); l++) {
            if (l % (groupsLength) == 0 && l != 0) {
                result.add(group);
                group = new int[groupsLength];
                p = 0;
            }
            group[p] = groupsForThisFirst.get(l);
            p++;
        }
        result.add(group);
        return result;
    }

    public void printGroups() {
        StringBuilder printRes = new StringBuilder("{");

        for (int[] group: groupsOfInteger){
            printRes.append(Arrays.toString(group));
            printRes.append(", ");
        }
        printRes.delete(printRes.length() - 2, printRes.length());
        printRes.append("}");
        System.out.println(printRes);
    }

    private int[] stringIndexesToInteger(String[] indexes){
        TreeSet<Integer> sortedIndexes = new TreeSet<>();

        for (String interval: indexes){
            splitIndexesAndAddToSet(sortedIndexes, interval);
        }

        return getIntsArrayFromSet(sortedIndexes);
    }

    private static int[] getIntsArrayFromSet(TreeSet<Integer> sortedIndexes) {
        int[] result = new int[sortedIndexes.size()];
        int i = 0;
        for (Integer integer: sortedIndexes){
            result[i] = integer;
            i++;
        }
        return result;
    }

    private void splitIndexesAndAddToSet(TreeSet<Integer> sortedIndexes, String interval) {
        if(interval.contains(",")){
            String[] intervals = interval.split(",");
            for (String partOfIntervals: intervals){
                checkHyphenAndAddToSet(sortedIndexes, partOfIntervals);
            }
        }else {
            checkHyphenAndAddToSet(sortedIndexes, interval);
        }
    }

    private void checkHyphenAndAddToSet(TreeSet<Integer> sortedIndexes, String interval) {
        if(interval.contains("-")){
            String[] parts = interval.split("-");
            for (int i = Integer.parseInt(parts[0]); i <= Integer.parseInt(parts[1]); i++){
                sortedIndexes.add(i);
            }
        } else {
            sortedIndexes.add(Integer.valueOf(interval));
        }
    }

    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }

    public void setIntegerIndexes(int[] integerIndexes) {
        this.integerIndexes = integerIndexes;
    }

    public String[] getIndexes() {
        return indexes;
    }

    public int[] getIntegerIndexes() {
        return integerIndexes;
    }

    public ArrayList<int[]> getGroupsOfInteger() {
        return groupsOfInteger;
    }

    public void setGroupsOfInteger(ArrayList<int[]> groupsOfInteger) {
        this.groupsOfInteger = groupsOfInteger;
    }
}

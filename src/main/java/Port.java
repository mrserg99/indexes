import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Port {
    private String[] indexes;
    private int[] integerIndexes;

    Port(String[] indexes){
        this.indexes = indexes;
    }

    public void stringIndexesToInteger(){
        integerIndexes = stringIndexesToInteger(indexes);
    }

    public void sortedGroupsOfIntegerFromIndexes(){
        int[][] groups = getGroups();

        ArrayList<int[]> result = new ArrayList<>();

        int maxSize = 1;

        for (int k = 1; k < groups.length; k++){
            maxSize *= groups[k].length;
        }

        for (int k = 0; k < groups[0].length; k++){
            ArrayList<Integer> groupsForThisFirst = getAllGroups(groups, 1, new ArrayList<>(), maxSize);

            result.addAll(makeGroupsForThisFirst(groups.length, groups[0][k], groupsForThisFirst));
        }

        printResult(result);
    }

    private ArrayList<int[]> makeGroupsForThisFirst(int groupsLength, int first, ArrayList<Integer> groupsForThisFirst) {
        ArrayList<int[]> result = new ArrayList<>();
        int[] group = new int[groupsLength];
        int p = 0;

        for (int l = 0; l < groupsForThisFirst.size(); l++) {
            if (l % (groupsLength - 1) == 0 && l != 0) {
                result.add(group);
            }
            if (l % (groupsLength - 1) == 0) {
                p = 0;
                group = new int[groupsLength];
                group[p] = first;
            }
            p++;
            group[p] = groupsForThisFirst.get(l);
        }
        result.add(group);
        return result;
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

    private static void printResult(ArrayList<int[]> result) {
        StringBuilder printRes = new StringBuilder("{");

        for (int[] group: result){
            printRes.append(Arrays.toString(group));
            printRes.append(", ");
        }
        printRes.delete(printRes.length() - 2, printRes.length());
        printRes.append("}");
        System.out.println(printRes);
    }

    private ArrayList<Integer> getAllGroups(int[][] groups, int depth, ArrayList<Integer> savePath, int maxSize) {
        ArrayList<Integer> result = new ArrayList<>();

        if (depth == groups.length - 1) {

            for (int k = 0; k < groups[depth].length; k++) {
                result.addAll(savePath);
                result.add(groups[depth][k]);
            }
            return result;
        }

        for(int i = depth; i < groups.length - 1 && (result.size()/(groups.length - 1)) != maxSize; i++){
            for (int k = 0; k < groups[i].length; k++) {
                if (depth != groups.length - 1) {
                    savePath.add(groups[i][k]);
                    result.addAll(getAllGroups(groups, ++depth, savePath, maxSize));
                    savePath.remove(savePath.size() - 1);
                    depth--;
                }
            }
        }
        return result;
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
}

package sort;

public class Test {
    public static void main(String[] args) {
//        Sortible selectionSort = new Quick3WaySort();
        Integer[] array = new Integer[10];
        array[0] = 7;
        array[1] = 11;
        array[2] = 24;
        array[3] = 66;
        array[4] = 55;
        array[5] = 55;
        array[6] = 55;
        array[7] = 55;
        array[8] = 2;
        array[9] = 1;
//        selectionSort.sort(array);
//        for(int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
        QuickSelection quickSelection = new QuickSelection();
        System.out.println(quickSelection.findSmallest(array, 10));
    }
}

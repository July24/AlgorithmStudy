package sort;

public class Test {
    public static void main(String[] args) {
        Sortible selectionSort = new MergeUpdateImp();
        Integer[] array = new Integer[10];
        array[0] = 7;
        array[1] = 11;
        array[2] = 24;
        array[3] = 66;
        array[4] = 55;
        array[5] = 60;
        array[6] = 53;
        array[7] = 15;
        array[8] = 2;
        array[9] = 1;
        selectionSort.sort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

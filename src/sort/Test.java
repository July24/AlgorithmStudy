package sort;

public class Test {
    public static void main(String[] args) {
        Sortible selectionSort = new BottomUpMergeImp();
        Integer[] array = new Integer[10];
        array[0] = 66;
        array[1] = 43;
        array[2] = 24;
        array[3] = 7;
        array[4] = 55;
        array[5] = 60;
        array[6] = 55;
        array[7] = 15;
        array[8] = 2;
        array[9] = 1;
        selectionSort.sort(array);
//        Comparable[] abc = selectionSort.getResult();
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
}

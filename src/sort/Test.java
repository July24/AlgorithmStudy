package sort;

public class Test {
    public static void main(String[] args) {
        Sortible selectionSort = new QuickSortUpdateImp();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = (int)(Math.random()*2000);
        }
        selectionSort.sort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

package sort;

public class Test {
    public static void main(String[] args) {
        AbstractSortAlgorithm selectionSort = new HeapSort();
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (int)(Math.random()*2000);
        }
        selectionSort.sort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

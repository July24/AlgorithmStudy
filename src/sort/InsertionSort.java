package sort;

/**
 * Insertion sort.
 * @author yehaoyu
 */
public class InsertionSort extends BaseSortAlgorithm {
    @Override
    public void sort(Comparable[] comp) {
        int length = comp.length;
        for(int i = 1; i < length; i++) {
            for(int j = i; j > 0; j--) {
                if(less(comp[j], comp[j - 1])) {
                    exchange(comp, j, j - 1);
                }
            }
        }
    }
}

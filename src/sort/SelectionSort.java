package sort;

/**
 * Selection sort.
 */
public class SelectionSort extends AbstractSortAlgorithm {
    @Override
    public void sort(Comparable[] comp) {
        int length = comp.length;
        for(int i = 0; i < length; i++) {
            int min = i;
            for(int j = i + 1; j < length; j++) {
                if(less(comp[j], comp[min])) {
                    min = j;
                }
            }
            exchange(comp, i, min);
        }
    }
}

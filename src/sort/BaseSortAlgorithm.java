package sort;

/**
 * Base sort class.
 * @author yehaoyu
 */
public abstract class BaseSortAlgorithm implements Sortible{
    protected boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    protected boolean greater(Comparable first, Comparable second) {
        return first.compareTo(second) > 0;
    }

    protected void exchange(Comparable[] comp, int i, int j) {
        Comparable temp = comp[i];
        comp[i] = comp[j];
        comp[j] = temp;
    }
}

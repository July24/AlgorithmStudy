package sort;

/**
 * 找到一串无序数列中第几小的数
 * 方法：通过快速排序的分区算法，不需要完全排序就可以找到第几小的数
 * 平均时间复杂度：N
 * @author 叶皓宇
 */
public class QuickSelection {
    private QuickSort quickSort;

    public QuickSelection() {
        quickSort = new QuickSort();
    }

    public Comparable findSmallest(Comparable[] comp, int n) {
        quickSort.shuffle(comp);
        int length = comp.length;
        if(n < 0 || n >= length) {
            return null;
        }
        int low  = 0;
        int high = length - 1;
        while(low <= high) {
            int j = quickSort.partition(comp, low, high);
            if(n < j) {
                high = j - 1;
            } else if(n > j) {
                low = j + 1;
            } else {
                return comp[j];
            }
        }
        return null;
    }
}

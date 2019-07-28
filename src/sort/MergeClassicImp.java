package sort;

/**
 * 归并排序经典实现。
 *  将数组无限二分成只有一个数的数组后，在两两合并
 * @author 叶皓宇
 */
public class MergeClassicImp extends BaseMergeSort {

    @Override
    public void sort(Comparable[] comp) {
        aux = new Comparable[comp.length];
        recursionSort(comp,0, comp.length - 1);
    }

    private void recursionSort(Comparable[] comp, int i, int j) {
        if(j <= i) {
            return;
        }
        int mid = i + (j - i)/2;
        recursionSort(comp, 0, mid);
        recursionSort(comp, mid + 1, j);
        merge(comp, i, mid, j);
    }
}

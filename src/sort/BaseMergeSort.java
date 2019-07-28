package sort;

/**
 * 归并排序
 * @author 叶皓宇
 */
public abstract class BaseMergeSort extends BaseSortAlgorithm {
    Comparable[] aux;

    void merge(Comparable[] comp, int low , int mid, int high) {
        if (high + 1 >= 0) {
            System.arraycopy(comp, 0, aux, 0, high + 1);
        }
        int i = low;
        int j = mid + 1;
        for(int k = low; k <= high; k++) {
            if(i > mid) {
                comp[k] = aux[j++];
            } else if (j > high) {
                comp[k] = aux[i++];
            } else if(less(aux[i], aux[j])) {
                comp[k] = aux[i++];
            } else {
                comp[k] = aux[j++];
            }
        }
    }
}
